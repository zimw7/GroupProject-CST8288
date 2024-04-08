package service;

import entity.User;
import java.util.List;
import util.ContactType;

public interface NotificationService {

    void sendNotification(User user, String message, ContactType contactType);

    List<String> getNotifications(int userid);
}
