package entity;

import util.FoodType;

public class SurplusFood extends Food { 

    private double discountRate;
    private boolean isForDonation;

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public boolean isIsForDonation() {
        return isForDonation;
    }

    public void setIsForDonation(boolean isForDonation) {
        this.isForDonation = isForDonation;
    }
    

}
