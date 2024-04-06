<%-- 
    Document   : consumer_dashboard
    Created on : Mar. 30, 2024, 11:01:41 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.SurplusFood"%>
<%@page import="java.util.List"%>
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

    <div>
        <h4>Purchase Discounted Items</h4>
        <% if (request.getAttribute("successMessage") != null) {%>
            <p style="color:green;"><%= request.getAttribute("successMessage")%></p>
            <% }%>
             
        <form action= "${pageContext.request.contextPath}/ClientServlet" method="post">
            <table>
                <tr>
                    <th>Select Quantity</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Food Type</th>
                    <th>Expiration</th>
                </tr>
                <% List<SurplusFood> foodList = (List<SurplusFood>) request.getAttribute("surplusfoods");
                    for (SurplusFood food : foodList) {%>
                <tr>
                    <td>
                        <select id="quantitySelect" name="quantity">
                            <% int max = food.getQuantity();
                                for (int i = 0; i <= max; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>
                    </td>
                    <td><%= food.getId()%></td>
                    <td><%= food.getName()%></td>
                    <td><%= food.getQuantity()%></td>
                    <td><%= food.getPrice()%></td>
                    <td><%= food.getFoodType().name()%></td>
                    <td><%= food.getExpirationDate()%></td>
                     <!-- Hidden field inside the loop for each food -->
                    <td><input type="hidden" name="foodID" value="<%= food.getId() %>"></td>
                </tr>
                <% }%>
            </table>  
             <input type="submit" value="Submit">
        </form>
    </div><br>
 
    
    <div>
        <h4>Subscribe to Surplus Food Alerts</h4>
        <p>&#128071; &#128071; &#128071;</p>
        
        <a href="${pageContext.request.contextPath}/views/consumer_subscription.jsp">Subscribe Now</a>
        
    </div><br><br>
    
    <p><a href="index">&#128075; Log out </a></p>
    
</body> 
</html>
