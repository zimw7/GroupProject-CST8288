/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service.impl;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import entity.Subscription;
import service.SubscriptionService;
import dao.SubscriptionDao;
import dao.UserDao;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.UserDaoImpl;
import static org.junit.Assert.assertFalse;


public class SubscriptionServiceImplTest {

    private SubscriptionService subscriptionService;
    private SubscriptionDao subscriptionDao;
    private UserDao userDao;

    @Before
    public void setUp() {
        subscriptionDao = new SubscriptionDaoImpl();
        userDao = new UserDaoImpl();
        subscriptionService = new SubscriptionServiceImpl();
        
    }


    @Test
    public void testUnsubscribe() {
        int subscriptionId = 1;
        subscriptionService.unsubscribe(subscriptionId);
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        boolean subscriptionFound = false;
        for (Subscription subscription : subscriptions) {
            if (subscription.getId() == subscriptionId) {
                subscriptionFound = true;
                break;
            }
        }
        assertFalse("Unsubscribed subscription still exists", subscriptionFound);
    }

    @Test
    public void testGetAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        assertNotNull(subscriptions);
    }

    @Test
    public void testGetSubscriptionsByID() {
        int userId = 1; // 假设用户ID为1
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByID(userId);
        assertNotNull(subscriptions);
    }
}
