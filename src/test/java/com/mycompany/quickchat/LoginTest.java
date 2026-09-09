package com.mycompany.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Login class.
 */
public class LoginTest {

    /**
     * Test that a correctly formatted username returns true.
     */
    @Test
    public void testCheckUserNameValid() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkUserName());
    }

    /**
     * Test that an incorrectly formatted username returns false.
     */
    @Test
    public void testCheckUserNameInvalid() {
        Login login = new Login(
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkUserName());
    }

    /**
     * Test that a password meeting the complexity requirements
     * returns true.
     */
    @Test
    public void testCheckPasswordComplexityValid() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkPasswordComplexity());
    }

    /**
     * Test that a password not meeting the complexity requirements
     * returns false.
     */
    @Test
    public void testCheckPasswordComplexityInvalid() {
        Login login = new Login(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    /**
     * Test that a correctly formatted cell phone number returns true.
     */
    @Test
    public void testCheckCellPhoneNumberValid() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.checkCellPhoneNumber());
    }

    /**
     * Test that an incorrectly formatted cell phone number returns false.
     */
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        assertFalse(login.checkCellPhoneNumber());
    }

    /**
     * Test that a correctly formatted username returns
     * the required success message.
     */
    @Test
    public void testUsernameSuccessMessage() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String result;

        if (login.checkUserName()) {
            result = "Username successfully captured.";
        } else {
            result = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        assertEquals("Username successfully captured.", result);
    }

    /**
     * Test that an incorrectly formatted username returns
     * the required error message.
     */
    @Test
    public void testUsernameErrorMessage() {
        Login login = new Login(
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String result;

        if (login.checkUserName()) {
            result = "Username successfully captured.";
        } else {
            result = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                result
        );
    }

    /**
     * Test that a valid password returns the required success message.
     */
    @Test
    public void testPasswordSuccessMessage() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String result;

        if (login.checkPasswordComplexity()) {
            result = "Password successfully captured.";
        } else {
            result = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        assertEquals("Password successfully captured.", result);
    }

    /**
     * Test that an invalid password returns the required error message.
     */
    @Test
    public void testPasswordErrorMessage() {
        Login login = new Login(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String result;

        if (login.checkPasswordComplexity()) {
            result = "Password successfully captured.";
        } else {
            result = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                result
        );
    }

    /**
     * Test that a valid cell phone number returns the required
     * success message.
     */
    @Test
    public void testCellNumberSuccessMessage() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String result;

        if (login.checkCellPhoneNumber()) {
            result = "Cell number successfully captured.";
        } else {
            result = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        assertEquals("Cell number successfully captured.", result);
    }

    /**
     * Test that an invalid cell phone number returns the required
     * error message.
     */
    @Test
    public void testCellNumberErrorMessage() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        String result;

        if (login.checkCellPhoneNumber()) {
            result = "Cell number successfully captured.";
        } else {
            result = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        assertEquals(
                "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                result
        );
    }

    /**
     * Test that a valid user can register successfully and log in.
     */
    @Test
    public void testLoginSuccessful() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        login.registerUser();
        login.setLoginDetails("kyl_1", "Ch&&sec@ke99!");

        assertTrue(login.loginUser());
    }

    /**
     * Test that an incorrect password causes login to fail.
     */
    @Test
    public void testLoginFailed() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        login.registerUser();
        login.setLoginDetails("kyl_1", "wrong-password");

        assertFalse(login.loginUser());
    }

    /**
     * Test that the correct username and password return
     * the required Welcome message.
     */
    @Test
    public void testReturnLoginStatusSuccessful() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        login.registerUser();
        login.setLoginDetails("kyl_1", "Ch&&sec@ke99!");

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus()
        );
    }

    /**
     * Test that incorrect login details return the required
     * error message.
     */
    @Test
    public void testReturnLoginStatusFailed() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        login.registerUser();
        login.setLoginDetails("kyl_1", "wrong-password");

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus()
        );
    }

    /**
     * Test that login is not possible when registration fails.
     */
    @Test
    public void testLoginFailsAfterRegistrationFailure() {
        Login login = new Login(
                "kyle!!!!!!!",
                "password",
                "08966553",
                "Kyle",
                "Smith"
        );

        login.registerUser();
        login.setLoginDetails("kyle!!!!!!!", "password");

        assertFalse(login.loginUser());
    }
}