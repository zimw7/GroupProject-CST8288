package entity;

import util.UserType;

/**
 * Represents a user of the Food Waste Reduction Platform. This class encapsulates
 * all the necessary information about users, including their credentials, contact information,
 * and subscription status to notifications.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To serve as a data model for users within the platform, supporting functionalities
 *          such as authentication, contact information management, and subscription preferences.
 */
public class User {

    private int id;
    private String userName;
    private String password;
    private UserType userType;
    private String phoneNumber;
    private String email;
    private boolean isSubscribed;

    /**
     * Default constructor for creating a User instance without setting any initial properties.
     */
    public User() {
    }

    /**
     * Constructs a new User with specified properties. This constructor is useful for creating
     * a User instance when all attributes are known.
     *
     * @param id The user's unique identifier.
     * @param userName The user's username for login and identification.
     * @param password The user's password for authentication.
     * @param userType The type of user (e.g., consumer, retailer, or charity).
     * @param phoneNumber The user's phone number for contact.
     * @param email The user's email address for contact.
     * @param isSubscribed The user's subscription status to notifications.
     */
    public User(int id, String userName, String password, UserType userType, String phoneNumber, String email, boolean isSubscribed) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isSubscribed = isSubscribed;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The new unique identifier for the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The user's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName The new username for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the type of the user.
     *
     * @return The user's type.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the type of the user.
     *
     * @param userType The new user type.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return The user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The new phone number for the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The new email address for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks if the user is subscribed to notifications.
     *
     * @return True if the user is subscribed, false otherwise.
     */
    public boolean isSubscribed() {
        return isSubscribed;
    }

    /**
     * Sets the user's subscription status to notifications.
     *
     * @param isSubscribed The new subscription status of the user.
     */
    public void setIsSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }
}
