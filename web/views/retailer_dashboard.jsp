
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
        <h2>Welcome, <%= ((User) session.getAttribute("user")).getUserName()%>!</h2>
        <h3>Retailer Dashboard</h3>

        <div>
            <h4>Manage Inventory</h4>
            <% List<Food> foodsInventory = (List<Food>) request.getAttribute("foodsInventory"); %>
            <a href="views/addFood.jsp"><button type="button"> Add item </button></a><br>
            <form action="getFoodDetail.do" method="post">
                <select name="selectOption" id="selectOption">
                    <% for (Food food : foodsInventory) {%>
                    <option value="<%= food.getId()%>"> <%= food.getName()%> </option>
                    <% } %>
                </select>
                <input type="hidden" name="updateFlag" value="1"> 
                <button class="but" type="submit">Update</button>
            </form><br>
            <form action="getFoodDetail.do" method="post">
                <select name="selectOption" id="selectOption">
                    <% for (Food food : foodsInventory) {%>
                    <option value="<%= food.getId()%>"> <%= food.getName()%> </option>
                    <% } %>
                </select>
                <input type="hidden" name="updateFlag" value="0"> 
                <button class="but" type="submit">List as surplus food</button>
            </form><br>

            <br>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>quantity</th>
                        <th>price</th>
                        <th>Food type</th>
                        <th>Expiration</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Food food : foodsInventory) {%>
                    <tr>
                        <td><%= food.getId()%></td>
                        <td><%= food.getName()%></td>
                        <td><%= food.getQuantity()%></td>
                        <td><%= food.getPrice()%></td>
                        <td><%= food.getFoodType().toString()%></td>
                        <td><%= food.getExpirationDate()%></td>
                
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
                        <th>Food type</th>
                        <th>Expiration</th>
                        <th>Discount_rate</th>
                        <th>Donation</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<SurplusFood> surplusfoods = (List<SurplusFood>) request.getAttribute("surplusfoods");
                        for (SurplusFood surplusfood : surplusfoods) {%>
                    <tr>
                        <td><%= surplusfood.getId()%></td>
                        <td><%= surplusfood.getName()%></td>
                        <td><%= surplusfood.getQuantity()%></td>
                        <td><%= surplusfood.getPrice()%></td>
                        <td><%= surplusfood.getFoodType().toString()%></td>
                        <td><%= surplusfood.getExpirationDate()%></td>
                        <td><%= surplusfood.getDiscountRate()%></td>
                        <td><%= surplusfood.isIsForDonation()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>


        <p><a href="index">&#128075; Log out </a></p>
    </body>
</html>
