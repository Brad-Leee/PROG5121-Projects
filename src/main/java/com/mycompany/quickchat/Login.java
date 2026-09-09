package com.mycompany.quickchat;

import java.util.Objects;
/**
 * Handles user registration and login validation
 * for the QuickChat application.
 */
public class Login {

    private String userName;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public Login(String userName, String password, String cellPhoneNumber,
                 String firstName, String lastName) {

        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return userName != null
                && userName.contains("_")
                && userName.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password != null
                && password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[^A-Za-z0-9].*");
    }

    /*
 * Regular expression used to validate a South African
 * international cellphone number.
 *
 * Reference:
 * ICASA. 2016. Numbering Plan Regulations.
 */
public boolean checkCellPhoneNumber() {
    return cellPhoneNumber != null
            && cellPhoneNumber.matches("^\\+27\\d{9}$");
}

    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User successfully registered.";
    }

    public boolean loginUser(String enteredUserName, String enteredPassword) {
        return Objects.equals(userName, enteredUserName)
                && Objects.equals(password, enteredPassword);
    }

    public String returnLoginStatus(String enteredUserName, String enteredPassword) {

        if (loginUser(enteredUserName, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}