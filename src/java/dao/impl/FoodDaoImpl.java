package dao.impl;

import dao.FoodDao;
import entity.Food;
import util.DBConnection;
import util.FoodType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the FoodDao interface for managing food entities in the database.
 * This class provides CRUD operations for Food entities, supporting inventory management
 * and surplus food tracking within the Food Waste Reduction Platform.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To provide a concrete implementation for managing food items in the database, facilitating
 *          the tracking and redistribution of surplus food to reduce waste and support community welfare.
 */
public class FoodDaoImpl implements FoodDao {

    /**
     * Inserts a new Food entity into the database.
     *
     * @param food The Food item to be added.
     */
    @Override
    public void addFood(Food food) {
        String sql = "INSERT INTO FOOD (NAME, QUANTITY, PRICE, FOOD_TYPE, EXPIRATION_DATE, USER_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getName());
            stmt.setInt(2, food.getQuantity());
            stmt.setDouble(3, food.getPrice());
            stmt.setString(4, food.getFoodType().toString());
            stmt.setDate(5, new java.sql.Date(food.getExpirationDate().getTime()));
            stmt.setInt(6, food.getUserID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing Food entity in the database.
     *
     * @param food The Food item with updated information.
     */
    @Override
    public void updateFood(Food food) {
        String sql = "UPDATE FOOD SET NAME = ?, QUANTITY = ?, PRICE = ?, FOOD_TYPE = ?, EXPIRATION_DATE = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getName());
            stmt.setInt(2, food.getQuantity());
            stmt.setDouble(3, food.getPrice());
            stmt.setString(4, food.getFoodType().toString());
            stmt.setDate(5, new java.sql.Date(food.getExpirationDate().getTime()));
            stmt.setInt(6, food.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Food entity from the database based on its ID.
     *
     * @param foodId The ID of the Food item to be deleted.
     */
    @Override
    public void deleteFood(int foodId) {
        String sql = "DELETE FROM FOOD WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, foodId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /**
     * Retrieves a Food entity by its ID.
     *
     * @param foodId The ID of the Food item to retrieve.
     * @return The Food entity if found, otherwise null.
     */
    @Override
    public Food getFoodById(int foodId) {
        String sql = "SELECT * FROM FOOD WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, foodId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("ID"));
                    food.setName(rs.getString("NAME"));
                    food.setQuantity(rs.getInt("QUANTITY"));
                    food.setPrice(rs.getDouble("PRICE"));
                    food.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                    food.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                    food.setUserID(rs.getInt("USER_ID"));
                    return food;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all Food entities from the database.
     *
     * @return A list of all Food items.
     */
    @Override
    public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM FOOD ORDER BY ID";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt("ID"));
                food.setName(rs.getString("NAME"));
                food.setQuantity(rs.getInt("QUANTITY"));
                food.setPrice(rs.getDouble("PRICE"));
                food.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                food.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                food.setUserID(rs.getInt("USER_ID"));
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    /**
     * Retrieves Food entities associated with a specific user ID.
     *
     * @param userID The ID of the user whose Food items are to be retrieved.
     * @return A list of Food entities.
     */
    @Override
    public List<Food> getFoodByUserID(int userID) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM FOOD WHERE USER_ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("ID"));
                    food.setName(rs.getString("NAME"));
                    food.setQuantity(rs.getInt("QUANTITY"));
                    food.setPrice(rs.getDouble("PRICE"));
                    food.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                    food.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                    food.setUserID(rs.getInt("USER_ID"));
                    foods.add(food);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

}
