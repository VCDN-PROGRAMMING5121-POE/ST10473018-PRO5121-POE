public class MessagingSystemTest {

    public static void main(String[] args) {
        MessagingSystem messagingSystem = new MessagingSystem();

        // Test valid recipient number
        boolean validNumber = messagingSystem.checkRecipientCell("+27718693002");
        System.out.println("Test valid recipient number: " + (validNumber ? "PASSED" : "FAILED"));

        // Test invalid recipient number (missing +)
        boolean invalidNumber1 = messagingSystem.checkRecipientCell("27718693002");
        System.out.println("Test invalid recipient number (no +): " + (!invalidNumber1 ? "PASSED" : "FAILED"));

        // Test invalid recipient number (too long)
        boolean invalidNumber2 = messagingSystem.checkRecipientCell("+12345678901234");
        System.out.println("Test invalid recipient number (too long): " + (!invalidNumber2 ? "PASSED" : "FAILED"));

        // Test message validation (valid length)
        String validMessage = "Hello, this is a test message.";
        String validMsgResult = messagingSystem.validateMessage(validMessage);
        System.out.println("Test valid message length: " + (validMsgResult.equals("Message ready to send.") ? "PASSED" : "FAILED"));

        // Test message validation (too long)
        String longMessage = "A".repeat(260);
        String longMsgResult = messagingSystem.validateMessage(longMessage);
        String expected = "Message exceeds 250 characters by 10, please reduce size.";
        System.out.println("Test message too long: " + (longMsgResult.equals(expected) ? "PASSED" : "FAILED"));

        // Test creating a message object
        var message = messagingSystem.createMessage("+27718693002", "Test content");
        boolean messageCreated = message != null 
            && message.getRecipient().equals("+27718693002") 
            && message.getMessageText().equals("Test content");
        System.out.println("Test create message: " + (messageCreated ? "PASSED" : "FAILED"));

        // Test total messages sent initially (should be zero)
        int totalMessages = messagingSystem.returnTotalMessages();
        System.out.println("Test initial total messages: " + (totalMessages == 0 ? "PASSED" : "FAILED"));
    }
}