<%@page import="entity.Subscription"%>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subscriptions List</title>
    </head>
    <body>
        <h2>Subscriptions List</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Contact Type</th>
                    <th>Preference Type</th>
                    <th>Retailer Name</th>
                </tr>
            </thead>
            <tbody>
                <% List<Subscription> subscriptions = (List<Subscription>) request.getAttribute("subscriptions");
                    for (Subscription subscription : subscriptions) {%>
                <tr>
                    <td><%= subscription.getId()%></td>
                    <td><%= subscription.getUserID()%></td>
                    <td><%= subscription.getContactType().toString()%></td>
                    <td><%= subscription.getPreferenceType().toString()%></td>
                    <td><%= subscription.getRetailerUsername()%></td>
                </tr>
                <% }%>
            </tbody>
        </table>
        <p>Back to &#128073; <a href="${pageContext.request.contextPath}/views/admin/admin_dashboard.jsp">DashBoard</a></p>
    </body>
</html>
