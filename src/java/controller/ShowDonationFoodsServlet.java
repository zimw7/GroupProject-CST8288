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

/**
 * Servlet for displaying foods available for donation.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
@WebServlet("/ShowDonationFoodsServlet")
public class ShowDonationFoodsServlet extends HttpServlet {

    private SurplusFoodService surplusFoodService = null;
    private NotificationService notificationService = null;

    /**
     * Initializes the servlet. This method is invoked by the servlet container
     * to indicate to a servlet that the servlet is being placed into service.
     * Here, it initializes {@link SurplusFoodService} and
     * {@link NotificationService} to retrieve foods for donation and user
     * notifications respectively.
     *
     * @throws ServletException if an exception occurs that interrupts the
     * servlet's normal operation.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        this.surplusFoodService = new SurplusFoodServiceImpl();
        this.notificationService = new NotificationServiceImpl();

    }

    /**
     * Handles the HTTP GET requests.
     *
     * @param request The servlet request, containing user session information.
     * @param response The servlet response, used for forwarding to the charity
     * dashboard view.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
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
