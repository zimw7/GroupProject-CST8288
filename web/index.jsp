<%-- 
    Document   : index
    Created on : Mar. 30, 2024, 1:39:51 p.m.
    Author     : Zimeng Wang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h2>&#129386; FoodSaver &#129386;</h2><br>
        <h2>User Login</h2>
        
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/index" method="post">
            Username: <input type="text" name="username" required><br><br>
            Password: <input type="password" name="password" required><br><br>
            
            <input type="submit" value="Login">
        </form><br><br>
            <p>Not a user? &#8594; <a href="${pageContext.request.contextPath}/views/register.jsp">Register</a></p>
         

            
    </body>
</html>

