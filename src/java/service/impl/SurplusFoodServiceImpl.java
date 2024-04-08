package service.impl;

import dao.SubscriptionDao;
import dao.SurplusFoodDao;
import dao.UserDao;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.SurplusFoodDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Subscription;
import entity.SurplusFood;
import entity.User;
import java.util.List;
import service.SurplusFoodService;
import util.UserType;


public class SurplusFoodServiceImpl implements SurplusFoodService {

    private NotificationServiceImpl notificationService = null;
    private SubscriptionDao subscriptionDao = null;
    private SurplusFoodDao surplusfoodDao = null;
    private UserDao userDao = null;

    public SurplusFoodServiceImpl() {
        this.notificationService = new NotificationServiceImpl();
        this.subscriptionDao = new SubscriptionDaoImpl();
        this.userDao = new UserDaoImpl();
        surplusfoodDao = new SurplusFoodDaoImpl();
    }

     
    @Override
    public List<SurplusFood> getSurplusFoodByUser(User user) {
        return surplusfoodDao.getSurplusFoodByUserID(user.getId());
    }


    public void listSurplusFood(SurplusFood food, User retailer) {
        List<Subscription> subscriptions = subscriptionDao.getSubscriptionsByPreference(food.getFoodType(), retailer.getUserName());

        for (Subscription subscription : subscriptions) {
            int userID = subscription.getUserID();
            User user = userDao.getUserById(userID);
            if((user.getUserType() == UserType.CUSTOMER && !food.isIsForDonation()) || 
                    (user.getUserType() == UserType.CHARITY && food.isIsForDonation())) {
                String message = "Available surplus food: " + food.getName() + " at " + retailer.getUserName();

                notificationService.sendNotification(
                        user,
                        message,
                        subscription.getContactType()
                );
            }
        }
    }

    @Override
    public List<SurplusFood> getAllSurplusFood() {
        return surplusfoodDao.getAllSurplusFoods();
    }

    @Override
    public void addSurplusFood(SurplusFood surplusfood) {
        surplusfoodDao.addSurplusFood(surplusfood);
    }
    
    @Override
    public void deleteSurplusFood(int surplusfoodID) {
       surplusfoodDao.deleteSurplusFood(surplusfoodID);
    }

    @Override
    public SurplusFood getSurplusFoodDetail(int surplusfoodID) {
        return surplusfoodDao.getSurplusFoodById(surplusfoodID);
    }

    @Override
    public void updateSurplusFood(SurplusFood surplusfood) {
        surplusfoodDao.updateSurplusFood(surplusfood);
    }
    
    @Override
    public void updateSurplusQuantity (int id, int quantity){
        SurplusFood food = surplusfoodDao.getSurplusFoodById(id);
        int newQuantity = food.getQuantity() - quantity;
        surplusfoodDao.updateSurplusQuantity ( id, newQuantity);
    }


    
    @Override
    public boolean claimSurplusFood(int foodId, int quantity) {
        SurplusFood food = surplusfoodDao.getSurplusFoodById(foodId);
        if (food != null && food.getQuantity() >= quantity) { 
            int newQuantity = food.getQuantity() - quantity;
            if (newQuantity > 0) {
                surplusfoodDao.updateSurplusQuantity(foodId, newQuantity);
            } else {
                surplusfoodDao.deleteSurplusFood(foodId); 
            }
            return true; 
        }
        return false; 
    }

    @Override
    public List<SurplusFood> getSurplusFoodsForDonation() {
         return surplusfoodDao.getSurplusFoodsForDonation();

    }

    @Override
    public List<SurplusFood> getSurplusFoodsForSale() {
        return surplusfoodDao.getSurplusFoodsForSale();
    }
    
    
}
