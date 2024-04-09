package util;

/**
 * Enumerates the different types of food preferences that can be selected by
 * users.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
public enum PreferenceType {
    /**
     * Preference for dairy products, such as milk, cheese, yogurt, and other
     * derivatives of milk.
     */
    DAIRY,
    /**
     * Preference for foods that are perishable and often require refrigeration.
     * This includes fruits, vegetables, meats, and other items that spoil or
     * deteriorate quickly.
     */
    PERISHABLE,
    /**
     * Preference for foods rich in carbohydrates. This category includes
     * breads, grains, pasta, and similar products that provide energy through
     * carbohydrates.
     */
    CARBOHYDRATE,
    /**
     * Preference for beverages, which can range from water and juices to sodas
     * and alcoholic drinks. This category encompasses all liquid consumables.
     */
    DRINK
}
