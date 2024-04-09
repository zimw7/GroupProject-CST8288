package dao;

import entity.Subscription;
import java.util.List;
import util.FoodType;

/**
 * The SubscriptionDao interface defines the database access operations for managing Subscription entities.
 * It supports adding, deleting, and retrieving subscriptions based on user preferences and food types.
 * 
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To facilitate the management of user subscriptions to surplus food alerts, enhancing the platform's
 *          ability to notify users about available food items that align with their preferences and dietary needs.
 */
public interface SubscriptionDao {

    /**
     * Adds a new Subscription to the database.
     * 
     * @param subscription The Subscription entity to be added.
     */
    void addSubscription(Subscription subscription);

    /**
     * Deletes a Subscription from the database based on its ID.
     * 
     * @param subscriptionId The ID of the Subscription to be deleted.
     */
    void deleteSubscription(int subscriptionId);

    /**
     * Retrieves a list of Subscriptions based on food type preference and retailer name.
     * 
     * @param foodType The type of food preference.
     * @param retailer_name The name of the retailer.
     * @return A list of Subscription entities matching the criteria.
     */
    List<Subscription> getSubscriptionsByPreference(FoodType foodType, String retailer_name);

    /**
     * Finds subscriptions for a specific user based on preference type and retailer username.
     * 
     * @param userId The user's ID.
     * @param preferenceType The type of food preference.
     * @param retailerUsername The retailer's username.
     * @return A list of Subscription entities matching the criteria.
     */
    List<Subscription> findSubscriptionsByUserAndPreference(int userId, String preferenceType, String retailerUsername);

    /**
     * Retrieves all Subscriptions from the database.
     * 
     * @return A list of all Subscription entities.
     */
    List<Subscription> getAllSubscription();

    /**
     * Retrieves Subscriptions by user ID.
     * 
     * @param userid The ID of the user.
     * @return A list of Subscription entities associated with the specified user ID.
     */
    List<Subscription> getSubscriptionByID(int userid);
}
