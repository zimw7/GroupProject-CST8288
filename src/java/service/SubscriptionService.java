package service;

import entity.Subscription;
import java.util.List;
import util.SubscriptionResult;

public interface SubscriptionService {

    SubscriptionResult subscribe(Subscription subscription);

    void unsubscribe(int subscriptionId);

    List<Subscription> getAllSubscriptions();
    
    List<Subscription> getSubscriptionsByID(int userid);
}
