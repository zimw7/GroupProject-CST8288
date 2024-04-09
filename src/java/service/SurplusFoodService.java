package service;
/**
 * The SurplusFoodService interface provides methods for managing surplus food items.
 * It includes methods for adding, listing, updating, and deleting surplus food items.
 * Additionally, it provides methods for claiming surplus food, retrieving surplus food details,
 * and listing surplus food items for donation or sale.
 * 
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 */
import entity.SurplusFood;
import entity.User;
import java.util.List;

public interface SurplusFoodService {

    /**
     * Retrieves a list of all surplus food items in the system.
     * 
     * @return A list of SurplusFood objects representing all surplus food items.
     */
    List<SurplusFood> getAllSurplusFood();

     /**
     * Adds a new surplus food item to the system.
     * 
     * @param surplusfood The SurplusFood object representing the surplus food item to be added.
     */
    void addSurplusFood(SurplusFood surplusfood);

    /**
     * Deletes a surplus food item from the system based on the food ID.
     * 
     * @param foodId The ID of the surplus food item to delete.
     */ 
    void deleteSurplusFood(int foodId);

    /**
     * Claims a quantity of a specific surplus food item.
     * 
     * @param foodId The ID of the surplus food item to claim.
     * @param quantity The quantity to claim.
     * @return true if the claim is successful, false otherwise.
     */
    boolean claimSurplusFood(int foodId, int quantity);

    /**
     * Retrieves a list of surplus food items available for donation.
     * 
     * @return A list of SurplusFood objects available for donation.
     */ 
    List<SurplusFood> getSurplusFoodsForDonation();

     /**
     * Retrieves a list of surplus food items available for sale.
     * 
     * @return A list of SurplusFood objects available for sale.
     */
    List<SurplusFood> getSurplusFoodsForSale();

    /**
     * Retrieves a list of surplus food items added by a specific user.
     * 
     * @param user The user for whom to retrieve surplus food items.
     * @return A list of SurplusFood objects added by the user.
     */
    List<SurplusFood> getSurplusFoodByUser(User user);

    /**
     * Retrieves the details of a specific surplus food item.
     * 
     * @param surplusfoodID The ID of the surplus food item to retrieve.
     * @return The SurplusFood object representing the details of the surplus food item.
     */
    SurplusFood getSurplusFoodDetail(int surplusfoodID);

    /**
     * Updates the details of an existing surplus food item.
     * 
     * @param food The SurplusFood object containing the updated information.
     */
    void updateSurplusFood(SurplusFood food);

    /**
     * Lists a surplus food item for sale by a retailer.
     * 
     * @param food The SurplusFood object to be listed for sale.
     * @param retailer The retailer who lists the surplus food item.
     */
    void listSurplusFood(SurplusFood food, User retailer);

     /**
     * Updates the quantity of a surplus food item.
     * 
     * @param id The ID of the surplus food item to update.
     * @param quantity The new quantity of the surplus food item.
     */
    void updateSurplusQuantity(int id, int quantity);
}
