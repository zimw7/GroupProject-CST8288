<%-- 
    Document   : consumer_dashboard
    Created on : Mar. 30, 2024, 11:01:41 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Consumer Dashboard</title>
</head>
<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <h2>Welcome, <%= ((User)session.getAttribute("user")).getUserName() %>!</h2>
    <h3>Consumer Dashboard</h3>
    <br>
    
    <div>
        <h4>Purchase Discounted Items</h4>
        <form action="consumer_purchase.jsp" method="get">
            <button type="submit">Purchase</button>
        </form>
    </div>
    <br>
    
    <div>
        <h2>Subscribe to Surplus Food Alerts</h2>
     
        <form action="${pageContext.request.contextPath}/SubscribeServlet" method="post">
            <label for="preference">Food Preference:</label>
            <select name="preferenceType" id="preference">
                <option value="DAIRY">Dairy</option>
                <option value="PERISHABLE">Perishable</option>
                <option value="CARBOHYDRATE">Carbohydrate</option>
                <option value="DRINK">Drink</option>
            </select>
            <br><br>
            
            <label>Contact Type:</label>
            <input type="radio" id="email" name="contactType" value="EMAIL">
            <label for="email">Email</label>
            <input type="radio" id="text" name="contactType" value="TEXT">
            <label for="text">Text</label>
            <br><br>
            
            <label for="retailer_username">Location: </label>
            <input type="text" id="retailer_username" name="retailer_username" required>
            <br><br>
            
            <input type="submit" value="Subscribe">
        </form>
            
            <% if (request.getAttribute("errorMessage") != null) { %>
                 <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
            <% if (request.getAttribute("successMessage") != null) { %>
                <p style="color:green;"><%= request.getAttribute("successMessage") %></p>
            <% } %>


    </div>
    <br><br>
    
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">&#128075; Log out</button>
    </form>
</body> 
</html>
