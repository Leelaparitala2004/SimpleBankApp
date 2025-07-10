import model.User;
import service.BankService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();
        bankService.loadFromFile();

        User currentUser = null;
        boolean running = true;

        while (running) {
            System.out.println("\n===== SUNNY's BANK =====");
            if (currentUser == null) {
                System.out.println("1. Register\n2. Login\n3. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // flush

                switch (choice) {
                    case 1:
                        System.out.print(" username: ");
                        String username = sc.nextLine();
                        System.out.print("password: ");
                        String password = sc.nextLine();
                        bankService.register(username, password);
                        break;
                    case 2:
                        System.out.print("username: ");
                        username = sc.nextLine();
                        System.out.print(" password: ");
                        password = sc.nextLine();
                        currentUser = bankService.login(username, password);
                        break;
                    case 3:
                        running = false;
                        bankService.saveToFile();
                        System.out.println(" boiii!");
                        break;
                }
            } else {
                System.out.println("Welcome, " + currentUser.getUsername());
                System.out.println("1. Balance\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Logout");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                try {
                    switch (choice) {
                        case 1:
                            System.out.println(" Balance: " + currentUser.getAccount().getBalance());
                            break;
                        case 2:
                            System.out.print(" amount: ");
                            double deposit = sc.nextDouble();
                            currentUser.getAccount().deposit(deposit);
                            System.out.println(" Deposited successfully!");
                            break;
                        case 3:
                            System.out.print("amount: ");
                            double withdraw = sc.nextDouble();
                            currentUser.getAccount().withdraw(withdraw);
                            System.out.println(" Withdrawn successfully!");
                            break;
                        case 4:
                            System.out.print(" recipient username: ");
                            String recipientName = sc.nextLine();
                            System.out.print("Enter amount: ");
                            double amount = sc.nextDouble();
                            User recipient = bankService.getUser(recipientName);
                            if (recipient != null) {
                                currentUser.getAccount().transfer(recipient.getAccount(), amount);
                                System.out.println(" Transferred successfully!");
                            } else {
                                System.out.println(" Recipient not found.");
                            }
                            break;
                        case 5:
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(  e.getMessage());
                }

                bankService.saveToFile();
            }
        }

        sc.close();
    }
}
