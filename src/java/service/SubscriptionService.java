package service;
/**
 * The SubscriptionService interface provides methods for managing subscriptions.
 * It includes methods for subscribing, unsubscribing, and retrieving subscriptions.
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import entity.Subscription;
import java.util.List;
import util.SubscriptionResult;

public interface SubscriptionService {

    /**
     * Subscribes a user to a specific subscription.
     * 
     * @param subscription The Subscription object representing the subscription details.
     * @return A SubscriptionResult object indicating the result of the subscription attempt.
     */
    SubscriptionResult subscribe(Subscription subscription);

    /**
     * Unsubscribes a user from a subscription based on the subscription ID.
     * 
     * @param subscriptionId The ID of the subscription to unsubscribe from.
     */
    void unsubscribe(int subscriptionId);

    /**
     * Retrieves a list of all subscriptions.
     * 
     * @return A list of Subscription objects representing all subscriptions.
     */
    List<Subscription> getAllSubscriptions();
    
    /**
     * Retrieves a list of subscriptions for a specific user based on the user ID.
     * 
     * @param userid The ID of the user for whom to retrieve subscriptions.
     * @return A list of Subscription objects representing the user's subscriptions.
     */
    List<Subscription> getSubscriptionsByID(int userid);
}
