package dao;

import entity.Alert;
import java.util.List;

/**
 * The AlertDao interface provides a contract for alert management operations
 * within the Food Waste Reduction Platform. It allows for the addition, retrieval,
 * and deletion of alert entities based on user interaction and system requirements.
 * 
 * @author Zimeng Wang, Mengying Liu, Wenxin Li
 * @date Apr 5, 2024
 * @labSection CST8288 - 012
 * @purpose To define the operations required for managing alerts related to surplus food notifications.
 *          Alerts are generated based on user subscriptions and preferences, facilitating
 *          the efficient redistribution of surplus food by notifying interested users.
 */
public interface AlertDao {

    /**
     * Adds a new alert to the database.
     * 
     * @param alert The alert entity to be added.
     */
    void addAlert(Alert alert);

    /**
     * Retrieves a list of alerts for a specific user based on their user ID.
     * 
     * @param userid The ID of the user for whom alerts are to be retrieved.
     * @return A list of Alert entities associated with the specified user.
     */
    List<Alert> getAlertByUserID(int userid);
    
    /**
     * Deletes an alert from the database based on its ID.
     * 
     * @param alertid The ID of the alert to be deleted.
     */
    void deleteAlert(int alertid);
}
