package service;

import util.ContactType;

public interface NotificationService {
    void sendNotification(String contactInfo, String message, ContactType contactType);
}
