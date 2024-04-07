package entity;

import util.ContactType;
import util.PreferenceType;

public class Subscription {

    private int id;
    private int userID;
    private ContactType contactType;
    private PreferenceType preferenceType;
    private String retailerUsername;

    public Subscription() {
    }

    public Subscription(int id, int userID, ContactType contactType, PreferenceType preferenceType, String retailerUsername) {
        this.id = id;
        this.userID = userID;
        this.contactType = contactType;
        this.preferenceType = preferenceType;
        this.retailerUsername = retailerUsername;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public PreferenceType getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(PreferenceType preferenceType) {
        this.preferenceType = preferenceType;
    }

    public String getRetailerUsername() {
        return retailerUsername;
    }

    public void setRetailerUsername(String retailerUsername) {
        this.retailerUsername = retailerUsername;
    }

}
