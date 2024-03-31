package entity;

import util.FoodType;

public class SurplusFood extends Food { 

    private FoodType type;
    private double discountRate;
    private boolean isForDonation;
    
     public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
    
    public boolean isForDonation() {
        return isForDonation;
    }

    public void setForDonation(boolean isForDonation) {
        this.isForDonation = isForDonation;
    }

}
