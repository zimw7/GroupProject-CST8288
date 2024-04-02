/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.FoodDao;
import dao.impl.FoodDaoImpl;
import entity.Food;
import entity.User;
import java.util.List;
import service.FoodService;

public class FoodServiceImpl implements FoodService {

    private FoodDao foodDao = null;

    public FoodServiceImpl() {
        foodDao = new FoodDaoImpl();
    }

    @Override
    public List<Food> getFoodInventoryByUser(User user) {
        return foodDao.getFoodByUserID(user.getId());
    }

    @Override
    public void addFoodInventory(Food food) {
        foodDao.addFood(food);
    }

    @Override
    public Food getFoodDetail(int foodID) {
        return foodDao.getFoodById(foodID);
    }

    @Override
    public void updateOneFood(Food food) {
        foodDao.updateFood(food);
    }

    @Override
    public void deleteOneFood(Food food) {
        foodDao.deleteFood(food.getId());
    }
    
    
}
