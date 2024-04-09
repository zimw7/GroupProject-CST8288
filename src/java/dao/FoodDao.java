package dao;

import entity.Food;
import java.util.List;

/**
 * The FoodDao interface defines the database access operations for managing Food entities.
 * It supports adding, updating, deleting, and retrieving Food items from the database.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To provide an abstraction layer for CRUD operations on Food entities, enabling
 *          efficient inventory management and the identification of surplus food items for donation
 *          or discount sale.
 */
public interface FoodDao {

    /**
     * Adds a new Food item to the database.
     * 
     * @param food The Food entity to be added.
     */
    void addFood(Food food);

    /**
     * Updates an existing Food item in the database.
     * 
     * @param food The Food entity with updated information.
     */
    void updateFood(Food food);

    /**
     * Deletes a Food item from the database based on its ID.
     * 
     * @param foodId The ID of the Food item to be deleted.
     */
    void deleteFood(int foodId);

    /**
     * Retrieves a Food item by its ID.
     * 
     * @param foodId The ID of the Food item to retrieve.
     * @return The Food entity if found, otherwise null.
     */
    Food getFoodById(int foodId);

    /**
     * Retrieves all Food items from the database.
     * 
     * @return A list of all Food entities.
     */
    List<Food> getAllFoods();

    /**
     * Retrieves Food items associated with a specific user ID.
     * 
     * @param userID The ID of the user whose Food items are to be retrieved.
     * @return A list of Food entities associated with the specified user.
     */
    List<Food> getFoodByUserID(int userID);
}
