/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.Food;
import entity.User;
import java.util.List;

public interface FoodService {
    List<Food> getFoodInventoryByUser(User user);
    void addFoodInventory(Food food);
    Food getFoodDetail(int foodID);
    void updateOneFood(Food food);
    void deleteOneFood(Food food);
}
