package dao;

import entity.Subscription;
import java.util.List;
import util.FoodType;

public interface SubscriptionDao {
    void addSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(int subscriptionId);
    Subscription getSubscriptionById(int subscriptionId);
    List<Subscription> getAllSubscriptions();
    List<Subscription> getSubscriptionsByPreference(FoodType foodType, String location);

}
