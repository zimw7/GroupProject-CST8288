<%-- 
    Document   : subscription
    Created on : Mar. 30, 2024, 11:23:46 p.m.
    Author     : Zimeng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Subscribe to Surplus Food Alerts</title>
</head>
<body>
    <h2>Subscribe to Surplus Food Alerts</h2>
    
    <% if (request.getAttribute("successMessage") != null) { %>
        <p style="color:green;"><%= request.getAttribute("successMessage").toString() %></p>
    <% } %>
    
    
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
        <input type="submit" value="Subscribe">
    </form><br><br>
    
    <p><a href="${pageContext.request.contextPath}/views/consumer_dashboard.jsp">Back to Dashboard</a></p>


</body>
</html>

