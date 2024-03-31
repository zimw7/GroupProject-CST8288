<%-- 
    Document   : retailer_dashboard
    Created on : Mar. 30, 2024, 11:03:48 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Retailer Dashboard</title>
</head>
<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <h2>Welcome, <%= ((User)session.getAttribute("user")).getUserName() %>!</h2>
    <h3>Retailer Dashboard</h3>
    
    <div>
        <h4>Manage Inventory</h4>
        
    </div>
    
    <div>
        <h4>List Surplus Food Items</h4>
        
    </div>
    
    
    <p><a href="../index">&#128075; Log out </a></p>
</body>
</html>
