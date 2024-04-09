package util;

/**
 * Enumerates the possible results of a login attempt. This is used to specify
 * the outcome of authentication processes within the system.
 *
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
public enum LoginResult {
    /**
     * Indicates that the login attempt was successful.
     */
    SUCCESS,
    /**
     * Indicates that the login attempt failed because the specified user was
     * not found.
     */
    USER_NOT_FOUND,
    /**
     * Indicates that the login attempt failed due to an invalid password being
     * supplied.
     */
    INVALID_PASSWORD,
    /**
     * Indicates that an unspecified error occurred during the login attempt.
     * This result can be used for errors that do not fit into the other
     * categories, such as database connectivity issues.
     */
    ERROR
}
