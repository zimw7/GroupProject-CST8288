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

@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {

    private SurplusFoodService surplusFoodService = null;
    private NotificationService notificationService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        this.surplusFoodService = new SurplusFoodServiceImpl();
        this.notificationService = new NotificationServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<SurplusFood> surplusfoods = null;
        surplusfoods = surplusFoodService.getSurplusFoodsForSale();
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
