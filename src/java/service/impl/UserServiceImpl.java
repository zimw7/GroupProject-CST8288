package service.impl;

/**
 * This class implements UserService interface, which concretes all abstract
 * methods for logining in, registering, retrieving all users, retrieving by id..
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;
import java.util.List;
import util.LoginResult;
import util.UserType;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    /**
     * constructor
     */
    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    /**
     * Attempts to log in a user with the specified username and password.
     *
     * @param username The username of the user trying to log in.
     * @param password The password of the user trying to log in.
     * @return A LoginResult object indicating the result of the login attempt.
     */
    @Override
    public LoginResult login(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            return LoginResult.USER_NOT_FOUND;
        }
        if (user.getPassword().equals(password)) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.INVALID_PASSWORD;
        }
    }

    /**
     * Registers a new user with the system.
     *
     * @param user The User object representing the user to be registered.
     * @return true if the registration is successful, false otherwise.
     */
    @Override
    public boolean register(User user) {
        User existingUser = userDao.getUserByUsername(user.getUserName());
        if (existingUser != null) {
            // User already exists
            return false;
        }
        userDao.addUser(user);
        return true;
    }

    /**
     * Retrieves a list of all users registered in the system.
     *
     * @return A list of User objects representing all registered users.
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Retrieves user information based on the username.
     *
     * @param username The username of the user to retrieve information for.
     * @return The User object representing the user with the specified
     * username.
     */
    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * Retrieves a list of all retailers registered in the system.
     *
     * @return A list of User objects representing all registered retailers.
     */
    @Override
    public List<User> getAllRetailers() {
        return userDao.getAllUsersByType(UserType.RETAIL);
    }

    /**
     * Retrieves the username based on the user ID.
     *
     * @param userid The ID of the user for whom to retrieve the username.
     * @return The username corresponding to the specified user ID.
     */
    @Override
    public String getUserNameByUserID(int userid) {
        User user = userDao.getUserById(userid);
        return user.getUserName();
    }

}
