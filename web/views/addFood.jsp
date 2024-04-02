<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Food</title>
    </head>
    <body>
        <h2>&#129386; FoodSaver &#129386;</h2><br>
        <h2>Add Food Item</h2>
        <% if (request.getAttribute("errorMessage") != null) {%>
        <p style="color:red;"><%= request.getAttribute("errorMessage")%></p>
        <% } %>

        <% if (request.getAttribute("successMessage") != null) {%>
        <p style="color:green;"><%= request.getAttribute("successMessage")%></p>
        <% }%>


        <form action="addFood.do" method="post">
            Name: <input type="text" name="name" required><br><br>
            Quantity: <input type="number" name="quantity" required><br><br>
            Price: <input type="number" name="price" step=0.01 required><br><br>

            <label>Food Type:</label><br><br>
            <input type="radio" id="dairy" name="foodType" value="DAIRY" required>
            <label for="dairy">DAIRY</label><br><br>
            <input type="radio" id="perishable" name="foodType" value="PERISHABLE" required>
            <label for="perishable">PERISHABLE</label><br><br>
            <input type="radio" id="carbohydrate" name="foodType" value="CARBOHYDRATE" required>
            <label for="carbohydrate">CARBOHYDRATE</label><br><br>
            <input type="radio" id="drink" name="foodType" value="DRINK" required>
            <label for="drink">DRINK</label><br><br>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            Expiration:<input type="text" name="expiration" id="datepicker"><br><br>
            
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            <script>
                $("#datepicker").datepicker();
            </script>

            <button class="but" type="submit">Add</button>
        </form><br>
        <p>Back to dashboard &#8594; <a href="${pageContext.request.contextPath}/FoodServlet">DashBoard</a></p>
    </body>
</html>
