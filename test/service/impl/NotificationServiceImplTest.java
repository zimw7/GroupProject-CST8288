/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service.impl;
import util.ContactType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import service.NotificationService;

public class NotificationServiceImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testSendNotificationWithEmail() {
        // Arrange
        NotificationService notificationService = new NotificationServiceImpl();
        String contactInfo = "example@example.com";
        String message = "Test message";
        ContactType contactType = ContactType.EMAIL;

        // Act
        notificationService.sendNotification(contactInfo, message, contactType);

        // Assert
        
        assertEquals("Sending email to " + contactInfo + " with message: " + message + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testSendNotificationWithText() {
        // Arrange
        NotificationService notificationService = new NotificationServiceImpl();
        String contactInfo = "1234567890";
        String message = "Test message";
        ContactType contactType = ContactType.TEXT;

        // Act
        notificationService.sendNotification(contactInfo, message, contactType);

        // Assert
        assertEquals("Sending text message to " + contactInfo + " with message: " + message + System.lineSeparator(), outContent.toString());
    }
}
