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

    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
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
    public void deleteOneFood(int foodID) {
        foodDao.deleteFood(foodID);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }

}
