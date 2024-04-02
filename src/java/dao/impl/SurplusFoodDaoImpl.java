/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import entity.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import util.FoodType;
import dao.SurplusFoodDao;
import entity.SurplusFood;

public class SurplusFoodDaoImpl implements SurplusFoodDao {

    @Override
    public void addSurplusFood(SurplusFood surplusfood) {
        String sql = "INSERT INTO FOOD (NAME, QUANTITY, PRICE, FOOD_TYPE, EXPIRATION_DATE, USER_ID, DISCOUNT_RATE, IS_FOR_DONATION) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, surplusfood.getName());
            stmt.setInt(2, surplusfood.getQuantity());
            stmt.setDouble(3, surplusfood.getPrice());
            stmt.setString(4, surplusfood.getFoodType().toString());
            stmt.setDate(5, new java.sql.Date(surplusfood.getExpirationDate().getTime()));
            stmt.setDouble(6, surplusfood.getDiscountRate());
            stmt.setBoolean(7, surplusfood.isIsForDonation());
            stmt.setInt(8, surplusfood.getUserID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSurplusFood(SurplusFood surplusfood) {
        String sql = "UPDATE SURPLUS_FOOD SET NAME = ?, QUANTITY = ?, PRICE = ?, FOOD_TYPE = ?, EXPIRATION_DATE = ?, "
                + "DISCOUNT_RATE = ?, IS_FOR_DONATION = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, surplusfood.getName());
            stmt.setInt(2, surplusfood.getQuantity());
            stmt.setDouble(3, surplusfood.getPrice());
            stmt.setString(4, surplusfood.getFoodType().toString());
            stmt.setDate(5, new java.sql.Date(surplusfood.getExpirationDate().getTime()));
            stmt.setDouble(6, surplusfood.getDiscountRate());
            stmt.setBoolean(7, surplusfood.isIsForDonation());
            stmt.setInt(8, surplusfood.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSurplusFood(int foodId) {
        String sql = "DELETE FROM SURPLUS_FOOD WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, foodId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SurplusFood getSurplusFoodById(int foodId) {
        String sql = "SELECT * FROM SURPLUS_FOOD WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, foodId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SurplusFood surplusfood = new SurplusFood();
                    surplusfood.setId(rs.getInt("ID"));
                    surplusfood.setName(rs.getString("NAME"));
                    surplusfood.setQuantity(rs.getInt("QUANTITY"));
                    surplusfood.setPrice(rs.getDouble("PRICE"));
                    surplusfood.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                    surplusfood.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                    surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                    surplusfood.setUserID(rs.getInt("USER_ID"));
                    return surplusfood;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SurplusFood> getAllSurplusFoods() {
        List<SurplusFood> surplusfoods = new ArrayList<>();
        String sql = "SELECT * FROM SURPLUS_FOOD ORDER BY ID";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SurplusFood surplusfood = new SurplusFood();
                surplusfood.setId(rs.getInt("ID"));
                surplusfood.setName(rs.getString("NAME"));
                surplusfood.setQuantity(rs.getInt("QUANTITY"));
                surplusfood.setPrice(rs.getDouble("PRICE"));
                surplusfood.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                surplusfood.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                surplusfood.setUserID(rs.getInt("USER_ID"));
                surplusfoods.add(surplusfood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusfoods;
    }

    @Override
    public List<SurplusFood> getSurplusFoodsForDonation() {
        List<SurplusFood> surplusfoods = new ArrayList<>();
        String sql = "SELECT * FROM SURPLUS_FOOD WHERE IS_FOR_DONATION = TRUE";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SurplusFood surplusfood = new SurplusFood();
                surplusfood.setId(rs.getInt("ID"));
                surplusfood.setName(rs.getString("NAME"));
                surplusfood.setQuantity(rs.getInt("QUANTITY"));
                surplusfood.setPrice(rs.getDouble("PRICE"));
                surplusfood.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                surplusfood.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                surplusfood.setUserID(rs.getInt("USER_ID"));
                surplusfoods.add(surplusfood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusfoods;
    }

    @Override
    public List<SurplusFood> getSurplusFoodsForSale() {
        List<SurplusFood> surplusfoods = new ArrayList<>();
        String sql = "SELECT * FROM FOOD WHERE IS_FOR_DONATION = FALSE";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SurplusFood surplusfood = new SurplusFood();
                surplusfood.setId(rs.getInt("ID"));
                surplusfood.setName(rs.getString("NAME"));
                surplusfood.setQuantity(rs.getInt("QUANTITY"));
                surplusfood.setPrice(rs.getDouble("PRICE"));
                surplusfood.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                surplusfood.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                surplusfood.setUserID(rs.getInt("USER_ID"));
                surplusfoods.add(surplusfood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusfoods;
    }

}
