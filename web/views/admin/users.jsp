<%@page import="entity.User"%>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
    </head>
    <body>
        <h2>User List</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>User Type</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Is subscribed</th>
                </tr>
            </thead>
            <tbody>
                <% List<User> users = (List<User>) request.getAttribute("users");
                for (User user : users) {%>
                <tr>
                    <td><%= user.getId()%></td>
                    <td><%= user.getUserName()%></td>
                    <td><%= user.getPassword()%></td>
                    <td><%= user.getUserType().toString()%></td>
                    <td><%= user.getPhoneNumber()%></td>
                    <td><%= user.getEmail()%></td>
                    <td><%= user.isSubscribed()%></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    <p>Back to &#128073; <a href="${pageContext.request.contextPath}/views/admin/admin_dashboard.jsp">DashBoard</a></p>
    </body>
</html>
