package controller;

import entity.SurplusFood;
import service.SurplusFoodService;
import service.impl.SurplusFoodServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowDonationFoodsServlet")
public class ShowDonationFoodsServlet extends HttpServlet {
    private SurplusFoodService surplusFoodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.surplusFoodService = new SurplusFoodServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("ShowDonationFoodsServlet is called");
        List<SurplusFood> foodsForDonation = surplusFoodService.getSurplusFoodsForDonation();
        System.out.println(foodsForDonation.size());
        
        
        System.out.println("Foods for donation:");
        if (foodsForDonation != null) {
            for (SurplusFood food : foodsForDonation) {
                System.out.println(food.getName() + " - Quantity: " + food.getQuantity());
            }
        } else {
            System.out.println("foodsForDonation is null");
        }
        
        
        
        request.setAttribute("foodsForDonation", foodsForDonation);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity_dashboard.jsp"); 
        dispatcher.forward(request, response);
    }
}
