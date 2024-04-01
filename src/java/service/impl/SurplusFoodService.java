package service.impl;

import dao.SubscriptionDao;
import entity.Subscription;
import entity.SurplusFood;
import entity.User;
import java.util.List;
import util.ContactType;

public class SurplusFoodService {
    private NotificationServiceImpl notificationService;
    private SubscriptionDao subscriptionDao;
    
    public SurplusFoodService(NotificationServiceImpl notificationService, SubscriptionDao subscriptionDao) {
        this.notificationService = notificationService;
        this.subscriptionDao = subscriptionDao;
    }

    public void listSurplusFood(SurplusFood food) {
    List<Subscription> subscriptions = subscriptionDao.getSubscriptionsByPreference(food.getFoodType());

    for (Subscription subscription : subscriptions) {
        User user = subscription.getUser();
        String message = "Available surplus food: " + food.getName();

        notificationService.sendNotification(
                subscription.getContactType() == ContactType.EMAIL ? user.getEmail() : user.getPhoneNumber(),
                message,
                subscription.getContactType()
        );
    }
}


}
