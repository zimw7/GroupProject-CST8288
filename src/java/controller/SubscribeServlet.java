package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import entity.Subscription;
import entity.User;
import java.util.List;
import service.SubscriptionService;
import service.UserService;
import service.impl.SubscriptionServiceImpl;
import service.impl.UserServiceImpl;
import util.ContactType;
import util.PreferenceType;
import util.SubscriptionResult;

@WebServlet(name = "SubscribeServlet", urlPatterns = {"/SubscribeServlet"})
public class SubscribeServlet extends HttpServlet {
    private SubscriptionService subscriptionService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.subscriptionService = new SubscriptionServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> retailers = userService.getAllRetailers();
        for(User retailer : retailers) {
            System.out.println("Retailer username: " + retailer.getUserName()); 
        }
        request.setAttribute("retailers", retailers);
        request.getRequestDispatcher("/views/subscribe.jsp").forward(request, response);
    }


    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String preferenceType = request.getParameter("preferenceType");
    String contactType = request.getParameter("contactType");
    String retailerUsername = request.getParameter("retailer_username");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null) {
        if (contactType == null || contactType.isEmpty()) {
           
            request.setAttribute("errorMessage", "Contact Type must be selected.");
            
            List<User> retailers = userService.getAllRetailers();
            request.setAttribute("retailers", retailers);
            request.getRequestDispatcher("/views/subscribe.jsp").forward(request, response);
            return; 
        }

        Subscription subscription = new Subscription();
        subscription.setUserID(user.getId());
        subscription.setPreferenceType(PreferenceType.valueOf(preferenceType));
        subscription.setContactType(ContactType.valueOf(contactType));
        subscription.setRetailerUsername(retailerUsername);

        SubscriptionResult result = subscriptionService.subscribe(subscription);

        String message;
        if (result == SubscriptionResult.ALREADY_SUBSCRIBED) {
            message = "You've already subscribed to this preference. Thank you for your subscription!";
            request.setAttribute("errorMessage", message);
        } else {
            message = "Subscription added successfully!";
            request.setAttribute("successMessage", message);
        }

        List<User> retailers = userService.getAllRetailers();
        request.setAttribute("retailers", retailers);
        request.getRequestDispatcher("/views/subscribe.jsp").forward(request, response);
    } else {
        response.sendRedirect("index.jsp");
    }
}

    
    
}
