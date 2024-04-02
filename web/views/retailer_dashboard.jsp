<%-- 
    Document   : retailer_dashboard
    Created on : Mar. 30, 2024, 11:03:48 p.m.
    Author     : Zimeng
--%>
<%@page import="java.util.List" %>
<%@page import="entity.Food" %>
<%@page import="entity.SurplusFood" %>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Retailer Dashboard</title>
</head>
<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <h2>Welcome, <%= ((User)session.getAttribute("user")).getUserName() %>!</h2>
    <h3>Retailer Dashboard</h3>
    
    <div>
        <h4>Manage Inventory</h4>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th>FOOD_TYPE</th>
                    <th>EXPIRATION_DATE</th>
                </tr>
            </thead>
            <tbody>
                <% List<Food> foodsInventory = (List<Food>) request.getAttribute("foodsInventory");
                for (Food food : foodsInventory) {%>
                <tr>
                    <td><%= food.getId()%></td>
                    <td><%= food.getName()%></td>
                    <td><%= food.getQuantity()%></td>
                    <td><%= food.getPrice() %></td>
                    <td><%= food.getFoodType().toString() %></td>
                    <td><%= food.getExpirationDate() %></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </div>
    
    <div>
        <h4>List Surplus Food Items</h4>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th>FOOD_TYPE</th>
                    <th>EXPIRATION_DATE</th>
                </tr>
            </thead>
            <tbody>
                <% List<SurplusFood> surplusfoods = (List<SurplusFood>) request.getAttribute("surplusfoods");
                for (SurplusFood surplusfood : surplusfoods) {%>
                <tr>
                    <td><%= surplusfood.getId()%></td>
                    <td><%= surplusfood.getName()%></td>
                    <td><%= surplusfood.getQuantity()%></td>
                    <td><%= surplusfood.getPrice() %></td>
                    <td><%= surplusfood.getFoodType().toString() %></td>
                    <td><%= surplusfood.getExpirationDate() %></td>
                    <td><%= surplusfood.getDiscountRate() %></td>
                    <td><%= surplusfood.isIsForDonation() %></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </div>
    
    
    <p><a href="index">&#128075; Log out </a></p>
</body>
</html>
