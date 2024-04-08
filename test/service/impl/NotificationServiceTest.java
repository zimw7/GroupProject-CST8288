/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service.impl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.AlertDao;
import dao.impl.AlertDaoImpl;
import entity.User;
import service.NotificationService;
import util.ContactType;
import util.UserType;

public class NotificationServiceTest {

    private NotificationService notificationService;
    private AlertDao alertDao;

    @Before
    public void setUp() {
        
        alertDao = new AlertDaoImpl();
        notificationService = new NotificationServiceImpl();
    }

    @Test
    public void testSendNotificationEmail() {
        User user = new User(1,"testUser","password",UserType.CUSTOMER, "1234567890","test@example.com",true);
        String message = "Test message";
        ContactType contactType = ContactType.EMAIL;

        notificationService.sendNotification(user, message, contactType);

        List<String> notifications = notificationService.getNotifications(user.getId());
        assertTrue(notifications.contains(message));
    }

    @Test
    public void testSendNotificationText() {
       User user = new User(1,"testUser","password",UserType.CUSTOMER, "1234567890","test@example.com",true);
        String message = "Test message";
        ContactType contactType = ContactType.TEXT;

        notificationService.sendNotification(user, message, contactType);

        List<String> notifications = notificationService.getNotifications(user.getId());
        assertTrue(notifications.contains(message));
    }

    @Test
    public void testGetNotifications() {
        User user = new User(1,"testUser","password",UserType.CUSTOMER, "1234567890","test@example.com",true);
        String message1 = "Test message 1";
        String message2 = "Test message 2";
        ContactType contactType = ContactType.EMAIL;

        notificationService.sendNotification(user, message1, contactType);
        notificationService.sendNotification(user, message2, contactType);

        List<String> notifications = notificationService.getNotifications(user.getId());
        assertEquals(2, notifications.size());
        assertTrue(notifications.contains(message1));
        assertTrue(notifications.contains(message2));
    }
}
