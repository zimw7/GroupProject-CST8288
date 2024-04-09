package dao.impl;

import dao.UserDao;
import entity.User;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.UserType;

/**
 * Provides concrete implementation of the UserDao interface, facilitating
 * operations on User entities within the database. This includes adding,
 * updating, deleting users, and querying user information.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To manage User entities within the Food Waste Reduction Platform, enabling
 *          user authentication, preferences storage, and communication settings.
 */
public class UserDaoImpl implements UserDao {

    /**
     * Adds a new User to the database.
     *
     * @param user The User to be added to the database.
     */
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

    /**
     * Updates an existing User in the database.
     *
     * @param user The User entity with updated fields.
     */
    @Override
    public void updateUser(User user) {
    }

    /**
     * Deletes a User from the database using the User's ID.
     *
     * @param userId The ID of the User to be deleted.
     */
    @Override
    public void deleteUser(int userId) {
    }

    /**
     * Retrieves a User by their ID.
     *
     * @param userId The ID of the User to retrieve.
     * @return The User if found, else null.
     */
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

    /**
     * Retrieves a User by their username.
     *
     * @param username The username of the User to retrieve.
     * @return The User if found, else null.
     */
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

    /**
     * Updates the subscription status of a User.
     *
     * @param userId        The ID of the User whose subscription status is to be updated.
     * @param isSubscribed The new subscription status.
     */
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

    /**
     * Retrieves all Users from the database.
     *
     * @return A list of all Users.
     */
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

    /**
     * Retrieves all Users of a specific type from the database.
     *
     * @param userType The type of Users to retrieve.
     * @return A list of Users of the specified type.
     */
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
