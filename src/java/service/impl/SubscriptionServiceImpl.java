package service.impl;

/**
 * This class implements SubscriptionService interface, which concretes all
 * abstract methods to register, login, retrieve all, retrieve by ID, retrieve
 * by name functions.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
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

    /**
     * constructor
     */
    public SubscriptionServiceImpl() {
        this.subscriptionDao = new SubscriptionDaoImpl();
        this.userDao = new UserDaoImpl();
    }

    /**
     * Subscribes a user to a specific subscription.
     *
     * @param subscription The Subscription object representing the subscription
     * details.
     * @return A SubscriptionResult object indicating the result of the
     * subscription attempt.
     */
    @Override
    public SubscriptionResult subscribe(Subscription subscription) {
        List<Subscription> existingSubscriptions = subscriptionDao.findSubscriptionsByUserAndPreference(
                subscription.getUserID(), subscription.getPreferenceType().toString(), subscription.getRetailerUsername());

        if (!existingSubscriptions.isEmpty()) {
            return SubscriptionResult.ALREADY_SUBSCRIBED;
        } else {
            subscriptionDao.addSubscription(subscription);
            userDao.updateUserIsSubscribed(subscription.getUserID(), true);
            return SubscriptionResult.SUCCESS;
        }
    }

    /**
     * Unsubscribes a user from a subscription based on the subscription ID.
     *
     * @param subscriptionId The ID of the subscription to unsubscribe from.
     */
    @Override
    public void unsubscribe(int subscriptionId) {
        subscriptionDao.deleteSubscription(subscriptionId);
    }

    /**
     * Retrieves a list of all subscriptions.
     *
     * @return A list of Subscription objects representing all subscriptions.
     */
    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionDao.getAllSubscription();
    }

    /**
     * Retrieves a list of subscriptions for a specific user based on the user
     * ID.
     *
     * @param userid The ID of the user for whom to retrieve subscriptions.
     * @return A list of Subscription objects representing the user's
     * subscriptions.
     */
    @Override
    public List<Subscription> getSubscriptionsByID(int userid) {
        return subscriptionDao.getSubscriptionByID(userid);
    }

}
