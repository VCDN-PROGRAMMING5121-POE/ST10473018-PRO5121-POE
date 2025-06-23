/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ArrayTest {
    private Message msg1, msg2, msg3, msg4, msg5;
    private MessagingSystem messagingSystem;

    @BeforeEach
    public void setup() {
        messagingSystem = new MessagingSystem();

        // Initialize test messages
        msg1 = new Message("1", "+27834557896", "Did you get the cake?", "Sent");
        msg2 = new Message("2", "+27838884567", 
            "Where are you? You are late! I have asked you to be on time.", "Stored");
        msg3 = new Message("3", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        msg4 = new Message("4", "0838884567", "It is dinner time!", "Sent");
        msg5 = new Message("5", "+27838884567", "Ok, I am leaving without you.", "Stored");

        // Set senders for messages
        msg1.setSender("TestSender");
        msg2.setSender("TestSender");
        msg3.setSender("TestSender");
        msg4.setSender("TestSender");
        msg5.setSender("TestSender");

        // Add messages to the system
        messagingSystem.addMessage(msg1);
        messagingSystem.addMessage(msg2);
        messagingSystem.addMessage(msg3);
        messagingSystem.addMessage(msg4);
        messagingSystem.addMessage(msg5);
    }

    @Test
    public void testMessageCreation() {
        assertNotNull(msg1);
        assertEquals("1", msg1.messsageId());
        assertEquals("+27834557896", msg1.getRecipient());
        assertEquals("Did you get the cake?", msg1.getContent());
        assertEquals("Sent", msg1.getFlag());
        assertEquals("TestSender", msg1.getSender());
        assertNotNull(msg1.getMessageHash());
    }

    @Test
    public void testSentMessagesArrayCorrect() {
        List<Message> sentMessages = messagingSystem.getMessagesByFlag("Sent");
        
        assertEquals(2, sentMessages.size(), "Should have 2 sent messages");
        assertTrue(sentMessages.contains(msg1), "Should contain msg1");
        assertTrue(sentMessages.contains(msg4), "Should contain msg4");
        assertFalse(sentMessages.contains(msg2), "Should not contain stored messages");
    }

    @Test
    public void testStoredMessagesArrayCorrect() {
        List<Message> storedMessages = messagingSystem.getMessagesByFlag("Stored");
        
        assertEquals(2, storedMessages.size());
        assertTrue(storedMessages.contains(msg2));
        assertTrue(storedMessages.contains(msg5));
    }

    @Test
    public void testDisregardedMessagesArrayCorrect() {
        List<Message> disregardedMessages = messagingSystem.getMessagesByFlag("Disregard");
        
        assertEquals(1, disregardedMessages.size());
        assertTrue(disregardedMessages.contains(msg3));
    }

    @Test
    public void testLongestMessage() {
        Message longest = messagingSystem.findLongestMessage();
        
        assertNotNull(longest);
        assertEquals(msg2.getContent(), longest.getContent());
        assertEquals(58, longest.getContent().length());
    }

    @Test
    public void testSearchMessageByIdFound() {
        Message found = messagingSystem.getMessageID("4");
        
        assertNotNull(found);
        assertEquals(msg4.getContent(), found.getContent());
        assertEquals(msg4.getRecipient(), found.getRecipient());
        assertEquals("Sent", found.getFlag());
    }

    @Test
    public void testSearchMessageByIdNotFound() {
        Message found = messagingSystem.findMessageById("99");
        assertNull(found);
    }

    @Test
    public void testSearchByRecipientFound() {
        List<Message> foundMessages = messagingSystem.getRecipientt("+27838884567");
        
        assertEquals(2, foundMessages.size());
        assertTrue(foundMessages.contains(msg2));
        assertTrue(foundMessages.contains(msg5));
    }

    @Test
    public void testSearchByRecipientNotFound() {
        List<Message> foundMessages = messagingSystem.getRecipient("+00000000000");
        assertTrue(foundMessages.isEmpty());
    }

    @Test
    public void testDeleteMessageByHashSuccess() {
        String hashToDelete = msg2.getHash();
        int initialSize = messagingSystem.getAllMessages().size();
        
        boolean deleted = messagingSystem.deleteMessageByHash(hashToDelete);
        
        assertTrue(deleted);
        assertEquals(initialSize - 1, messagingSystem.getAllMessages().size());
        assertNull(messagingSystem.findMessageById("2"));
    }

    @Test
    public void testDeleteMessageByHashFailure() {
        boolean deleted = messagingSystem.deleteMessageByHash("invalid_hash");
        assertFalse(deleted);
        assertEquals(5, messagingSystem.getAllMessages().size());
    }

    @Test
    public void testDisplayReportContent() {
        String report = messagingSystem.generateSentMessagesReport();
        
        assertTrue(report.contains("=== Sent Messages Report ==="));
        assertTrue(report.contains(msg1.getMessageID()));
        assertTrue(report.contains(msg1.getMessageText()));
        assertTrue(report.contains(msg4.getMessageID()));
        assertTrue(report.contains(msg4.getMessageText()));
        
        // Verify non-sent messages are not in report
        assertFalse(report.contains(msg2.getMessageText()));
        assertFalse(report.contains(msg3.getMessageText()));
        assertFalse(report.contains(msg5.getMessageText()));
    }

    @Test
    public void testGetAllMessages() {
        List<Message> allMessages = messagingSystem.getAllMessages();
        assertEquals(5, allMessages.size());
        assertTrue(allMessages.contains(msg1));
        assertTrue(allMessages.contains(msg2));
        assertTrue(allMessages.contains(msg3));
        assertTrue(allMessages.contains(msg4));
        assertTrue(allMessages.contains(msg5));
    }

    @Test
    public void testMessageHashConsistency() {
        // Hash should be consistent for same content
        Message duplicateMsg = new Message("6", "+27834557896", "Did you get the cake?", "Sent");
        assertEquals(msg1.getMessageHash(), duplicateMsg.getMessageHash());
        
        // Hash should differ for different content
        Message differentMsg = new Message("7", "+27834557896", "Did you get the cake", "Sent");
        assertNotEquals(msg1.getMessageHash(), differentMsg.getMessageHash());
    }
}