package dao.impl;


import dao.AlertDao;
import entity.Alert;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertDaoImpl implements AlertDao {

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
