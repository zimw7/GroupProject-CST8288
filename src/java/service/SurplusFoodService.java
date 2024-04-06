package service;

import entity.SurplusFood;
import entity.User;
import java.util.List;

public interface SurplusFoodService {
    List<SurplusFood> getAllSurplusFood();
    void addSurplusFood(SurplusFood surplusfood);
    void deleteSurplusFood(int foodId); 
    void updateFoodQuantity(int foodID, int quantity); 
    boolean claimSurplusFood(int foodId, int quantity);
    List<SurplusFood> getSurplusFoodsForDonation();
    List<SurplusFood> getSurplusFoodByUser(User user);
    SurplusFood getSurplusFoodDetail(int surplusfoodID);
    void updateSurplusFood(SurplusFood food);
    
}
