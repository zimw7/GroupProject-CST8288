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

        <form action="updateOneFood.do" method="post">
            ID: <input type="text" name="id" readonly value="<%= food.getId()%>" > <br><br>
            Name: <input type="text" name="name" required value="<%= food.getName()%>"><br><br>
            Quantity: <input type="number" name="quantity" required value="<%= food.getQuantity()%>"><br><br>
            Price: <input type="number" name="price" step=0.01 required value="<%= food.getPrice()%>"><br><br>

            <label>Food Type:</label><br><br>
            <input type="radio" id="dairy" name="foodType" value="DAIRY" required
                   <% if (food.getFoodType() == FoodType.DAIRY) {
                   %>
                   checked
                   <% } %>
                   >
            <label for="dairy">DAIRY</label><br><br>
            <input type="radio" id="perishable" name="foodType" value="PERISHABLE" required
                   <% if (food.getFoodType() == FoodType.PERISHABLE) {
                   %>
                   checked
                   <% } %>
                   >
            <label for="perishable">PERISHABLE</label><br><br>
            <input type="radio" id="carbohydrate" name="foodType" value="CARBOHYDRATE" required
                   <% if (food.getFoodType() == FoodType.CARBOHYDRATE) {
                   %>
                   checked
                   <% } %>
                   >
            <label for="carbohydrate">CARBOHYDRATE</label><br><br>
            <input type="radio" id="drink" name="foodType" value="DRINK" required
                   <% if (food.getFoodType() == FoodType.DRINK) {
                   %>
                   checked
                   <% } %>
                   >
            <label for="drink">DRINK</label><br><br>
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
        <p>Back to dashboard &#8594; <a href="${pageContext.request.contextPath}/FoodServlet">DashBoard</a></p>
    </body>
</html>
