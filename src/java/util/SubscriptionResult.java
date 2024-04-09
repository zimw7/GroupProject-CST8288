package util;

/**
 * Enumerates possible outcomes of a subscription attempt. This allows the
 * application to provide feedback on the result of trying to subscribe to a
 * service or product.
 *
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
public enum SubscriptionResult {
     /**
     * Indicates that the subscription was successfully created. This result means the user has been
     * successfully added to the subscription list for the requested service or product.
     */
    SUCCESS,
    /**
     * Indicates that the user is already subscribed to the service or product. This result prevents
     * duplicate subscriptions and informs the user that they are already receiving the subscribed content.
     */
    ALREADY_SUBSCRIBED
}
