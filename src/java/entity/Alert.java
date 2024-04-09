package entity;

/**
 * Represents an alert within the Food Waste Reduction Platform. Alerts are used
 * to notify users about various events or opportunities, such as availability of
 * surplus food that matches their preferences.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To encapsulate information about notifications sent to users, facilitating
 *          communication and engagement within the platform.
 */
public class Alert {

    private int id;
    private int userID;
    private String message;

    /**
     * Gets the ID of the Alert.
     *
     * @return The ID of the Alert.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Alert.
     *
     * @param id The new ID of the Alert.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user ID associated with the Alert.
     *
     * @return The user ID associated with the Alert.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID associated with the Alert.
     *
     * @param userID The new user ID associated with the Alert.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the message of the Alert.
     *
     * @return The message of the Alert.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the Alert.
     *
     * @param message The message of the Alert.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
