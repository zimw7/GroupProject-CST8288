package controller;

import entity.SurplusFood;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import service.SurplusFoodService;
import service.impl.SurplusFoodServiceImpl;

/**
 * Servlet implementation for handling food claim requests by charities. It
 * interacts with the {@link SurplusFoodService} to claim surplus food items for
 * donation purposes.
 *
 * @author Zimeng Wang
 * @author Wenxin Li
 * @author Mengying Liu
 * @since 1.0
 * @version 1.5
 */
@WebServlet("/ClaimFoodServlet")
public class ClaimFoodServlet extends HttpServlet {

    private SurplusFoodService surplusFoodService;

    /**
     * Initializes the servlet and creates an instance of
     * {@link SurplusFoodServiceImpl}. This method is called by the servlet
     * container to indicate to a servlet that the servlet is being placed into
     * service.
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
     * Processes the POST requests for claiming surplus food items.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
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
