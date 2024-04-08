package dao;

import entity.Alert;
import java.util.List;

public interface AlertDao {

    void addAlert(Alert alert);

    List<Alert> getAlertByUserID(int userid);
    
    void deleteAlert(int alertid);
}
