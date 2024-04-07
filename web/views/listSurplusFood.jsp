<%@page import="entity.Food" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="util.FoodType" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Surplus Food</title>
    </head>
    <body>
        <h2>&#129386; FoodSaver &#129386;</h2><br>
        <h2>List Surplus Food</h2>
        <% if (request.getAttribute("errorMessage") != null) {%>
        <p style="color:red;"><%= request.getAttribute("errorMessage")%></p>
        <% } %>

        <% if (request.getAttribute("successMessage") != null) {%>
        <p style="color:green;"><%= request.getAttribute("successMessage")%></p>
        <% }%>

        <% Food food = (Food) session.getAttribute("foodDetail");%>
        <%
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String expirationStr = dateFormat.format(food.getExpirationDate());
        %>
        <table>
            <tr>
                <th>
                    ID
                </th>
                <td>
                    <%= food.getId()%>
                </td>
            </tr>
            <tr>
                <th>
                    Name
                </th>
                <td>
                    <%= food.getName()%>
                </td>
            </tr>
            <tr>
                <th>
                    Quantity
                </th>
                <td>
                    <%= food.getQuantity()%>
                </td>
            </tr>
            <tr>
                <th>
                    Price
                </th>
                <td>
                    <%= food.getPrice()%>
                </td>
            </tr>
            <tr>
                <th>
                    Food Type
                </th>
                <td>
                    <%= food.getFoodType().name()%>
                </td>
            </tr>
            <tr>
                <th>
                    Expiration
                </th>
                <td>

                    <%= expirationStr%>
                </td>
            </tr>
        </table><br><br>
        <form action="listOneSurplusFood.do" method="post">
            Discount Rate: <input type="number" name="discountRate" step="0.01" min="0" max="1" > <br><br>
            <input
                type="checkbox"
                id="donation"
                name="donation" />
            <label for="donation">Is For Donation?</label><br><br>


            <button class="but" type="submit">List As Surplus Food</button>
        </form><br>
        <p>Back to &#128073; <a href="${pageContext.request.contextPath}/RetailerServlet">DashBoard</a></p>
    </body>
</html>
