package service;
/**
 * This business logic class works as an interface。 It contains abstract methods
 * to get and send notification functions.
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import entity.User;
import java.util.List;
import util.ContactType;

public interface NotificationService {

     /**
     * Sends a notification to the specified user using the specified contact type.
     * 
     * @param user The user to whom the notification will be sent.
     * @param message The message content of the notification.
     * @param contactType The type of contact method to use for sending the notification.
     */
    void sendNotification(User user, String message, ContactType contactType);

     /**
     * Retrieves a list of notifications for the specified user.
     * 
     * @param userid The ID of the user for whom to retrieve notifications.
     * @return A list of strings representing the notifications for the user.
     */
    List<String> getNotifications(int userid);
}
