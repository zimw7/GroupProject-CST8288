package controller;

import entity.SurplusFood;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import service.SurplusFoodService;
import service.impl.SurplusFoodServiceImpl;

@WebServlet("/ClaimFoodServlet")
public class ClaimFoodServlet extends HttpServlet {
    private SurplusFoodService surplusFoodService;

    @Override
    public void init() throws ServletException {
        super.init();
        surplusFoodService = new SurplusFoodServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int quantityToClaim = Integer.parseInt(request.getParameter("quantity")); 
        boolean claimSuccess = surplusFoodService.claimSurplusFood(foodId, quantityToClaim); 

        if (claimSuccess) {
            request.setAttribute("claimSuccess", "Food item claimed successfully.");
        } else {
            request.setAttribute("claimError", "Failed to claim the food item. It may no longer be available.");
        }

        
        List<SurplusFood> foodsForDonation = surplusFoodService.getSurplusFoodsForDonation();
        request.setAttribute("foodsForDonation", foodsForDonation);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity_dashboard.jsp"); 
        dispatcher.forward(request, response);
    }


}
