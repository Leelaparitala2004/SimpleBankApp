package service;

import model.User;
import model.BankAccount;

import java.io.*;
import java.util.*;

public class BankService {
    private Map<String, User> users = new HashMap<>();
    private final String FILE_PATH = "data/users.txt";

    // Register a new user
    public void register(String username, String password) {
        String accountNumber = "AC" + new Random().nextInt(99999);
        User user = new User(username, password, accountNumber);
        users.put(username, user);
        System.out.println("Account created successfully. Account No: " + accountNumber);
    }

    // Login for existing user
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return user;
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    // Save all users to file
    public void saveToFile() {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();

            // Create 'data' folder if it doesn't exist
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (User user : users.values()) {
                    writer.write(user.toDataString());
                    writer.newLine();
                }
            }

            System.out.println("Data saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load users from file at startup
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String username = parts[0];
                String password = parts[1];
                String accNo = parts[2];
                double balance = Double.parseDouble(parts[3]);

                User user = new User(username, password, accNo);
                user.getAccount().setBalance(balance);
                users.put(username, user);
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Get a user object by username
    public User getUser(String username) {
        return users.get(username);
    }
}
