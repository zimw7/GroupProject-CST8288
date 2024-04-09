package entity;

import util.ContactType;
import util.PreferenceType;

/**
 * Represents a user's subscription within the Food Waste Reduction Platform. Subscriptions
 * are used to notify users about new listings that match their preferences, including the
 * type of food they are interested in and their preferred retailers.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To manage user preferences for receiving alerts about surplus food, enabling
 *          personalized notifications based on food type, retailer, and contact preferences.
 */
public class Subscription {

    private int id;
    private int userID;
    private ContactType contactType;
    private PreferenceType preferenceType;
    private String retailerUsername;

    /**
     * Default constructor.
     */
    public Subscription() {
    }

    /**
     * Constructs a Subscription with specified details.
     * 
     * @param id Unique identifier for the subscription.
     * @param userID The user's ID to whom the subscription belongs.
     * @param contactType The preferred method of contact for receiving alerts.
     * @param preferenceType The type of food preference.
     * @param retailerUsername The username of the preferred retailer.
     */
    public Subscription(int id, int userID, ContactType contactType, PreferenceType preferenceType, String retailerUsername) {
        this.id = id;
        this.userID = userID;
        this.contactType = contactType;
        this.preferenceType = preferenceType;
        this.retailerUsername = retailerUsername;
    }

     /**
     * Gets the user ID associated with this subscription.
     *
     * @return The user ID associated with this subscription.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID for this subscription. 
     *
     * @param userID The new user ID to associate with this subscription.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the ID of this subscription. 
     *
     * @return The ID of this subscription.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID for this subscription. 
     *
     * @param id The new ID for this subscription.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the contact type for this subscription. 
     *
     * @return The contact type for this subscription.
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * Sets the contact type for this subscription. 
     *
     * @param contactType The new contact type for this subscription.
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * Gets the preference type for this subscription. 
     *
     * @return The preference type for this subscription.
     */
    public PreferenceType getPreferenceType() {
        return preferenceType;
    }

    /**
     * Sets the preference type for this subscription. 
     * @param preferenceType The new preference type for this subscription.
     */
    public void setPreferenceType(PreferenceType preferenceType) {
        this.preferenceType = preferenceType;
    }

    /**
     * Gets the retailer username associated with this subscription. This username identifies
     * the retailer or donor from whom the user prefers to receive or purchase surplus food.
     *
     * @return The retailer username associated with this subscription.
     */
    public String getRetailerUsername() {
        return retailerUsername;
    }

    /**
     * Sets the retailer username for this subscription. This setting identifies the retailer
     * or donor from whom the user prefers to receive or purchase surplus food.
     *
     * @param retailerUsername The new retailer username to associate with this subscription.
     */
    public void setRetailerUsername(String retailerUsername) {
        this.retailerUsername = retailerUsername;
    }

}
