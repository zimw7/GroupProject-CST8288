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

public class SubscriptionDaoImpl implements SubscriptionDao {

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

    @Override
    public List<Subscription> findSubscriptionsByUserAndPreference(int userId, String preferenceType, String retailerUsername) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM Subscription WHERE user_id = ? AND preference_type = ? AND retailer_username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, preferenceType);
            stmt.setString(3, retailerUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    @Override
    public List<Subscription> getSubscriptionsByPreference(FoodType foodType) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM SUBSCRIPTION WHERE PREFERENCE_TYPE = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodType.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subscription subscription = new Subscription();

                    subscription.setId(rs.getInt("ID"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting subscriptions by preference: " + e.getMessage());
        }
        return subscriptions;
    }

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

}
