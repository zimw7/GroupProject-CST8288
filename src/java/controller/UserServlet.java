package controller;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import service.UserService;
import service.impl.UserServiceImpl;
import util.LoginResult;

@WebServlet("/index")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

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
