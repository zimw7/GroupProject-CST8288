/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package service.impl;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import dao.FoodDao;
import entity.Food;
import entity.User;

/**
 * Unit tests for the FoodServiceImpl class.
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
public class FoodServiceImplTest {

private FoodDao foodDaoStub;
    private FoodServiceImpl foodService;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @Before
    public void setUp() {
        foodDaoStub = new FoodDaoStub();
        foodService = new FoodServiceImpl(foodDaoStub);
    }

    /**
     * Tests the getFoodInventoryByUser method of FoodServiceImpl.
     */
    @Test
    public void testGetFoodInventoryByUser() {
        // Prepare test data
        User user = new User();
        user.setId(1);

        // Call the method to be tested
        List<Food> actualFoodList = foodService.getFoodInventoryByUser(user);

        // Verify the result
        assertEquals(2, actualFoodList.size()); // Assuming the stub returns 2 foods for user with ID 1
    }

     /**
     * Tests the addFoodInventory method of FoodServiceImpl.
     */
    @Test
    public void testAddFoodInventory() {
        // Prepare test data
        Food food = new Food();

        // Call the method to be tested
        foodService.addFoodInventory(food);

        // Verify that the method in FoodDao is called
        assertEquals(1, ((FoodDaoStub) foodDaoStub).getAddFoodCallCount());
    }

    /**
     * Tests the getFoodDetail method of FoodServiceImpl.
     */
    @Test
    public void testGetFoodDetail() {
        // Prepare test data
        int foodID = 1;

        // Call the method to be tested
        Food actualFood = foodService.getFoodDetail(foodID);

        // Verify the result
        assertEquals(foodID, actualFood.getId()); // Assuming the stub returns food with ID equal to foodID
    }

    /**
     * Tests the updateOneFood method of FoodServiceImpl.
     */
    @Test
    public void testUpdateOneFood() {
        // Prepare test data
        Food food = new Food();

        // Call the method to be tested
        foodService.updateOneFood(food);

        // Verify that the method in FoodDao is called
        assertEquals(1, ((FoodDaoStub) foodDaoStub).getUpdateFoodCallCount());
    }

    /**
     * Tests the deleteOneFood method of FoodServiceImpl.
     */
    @Test
    public void testDeleteOneFood() {
        // Prepare test data
        Food food = new Food();
        food.setId(1);

        // Call the method to be tested
        foodService.deleteOneFood(1);

        // Verify that the method in FoodDao is called with correct argument
        assertEquals(1, ((FoodDaoStub) foodDaoStub).getDeleteFoodCallCount());
    }
}

/**
 * A stub implementation of the FoodDao interface for testing purposes.
 */
class FoodDaoStub implements FoodDao {
    private int addFoodCallCount = 0;
    private int updateFoodCallCount = 0;
    private int deleteFoodCallCount = 0;

    @Override
    public void addFood(Food food) {
        addFoodCallCount++;
    }

    @Override
    public void updateFood(Food food) {
        updateFoodCallCount++;
    }

    @Override
    public void deleteFood(int foodId) {
        deleteFoodCallCount++;
    }

    @Override
    public Food getFoodById(int foodId) {
        // Stub implementation, return a new food object with provided ID
        Food food = new Food();
        food.setId(foodId);
        return food;
    }

    @Override
    public List<Food> getAllFoods() {
        // Stub implementation, return an empty list
        return new ArrayList<>();
    }

    @Override
    public List<Food> getFoodByUserID(int userID) {
        // Stub implementation, return a list of 2 foods for user with ID 1
        List<Food> foods = new ArrayList<>();
        if (userID == 1) {
            foods.add(new Food());
            foods.add(new Food());
        }
        return foods;
    }

    public int getAddFoodCallCount() {
        return addFoodCallCount;
    }

    public int getUpdateFoodCallCount() {
        return updateFoodCallCount;
    }

    public int getDeleteFoodCallCount() {
        return deleteFoodCallCount;
    }
}
