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
        <h2>Welcome, <%= ((User) session.getAttribute("user")).getUserName()%>!</h2>
        <h2 style="color:darkblue;"><i>Charity Dashboard</i></h2>

        <div>
            <% if (request.getAttribute("claimSuccess") != null) {%>
            <p style="color:green;"><%= request.getAttribute("claimSuccess")%></p>
            <% } %>
            <% if (request.getAttribute("claimError") != null) {%>
            <p style="color:red;"><%= request.getAttribute("claimError")%></p>
            <% } %>
            <% if (request.getAttribute("notifications") != null) {
                    List<String> notifications = (List<String>) request.getAttribute("notifications");
                    for (String notification : notifications) {
            %>
            <p style="color:blue;"><%= notification%></p>
            <% }
                }%> 
            <h2>Request Food Donations</h2>

            <table border="1">
                <tr>
                    <th>Donatable Food</th>
                    <th>Available Quantity</th>
                    <th>Actions</th>
                </tr>
                <%
                    List<SurplusFood> foodsForDonation = (List<SurplusFood>) request.getAttribute("foodsForDonation");
                    if (foodsForDonation != null && !foodsForDonation.isEmpty()) {
                        for (SurplusFood food : foodsForDonation) {
                %>
                <tr>
                    <td><%= food.getName()%></td>
                    <td><%= food.getQuantity()%></td>
                    <td>
                        <form action="ClaimFoodServlet" method="post">
                            <input type="hidden" name="foodId" value="<%= food.getId()%>">
                            <input type="number" name="quantity" value="1" min="1" max="<%= food.getQuantity()%>">
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

            <%-- Table rendering here --%>

        </div><br><br>
        <div>
            <form action="${pageContext.request.contextPath}/SubscribeServlet" method="get">
                <button type="submit" style="font-size: 15px; padding: 8px 16px;">&#128276; Subscribe to Alerts &#128276;</button>
            </form>

        </div><br>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">&#128075; Log out</button>
        </form>


    </body>
</html>
