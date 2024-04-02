/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.Food;
import entity.SurplusFood;
import entity.User;
import java.util.List;

public interface FoodService {
    List<Food> getFoodInventoryByUser(User user);
    List<SurplusFood> getAllSurplusFood();
}
