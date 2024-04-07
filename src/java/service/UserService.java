package service;

import entity.User;
import java.util.List;
import util.LoginResult;

public interface UserService {
    LoginResult login(String username, String password);
    boolean register(User user);
    List<User> getAllUsers();
    User getUserByUsername(String username);
    List<User> getAllRetailers();
}
