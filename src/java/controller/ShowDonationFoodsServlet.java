package controller;

import entity.SurplusFood;
import entity.User;
import service.SurplusFoodService;
import service.impl.SurplusFoodServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import service.NotificationService;
import service.impl.NotificationServiceImpl;

@WebServlet("/ShowDonationFoodsServlet")
public class ShowDonationFoodsServlet extends HttpServlet {

    private SurplusFoodService surplusFoodService = null;
    private NotificationService notificationService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        this.surplusFoodService = new SurplusFoodServiceImpl();
        this.notificationService = new NotificationServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        System.out.println("ShowDonationFoodsServlet is called");
        List<SurplusFood> foodsForDonation = surplusFoodService.getSurplusFoodsForDonation();
        System.out.println(foodsForDonation.size());

        System.out.println("Foods for donation:");
        if (foodsForDonation != null) {
            for (SurplusFood food : foodsForDonation) {
                System.out.println(food.getName() + " - Quantity: " + food.getQuantity());
            }
        } else {
            System.out.println("foodsForDonation is null");
        }

        request.setAttribute("foodsForDonation", foodsForDonation);

        List<String> notifications = notificationService.getNotifications(user.getId());
        request.setAttribute("notifications", notifications);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity_dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
