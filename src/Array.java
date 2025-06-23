/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Array {
    // Arrays to store different types of messages
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> disregardedMessages = new ArrayList<>();
    private static ArrayList<String> storedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();
    
    // HashMaps to store message details
    private static HashMap<String, String> recipientMap = new HashMap<>();
    private static HashMap<String, String> messageContentMap = new HashMap<>();
    private static HashMap<String, String> flagMap = new HashMap<>();
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize with test data
        initializeTestData();
        
        while (true) {
            System.out.println("\nMessage System Menu:");
            System.out.println("1. Display sender and recipient of all sent messages");
            System.out.println("2. Display the longest sent message");
            System.out.println("3. Search for a message by ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete a message by hash");
            System.out.println("6. Display full report of sent messages");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    displaySendersAndRecipients();
                    break;
                case 2:
                    displayLongestMessage();
                    break;
                case 3:
                    searchByMessageID();
                    break;
                case 4:
                    searchByRecipient();
                    break;
                case 5:
                    deleteByHash();
                    break;
                case 6:
                    displayFullReport();
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void initializeTestData() {
        // Test Data Message 1
        addMessage("1", "+27834557896", "Did you get the cake?", "Sent");
        
        // Test Data Message 2
        addMessage("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        
        // Test Data Message 3
        addMessage("3", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        
        // Test Data Message 4
        addMessage("4", "0838884567", "It is dinner time !", "Sent");
        
        // Test Data Message 5
        addMessage("5", "+27838884567", "Ok, I am leaving without you.", "Stored");
    }
    
    private static void addMessage(String id, String recipient, String message, String flag) {
        messageIDs.add(id);
        recipientMap.put(id, recipient);
        messageContentMap.put(id, message);
        flagMap.put(id, flag);
        
        String hash = Integer.toString(message.hashCode());
        messageHashes.add(hash);
        
        switch (flag) {
            case "Sent":
                sentMessages.add(message);
                break;
            case "Disregard":
                disregardedMessages.add(message);
                break;
            case "Stored":
                storedMessages.add(message);
                break;
        }
    }
    
    private static void displaySendersAndRecipients() {
        System.out.println("\nSenders and Recipients of Sent Messages:");
        for (String id : messageIDs) {
            if (flagMap.get(id).equals("Sent")) {
                System.out.println("Message ID: " + id);
                System.out.println("Recipient: " + recipientMap.get(id));
                System.out.println("------------------------");
            }
        }
    }
    
    private static void displayLongestMessage() {
        String longest = "";
        for (String message : sentMessages) {
            if (message.length() > longest.length()) {
                longest = message;
            }
        }
        System.out.println("\nLongest Sent Message: " + longest);
    }
    
    private static void searchByMessageID() {
        System.out.print("\nEnter Message ID to search: ");
        String id = scanner.nextLine();
        
        if (messageIDs.contains(id)) {
            System.out.println("Recipient: " + recipientMap.get(id));
            System.out.println("Message: " + messageContentMap.get(id));
        } else {
            System.out.println("Message ID not found.");
        }
    }
    
    private static void searchByRecipient() {
        System.out.print("\nEnter Recipient to search: ");
        String recipient = scanner.nextLine();
        
        System.out.println("Messages for " + recipient + ":");
        for (String id : messageIDs) {
            if (recipientMap.get(id).equals(recipient)) {
                System.out.println("- " + messageContentMap.get(id));
            }
        }
    }
    
    private static void deleteByHash() {
        System.out.print("\nEnter Message Hash to delete: ");
        String hash = scanner.nextLine();
        
        if (messageHashes.contains(hash)) {
            int index = messageHashes.indexOf(hash);
            String id = messageIDs.get(index);
            
            // Remove from all collections
            String message = messageContentMap.get(id);
            sentMessages.remove(message);
            disregardedMessages.remove(message);
            storedMessages.remove(message);
            
            messageHashes.remove(index);
            messageIDs.remove(index);
            recipientMap.remove(id);
            messageContentMap.remove(id);
            flagMap.remove(id);
            
            System.out.println("Message successfully deleted.");
        } else {
            System.out.println("Message hash not found.");
        }
    }
    
    private static void displayFullReport() {
        System.out.println("\nFull Report of Sent Messages:");
        System.out.println("----------------------------------------");
        
        for (String id : messageIDs) {
            if (flagMap.get(id).equals("Sent")) {
                System.out.println("Message ID: " + id);
                System.out.println("Message Hash: " + messageHashes.get(messageIDs.indexOf(id)));
                System.out.println("Recipient: " + recipientMap.get(id));
                System.out.println("Message: " + messageContentMap.get(id));
                System.out.println("----------------------------------------");
            }
        }
    }
}