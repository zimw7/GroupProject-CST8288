<%-- 
    Document   : charity_dashboard
    Created on : Mar. 30, 2024, 11:03:57 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Charity Dashboard</title>
</head>
<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <h2>Welcome, <%= ((User)session.getAttribute("user")).getUserName() %>!</h2>
    <h3>Charitable Organization Dashboard</h3>
    
    <div>
        <h4>Claim Food Items for Donation</h4>
        
    </div>
    
    
    <p><a href="../index">&#128075; Log out </a></p>
</body>
</html>
