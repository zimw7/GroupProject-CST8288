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
 *
 * @author liuma
 */
@WebServlet("/PurchaseSurplusServlet")
public class PurchaseSurplusServlet extends HttpServlet {
    private SurplusFoodService surplusFoodService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        surplusFoodService = new SurplusFoodServiceImpl();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodID = Integer.parseInt(request.getParameter("foodID"));
        int quantity = Integer.parseInt(request.getParameter("quantity")); 
         
        try{
            surplusFoodService.updateSurplusQuantity(foodID, quantity);
            
            request.setAttribute("successMessage", "Check out successful!");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Check out unsuccessful, please try again.");
        }

        List<SurplusFood> foodList = surplusFoodService.getAllSurplusFood();
        request.setAttribute("surplusFood", foodList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/consumer_dashboard.jsp"); 
        dispatcher.forward(request, response);
    }
}
