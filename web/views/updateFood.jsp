<%@page import="entity.Food" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="util.FoodType" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update food</title>
    </head>
    <body>
        <h2>&#129386; FoodSaver &#129386;</h2><br>
        <h2>update Food Item</h2>
        <% if (request.getAttribute("errorMessage") != null) {%>
        <p style="color:red;"><%= request.getAttribute("errorMessage")%></p>
        <% } %>

        <% if (request.getAttribute("successMessage") != null) {%>
        <p style="color:green;"><%= request.getAttribute("successMessage")%></p>
        <% }%>

        <% Food food = (Food) session.getAttribute("foodDetail");%>

        <form action="updateFood.do" method="post">
            ID: <input type="text" name="id" readonly value="<%= food.getId()%>" > <br><br>
            Name: <input type="text" name="name" required value="<%= food.getName()%>"><br><br>
            Quantity: <input type="number" name="quantity" required value="<%= food.getQuantity()%>"><br><br>
            Price: <input type="number" name="price" step=0.01 required value="<%= food.getPrice()%>"><br><br>

            Food Type:
            <select name="foodType" id="foodType">
                <option value="DAIRY"<% if (food.getFoodType() == FoodType.DAIRY) {
                   %>
                   selected
                   <% } %>
                   > DAIRY </option>
                <option value="PERISHABLE"<% if (food.getFoodType() == FoodType.PERISHABLE) {
                   %>
                   selected
                   <% } %>
                   > PERISHABLE </option>
                <option value="CARBOHYDRATE"<% if (food.getFoodType() == FoodType.CARBOHYDRATE) {
                   %>
                   selected
                   <% } %>
                   > CARBOHYDRATE </option>
                <option value="DRINK"<% if (food.getFoodType() == FoodType.DRINK) {
                   %>
                   selected
                   <% } %>
                   > DRINK </option>
            </select><BR><BR>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <%
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String expirationStr = dateFormat.format(food.getExpirationDate());
            %>
            Expiration:<input type="text" name="expiration" id="datepicker" value="<%= expirationStr%>"><br><br>

            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            <script>
                $("#datepicker").datepicker();
            </script>

            <button class="but" type="submit">Update</button>
        </form><br>
        <p>Back to &#128073; <a href="${pageContext.request.contextPath}/RetailerServlet">DashBoard</a></p>
    </body>
</html>
