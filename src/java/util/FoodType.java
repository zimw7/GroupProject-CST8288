package util;

/**
 * Enum representing different types of food items. This classification can be
 * used to categorize food inventory or preferences for subscriptions and
 * dietary restrictions.
 *
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
public enum FoodType {
    /**
     * Represents dairy products, which include milk, cheese, yogurt, and other
     * milk-based products.
     */
    DAIRY,
    /**
     * Represents perishable goods that spoil or decay in a short period,
     * requiring refrigeration or special storage.
     */
    PERISHABLE,
    /**
     * Represents foods high in carbohydrates, such as bread, pasta, grains, and
     * cereals.
     */
    CARBOHYDRATE,
    /**
     * Represents beverages, including both alcoholic and non-alcoholic drinks.
     */
    DRINK
}
