/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service.impl;
import dao.SubscriptionDao;
import dao.UserDao;
import dao.impl.SubscriptionDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Subscription;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import util.SubscriptionResult;

import static org.junit.Assert.assertEquals;
import util.ContactType;
import util.PreferenceType;

public class SubscriptionServiceImplTest {

    private SubscriptionDao subscriptionDao;
    private UserDao userDao;
    private SubscriptionServiceImpl subscriptionService;
    private UserServiceImpl testUser;
    
    @Before
    public void setUp() {
        subscriptionDao = new SubscriptionDaoImpl();
        userDao = new UserDaoImpl();
        subscriptionService = new SubscriptionServiceImpl();
        testUser =  new UserServiceImpl();
    }


    @Test
    public void testSubscribe_AlreadySubscribed() {
        // Prepare test data
        User test = testUser.getUserByUsername("JOE DOE");
        Subscription subscription = new Subscription (5,test, ContactType.TEXT, PreferenceType.DAIRY, "SOBEYS");

        // Call the method to be tested
        SubscriptionResult result = subscriptionService.subscribe(subscription);

        // Verify the result
        assertEquals(SubscriptionResult.ALREADY_SUBSCRIBED, result);
    }
}
