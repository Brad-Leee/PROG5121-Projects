package com.mycompany.quickchat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handles message creation, validation and processing
 * for the QuickChat application.
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    private static int totalMessagesSent = 0;

    /**
     * Creates a Message object using the message details.
     *
     * @param messageNumber number assigned to the message
     * @param recipient recipient cellphone number
     * @param message message text
     */
    public Message(int messageNumber,
                   String recipient, String message) {

        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    /**
     * Generates a random ten-digit Message ID.
     *
     * @return randomly generated ten-digit Message ID
     */
    private String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        String randomNumber = String.valueOf(number);

        return randomNumber.substring(0, 10);
    }

    /**
     * Returns the generated Message ID.
     *
     * @return Message ID
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Checks that the message ID is no more than ten characters long.
     *
     * @return true if the message ID is valid
     */
    public boolean checkMessageID() {

        return messageID != null
                && messageID.length() <= 10;
    }

    /**
     * Checks that the recipient cellphone number is correctly formatted
     * and contains an international code.
     *
     * @return validation message
     */
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.startsWith("+")
                && recipient.length() <= 12) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted "
                    + "or does not contain an international code. "
                    + "Please correct the number and try again.";
        }
    }

    /**
     * Checks that the message does not exceed 250 characters.
     *
     * @return validation message
     */
    public String checkMessageLength() {

        if (message != null && message.length() < 250) {

            return "Message ready to send.";

        } else {

            int excessCharacters = 0;

            if (message != null) {
                excessCharacters = message.length() - 250;
            }

            return "Message exceeds 250 characters by "
                    + excessCharacters
                    + "; please reduce the size.";
        }
    }

    /**
     * Creates the Message Hash using the first two characters
     * of the Message ID, the message number, and the first
     * and last words of the message.
     *
     * @return the Message Hash in uppercase
     */
    public String createMessageHash() {

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0]
                .replaceAll("[^a-zA-Z0-9]", "");

        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "");

        messageHash = (
                messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord
                + lastWord
        ).toUpperCase();

        return messageHash;
    }

    /**
     * Allows the user to choose whether the message is sent,
     * disregarded or stored.
     *
     * @param choice user's selected message option
     * @return message processing result
     */
    public String SentMessage(String choice) {

        switch (choice.toLowerCase()) {

            case "send":

                totalMessagesSent++;

                return "Message successfully sent.";

            case "disregard":

                return "Press 0 to delete the message.";

            case "store":

                storeMessage();

                return "Message successfully stored.";

            default:

                return "Invalid message option.";
        }
    }

    /**
     * Stores the current message in a JSON file.
     * Existing stored messages are retained.
     *
     * JSON conversion uses the Gson library.
     * Source: Google Gson User Guide
     * https://github.com/google/gson/blob/main/UserGuide.md
     *
     * @return true if the message was successfully stored
     */
    public boolean storeMessage() {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        ArrayList<Message> messages = new ArrayList<>();

        /*
         * Read existing messages from the JSON file
         * if the file already exists and contains data.
         */
        try (FileReader reader =
                new FileReader("messages.json")) {

            Message[] existingMessages =
                    gson.fromJson(reader, Message[].class);

            if (existingMessages != null) {

                for (Message existingMessage : existingMessages) {

                    messages.add(existingMessage);
                }
            }

        } catch (IOException e) {

            /*
             * The file may not exist yet.
             * A new list will be created.
             */
        }

        /*
         * Add the current message to the list.
         */
        messages.add(this);

        /*
         * Write all stored messages back to the JSON file.
         */
        try (FileWriter writer =
                new FileWriter("messages.json")) {

            gson.toJson(messages, writer);

            return true;

        } catch (IOException e) {

            System.out.println(
                    "Error storing message: "
                    + e.getMessage());

            return false;
        }
    }

    /**
     * Returns the message details.
     *
     * @return message details
     */
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    /**
     * Returns the total number of messages successfully sent.
     *
     * @return total number of messages sent
     */
    public int returnTotalMessagess() {

        return totalMessagesSent;
    }
}