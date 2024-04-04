/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import entity.User;
import service.impl.UserServiceImpl;
import util.LoginResult;
import util.UserType;

public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testLoginSuccess() {
        // Create a user 
        User user = new User(123,"testUser", "password123", UserType.CUSTOMER,"6131111111","TEST@GMAIL.COM",true);
        userService.register(user);

        // Perform login with correct credentials
        LoginResult result = userService.login("testUser", "password123");

        assertEquals(LoginResult.SUCCESS, result);
    }

    @Test
    public void testLoginUserNotFound() {
        // Perform login with non-existing user
        LoginResult result = userService.login("nonExistingUser", "password123");

        assertEquals(LoginResult.USER_NOT_FOUND, result);
    }

    @Test
    public void testLoginInvalidPassword() {
        // Create a user with known credentials
        User user = new User(123,"testUser", "password123", UserType.CUSTOMER,"6131111111","TEST@GMAIL.COM",true);
        userService.register(user);

        // Perform login with incorrect password
        LoginResult result = userService.login("testUser", "wrongPassword");

        assertEquals(LoginResult.INVALID_PASSWORD, result);
    }


    @Test
    public void testRegisterExistingUser() {
        // Register an existing user
        User user = new User(123,"testUser", "password123", UserType.CUSTOMER,"6131111111","TEST@GMAIL.COM",true);
        userService.register(user);

        // Try registering the same user again
        boolean registeredAgain = userService.register(user);

        assertFalse(registeredAgain);
    }

}
