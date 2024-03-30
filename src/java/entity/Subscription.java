package entity;

import util.ContactType;
import util.PreferenceType;

public class Subscription {

    private int id;
    private User user;
    private ContactType contactType;
    private PreferenceType preferenceType;

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

}
