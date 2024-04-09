package controller;

import entity.SurplusFood;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.NotificationService;
import service.SurplusFoodService;
import service.impl.NotificationServiceImpl;

import service.impl.SurplusFoodServiceImpl;

/**
 * A servlet that handles customer requests.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {

    private SurplusFoodService surplusFoodService = null;
    private NotificationService notificationService = null;

    /**
     * Initializes the servlet. This method is called by the servlet container
     * to indicate to a servlet that the servlet is being placed into service.
     * It initializes instances of {@link SurplusFoodService} and
     * {@link NotificationService} for later use in handling client requests.
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
     * Handles HTTP GET requests from Customers.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<SurplusFood> surplusfoods = null;
        surplusfoods = surplusFoodService.getAllSurplusFood();
        request.setAttribute("surplusFood", surplusfoods);

        List<String> notifications = notificationService.getNotifications(user.getId());
        request.setAttribute("notifications", notifications);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/consumer_dashboard.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
