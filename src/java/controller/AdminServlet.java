package controller;

import entity.Food;
import entity.Subscription;
import entity.SurplusFood;
import entity.User;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.FoodService;
import service.SubscriptionService;
import service.SurplusFoodService;
import service.UserService;
import service.impl.FoodServiceImpl;
import service.impl.SubscriptionServiceImpl;
import service.impl.SurplusFoodServiceImpl;
import service.impl.UserServiceImpl;
/**
 * Servlet implementation class AdminServlet that can view
 * user, food, surplus food, and subscription lists within the admin dashboard.
 * This servlet supports actions like logging in as an admin, viewing users, foods, surplus foods,
 * and subscriptions.
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet", "*.admindo"})
public class AdminServlet extends HttpServlet {

     /**
     * Redirects HTTP GET requests to the doPost method.
     * This method ensures that both GET and POST requests are handled in the same manner.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles HTTP POST requests by dynamically invoking methods based on the request URI.
     * It extracts a method name from the URI and uses reflection to call a corresponding method.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * Processes login requests for the admin dashboard. It checks the credentials against
     * hardcoded values and redirects the user accordingly.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if (userName.equals("admin") && password.equals("qwer1234")) {
            response.sendRedirect(request.getContextPath() + "/views/admin/admin_dashboard.jsp");
            return;
        }
        request.setAttribute("errorMessage", "login failed");
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
    
    /**
     * Retrieves all users from the database and forwards them to the admin user management page.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userservice = new UserServiceImpl();
        List<User> users = null;

        users = userservice.getAllUsers();

        request.setAttribute("users", users);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/users.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Retrieves all foods from the database and forwards them to the admin food management page.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void getFoods(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FoodService foodservice = new FoodServiceImpl();
        List<Food> foods = null;

        foods = foodservice.getAllFoods();

        request.setAttribute("foods", foods);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/foods.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * Retrieves all surplus foods from the database and forwards them to the admin surplus food management page.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void getSurplusFoods(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SurplusFoodService surplusFoodService = new SurplusFoodServiceImpl();
        List<SurplusFood> surplusfoods = null;

        surplusfoods = surplusFoodService.getAllSurplusFood();

        request.setAttribute("surplusfoods", surplusfoods);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/surplusfoods.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * Retrieves all subscriptions from the database and forwards them to the admin subscription management page.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void getSubscriptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubscriptionService subscriptionFoodService = new SubscriptionServiceImpl();
        List<Subscription> subscriptions = null;

        subscriptions = subscriptionFoodService.getAllSubscriptions();

        request.setAttribute("subscriptions", subscriptions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/subscriptions.jsp");
        dispatcher.forward(request, response);
    }
}
