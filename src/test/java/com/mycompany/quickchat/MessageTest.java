package com.mycompany.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the QuickChat Message class.
 */
public class MessageTest {

    /**
     * Tests that a valid message is accepted.
     */
    @Test
    public void testMessageLengthSuccess() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    /**
     * Tests that a message longer than 250 characters
     * is rejected.
     */
    @Test
    public void testMessageLengthFailure() {

        String longMessage = "This is a very long message "
                + "that is used to test whether the system "
                + "correctly identifies a message that exceeds "
                + "the maximum allowed length. "
                + "This message continues so that it becomes "
                + "longer than two hundred and fifty characters "
                + "and should therefore fail the validation "
                + "requirement for QuickChat.";

        Message message = new Message(
                0,
                "+27718693002",
                longMessage
        );

        assertTrue(
                message.checkMessageLength()
                        .startsWith("Message exceeds 250 characters by ")
        );
    }

    /**
     * Tests that a correctly formatted international
     * cellphone number is accepted.
     */
    @Test
    public void testRecipientCellSuccess() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    /**
     * Tests that an incorrectly formatted cellphone
     * number is rejected.
     */
    @Test
    public void testRecipientCellFailure() {

        Message message = new Message(
                1,
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    /**
     * Tests that the generated Message ID contains
     * exactly ten digits.
     */
    @Test
    public void testMessageID() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        String messageID = message.getMessageID();

        assertNotNull(messageID);
        assertEquals(10, messageID.length());
        assertTrue(messageID.matches("\\d{10}"));
    }

    /**
     * Tests that the Message Hash is generated correctly.
     */
    @Test
    public void testMessageHash() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        String hash = message.createMessageHash();

        assertTrue(hash.matches("\\d{2}:0:HITONIGHT"));
    }

    /**
     * Tests that a message can be successfully sent.
     */
    @Test
    public void testSendMessage() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("send")
        );
    }

    /**
     * Tests that a message can be disregarded.
     */
    @Test
    public void testDisregardMessage() {

        Message message = new Message(
                1,
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("disregard")
        );
    }

    /**
     * Tests that a message can be stored.
     */
    @Test
    public void testStoreMessage() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("store")
        );
    }

    /**
     * Tests that a message can be stored in JSON format.
     */
    @Test
    public void testStoreMessageInJson() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertTrue(message.storeMessage());
    }
}