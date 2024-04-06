<%-- 
    Document   : home
    Created on : Mar. 30, 2024, 6:54:04 p.m.
    Author     : Zimeng Wang
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>&#129386; FoodSaver &#129386;</h2><br>
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
            out.println("<h2>Welcome, " + user.getUserName() + "!</h2>");
            switch(user.getUserType()) {
                case RETAIL:
                    response.sendRedirect("../FoodServlet");
                    break;
                case CUSTOMER:
                    response.sendRedirect("consumer_dashboard.jsp"); 
                    break;
                case CHARITY:
                    response.sendRedirect("../ShowDonationFoodsServlet");
                    break;
                default:
                    out.println("<p>Unrecognized user type. Please <a href=\"../index.jsp\">log in</a> again.</p>");
                    break;
            }
        } else {
            // User not logged in or session expired
            out.println("<p>Please <a href=\"../index.jsp\">log in</a>.</p>");
        }
    %>
</body>
</html>
