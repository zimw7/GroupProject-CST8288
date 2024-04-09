package dao.impl;

import dao.AlertDao;
import entity.Alert;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AlertDao interface for managing alert entities in the database.
 * This class provides CRUD operations for Alert entities, facilitating the management of user notifications.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To provide a concrete implementation for managing alerts in the database, enabling efficient
 *          notification delivery to users based on their subscriptions and preferences.
 */
public class AlertDaoImpl implements AlertDao {

     /**
     * Inserts a new Alert entity into the database.
     *
     * @param alert The Alert to be added.
     */
    @Override
    public void addAlert(Alert alert) {
        String sql = "INSERT INTO ALERT (USER_ID, MESSAGE) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alert.getUserID());
            stmt.setString(2, alert.getMessage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all alerts associated with a specific user ID.
     *
     * @param userid The ID of the user for which alerts are to be retrieved.
     * @return A list of Alert entities.
     */
    @Override
    public List<Alert> getAlertByUserID(int userid) {
        List<Alert> alerts = new ArrayList<>();
        String sql = "SELECT * FROM ALERT WHERE USER_ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert();
                alert.setId(rs.getInt("ID"));
                alert.setUserID(rs.getInt("USER_ID"));
                alert.setMessage(rs.getString("MESSAGE"));
                alerts.add(alert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alerts;
    }

    /**
     * Deletes an Alert entity from the database based on its ID.
     *
     * @param alertid The ID of the Alert to be deleted.
     */
    @Override
    public void deleteAlert(int alertid) {
        String sql = "DELETE FROM ALERT WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alertid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
