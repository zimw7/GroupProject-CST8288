
package entity;

import util.UserType;

public class User {

    private int id;
    private String userName;
    private String password;
    private UserType userType;
    private String phoneNumber;
    private String email;
    private boolean isSubscribed;

    public User() {
    }

    public User(int id, String userName, String password, UserType userType, String phoneNumber, String email, boolean isSubscribed) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isSubscribed = isSubscribed;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }
    
    
}
