package service.impl;
/**
 * This class implements SurplusFoodService interface, which concretes all
 * abstract methods to implement: add, retrieve, update, delete, and list-all
 * functions for surplusFood entity via DAO .
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
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

    /**
     * Retrieves a list of all surplus food items in the system.
     *
     * @return A list of SurplusFood objects representing all surplus food
     * items.
     */
    @Override
    public List<SurplusFood> getAllSurplusFood() {
        return surplusfoodDao.getAllSurplusFoods();
    }

    /**
     * Adds a new surplus food item to the system.
     *
     * @param surplusfood The SurplusFood object representing the surplus food
     * item to be added.
     */
    @Override
    public void addSurplusFood(SurplusFood surplusfood) {
        surplusfoodDao.addSurplusFood(surplusfood);
    }

    /**
     * Deletes a surplus food item from the system based on the food ID.
     *
     * @param surplusfoodID The ID of the surplus food item to delete.
     */
    @Override
    public void deleteSurplusFood(int surplusfoodID) {
        surplusfoodDao.deleteSurplusFood(surplusfoodID);
    }

    /**
     * Claims a quantity of a specific surplus food item.
     *
     * @param foodId The ID of the surplus food item to claim.
     * @param quantity The quantity to claim.
     * @return true if the claim is successful, false otherwise.
     */
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

    /**
     * Retrieves a list of surplus food items available for donation.
     *
     * @return A list of SurplusFood objects available for donation.
     */
    @Override
    public List<SurplusFood> getSurplusFoodsForDonation() {
        return surplusfoodDao.getSurplusFoodsForDonation();

    }

    /**
     * Retrieves a list of surplus food items available for sale.
     *
     * @return A list of SurplusFood objects available for sale.
     */
    @Override
    public List<SurplusFood> getSurplusFoodsForSale() {
        return surplusfoodDao.getSurplusFoodsForSale();
    }

    /**
     * Retrieves a list of surplus food items added by a specific user.
     *
     * @param user The user for whom to retrieve surplus food items.
     * @return A list of SurplusFood objects added by the user.
     */
    @Override
    public List<SurplusFood> getSurplusFoodByUser(User user) {
        return surplusfoodDao.getSurplusFoodByUserID(user.getId());
    }

    /**
     * Retrieves the details of a specific surplus food item.
     *
     * @param surplusfoodID The ID of the surplus food item to retrieve.
     * @return The SurplusFood object representing the details of the surplus
     * food item.
     */
    @Override
    public SurplusFood getSurplusFoodDetail(int surplusfoodID) {
        return surplusfoodDao.getSurplusFoodById(surplusfoodID);
    }

    /**
     * Updates the details of an existing surplus food item.
     *
     * @param surplusfood The SurplusFood object containing the updated
     * information.
     */
    @Override
    public void updateSurplusFood(SurplusFood surplusfood) {
        surplusfoodDao.updateSurplusFood(surplusfood);
    }

    /**
     * Lists a surplus food item for sale by a retailer.
     *
     * @param food The SurplusFood object to be listed for sale.
     * @param retailer The retailer who lists the surplus food item.
     */
    public void listSurplusFood(SurplusFood food, User retailer) {
        List<Subscription> subscriptions = subscriptionDao.getSubscriptionsByPreference(food.getFoodType(), retailer.getUserName());

        for (Subscription subscription : subscriptions) {
            int userID = subscription.getUserID();
            User user = userDao.getUserById(userID);
            if ((user.getUserType() == UserType.CUSTOMER)
                    || (user.getUserType() == UserType.CHARITY && food.isIsForDonation())) {
                String message = "Available surplus food: " + food.getName() + " at " + retailer.getUserName();

                notificationService.sendNotification(
                        user,
                        message,
                        subscription.getContactType()
                );
            }
        }
    }

    /**
     * Updates the quantity of a surplus food item.
     *
     * @param id The ID of the surplus food item to update.
     * @param quantity The new quantity of the surplus food item.
     */
    @Override
    public void updateSurplusQuantity(int id, int quantity) {
        SurplusFood food = surplusfoodDao.getSurplusFoodById(id);
        int newQuantity = food.getQuantity() - quantity;
        surplusfoodDao.updateSurplusQuantity(id, newQuantity);
    }
}
