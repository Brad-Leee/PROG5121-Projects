package com.mycompany.quickchat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    public void testValidUsername() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        Login login = new Login(
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkUserName());

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                login.registerUser()
        );
    }

    @Test
    public void testValidPassword() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        Login login = new Login(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkPasswordComplexity());

        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                login.registerUser()
        );
    }

    @Test
    public void testValidCellPhoneNumber() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkCellPhoneNumber());

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain international code.",
                login.registerUser()
        );
    }

    @Test
    public void testSuccessfulRegistration() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "User successfully registered.",
                login.registerUser()
        );
    }

    @Test
    public void testSuccessfulLogin() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(
                login.loginUser("kyl_1", "Ch&&sec@ke99!")
        );

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!")
        );
    }

    @Test
    public void testFailedLogin() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(
                login.loginUser("kyl_1", "wrong-password")
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus("kyl_1", "wrong-password")
        );
    }
}