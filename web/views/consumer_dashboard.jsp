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
    <h2 style="color:darkblue;"><i>Consumer Dashboard</i></h2>
    
    <div>
        
        <% if (request.getAttribute("successCheckout") != null) {%>
            <p style="color:green;"><%= request.getAttribute("successCheckout")%></p>
            <% }%>
        <% if (request.getAttribute("errorCheckout") != null) { %>
            <p style="color:red;"><%= request.getAttribute("errorCheckout") %></p>
        <% } %>   
        
        <h2>Purchase Discounted Items</h2>
            <table border="1">
                <tr>
                    <th>Select Quantity</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Food Type</th>
                    <th>Expiration</th>
                </tr>
                
                <% List<SurplusFood> foodList = (List<SurplusFood>) request.getAttribute("surplusFood");
                    if (foodList != null) {
                        for (SurplusFood food : foodList) {%>
                            <tr>
                                <td>
                                    <form action="PurchaseSurplusServlet" method="post">
                                        <input type="hidden" name="foodID" value="<%= food.getId() %>">
                                        <input type="number" name="quantity" value="0" min="0" max="<%= food.getQuantity() %>">
                                        <button type="submit">Check out</button>
                                    </form>
                                </td>
                                <td><%= food.getName()%></td>
                                <td><%= food.getQuantity()%></td>
                                <td><%= food.getPrice()%></td>
                                <td><%= food.getFoodType().name()%></td>
                                <td><%= food.getExpirationDate()%></td>
                            </tr>
                    <% }
                } %>
                
            </table>  
    </div><br><br>
    
    <div>
        <form action="${pageContext.request.contextPath}/SubscribeServlet" method="get">
            <button type="submit" style="font-size: 15px; padding: 8px 16px;">&#128276; Subscribe to Alerts &#128276;</button>
        </form>

    </div><br><br>

    
    <div>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">&#128075; Log out</button>
        </form>
    </div>
            
            
        
</body> 
</html>
