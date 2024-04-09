package service;

/**
 * This business logic class works as an interface It contains abstract methods
 * to add, retrieve, update, delete, and list-all functions.
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import entity.Food;
import entity.User;
import java.util.List;

/**
 * The FoodService interface provides methods to manage food inventory and food
 * details.
 */
public interface FoodService {

    /**
     * Retrieves the list of food items available in inventory for a specific
     * user.
     *
     * @param user The user for whom to retrieve the food inventory.
     * @return The list of Food objects representing the available food items.
     */
    List<Food> getFoodInventoryByUser(User user);

    /**
     * Adds a new food item to the inventory.
     *
     * @param food The Food object representing the new food item to be added.
     */
    void addFoodInventory(Food food);

    /**
     * Retrieves the details of a specific food item.
     *
     * @param foodID The ID of the food item to retrieve.
     * @return The Food object representing the details of the food item.
     */
    Food getFoodDetail(int foodID);

    /**
     * Updates the details of an existing food item in the inventory.
     *
     * @param food The Food object containing the updated information.
     */
    void updateOneFood(Food food);

    /**
     * Deletes a specific food item from the inventory.
     *
     * @param foodID The ID of the food item to delete.
     */
    void deleteOneFood(int foodID);

    /**
     * Retrieves the list of all food items in the inventory.
     *
     * @return The list of Food objects representing all food items.
     */
    List<Food> getAllFoods();
}
