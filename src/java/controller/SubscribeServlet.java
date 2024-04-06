package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import entity.Subscription;
import entity.SurplusFood;
import entity.User;
import java.util.List;
import service.SubscriptionService;
import service.SurplusFoodService;
import service.impl.SubscriptionServiceImpl;
import service.impl.SurplusFoodServiceImpl;
import util.ContactType;
import util.PreferenceType;
import util.SubscriptionResult;


@WebServlet(name = "SubscribeServlet", urlPatterns = {"/SubscribeServlet"})
public class SubscribeServlet extends HttpServlet {
    private SubscriptionService subscriptionService; 
    private SurplusFoodService surplusFoodService;

    @Override
    public void init() {
        this.subscriptionService = new SubscriptionServiceImpl(); 
        this.surplusFoodService = new SurplusFoodServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String preferenceType = request.getParameter("preferenceType");
        String contactType = request.getParameter("contactType");
        String retailerUsername = request.getParameter("retailer_username");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        

        if (user != null) {
            Subscription subscription = new Subscription();
            subscription.setUser(user);
            subscription.setPreferenceType(PreferenceType.valueOf(preferenceType));
            subscription.setContactType(ContactType.valueOf(contactType));
            subscription.setRetailerUsername(retailerUsername); 

            

            SubscriptionResult result = subscriptionService.subscribe(subscription);
            if (result == SubscriptionResult.ALREADY_SUBSCRIBED) {
                request.setAttribute("errorMessage", "You've already subscribed to this preference. Thank you for your subscription!");
            } else {
                request.setAttribute("successMessage", "Subscription added successfully!");
            }
            
            List<SurplusFood> surplusfoods = surplusFoodService.getAllSurplusFood();
            request.setAttribute("surplusfoods", surplusfoods);
            
            request.getRequestDispatcher("/views/consumer_dashboard.jsp").forward(request, response);




        } else {
            response.sendRedirect("index.jsp"); 
        }
    }
}