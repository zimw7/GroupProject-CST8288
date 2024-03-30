package entity;

public class SurplusFood extends Food { 

    private double discountRate;
    private boolean isForDonation;
    
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
