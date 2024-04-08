package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import entity.Subscription;
import entity.User;
import java.lang.reflect.Method;
import java.util.List;
import service.SubscriptionService;
import service.UserService;
import service.impl.SubscriptionServiceImpl;
import service.impl.UserServiceImpl;
import util.ContactType;
import util.PreferenceType;
import util.SubscriptionResult;

@WebServlet(name = "SubscribeServlet", urlPatterns = {"/SubscribeServlet", "*.subdo"})
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        List<User> retailers = userService.getAllRetailers();
        for (User retailer : retailers) {
            System.out.println("Retailer username: " + retailer.getUserName());
        }
        request.setAttribute("retailers", retailers);
        
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByID(user.getId());
        request.setAttribute("subscriptions", subscriptions);
        
        request.getRequestDispatcher("/views/subscribe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();

        String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        System.out.println(methodName);
        Method method = null;
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("method error" + methodName, e);
        }
    }
    
    private void subscribe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String preferenceType = request.getParameter("preferenceType");
        String contactType = request.getParameter("contactType");
        String retailerUsername = request.getParameter("retailer_username");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (contactType == null || contactType.isEmpty()) {

                request.setAttribute("errorMessage", "Contact Type must be selected.");

                doGet(request, response);
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

            doGet(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
    
    private void unsubscribe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int subscriptionID = Integer.parseInt(request.getParameter("selectOption"));
        subscriptionService.unsubscribe(subscriptionID);
        request.setAttribute("successMessage", "Unsubscribe successful!");
        doGet(request, response);
    }
}
