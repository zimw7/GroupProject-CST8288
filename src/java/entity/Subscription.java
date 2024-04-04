package entity;

import util.ContactType;
import util.PreferenceType;

public class Subscription {

    private int id;
    private User user;
    private ContactType contactType;
    private PreferenceType preferenceType;
    private String retailerUsername;

    public Subscription() {
    }

    public Subscription(int id, User user, ContactType contactType, PreferenceType preferenceType, String retailerUsername) {
        this.id = id;
        this.user = user;
        this.contactType = contactType;
        this.preferenceType = preferenceType;
        this.retailerUsername = retailerUsername;
    }

     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
