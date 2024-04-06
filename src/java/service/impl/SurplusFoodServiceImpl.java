package service.impl;

import dao.SubscriptionDao;
import dao.SurplusFoodDao;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.SurplusFoodDaoImpl;
import entity.Subscription;
import entity.SurplusFood;
import entity.User;
import java.util.List;
import service.SurplusFoodService;
import util.ContactType;

public class SurplusFoodServiceImpl implements SurplusFoodService {

    private NotificationServiceImpl notificationService = null;
    private SubscriptionDao subscriptionDao = null;
    private SurplusFoodDao surplusfoodDao = null;

    public SurplusFoodServiceImpl() {
        this.notificationService = new NotificationServiceImpl();
        this.subscriptionDao = new SubscriptionDaoImpl();
        surplusfoodDao = new SurplusFoodDaoImpl();

    }

    @Override
    public List<SurplusFood> getSurplusFoodByUser(User user) {
        return surplusfoodDao.getSurplusFoodByUserID(user.getId());
    }

    public void listSurplusFood(SurplusFood food) {
        List<Subscription> subscriptions = subscriptionDao.getSubscriptionsByPreference(food.getFoodType());

        for (Subscription subscription : subscriptions) {
            User user = subscription.getUser();
            String message = "Available surplus food: " + food.getName();

            notificationService.sendNotification(
                    subscription.getContactType() == ContactType.EMAIL ? user.getEmail() : user.getPhoneNumber(),
                    message,
                    subscription.getContactType()
            );
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
        surplusfoodDao.updateSurplusQuantity ( id, quantity);
    }

}
