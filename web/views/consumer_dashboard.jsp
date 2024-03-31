<%-- 
    Document   : consumer_dashboard
    Created on : Mar. 30, 2024, 11:01:41 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Consumer Dashboard</title>
</head>
<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <h2>Welcome, <%= ((User)session.getAttribute("user")).getUserName() %>!</h2>
    <h3>Consumer Dashboard</h3>
    <br>
    <div>
        <h4>Purchase Discounted Items</h4>
        
    </div>
    <br>
    <div>
        <h4>Subscribe to Surplus Food Alerts</h4>
        <p>&#128071; &#128071; &#128071;</p>
        <a href="consumer_subscription.jsp">Subscribe Now</a>
        
    </div>
    <br><br>
    <p><a href="../index">&#128075; Log out </a></p>
</body> 
</html>
