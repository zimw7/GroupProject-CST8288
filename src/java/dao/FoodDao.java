package dao;

import entity.Food;
import java.util.List;

public interface FoodDao {
    void addFood(Food food);
    void updateFood(Food food);
    void deleteFood(int foodId);
    Food getFoodById(int foodId);
    List<Food> getAllFoods();
    List<Food> getSurplusFoodsForDonation();
    List<Food> getSurplusFoodsForSale();
    
}
