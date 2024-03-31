package service;

import entity.User;
import java.util.List;

public interface UserService {
    boolean login(String username, String password);
    boolean register(User user); 
    List<User> getAllUsers(); 
    User getUserByUsername(String username);
}
