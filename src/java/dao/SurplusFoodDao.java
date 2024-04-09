package dao;

import entity.SurplusFood;
import java.util.List;

/**
 * The SurplusFoodDao interface outlines the data access operations required
 * for managing surplus food items within the Food Waste Reduction Platform.
 * It provides methods for adding, updating, deleting, and querying surplus
 * food items, facilitating their redistribution to consumers and charitable
 * organizations.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To enable efficient management and redistribution of surplus food items,
 *          helping to minimize food waste and support community welfare.
 */
public interface SurplusFoodDao {

    /**
     * Adds a new surplus food item to the database.
     * 
     * @param food The SurplusFood entity to be added.
     */
    void addSurplusFood(SurplusFood food);

    /**
     * Updates an existing surplus food item in the database.
     * 
     * @param food The SurplusFood entity with updated information.
     */
    void updateSurplusFood(SurplusFood food);

    /**
     * Updates the quantity of a specific surplus food item.
     * 
     * @param id The ID of the surplus food item to update.
     * @param quantity The new quantity of the food item.
     */
    void updateSurplusQuantity(int id, int quantity);

    /**
     * Deletes a surplus food item from the database based on its ID.
     * 
     * @param foodId The ID of the surplus food item to be deleted.
     */
    void deleteSurplusFood(int foodId);

    /**
     * Retrieves a surplus food item by its ID.
     * 
     * @param foodId The ID of the surplus food item to retrieve.
     * @return The SurplusFood entity if found, otherwise null.
     */
    SurplusFood getSurplusFoodById(int foodId);

    /**
     * Retrieves all surplus food items from the database.
     * 
     * @return A list of all SurplusFood entities.
     */
    List<SurplusFood> getAllSurplusFoods();

    /**
     * Retrieves all surplus food items marked for donation.
     * 
     * @return A list of SurplusFood entities available for donation.
     */
    List<SurplusFood> getSurplusFoodsForDonation();

    /**
     * Retrieves all surplus food items marked for sale.
     * 
     * @return A list of SurplusFood entities available for sale.
     */
    List<SurplusFood> getSurplusFoodsForSale();

    /**
     * Retrieves surplus food items associated with a specific user ID.
     * 
     * @param userID The ID of the user whose surplus food items are to be retrieved.
     * @return A list of SurplusFood entities associated with the specified user.
     */
    List<SurplusFood> getSurplusFoodByUserID(int userID);
}
