package controller;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import service.UserService;
import service.impl.UserServiceImpl;
import util.LoginResult;

/**
 * Servlet responsible for handling user login functionality. This servlet
 * processes both GET and POST requests; GET requests forward the user to the
 * login page, while POST requests handle the login logic and authentication of
 * users attempting to log in.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
@WebServlet("/index")
public class UserServlet extends HttpServlet {

    /**
     * Handles the HTTP GET request. This method simply forwards the request to
     * the login page (index.jsp).
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST request. This method processes the user login by
     * validating the username and password against the stored credentials. If
     * successful, it redirects the user to the home page. Otherwise, it
     * redirects back to the login page with an appropriate error message.
     *
     * @param request servlet request containing the login credentials
     * @param response servlet response to redirect or forward the user based on
     * the authentication result
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        LoginResult result = userService.login(userName, password);

        switch (result) {
            case SUCCESS:
                User user = userService.getUserByUsername(userName);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/views/home.jsp");
                break;
            case USER_NOT_FOUND:
                request.setAttribute("errorMessage", "Username not found");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case INVALID_PASSWORD:
                request.setAttribute("errorMessage", "Invalid password");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("errorMessage", "An error occurred");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }
}
