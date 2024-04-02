/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.FoodDao;
import dao.SurplusFoodDao;
import dao.impl.FoodDaoImpl;
import dao.impl.SurplusFoodDaoImpl;
import entity.Food;
import entity.SurplusFood;
import entity.User;
import java.util.List;
import service.FoodService;

public class FoodServiceImpl implements FoodService {
    private FoodDao foodDao = null;
    private SurplusFoodDao surplusfoodDao = null;
    
    public FoodServiceImpl(){
        foodDao = new FoodDaoImpl();
        surplusfoodDao = new SurplusFoodDaoImpl();
    }

    @Override
    public List<Food> getFoodInventoryByUser(User user) {
        return foodDao.getFoodByUserID(user.getId());
    }

    @Override
    public List<SurplusFood> getAllSurplusFood() {
        return surplusfoodDao.getAllSurplusFoods();
    }
    
}
