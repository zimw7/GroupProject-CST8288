<%@page import="entity.SurplusFood"%>
<%@page import="java.util.List"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
    <title>Charity Dashboard</title>
  
</head>

<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <h2>Welcome, <%= ((User)session.getAttribute("user")).getUserName() %>!</h2>
    <h2 style="color:darkblue;"><i>Charity Dashboard</i></h2>
    
    <div>
        <h3>Request Food Donations</h3>
        
        <table border="1">
            <tr>
                <th>Donatable Food</th>
                <th>Available Quantity</th>
                <th>Actions</th>
            </tr>
            <% 
            List<SurplusFood> foodsForDonation = (List<SurplusFood>)request.getAttribute("foodsForDonation");
            if(foodsForDonation != null && !foodsForDonation.isEmpty()) {
                for(SurplusFood food : foodsForDonation) { 
            %>
                <tr>
                    <td><%= food.getName() %></td>
                    <td><%= food.getQuantity() %></td>
                    <td>
                        <form action="ClaimFoodServlet" method="post">
                            <input type="hidden" name="foodId" value="<%= food.getId() %>">
                            <input type="number" name="quantity" value="1" min="1" max="<%= food.getQuantity() %>">
                             <button type="submit">Claim</button>
                        </form>
                    </td>
                </tr>
            <% 
                }
            } else {
            %>
                <tr>
                    <td colspan="4">No foods available for donation.</td>
                </tr>
            <%
            }
            %>
        </table>

        <% if (request.getAttribute("claimSuccess") != null) { %>
            <p style="color:green;"><%= request.getAttribute("claimSuccess") %></p>
        <% } %>
        <% if (request.getAttribute("claimError") != null) { %>
            <p style="color:red;"><%= request.getAttribute("claimError") %></p>
        <% } %>

<%-- Table rendering here --%>

    </div><br><br>
    
     <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">&#128075; Log out</button>
     </form>

     
</body>
</html>
