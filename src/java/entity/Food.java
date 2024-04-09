package entity;

import java.util.Date;
import util.FoodType;

/**
 * Represents a food item within the Food Waste Reduction Platform. This entity contains
 * information about a specific food item, including its name, quantity, price, type,
 * expiration date, and the ID of the user (retailer or donor) who listed the item.
 *
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To encapsulate and manage information related to food items, facilitating
 *          the tracking, listing, and redistribution of surplus food to reduce waste.
 */
public class Food {

    private int id;
    private String name;
    private int quantity;
    private double price;
    private FoodType foodType;
    private Date expirationDate;
    private int userID;

        /**
     * Gets the unique identifier of the food item.
     *
     * @return The unique identifier of the food item.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the food item.
     *
     * @param id The new unique identifier for the food item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the food item.
     *
     * @return The name of the food item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food item.
     *
     * @param name The new name for the food item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity of the food item.
     *
     * @return The quantity of the food item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the food item.
     *
     * @param quantity The new quantity for the food item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price of the food item.
     *
     * @return The price of the food item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the food item.
     *
     * @param price The new price for the food item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the type of the food item.
     *
     * @return The food type of the food item.
     */
    public FoodType getFoodType() {
        return foodType;
    }

    /**
     * Sets the type of the food item.
     *
     * @param foodType The new food type for the food item.
     */
    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    /**
     * Gets the expiration date of the food item.
     *
     * @return The expiration date of the food item.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the food item.
     *
     * @param expirationDate The new expiration date for the food item.
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the user ID of the food item's owner.
     *
     * @return The user ID of the food item's owner.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID of the food item's owner.
     *
     * @param userID The new user ID for the food item's owner.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

}
