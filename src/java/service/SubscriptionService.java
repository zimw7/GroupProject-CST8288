package service;
import entity.Subscription;
import util.SubscriptionResult;

public interface SubscriptionService {
    SubscriptionResult subscribe(Subscription subscription);
    void unsubscribe(int subscriptionId);
}

