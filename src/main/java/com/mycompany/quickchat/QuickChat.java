package com.mycompany.quickchat;

import java.util.Scanner;

/**
 * Main class for the QuickChat registration and login application.
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
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
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
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        /*
         * Cell phone number validation
         */
        while (true) {

            System.out.print("Enter your cell phone number: ");
            cellPhoneNumber = scanner.nextLine();

            Login cellPhoneCheck = new Login(
                    userName, password, cellPhoneNumber, firstName, lastName);

            if (cellPhoneCheck.checkCellPhoneNumber()) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
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

        login.setLoginDetails(enteredUserName, enteredPassword);

        /*
         * Clear if/else decision to check
         * whether login was successful.
         */
        if (login.loginUser()) {
            System.out.println(login.returnLoginStatus());
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }

        scanner.close();
    }
}