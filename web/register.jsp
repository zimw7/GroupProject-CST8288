<%-- 
    Document   : register
    Created on : Mar. 30, 2024, 5:10:07 p.m.
    Author     : Zimeng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h2>User Register</h2>
        
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            Username: <input type="text" name="username" required><br><br>
            Password: <input type="password" name="password" required><br><br>
            Email: <input type="email" name="email" required><br><br>
            
            <label>User Type:</label><br><br>
            <input type="radio" id="retail" name="userType" value="RETAIL" required>
            <label for="retail">Retail</label><br><br>
            <input type="radio" id="customer" name="userType" value="CUSTOMER" required>
            <label for="customer">Customer</label><br><br>
            <input type="radio" id="charity" name="userType" value="CHARITY" required>
            <label for="charity">Charity</label><br><br>
            
            <input type="submit" value="Register"><br>
        </form><br>
        
        <p>Back to login &#8594; <a href="${pageContext.request.contextPath}/index.jsp">Login</a></p>
         
            
    </body>
</html>
