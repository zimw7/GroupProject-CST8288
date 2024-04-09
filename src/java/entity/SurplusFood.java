package entity;

/**
 * Represents surplus food within the Food Waste Reduction Platform. This entity extends the Food class,
 * adding specific attributes relevant to managing surplus food items, such as discount rates and donation flags.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To encapsulate and manage information specific to surplus food items, facilitating
 *          the efficient redistribution of surplus food through sales or donations, thus contributing
 *          to the reduction of food waste.
 */
public class SurplusFood extends Food { 

    private double discountRate;
    private boolean isForDonation;

    /**
     * Gets the discount rate applied to the surplus food item.
     *
     * @return The discount rate of the surplus food item.
     */
    public double getDiscountRate() {
        return discountRate;
    }

    /**
     * Sets the discount rate for the surplus food item. 
     *
     * @param discountRate The new discount rate to be applied to the surplus food item.
     */
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * Indicates whether the surplus food item is available for donation.
     *
     * @return True if the item is available for donation, false otherwise.
     */
    public boolean isIsForDonation() {
        return isForDonation;
    }

    /**
     * Sets the donation flag for the surplus food item. This flag indicates whether the item is
     * intended for donation rather than sale.
     *
     * @param isForDonation The new state indicating whether the surplus food item is for donation.
     */
    public void setIsForDonation(boolean isForDonation) {
        this.isForDonation = isForDonation;
    }
}
