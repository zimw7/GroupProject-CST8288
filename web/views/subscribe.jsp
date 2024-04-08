<%-- 
    Document   : subscribe
    Created on : Apr. 6, 2024, 1:39:32 p.m.
    Author     : Zimeng
--%>

<%@page import="entity.User"%>
<%@page import="util.UserType"%>
<%@page import="java.util.List"%>
<%@page import="entity.Subscription"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subscription</title>
    </head>

    <body>

        <div>
            <h2>Current subscriptions</h2>

            <table border="1">
                <tr>
                    <th>Food Type</th>
                    <th>Location</th>
                </tr>
                <%
                    List<Subscription> subscriptions = (List<Subscription>) request.getAttribute("subscriptions");
                    if (subscriptions != null) {
                        for (Subscription subscription : subscriptions) {
                %>
                <tr>
                    <td><%= subscription.getPreferenceType().toString()%></td>
                    <td><%= subscription.getRetailerUsername()%></td>
                    <td>
                        <form action="unsubscribe.subdo" method="post">
                            <input type="hidden" name="selectOption" value="<%=subscription.getId()%>">
                            <button class="but" type="submit">Unsubscribe</button>
                        </form>
                    </td>
                </tr>
                <% }
                    }%>
            </table>
            <h2>Subscribe to Surplus Food Alerts</h2>

            <form action="subscribe.subdo" method="post">
                <label for="preference">Food Preference:</label>
                <select name="preferenceType" id="preference">
                    <option value="DAIRY">Dairy</option>
                    <option value="PERISHABLE">Perishable</option>
                    <option value="CARBOHYDRATE">Carbohydrate</option>
                    <option value="DRINK">Drink</option>
                </select>
                <br><br><br>

                <label>Contact Type:</label>
                <input type="radio" id="email" name="contactType" value="EMAIL">
                <label for="email">Email</label>
                <input type="radio" id="text" name="contactType" value="TEXT">
                <label for="text">Text</label>
                <br><br><br>

                <label for="retailer_username">Location: </label>
                <select id="retailer_username" name="retailer_username" required>
                    <% List<User> retailers = (List<User>) request.getAttribute("retailers");
                        for (User retailer : retailers) {%>
                    <option value="<%= retailer.getUserName()%>"><%= retailer.getUserName()%></option>
                    <% } %>
                </select>
                <br><br><br>

                <input type="submit" value="Subscribe" style="font-size: 12px; padding: 4px 10px;">

            </form>

            <% if (request.getAttribute("errorMessage") != null) {%>
            <p style="color:red;"><%= request.getAttribute("errorMessage")%></p>
            <% } %>
            <% if (request.getAttribute("successMessage") != null) {%>
            <p style="color:green;"><%= request.getAttribute("successMessage")%></p>
            <% }%>


        </div><br>

        <div>
            <%
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    if (user.getUserType() == UserType.CUSTOMER) {
            %>
            <p>Back to &#128073; <a href="${pageContext.request.contextPath}/ClientServlet">DashBoard</a></p>
            <% } else if (user.getUserType() == UserType.CHARITY) {
            %>
            <p>Back to &#128073; <a href="${pageContext.request.contextPath}/ShowDonationFoodsServlet">DashBoard</a></p>
            <%}
                }%>


        </div>


    </body>
</html>
