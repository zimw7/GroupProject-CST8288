package service.impl;

import dao.SubscriptionDao;
import dao.UserDao;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Subscription;
import java.util.List;
import service.SubscriptionService;

/**
 *
 * @author Zimeng
 */
public class SubscriptionServiceImpl implements SubscriptionService {

    private UserDao userDao; 
    private SubscriptionDao subscriptionDao;

     public SubscriptionServiceImpl() {
        this.subscriptionDao = new SubscriptionDaoImpl();
        this.userDao = new UserDaoImpl(); 
    }

    @Override
    public void subscribe(Subscription subscription) {
        List<Subscription> existingSubscriptions = subscriptionDao.findSubscriptionsByUserAndPreference(
                subscription.getUser().getId(), subscription.getPreferenceType().toString());

        if (existingSubscriptions.isEmpty()) {
            subscriptionDao.addSubscription(subscription);
        } else {
            System.out.println("You've already subscribed to this preference.");
        }
        
        userDao.updateUserIsSubscribed(subscription.getUser().getId(), true);
    }


    @Override
    public void unsubscribe(int subscriptionId) {
        subscriptionDao.deleteSubscription(subscriptionId);
    }
}