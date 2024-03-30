<%-- 
    Document   : register
    Created on : Mar. 30, 2024, 1:39:51 p.m.
    Author     : Zimeng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    
    <head>
        <title>Register</title>
    </head>
    
    <body>
        <h2>Register</h2>
        
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            Email: <input type="email" name="email" required><br>
            <input type="submit" value="Register">
        </form>
            
    </body>
    
</html>