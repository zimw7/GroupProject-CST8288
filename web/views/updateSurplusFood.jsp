<%@page import="entity.SurplusFood" %>
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

        <% SurplusFood surplusfood = (SurplusFood) session.getAttribute("foodDetail");%>

        <form action="updateSurplusFood.do" method="post">
            ID: <input type="text" name="id" readonly value="<%= surplusfood.getId()%>" > <br><br>
            Name: <input type="text" name="name" required readonly value="<%= surplusfood.getName()%>"><br><br>
            Quantity: <input type="number" name="quantity" readonly required value="<%= surplusfood.getQuantity()%>"><br><br>
            Price: <input type="number" name="price" step=0.01 readonly required value="<%= surplusfood.getPrice()%>"><br><br>

            Food Type:
            <select name="foodType" id="foodType" readonly>
                <option value="DAIRY"<% if (surplusfood.getFoodType() == FoodType.DAIRY) {
                        %>
                        selected
                        <% } %>
                        > DAIRY </option>
                <option value="PERISHABLE"<% if (surplusfood.getFoodType() == FoodType.PERISHABLE) {
                        %>
                        selected
                        <% } %>
                        > PERISHABLE </option>
                <option value="CARBOHYDRATE"<% if (surplusfood.getFoodType() == FoodType.CARBOHYDRATE) {
                        %>
                        selected
                        <% } %>
                        > CARBOHYDRATE </option>
                <option value="DRINK"<% if (surplusfood.getFoodType() == FoodType.DRINK) {
                        %>
                        selected
                        <% } %>
                        > DRINK </option>
            </select><BR><BR>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <%
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String expirationStr = dateFormat.format(surplusfood.getExpirationDate());
            %>
            Expiration:<input type="text" name="expiration" id="datepicker" value="<%= expirationStr%>" readonly><br><br>

            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            <script>
                $("#datepicker").datepicker();
            </script>


            Discount Rate: <input type="number" name="discountRate" step="0.01" value="<%= surplusfood.getDiscountRate() %>"> <br><br>
            <input
                type="checkbox"
                id="donation"
                name="donation" <% if (surplusfood.isIsForDonation()) {
                        %>
                        checked
                        <% } %>
                        />
            <label for="donation">Is For Donation?</label><br><br>

            <button class="but" type="submit">Update</button>
        </form><br>
        <p>Back to dashboard &#8594; <a href="${pageContext.request.contextPath}/RetailerServlet">DashBoard</a></p>
    </body>
</html>
