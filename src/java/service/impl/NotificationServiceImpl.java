package service.impl;

/**
 * This class implements NotificationService interface, which concretes all
 * abstract methods to implement: get and send notification functions.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import dao.AlertDao;
import dao.impl.AlertDaoImpl;
import entity.Alert;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import util.ContactType;
import service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

    private AlertDao alertDao = null;

    /**
     * constructor
     */
    public NotificationServiceImpl() {
        this.alertDao = new AlertDaoImpl();
    }

    /**
     * Sends a notification to the specified user using the specified contact
     * type.
     *
     * @param user The user to whom the notification will be sent.
     * @param message The message content of the notification.
     * @param contactType The type of contact method to use for sending the
     * notification.
     */
    @Override
    public void sendNotification(User user, String message, ContactType contactType) {
        if (contactType == ContactType.EMAIL) {
            System.out.println("Sending email to " + user.getEmail() + " with message: " + message);
        } else if (contactType == ContactType.TEXT) {
            System.out.println("Sending text message to " + user.getPhoneNumber() + " with message: " + message);
        }

        Alert alert = new Alert();
        alert.setUserID(user.getId());
        alert.setMessage(message);
        alertDao.addAlert(alert);
    }

    /**
     * Retrieves a list of notifications for the specified user.
     *
     * @param userid The ID of the user for whom to retrieve notifications.
     * @return A list of strings representing the notifications for the user.
     */
    @Override
    public List<String> getNotifications(int userid) {
        List<Alert> alerts = alertDao.getAlertByUserID(userid);
        List<String> notifications = new ArrayList();

        for (Alert alert : alerts) {
            notifications.add(alert.getMessage());
            alertDao.deleteAlert(alert.getId());
        }
        return notifications;
    }
}
