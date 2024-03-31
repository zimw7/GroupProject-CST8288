package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl(); 
    }

    @Override
    public boolean login(String username, String password) {
        User user = userDao.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

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

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers(); 
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
