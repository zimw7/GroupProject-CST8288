package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import entity.Subscription;
import entity.User;
import service.SubscriptionService;
import service.impl.SubscriptionServiceImpl;
import util.ContactType;
import util.PreferenceType;
import util.SubscriptionResult;


@WebServlet(name = "SubscribeServlet", urlPatterns = {"/SubscribeServlet"})
public class SubscribeServlet extends HttpServlet {
    private SubscriptionService subscriptionService; 

    @Override
    public void init() {
        this.subscriptionService = new SubscriptionServiceImpl(); 
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
            request.getRequestDispatcher("/views/consumer_dashboard.jsp").forward(request, response);




        } else {
            response.sendRedirect("index.jsp"); 
        }
    }
}