package service.impl;

import dao.SubscriptionDao;
import dao.UserDao;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Subscription;
import java.util.List;
import service.SubscriptionService;
import util.SubscriptionResult;


public class SubscriptionServiceImpl implements SubscriptionService {

    private UserDao userDao; 
    private SubscriptionDao subscriptionDao;

     public SubscriptionServiceImpl() {
        this.subscriptionDao = new SubscriptionDaoImpl();
        this.userDao = new UserDaoImpl(); 
    }

    @Override
    public SubscriptionResult subscribe(Subscription subscription) {
        List<Subscription> existingSubscriptions = subscriptionDao.findSubscriptionsByUserAndPreference(
                subscription.getUser().getId(), subscription.getPreferenceType().toString(), subscription.getRetailerUsername());

        if (!existingSubscriptions.isEmpty()) {
            return SubscriptionResult.ALREADY_SUBSCRIBED;
        } else {
            subscriptionDao.addSubscription(subscription);
            userDao.updateUserIsSubscribed(subscription.getUser().getId(), true);
            return SubscriptionResult.SUCCESS;
        }
    }




    @Override
    public void unsubscribe(int subscriptionId) {
        subscriptionDao.deleteSubscription(subscriptionId);
    }
}