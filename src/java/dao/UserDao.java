package dao;

import entity.User;
import java.util.List;
import util.UserType;

/**
 * The UserDao interface defines the database access operations for managing User entities.
 * It supports adding, updating, deleting, and querying users within the Food Waste Reduction
 * Platform. 
 * 
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To facilitate the management of user accounts, enhancing the platform's
 *          ability to engage with different stakeholders in the food supply chain for
 *          effective food waste reduction.
 */
public interface UserDao {

    /**
     * Adds a new User to the database.
     * 
     * @param user The User entity to be added.
     */
    void addUser(User user);

    /**
     * Updates an existing User in the database.
     * 
     * @param user The User entity with updated information.
     */
    void updateUser(User user);

    /**
     * Deletes a User from the database based on their ID.
     * 
     * @param userId The ID of the User to be deleted.
     */
    void deleteUser(int userId);

    /**
     * Updates the subscription status of a User.
     * 
     * @param userId The ID of the User to update.
     * @param isSubscribed The new subscription status.
     */
    void updateUserIsSubscribed(int userId, boolean isSubscribed);

    /**
     * Retrieves a User by their ID.
     * 
     * @param userId The ID of the User to retrieve.
     * @return The User entity if found, otherwise null.
     */
    User getUserById(int userId);

    /**
     * Retrieves a User by their username.
     * 
     * @param username The username of the User to retrieve.
     * @return The User entity if found, otherwise null.
     */
    User getUserByUsername(String username);

    /**
     * Retrieves all Users from the database.
     * 
     * @return A list of all User entities.
     */
    List<User> getAllUsers();

    /**
     * Retrieves all Users of a specific type.
     * 
     * @param userType The type of User to retrieve.
     * @return A list of User entities of the specified type.
     */
    List<User> getAllUsersByType(UserType userType);
}
