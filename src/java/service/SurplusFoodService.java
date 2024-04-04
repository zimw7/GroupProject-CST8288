/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.SurplusFood;
import java.util.List;

public interface SurplusFoodService {
        List<SurplusFood> getAllSurplusFood();
        void addSurplusFood(SurplusFood surplusfood);
        void deleteSurplusFood(int surplusfoodID);   
        SurplusFood getSurplusFoodDetail(int surplusfoodID);
        void updateSurplusFood(SurplusFood food);
}
