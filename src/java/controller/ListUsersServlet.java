package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet("/listUsers")
public class ListUsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        List<User> users = userService.getAllUsers();

        request.setAttribute("users", users);

        request.getRequestDispatcher("/listUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        doGet(request, response);
    }
}
