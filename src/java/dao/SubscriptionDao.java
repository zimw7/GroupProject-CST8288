package dao;

import entity.Subscription;
import java.util.List;
import util.FoodType; 

public interface SubscriptionDao {
    void addSubscription(Subscription subscription);
    void deleteSubscription(int subscriptionId);
    List<Subscription> findSubscriptionsByUserAndPreference(int userId, String preferenceType);
    List<Subscription> getSubscriptionsByPreference(FoodType foodType); 
}
