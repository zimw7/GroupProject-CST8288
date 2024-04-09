package service.impl;

/**
 * This class implements FoodService interface, which concretes all
 * abstract methods to implement: add, retrieve, update, delete, and list-all
 * functions.
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import dao.FoodDao;
import dao.impl.FoodDaoImpl;
import entity.Food;
import entity.User;
import java.util.List;
import service.FoodService;

public class FoodServiceImpl implements FoodService {

    private FoodDao foodDao = null;

    /**
     * constructor.
     */
    public FoodServiceImpl() {
        foodDao = new FoodDaoImpl();
    }

    /**
     * overloaded constructor
     *
     * @param foodDao
     */
    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    /**
     * Retrieves the list of food items available in inventory for a specific
     * user.
     *
     * @param user The user for whom to retrieve the food inventory.
     * @return The list of Food objects representing the available food items.
     */
    @Override
    public List<Food> getFoodInventoryByUser(User user) {
        return foodDao.getFoodByUserID(user.getId());
    }

    /**
     * Adds a new food item to the inventory.
     *
     * @param food The Food object representing the new food item to be added.
     */
    @Override
    public void addFoodInventory(Food food) {
        foodDao.addFood(food);
    }

    /**
     * Retrieves the details of a specific food item.
     *
     * @param foodID The ID of the food item to retrieve.
     * @return The Food object representing the details of the food item.
     */
    @Override
    public Food getFoodDetail(int foodID) {
        return foodDao.getFoodById(foodID);
    }

    /**
     * Updates the details of an existing food item in the inventory.
     *
     * @param food The Food object containing the updated information.
     */
    @Override
    public void updateOneFood(Food food) {
        foodDao.updateFood(food);
    }

    /**
     * Deletes a specific food item from the inventory.
     *
     * @param foodID The ID of the food item to delete.
     */
    @Override
    public void deleteOneFood(int foodID) {
        foodDao.deleteFood(foodID);
    }

    /**
     * Retrieves the list of all food items in the inventory.
     *
     * @return The list of Food objects representing all food items.
     */
    @Override
    public List<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }

}
