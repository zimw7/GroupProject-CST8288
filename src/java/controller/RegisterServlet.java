
package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
       
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password); 
        newUser.setEmail(email);

        boolean registrationSuccess = userService.register(newUser);

        if (registrationSuccess) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
