package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.UserType;

/**
 * Servlet responsible for handling user registration requests.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    /**
     * Initializes the servlet. This method is called by the servlet container
     * to indicate that the servlet is being placed into service.
     *
     * @throws ServletException if an exception occurs that interrupts the
     * servlet's normal operation.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl();
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests by attempting to register a new user with the
     * details provided in the request.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String userTypeString = request.getParameter("userType");

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);

        if (userTypeString != null && !userTypeString.isEmpty()) {
            newUser.setUserType(UserType.valueOf(userTypeString));
        } else {
            request.setAttribute("errorMessage", "Please select a user type.");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        boolean registrationResult = userService.register(newUser);
        if (registrationResult) {
            request.setAttribute("successMessage", "Registration successful! You can now login.");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "User already exists. Please try a different username.");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }

    }
}
