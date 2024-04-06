package service;

import entity.SurplusFood;
import java.util.List;

public interface SurplusFoodService {
        List<SurplusFood> getAllSurplusFood();
        void addSurplusFood(SurplusFood surplusfood);
        void deleteSurplusFood(SurplusFood surplusfood); 
        void deleteSurplusFood(int foodId);
        void updateFoodQuantity(int foodID, int quantity);
        boolean claimSurplusFood(int foodId, int quantity); 
        List<SurplusFood> getSurplusFoodsForDonation();
}
