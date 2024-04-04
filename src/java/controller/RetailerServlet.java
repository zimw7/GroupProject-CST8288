/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Food;
import entity.SurplusFood;
import entity.User;
import java.io.IOException;
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

@WebServlet(name = "RetailerServlet", urlPatterns = {"/RetailerServlet", "*.do"})
public class RetailerServlet extends HttpServlet {

    private FoodService foodService = null;
    private SurplusFoodService surplusFoodService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        this.foodService = new FoodServiceImpl();
        this.surplusFoodService = new SurplusFoodServiceImpl();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Food> foodsInventory = null;
        List<SurplusFood> surplusfoods = null;

        foodsInventory = foodService.getFoodInventoryByUser(user);
        surplusfoods = surplusFoodService.getAllSurplusFood();

        request.setAttribute("foodsInventory", foodsInventory);
        request.setAttribute("surplusfoods", surplusfoods);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/retailer_dashboard.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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

        request.setAttribute("successMessage", "Add food item successful! Please go back to dashboard");
        request.getRequestDispatcher("/views/updateFood.jsp").forward(request, response);

    }

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
        request.setAttribute("successMessage", "Add food item successful! Please go back to dashboard");
        request.getRequestDispatcher("/views/listSurplusFood.jsp").forward(request, response);
    }

    private void deleteFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int foodID = Integer.parseInt(request.getParameter("selectOption"));
        foodService.deleteOneFood(foodID);
        request.setAttribute("successMessage", "Delete food item successful!");
        doGet(request, response);
    }

    private void getSurplusFoodDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String surplusfoodID = request.getParameter("selectOption");

        SurplusFood surplusfood = surplusFoodService.getSurplusFoodDetail(Integer.parseInt(surplusfoodID));

        HttpSession session = request.getSession();
        session.setAttribute("foodDetail", surplusfood);
        request.getRequestDispatcher("/views/updateSurplusFood.jsp").forward(request, response);
    }
    
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
        request.setAttribute("successMessage", "Add food item successful! Please go back to dashboard");
        request.getRequestDispatcher("/views/updateSurplusFood.jsp").forward(request, response);
    }
    
    private void deleteSurplusFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int surplusfoodID = Integer.parseInt(request.getParameter("selectOption"));
        surplusFoodService.deleteSurplusFood(surplusfoodID);
        request.setAttribute("successMessage", "Delete food item successful!");
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
