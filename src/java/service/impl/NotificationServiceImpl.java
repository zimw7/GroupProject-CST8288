package service.impl;

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

    public NotificationServiceImpl() {
        this.alertDao = new AlertDaoImpl();
    }

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
