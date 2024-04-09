/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.SurplusFood;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.SurplusFoodService;
import service.impl.SurplusFoodServiceImpl;

/**
 * Servlet responsible for handling requests to purchase surplus food items.
 *
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
@WebServlet("/PurchaseSurplusServlet")
public class PurchaseSurplusServlet extends HttpServlet {

    private SurplusFoodService surplusFoodService;

    /**
     * Initializes the servlet. This method is called by the servlet container
     * to indicate to a servlet
     *
     * @throws ServletException if an exception occurs that interrupts the
     * servlet's normal operation.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        surplusFoodService = new SurplusFoodServiceImpl();
    }

    /**
     * Handles HTTP POST requests for purchasing surplus food items.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodID = Integer.parseInt(request.getParameter("foodID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            surplusFoodService.updateSurplusQuantity(foodID, quantity);

            request.setAttribute("successCheckout", "Check out successful!");
        } catch (Exception e) {
            request.setAttribute("errorCheckout", "Check out unsuccessful, please try again.");
        }

        List<SurplusFood> foodList = surplusFoodService.getAllSurplusFood();
        request.setAttribute("surplusFood", foodList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/consumer_dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
