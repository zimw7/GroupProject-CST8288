package util;

/**
 * Enumerates the different types of users within the system. This
 * classification helps in distinguishing between various roles that users can
 * have, affecting their permissions, available features, and interactions
 * within the application.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
public enum UserType {
    /**
     * Represents users that are retailers. Retailers may have the ability to
     * list products, manage inventory, and view orders.
     */
    RETAIL,
    /**
     * Represents end consumers or customers. Customers typically have access to
     * place orders, view products, and manage their personal account settings.
     */
    CUSTOMER,
    /**
     * Represents charity organizations or users. Charities may have access to
     * claim donations, view available surplus food items, and interact with
     * retailers for food rescue operations.
     */
    CHARITY
}
