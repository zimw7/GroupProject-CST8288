package dao.impl;

import dao.SubscriptionDao;
import entity.Subscription;
import util.DBConnection;
import util.FoodType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.ContactType;
import util.PreferenceType;

public class SubscriptionDaoImpl implements SubscriptionDao {

    @Override
    public void addSubscription(Subscription subscription) {
        String sql = "INSERT INTO SUBSCRIPTION (USER_ID, CONTACT_TYPE, PREFERENCE_TYPE) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getUser().getId());
            stmt.setString(2, subscription.getContactType().toString());
            stmt.setString(3, subscription.getPreferenceType().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        String sql = "UPDATE SUBSCRIPTION SET CONTACT_TYPE = ?, PREFERENCE_TYPE = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subscription.getContactType().toString());
            stmt.setString(2, subscription.getPreferenceType().toString());
            stmt.setInt(3, subscription.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubscription(int subscriptionId) {
        String sql = "DELETE FROM SUBSCRIPTION WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subscription getSubscriptionById(int subscriptionId) {
        String sql = "SELECT * FROM SUBSCRIPTION WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setId(rs.getInt("ID"));
                    ContactType contactType = ContactType.valueOf(rs.getString("CONTACT_TYPE"));
                    PreferenceType preferenceType = PreferenceType.valueOf(rs.getString("PREFERENCE_TYPE"));
                    subscription.setContactType(contactType);
                    subscription.setPreferenceType(preferenceType);
                    return subscription;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM SUBSCRIPTION";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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
    public List<Subscription> getSubscriptionsByPreference(FoodType foodType, String location) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM SUBSCRIPTION WHERE PREFERENCE_TYPE = ? AND LOCATION = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodType.toString());
            stmt.setString(2, location);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subscription subscription = new Subscription();
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }
}
