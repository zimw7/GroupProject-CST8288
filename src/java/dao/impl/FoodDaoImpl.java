package dao.impl;

import dao.FoodDao;
import entity.Food;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {

    @Override
    public void addFood(Food food) {
        String sql = "INSERT INTO FOOD (NAME, QUANTITY, PRICE, FOOD_TYPE, EXPIRATION_DATE, USER_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getName());
            stmt.setInt(2, food.getQuantity());
            stmt.setDouble(3, food.getPrice());
            stmt.setString(4, food.getFoodType().toString());
            stmt.setDate(5, new Date(food.getExpirationDate().getTime()));
            stmt.setInt(6, food.getUser().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFood(Food food) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteFood(int foodId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Food getFoodById(int foodId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Food> getAllFoods() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    

    
}
