import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MessagingSystem {
    private final List<Message> sentMessages = new ArrayList<>();
    private int messageCounter = 0;

    public boolean checkRecipientCell(String number) {
        return number.startsWith("+") && number.length() <= 13 && number.substring(1).matches("\\d+");
    }

    public String validateMessage(String message) {
        if (message.length() > 250) {
            return "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce size.";
        }
        return "Message ready to send.";
    }

    public Message createMessage(String recipient, String messageText) {
        String id = String.format("%010d", new Random().nextInt(1_000_000_000));
        messageCounter++;
        return new Message(id, messageCounter, recipient, messageText);
    }

    public void storeMessage(Message message) {
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write("{\n");
            writer.write("  \"messageID\": \"" + message.getMessageID() + "\",\n");
            writer.write("  \"messageHash\": \"" + message.getMessageHash() + "\",\n");
            writer.write("  \"recipient\": \"" + message.getRecipient() + "\",\n");
            writer.write("  \"message\": \"" + message.getMessageText() + "\"\n");
            writer.write("}\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving message to file.");
        }
    }

    public int returnTotalMessages() {
        return sentMessages.size();
    }

    public void sendMessages() {
        String msgCountStr = JOptionPane.showInputDialog("How many messages do you want to send?");
        int msgCount = Integer.parseInt(msgCountStr);

        for (int i = 0; i < msgCount; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient number (e.g., +27718693002):");
            if (!checkRecipientCell(recipient)) {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted. Must start with '+' and be numeric.");
                i--; continue;
            }

            String content = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
            String validation = validateMessage(content);
            if (!validation.equals("Message ready to send.")) {
                JOptionPane.showMessageDialog(null, validation);
                i--; continue;
            }

            Message msg = createMessage(recipient, content);
            String[] options = {"Send", "Disregard", "Store"};
            int option = JOptionPane.showOptionDialog(null, "Choose what to do with the message:", "Message Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0 -> {
                    sentMessages.add(msg);
                    JOptionPane.showMessageDialog(null, msg.toString());
                }
                case 1 -> JOptionPane.showMessageDialog(null, "Press 0 to delete message.");
                case 2 -> {
                    storeMessage(msg);
                    JOptionPane.showMessageDialog(null, "Message successfully stored.");
                }
                default -> {
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + returnTotalMessages());
    }
}