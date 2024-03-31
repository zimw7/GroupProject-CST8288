package service;
import entity.Subscription;

public interface SubscriptionService {
    void subscribe(Subscription subscription);
    void unsubscribe(int subscriptionId);
}

