<%-- 
    Document   : subscribe
    Created on : Apr. 6, 2024, 1:39:32 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>subscription</title>
    </head>
    
    <body>
      
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
            <br><br><br>
            
            <label>Contact Type:</label>
            <input type="radio" id="email" name="contactType" value="EMAIL">
            <label for="email">Email</label>
            <input type="radio" id="text" name="contactType" value="TEXT">
            <label for="text">Text</label>
            <br><br><br>
            
            <label for="retailer_username">Location: </label>
            <select id="retailer_username" name="retailer_username" required>
                <% List<User> retailers = (List<User>) request.getAttribute("retailers");
                   for (User retailer : retailers) { %>
                       <option value="<%= retailer.getUserName() %>"><%= retailer.getUserName() %></option>
                <% } %>
            </select>
            <br><br><br>
            
            <input type="submit" value="Subscribe" style="font-size: 12px; padding: 4px 10px;">
            
        </form>
            
            <% if (request.getAttribute("errorMessage") != null) { %>
                 <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
            <% if (request.getAttribute("successMessage") != null) { %>
                <p style="color:green;"><%= request.getAttribute("successMessage") %></p>
            <% } %>


    </div><br>
            
    <div>     
        <p>Back to &#128073; <a href="${pageContext.request.contextPath}/ClientServlet">DashBoard</a></p>
    </div>
       
       
    </body>
</html>
