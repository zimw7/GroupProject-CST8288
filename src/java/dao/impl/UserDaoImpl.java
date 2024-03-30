package dao.impl;

import dao.UserDao;
import entity.User;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.UserType;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO USERS (USER_NAME, PASSWORD, USER_TYPE, PHONE_NUMBER, EMAIL, IS_SUBSCRIBED) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getUserType().toString());
                stmt.setString(4, user.getPhoneNumber());
                stmt.setString(5, user.getEmail());
                stmt.setBoolean(6, user.isSubscribed());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) { }
    @Override
    public void deleteUser(int userId) { }
    @Override
    public User getUserById(int userId) { return null;  }
    
    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM USERS WHERE USER_NAME = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("ID"));
                    user.setUserName(rs.getString("USER_NAME"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setUserType(UserType.valueOf(rs.getString("USER_TYPE")));
                    user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setIsSubscribed(rs.getBoolean("IS_SUBSCRIBED"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public List<User> getAllUsers() { return new ArrayList<>();  }
}
