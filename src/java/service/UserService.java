package service;

/**
 * The UserService interface provides methods for managing user-related
 * operations. It includes methods for user login, registration, and retrieval
 * of user information. Additionally, it provides methods for handling
 * retailer-specific functionalities.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import entity.User;
import java.util.List;
import util.LoginResult;

public interface UserService {

    /**
     * Attempts to log in a user with the specified username and password.
     *
     * @param username The username of the user trying to log in.
     * @param password The password of the user trying to log in.
     * @return A LoginResult object indicating the result of the login attempt.
     */
    LoginResult login(String username, String password);

    /**
     * Registers a new user with the system.
     *
     * @param user The User object representing the user to be registered.
     * @return true if the registration is successful, false otherwise.
     */
    boolean register(User user);

    /**
     * Retrieves a list of all users registered in the system.
     *
     * @return A list of User objects representing all registered users.
     */
    List<User> getAllUsers();

    /**
     * Retrieves user information based on the username.
     *
     * @param username The username of the user to retrieve information for.
     * @return The User object representing the user with the specified
     * username.
     */
    User getUserByUsername(String username);

    /**
     * Retrieves a list of all retailers registered in the system.
     *
     * @return A list of User objects representing all registered retailers.
     */
    List<User> getAllRetailers();

    /**
     * Retrieves the username based on the user ID.
     *
     * @param userid The ID of the user for whom to retrieve the username.
     * @return The username corresponding to the specified user ID.
     */
    String getUserNameByUserID(int userid);
}
