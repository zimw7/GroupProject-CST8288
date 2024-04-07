<%@page import="java.util.List" %>
<%@page import="entity.Food" %>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Inventory List</title>
    </head>
    <body>
        <h2>Food Inventory List</h2>

        <table border="1">
            <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>quantity</th>
                        <th>price</th>
                        <th>Food type</th>
                        <th>Expiration</th>
                        <th>OWNER</th>
                    </tr>
                </thead>
            <tbody>
                <% List<Food> foods = (List<Food>) request.getAttribute("foods");
                for (Food food : foods) {%>
                <tr>
                    <td><%= food.getId()%></td>
                        <td><%= food.getName()%></td>
                        <td><%= food.getQuantity()%></td>
                        <td><%= food.getPrice()%></td>
                        <td><%= food.getFoodType().toString()%></td>
                        <%
                            if (food.getExpirationDate().getTime() < (new Date().getTime() + 7 * 24 * 60 * 60 * 1000)) {
                        %>
                        <td bgcolor="#DC143C"><%= food.getExpirationDate()%></td>
                        <%
                        } else {
                        %>
                        <td><%= food.getExpirationDate()%></td>
                        <%
                            }
                        %>
                        <td><%= food.getUserID()%></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    <p>Back to &#128073; <a href="${pageContext.request.contextPath}/views/admin/admin_dashboard.jsp">DashBoard</a></p>
    </body>
</html>