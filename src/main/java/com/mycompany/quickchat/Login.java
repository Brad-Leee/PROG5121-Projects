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

    // Details entered during login
    private String enteredUserName;
    private String enteredPassword;

    // Keeps track of whether registration was successful
    private boolean registrationSuccessful;

    public Login(String userName, String password, String cellPhoneNumber,
                 String firstName, String lastName) {

        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        this.registrationSuccessful = false;
    }

    /**
     * Checks that the username contains an underscore
     * and is no more than five characters long.
     *
     * @return true if the username is correctly formatted
     */
    public boolean checkUserName() {
        return userName != null
                && userName.contains("_")
                && userName.length() <= 5;
    }

    /**
     * Checks that the password is at least eight characters long,
     * contains a capital letter, a number and a special character.
     *
     * @return true if the password meets the complexity requirements
     */
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
     * The expression allows the +27 international code
     * followed by up to ten digits.
     *
     * Reference:
     * Oracle. n.d. Pattern Class (Java Platform SE).
     * Available at: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html
     *
     */
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27\\d{1,10}$");
    }

    /**
     * Returns the appropriate registration message.
     *
     * @return registration result message
     */
    public String registerUser() {

        if (!checkUserName()) {
            registrationSuccessful = false;
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            registrationSuccessful = false;
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            registrationSuccessful = false;
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        registrationSuccessful = true;
        return "User successfully registered.";
    }

    /**
     * Stores the username and password entered by the user
     * during the login process.
     *
     * @param enteredUserName username entered during login
     * @param enteredPassword password entered during login
     */
    public void setLoginDetails(String enteredUserName, String enteredPassword) {
        this.enteredUserName = enteredUserName;
        this.enteredPassword = enteredPassword;
    }

    /**
     * Verifies the login details against the registered details.
     *
     * @return true if registration was successful and the login details match
     */
    public boolean loginUser() {
        return registrationSuccessful
                && Objects.equals(userName, enteredUserName)
                && Objects.equals(password, enteredPassword);
    }

    /**
     * Returns the appropriate login status message.
     *
     * @return login status message
     */
    public String returnLoginStatus() {

        if (loginUser()) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}