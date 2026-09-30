package com.mycompany.quickchat;

import java.util.Scanner;

/**
 * Main class for the QuickChat registration, login and messaging application.
 */
public class QuickChat {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("        QUICKCHAT REGISTRATION");
        System.out.println("=================================");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        String userName;
        String password;
        String cellPhoneNumber;

        /*
         * Username validation
         */
        while (true) {

            System.out.print("Enter your username: ");
            userName = scanner.nextLine();

            Login usernameCheck = new Login(
                    userName, "", "", firstName, lastName);

            if (usernameCheck.checkUserName()) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println(
                        "Username is not correctly formatted; "
                        + "please ensure that your username contains "
                        + "an underscore and is no more than five "
                        + "characters in length.");
            }
        }

        /*
         * Password validation
         */
        while (true) {

            System.out.print("Enter your password: ");
            password = scanner.nextLine();

            Login passwordCheck = new Login(
                    userName, password, "", firstName, lastName);

            if (passwordCheck.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println(
                        "Password is not correctly formatted; "
                        + "please ensure that the password contains "
                        + "at least eight characters, a capital letter, "
                        + "a number, and a special character.");
            }
        }

        /*
         * Cell phone number validation
         */
        while (true) {

            System.out.print("Enter your cell phone number: ");
            cellPhoneNumber = scanner.nextLine();

            Login cellPhoneCheck = new Login(
                    userName,
                    password,
                    cellPhoneNumber,
                    firstName,
                    lastName);

            if (cellPhoneCheck.checkCellPhoneNumber()) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println(
                        "Cell number is incorrectly formatted or "
                        + "does not contain an international code; "
                        + "please correct the number and try again.");
            }
        }

        /*
         * Create the registered user after all
         * registration requirements have passed.
         */
        Login login = new Login(
                userName,
                password,
                cellPhoneNumber,
                firstName,
                lastName);

        System.out.println(login.registerUser());

        /*
         * Login
         */
        System.out.println();
        System.out.println("=================================");
        System.out.println("             LOGIN");
        System.out.println("=================================");

        System.out.print("Enter your username: ");
        String enteredUserName = scanner.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        login.setLoginDetails(
                enteredUserName,
                enteredPassword);

        /*
         * Clear if/else decision to check
         * whether login was successful.
         */
        if (login.loginUser()) {

            System.out.println(login.returnLoginStatus());

            /*
             * Part 2 QuickChat messaging application
             */
            System.out.println();
            System.out.println("Welcome to QuickChat.");

            /*
             * The user defines the number of messages
             * immediately after successfully logging in.
             */
            System.out.print(
                    "How many messages would you like to send? ");

            int numberOfMessages = scanner.nextInt();
            scanner.nextLine();

            int menuOption = 0;

            /*
             * Menu continues until the user selects Quit.
             */
            while (menuOption != 3) {

                System.out.println();
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");

                System.out.print("Select an option: ");
                menuOption = scanner.nextInt();
                scanner.nextLine();

                switch (menuOption) {

                    case 1:

                        /*
                         * Loop through the exact number
                         * of messages selected by the user.
                         */
                        for (int i = 0; i < numberOfMessages; i++) {

                            System.out.println();
                            System.out.println(
                                    "Message " + (i + 1));

                            /*
                             * Capture recipient cellphone number.
                             */
                            System.out.print(
                                    "Enter recipient cellphone number: ");

                            String recipient =
                                    scanner.nextLine();

                            /*
                             * Capture message.
                             */
                            System.out.print(
                                    "Enter your message: ");

                            String messageText =
                                    scanner.nextLine();

                            /*
                             * Create a Message object.
                             */
                            Message message = new Message(
                                    i,
                                    recipient,
                                    messageText);

                            /*
                             * Validate the recipient cellphone number.
                             */
                            System.out.println(
                                    message.checkRecipientCell());

                            /*
                             * Validate the message length.
                             */
                            System.out.println(
                                    message.checkMessageLength());

                            /*
                             * Display the generated Message ID.
                             */
                            System.out.println(
                                    "Message ID generated: "
                                    + message.getMessageID());

                            /*
                             * Generate and display the Message Hash.
                             */
                            System.out.println(
                                    "Message Hash: "
                                    + message.createMessageHash());

                            /*
                             * Ask the user what to do
                             * with the message.
                             */
                            System.out.println();
                            System.out.println(
                                    "Choose what to do with this message:");

                            System.out.println("1) Send");
                            System.out.println("2) Disregard");
                            System.out.println("3) Store");

                            System.out.print(
                                    "Enter your choice: ");

                            int messageChoice =
                                    scanner.nextInt();

                            scanner.nextLine();

                            String choice;

                            switch (messageChoice) {

                                case 1:
                                    choice = "send";
                                    break;

                                case 2:
                                    choice = "disregard";
                                    break;

                                case 3:
                                    choice = "store";
                                    break;

                                default:
                                    choice = "invalid";
                            }

                            /*
                             * Process the selected message option.
                             */
                            String messageResult =
                                    message.SentMessage(choice);

                            System.out.println(messageResult);

                            /*
                             * Display the full message details
                             * after a successful send.
                             */
                            if (choice.equals("send")) {

                                System.out.println();
                                System.out.println(
                                        message.printMessages());
                            }
                        }

                        /*
                         * Display the total number of messages
                         * successfully sent.
                         */
                        Message totalMessage =
                                new Message(0, "", "");

                        System.out.println();
                        System.out.println(
                                "Total messages sent: "
                                + totalMessage.returnTotalMessagess());

                        break;

                    case 2:

                        System.out.println("Coming Soon.");
                        break;

                    case 3:

                        System.out.println("Goodbye.");
                        break;

                    default:

                        System.out.println(
                                "Invalid option. Please select "
                                + "1, 2 or 3.");
                }
            }

        } else {

            System.out.println(
                    "Username or password incorrect, "
                    + "please try again.");
        }

        scanner.close();
    }
}