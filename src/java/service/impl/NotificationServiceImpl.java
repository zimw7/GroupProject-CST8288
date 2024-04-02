package service.impl;

import util.ContactType;
import service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(String contactInfo, String message, ContactType contactType) {
        if (contactType == ContactType.EMAIL) {
            System.out.println("Sending email to " + contactInfo + " with message: " + message);
        } else if (contactType == ContactType.TEXT) {
            System.out.println("Sending text message to " + contactInfo + " with message: " + message);
        }
    }
}


