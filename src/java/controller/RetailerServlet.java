/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Food;
import entity.SurplusFood;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.FoodService;
import service.SurplusFoodService;
import service.impl.FoodServiceImpl;
import service.impl.SurplusFoodServiceImpl;
import util.FoodType;

/**
 * Servlet implementation class for handling retailer operations such as
 * managing food inventory, listing and managing surplus food, and generating
 * reports.
 *
 * @author Zimeng Wang, Wenxin Li, Mengying Liu.
 * @since 1.0
 * @version 1.5
 */
@WebServlet(name = "RetailerServlet", urlPatterns = {"/RetailerServlet", "*.do"})
public class RetailerServlet extends HttpServlet {

    private FoodService foodService = null;
    private SurplusFoodService surplusFoodService = null;

    /**
     * Initializes the servlet.
     *
     * @throws ServletException if an exception occurs that interrupts the
     * servlet's normal operation.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        this.foodService = new FoodServiceImpl();
        this.surplusFoodService = new SurplusFoodServiceImpl();
    }

    /**
     * Handles HTTP GET requests. This method is responsible for displaying the
     * retailer dashboard, including information about food inventory and
     * surplus foods associated with the logged-in user.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Food> foodsInventory = null;
        List<SurplusFood> surplusfoods = null;

        foodsInventory = foodService.getFoodInventoryByUser(user);
        surplusfoods = surplusFoodService.getSurplusFoodByUser(user);

        request.setAttribute("foodsInventory", foodsInventory);
        request.setAttribute("surplusfoods", surplusfoods);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/retailer_dashboard.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Handles HTTP POST requests by dynamically invoking methods based on the
     * request URI. This allows the servlet to delegate operations such as
     * adding, updating, and deleting food and surplus food entities to specific
     * methods identified by the request.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();

        String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        System.out.println(methodName);
        Method method = null;
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("method error" + methodName, e);
        }
    }

    /**
     * Processes requests for adding a new food item.
     *
     * @param request servlet request containing food item details.
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void addFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String foodType = request.getParameter("foodType");
        String expiration = request.getParameter("expiration");

        Food food = new Food();
        food.setName(name);
        food.setQuantity(Integer.parseInt(quantity));
        food.setPrice(Double.parseDouble(price));

        if (foodType != null && !foodType.isEmpty()) {
            food.setFoodType(FoodType.valueOf(foodType));
        } else {
            request.setAttribute("errorMessage", "Please select a user type.");
            doGet(request, response);
            return;
        }

        Date expirationDate = null;
        try {
            expirationDate = new SimpleDateFormat("MM/dd/yyyy").parse(expiration);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Expiration date error");
            doGet(request, response);
        }
        food.setExpirationDate(expirationDate);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        food.setUserID(user.getId());

        foodService.addFoodInventory(food);

        request.setAttribute("successMessage", "Add food item successful!");
        doGet(request, response);

    }

    /**
     * Retrieves detailed information for a specific food item based on its ID.
     *
     * @param request servlet request containing the foodID parameter.
     * @param response servlet response for forwarding to the next view.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void getFoodDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String foodID = request.getParameter("selectOption");

        Food food = foodService.getFoodDetail(Integer.parseInt(foodID));

        HttpSession session = request.getSession();
        session.setAttribute("foodDetail", food);
        if (Integer.parseInt(request.getParameter("updateFlag")) != 1) {
            request.getRequestDispatcher("/views/listSurplusFood.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/updateFood.jsp").forward(request, response);
        }
    }

    /**
     * Handles the update request for an existing food item.
     *
     * @param request servlet request containing updated food item details.
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void updateFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Food food = (Food) session.getAttribute("foodDetail");

        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String foodType = request.getParameter("foodType");
        String expiration = request.getParameter("expiration");

        food.setName(name);
        food.setQuantity(Integer.parseInt(quantity));
        food.setPrice(Double.parseDouble(price));

        if (foodType != null && !foodType.isEmpty()) {
            food.setFoodType(FoodType.valueOf(foodType));
        } else {
            request.setAttribute("errorMessage", "Please select a user type.");
            request.getRequestDispatcher("/views/updateFood.jsp").forward(request, response);
            return;
        }

        Date expirationDate = null;
        try {
            expirationDate = new SimpleDateFormat("MM/dd/yyyy").parse(expiration);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Expiration date error");
            request.getRequestDispatcher("/views/updateFood.jsp").forward(request, response);
        }
        food.setExpirationDate(expirationDate);

        foodService.updateOneFood(food);

        request.setAttribute("successMessage", "Update food item successful!");
        // refresh table entries
        doGet(request, response);
//        request.getRequestDispatcher("/views/updateFood.jsp").forward(request, response);

    }

    /**
     * Processes requests for listing an existing food item as surplus.
     *
     * @param request servlet request containing surplus food details.
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void listOneSurplusFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Food food = (Food) session.getAttribute("foodDetail");

        String discountRate = request.getParameter("discountRate");
        boolean isForDonation = request.getParameter("donation") != null;

        SurplusFood surplusfood = new SurplusFood();
        surplusfood.setName(food.getName());
        surplusfood.setQuantity(food.getQuantity());
        surplusfood.setPrice(food.getPrice());
        surplusfood.setFoodType(food.getFoodType());
        surplusfood.setExpirationDate(food.getExpirationDate());
        surplusfood.setUserID(food.getUserID());

        surplusfood.setDiscountRate(Double.parseDouble(discountRate));
        surplusfood.setIsForDonation(isForDonation);

        foodService.deleteOneFood(food.getId());
        surplusFoodService.addSurplusFood(surplusfood);
        User owner = (User) session.getAttribute("user");
        surplusFoodService.listSurplusFood(surplusfood, owner);
        request.setAttribute("successMessage", "Add surplus food item successful!");

        doGet(request, response);

    }

    /**
     * Handles requests for deleting a specific food item from the inventory.
     * After deletion, redirects back to the retailer dashboard with a success
     * message.
     *
     * @param request servlet request containing the foodID to delete.
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void deleteFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int foodID = Integer.parseInt(request.getParameter("selectOption"));
        foodService.deleteOneFood(foodID);
        request.setAttribute("successMessage", "Delete food item successful!");
        doGet(request, response);
    }

    /**
     * Retrieves and sets the detailed information of a specific surplus food
     * item based on its ID.
     *
     * @param request servlet request containing the surplusfoodID parameter.
     * @param response servlet response for forwarding to the
     * updateSurplusFood.jsp view.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void getSurplusFoodDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String surplusfoodID = request.getParameter("selectOption");

        SurplusFood surplusfood = surplusFoodService.getSurplusFoodDetail(Integer.parseInt(surplusfoodID));

        HttpSession session = request.getSession();
        session.setAttribute("foodDetail", surplusfood);
        request.getRequestDispatcher("/views/updateSurplusFood.jsp").forward(request, response);
    }

    /**
     * Updates an existing surplus food item's details. After processing the
     * update, redirects back to the retailer dashboard with a corresponding
     * message.
     *
     * @param request servlet request containing updated surplus food details.
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void updateSurplusFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        SurplusFood surplusfood = (SurplusFood) session.getAttribute("foodDetail");

        String discountRate = request.getParameter("discountRate");
        boolean isForDonation = request.getParameter("donation") != null;

        surplusfood.setName(surplusfood.getName());
        surplusfood.setQuantity(surplusfood.getQuantity());
        surplusfood.setPrice(surplusfood.getPrice());
        surplusfood.setFoodType(surplusfood.getFoodType());
        surplusfood.setExpirationDate(surplusfood.getExpirationDate());
        surplusfood.setUserID(surplusfood.getUserID());

        surplusfood.setDiscountRate(Double.parseDouble(discountRate));
        surplusfood.setIsForDonation(isForDonation);

        surplusFoodService.updateSurplusFood(surplusfood);
        request.setAttribute("successMessage", "Update surplus food item successful!");
        doGet(request, response);

    }

    /**
     * Removes a surplus food item from the surplus list and optionally adds it
     * back to the regular food inventory.
     *
     * @param request servlet request containing the ID of the surplus food to
     * "unsurplus".
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void unSurplusFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int surplusfoodID = Integer.parseInt(request.getParameter("selectOption"));
        SurplusFood surplusfood = surplusFoodService.getSurplusFoodDetail(surplusfoodID);
        surplusFoodService.deleteSurplusFood(surplusfoodID);
        foodService.addFoodInventory(surplusfood);
        request.setAttribute("successMessage", "Unsurplus food item successful!");
        doGet(request, response);
    }

    /**
     * Deletes a specific surplus food item from the surplus inventory.
     * Redirects back to the retailer dashboard with a success message after
     * deletion.
     *
     * @param request servlet request containing the surplusfoodID to delete.
     * @param response servlet response for redirecting to the retailer
     * dashboard.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void deleteSurplusFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int surplusfoodID = Integer.parseInt(request.getParameter("selectOption"));
        surplusFoodService.deleteSurplusFood(surplusfoodID);
        request.setAttribute("successMessage", "Delete food item successful!");
        doGet(request, response);
    }

    /**
     * Generates a report for selected surplus food items, saving the report to
     * a text file.
     *
     * @param request servlet request containing parameters for generating the
     * report.
     * @param response servlet response for redirecting to the retailer
     * dashboard with the report details.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void report(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String retailer = request.getParameter("retailer");
        String[] foodIDs = request.getParameterValues("selectOption");

        String fileName = "SurplusReport.txt";
        File myFile = new File(fileName);

        try (PrintWriter writer = new PrintWriter(fileName)) {
            Date today = new Date();
            writer.println("Report saved on: " + today + " for retailer: " + retailer);

            for (String foodID : foodIDs) {
                int id = Integer.parseInt(foodID);
                SurplusFood food = surplusFoodService.getSurplusFoodDetail(id);
                if (food != null) {
                    String name = food.getName();
                    String quantity = String.valueOf(food.getQuantity());
                    String price = String.valueOf(food.getPrice());
                    String type = String.valueOf(food.getFoodType());
                    String expiration = String.valueOf(food.getExpirationDate());
                    String discount = String.valueOf(food.getDiscountRate());
                    String donation = String.valueOf(food.isIsForDonation());

                    writer.println("\nFood ID: " + id);
                    writer.println("Name: " + name);
                    writer.println("Quantity: " + quantity);
                    writer.println("Price: " + price);
                    writer.println("Type: " + type);
                    writer.println("Expiration: " + expiration);
                    writer.println("Discount: " + discount);
                    writer.println("Is it for donation: " + donation);
                }
            }
            writer.flush();
            String locationMessage = "You can locate your file at: " + myFile.getCanonicalPath();
            request.setAttribute("successReportMessage", "Data saved successfully.");
            request.setAttribute("locationMessage", locationMessage);

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorReportMessage", "Error saving data, please try again.");
        }
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
