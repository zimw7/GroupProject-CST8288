package dao;

import entity.Subscription;
import java.util.List;
import util.FoodType;

public interface SubscriptionDao {

    void addSubscription(Subscription subscription);

    void deleteSubscription(int subscriptionId);

    List<Subscription> getSubscriptionsByPreference(FoodType foodType, String retailer_name);

    List<Subscription> findSubscriptionsByUserAndPreference(int userId, String preferenceType, String retailerUsername);

    List<Subscription> getAllSubscription();

    List<Subscription> getSubscriptionByID(int userid);
}
