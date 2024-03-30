package dao.impl;

import dao.SubscriptionDao;
import entity.Subscription;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteSubscription(int subscriptionId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Subscription getSubscriptionById(int subscriptionId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
