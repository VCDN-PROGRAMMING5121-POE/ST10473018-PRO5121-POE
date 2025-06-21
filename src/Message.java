public class Message {
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    private String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }

    @Override
    public String toString() {
        return "Message ID: " + messageID +
               "\\nMessage Hash: " + messageHash +
               "\\nRecipient: " + recipient +
               "\\nMessage: " + messageText;
    }
}