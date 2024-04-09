package dao.impl;

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

/**
 * Implements the SurplusFoodDao interface to provide concrete database operations
 * for SurplusFood entities. This includes adding, updating, deleting, and querying surplus
 * food items, supporting the platform's goal of reducing food waste.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To manage surplus food items within the Food Waste Reduction Platform database,
 *          helping to connect surplus food with those in need or those willing to purchase
 *          at a discounted rate.
 */
public class SurplusFoodDaoImpl implements SurplusFoodDao {

    /**
     * Adds a new surplus food item to the database.
     *
     * @param surplusfood The SurplusFood entity to add.
     */
    @Override
    public void addSurplusFood(SurplusFood surplusfood) {
        String sql = "INSERT INTO SURPLUS_FOOD (NAME, QUANTITY, FOOD_TYPE, EXPIRATION_DATE, PRICE, DISCOUNT_RATE, IS_FOR_DONATION, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, surplusfood.getName());
            stmt.setInt(2, surplusfood.getQuantity());
            stmt.setString(3, surplusfood.getFoodType().toString());
            stmt.setDate(4, new java.sql.Date(surplusfood.getExpirationDate().getTime()));
            stmt.setDouble(5, surplusfood.getPrice());
            stmt.setDouble(6, surplusfood.getDiscountRate());
            stmt.setBoolean(7, surplusfood.isIsForDonation());
            stmt.setInt(8, surplusfood.getUserID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /**
     * Updates an existing surplus food item in the database.
     *
     * @param surplusfood The SurplusFood entity with updated information.
     */
    @Override
    public void updateSurplusFood(SurplusFood surplusfood) {
        String sql = "UPDATE SURPLUS_FOOD SET NAME = ?, QUANTITY = ?, PRICE = ?, FOOD_TYPE = ?, EXPIRATION_DATE = ?, "
                + "DISCOUNT_RATE = ?, IS_FOR_DONATION = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    /**
     * Deletes a surplus food item from the database based on its ID.
     *
     * @param foodId The ID of the SurplusFood item to delete.
     */
    @Override
    public void deleteSurplusFood(int foodId) {
        String sql = "DELETE FROM SURPLUS_FOOD WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, foodId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Retrieves a specific SurplusFood item by its ID from the database.
    * 
    * @param foodId The unique identifier of the surplus food item to retrieve.
    * @return A SurplusFood object if found, otherwise returns null.
    */
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
                    surplusfood.setDiscountRate(rs.getDouble("DISCOUNT_RATE"));
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

    /**
    * Retrieves all surplus food items from the database.
    * 
    * @return A list of all SurplusFood items stored in the database, or an empty list if no items are found.
    */
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
                surplusfood.setDiscountRate(rs.getDouble("DISCOUNT_RATE"));
                surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                surplusfood.setUserID(rs.getInt("USER_ID"));
                surplusfoods.add(surplusfood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusfoods;
    }

    /**
    * Retrieves all surplus food items that are marked for donation.
    * 
    * @return A list of SurplusFood items available for donation, or an empty list if no such items are found.
    */
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
                surplusfood.setDiscountRate(rs.getDouble("DISCOUNT_RATE"));
                surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                surplusfood.setUserID(rs.getInt("USER_ID"));
                surplusfoods.add(surplusfood);
                System.out.println("Retrieved food for donation: " + surplusfood.getName());
            }
        } catch (SQLException e) {
             System.err.println("Error when retrieving foods for donation: " + e.getMessage());
            e.printStackTrace();
        }
        return surplusfoods;
    }

    /**
    * Retrieves all surplus food items that are available for sale.
    * 
    * @return A list of SurplusFood items available for sale, or an empty list if no such items are found.
    */
    @Override
    public List<SurplusFood> getSurplusFoodsForSale() {
        List<SurplusFood> surplusfoods = new ArrayList<>();
        String sql = "SELECT * FROM SURPLUS_FOOD WHERE IS_FOR_DONATION = FALSE";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SurplusFood surplusfood = new SurplusFood();
                surplusfood.setId(rs.getInt("ID"));
                surplusfood.setName(rs.getString("NAME"));
                surplusfood.setQuantity(rs.getInt("QUANTITY"));
                surplusfood.setPrice(rs.getDouble("PRICE"));
                surplusfood.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                surplusfood.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                surplusfood.setDiscountRate(rs.getDouble("DISCOUNT_RATE"));
                surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                surplusfood.setUserID(rs.getInt("USER_ID"));
                surplusfoods.add(surplusfood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusfoods;
    }

    /**
    * Retrieves all surplus food items associated with a specific user ID. 
    * 
    * @param userID The unique identifier of the user whose surplus food items are to be retrieved.
    * @return A list of SurplusFood items associated with the specified user, or an empty list if no such items are found.
    */
    @Override
    public List<SurplusFood> getSurplusFoodByUserID(int userID) {
        List<SurplusFood> surplusfoods = new ArrayList<>();
        String sql = "SELECT * FROM SURPLUS_FOOD WHERE USER_ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SurplusFood surplusfood = new SurplusFood();
                    surplusfood.setId(rs.getInt("ID"));
                    surplusfood.setName(rs.getString("NAME"));
                    surplusfood.setQuantity(rs.getInt("QUANTITY"));
                    surplusfood.setPrice(rs.getDouble("PRICE"));
                    surplusfood.setFoodType(FoodType.valueOf(rs.getString("FOOD_TYPE")));
                    surplusfood.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
                    surplusfood.setUserID(rs.getInt("USER_ID"));
                    surplusfood.setDiscountRate(rs.getDouble("DISCOUNT_RATE"));
                    surplusfood.setIsForDonation(rs.getBoolean("IS_FOR_DONATION"));
                    surplusfoods.add(surplusfood);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusfoods;
    }
    
    /**
    * Updates the quantity of a specific surplus food item in the database. 
    * 
    * @param id The unique identifier of the surplus food item to update.
    * @param quantity The new quantity to be set for the surplus food item.
    */
    @Override
    public void updateSurplusQuantity(int id, int quantity){
        String sql = "UPDATE SURPLUS_FOOD SET QUANTITY = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {   
            stmt.setInt(1, quantity);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
