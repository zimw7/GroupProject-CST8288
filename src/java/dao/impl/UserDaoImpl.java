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
        String sql = "INSERT INTO USERS (USER_NAME, PASSWORD, USER_TYPE, PHONE_NUMBER, EMAIL, IS_SUBSCRIBED) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType().toString());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getEmail());
            stmt.setBoolean(6, user.isSubscribed());
            int affectedRows = stmt.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
    }

    @Override
    public void deleteUser(int userId) {
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
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
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM USERS WHERE USER_NAME = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
    public void updateUserIsSubscribed(int userId, boolean isSubscribed) {
        String sql = "UPDATE users SET is_subscribed = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isSubscribed);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS ORDER BY ID";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                user.setEmail(rs.getString("EMAIL"));
                user.setUserType(UserType.valueOf(rs.getString("USER_TYPE")));
                user.setIsSubscribed(rs.getBoolean("IS_SUBSCRIBED"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getAllUsersByType(UserType userType) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE user_type = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userType.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USER_NAME"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
