package dao.impl;

import dao.SubscriptionDao;
import entity.Subscription;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.ContactType;
import util.FoodType;
import util.PreferenceType;

/**
 * Implements the SubscriptionDao interface to provide concrete database operations
 * for Subscription entities. This includes adding, deleting, and finding subscriptions
 * based on user preferences and associations.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To manage user subscriptions within the Food Waste Reduction Platform database,
 *          facilitating personalized alerts and notifications for surplus food.
 */
public class SubscriptionDaoImpl implements SubscriptionDao {

    /**
     * Adds a new subscription to the database.
     *
     * @param subscription The Subscription entity to add.
     */
    @Override
    public void addSubscription(Subscription subscription) {
        String sql = "INSERT INTO SUBSCRIPTION (USER_ID, CONTACT_TYPE, PREFERENCE_TYPE, RETAILER_USERNAME) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getUserID());
            stmt.setString(2, subscription.getContactType().toString());
            stmt.setString(3, subscription.getPreferenceType().toString());
            stmt.setString(4, subscription.getRetailerUsername());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        }
    }

     /**
     * Deletes an existing subscription from the database.
     *
     * @param subscriptionId The ID of the subscription to delete.
     */
    @Override
    public void deleteSubscription(int subscriptionId) {
        String sql = "DELETE FROM SUBSCRIPTION WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds subscriptions by user ID, preference type, and retailer username.
     *
     * @param userId          The user's ID.
     * @param preferenceType  The type of food preference.
     * @param retailerUsername The retailer's username associated with the subscription.
     * @return A list of Subscription entities matching the criteria.
     */
    @Override
    public List<Subscription> findSubscriptionsByUserAndPreference(int userId, String preferenceType, String retailerUsername) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM Subscription WHERE USER_ID = ? AND PREFERENCE_TYPE = ? AND RETAILER_USERNAME = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, preferenceType);
            stmt.setString(3, retailerUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt("ID"));
                subscription.setUserID(rs.getInt("USER_ID"));
                subscription.setContactType(ContactType.valueOf(rs.getString("CONTACT_TYPE")));
                subscription.setPreferenceType(PreferenceType.valueOf(rs.getString("PREFERENCE_TYPE")));
                subscription.setRetailerUsername(rs.getString("RETAILER_USERNAME"));
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    /**
    * Retrieves a list of subscriptions that match a specific food preference and retailer name.
    *
    * @param foodType The type of food preference (e.g., DAIRY, PERISHABLE).
    * @param retailer_name The name of the retailer associated with the subscription.
    * @return A list of Subscription entities that match the specified food type and retailer name.
    */

    @Override
    public List<Subscription> getSubscriptionsByPreference(FoodType foodType, String retailer_name) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM SUBSCRIPTION WHERE PREFERENCE_TYPE = ? AND RETAILER_USERNAME = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodType.toString());
            stmt.setString(2, retailer_name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setId(rs.getInt("ID"));
                    subscription.setUserID(rs.getInt("USER_ID"));
                    subscription.setContactType(ContactType.valueOf(rs.getString("CONTACT_TYPE")));
                    subscription.setPreferenceType(PreferenceType.valueOf(rs.getString("PREFERENCE_TYPE")));
                    subscription.setRetailerUsername(rs.getString("RETAILER_USERNAME"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting subscriptions by preference: " + e.getMessage());
        }
        return subscriptions;
    }

    /**
    * Retrieves all subscriptions from the database. This method is useful for administrative purposes,
    * allowing an overview of all active subscriptions in the system.
    *
    * @return A list of all Subscription entities in the database, ordered by their ID.
    */
    @Override
    public List<Subscription> getAllSubscription() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM SUBSCRIPTION ORDER BY ID";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setId(rs.getInt("ID"));
                    subscription.setUserID(rs.getInt("USER_ID"));
                    subscription.setContactType(ContactType.valueOf(rs.getString("CONTACT_TYPE")));
                    subscription.setPreferenceType(PreferenceType.valueOf(rs.getString("PREFERENCE_TYPE")));
                    subscription.setRetailerUsername(rs.getString("RETAILER_USERNAME"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting subscriptions by preference: " + e.getMessage());
        }
        return subscriptions;
    }

    /**
    * Retrieves all subscriptions for a specific user based on the user's ID. This method enables
    * fetching all subscriptions that a particular user has created, allowing for a personalized
    * user experience and management of their subscription preferences.
    *
    * @param userid The ID of the user whose subscriptions are to be retrieved.
    * @return A list of Subscription entities associated with the specified user ID.
    */
    @Override
    public List<Subscription> getSubscriptionByID(int userid) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM Subscription WHERE USER_ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt("ID"));
                subscription.setUserID(rs.getInt("USER_ID"));
                subscription.setContactType(ContactType.valueOf(rs.getString("CONTACT_TYPE")));
                subscription.setPreferenceType(PreferenceType.valueOf(rs.getString("PREFERENCE_TYPE")));
                subscription.setRetailerUsername(rs.getString("RETAILER_USERNAME"));
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

}
