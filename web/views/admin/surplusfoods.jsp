<%@page import="entity.SurplusFood" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Surplusfood Inventory List</title>
    </head>
    <body>
        <h2>Surplusfood Inventory List</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th>Food type</th>
                    <th>Expiration</th>
                    <th>Discount_rate</th>
                    <th>Donation</th>
                </tr>
            </thead>
            <tbody>
                <% List<SurplusFood> surplusfoods = (List<SurplusFood>) request.getAttribute("surplusfoods");
                    for (SurplusFood surplusfood : surplusfoods) {%>
                <tr>
                    <td><%= surplusfood.getId()%></td>
                    <td><%= surplusfood.getName()%></td>
                    <td><%= surplusfood.getQuantity()%></td>
                    <td><%= surplusfood.getPrice()%></td>
                    <td><%= surplusfood.getFoodType().toString()%></td>
                    <td><%= surplusfood.getExpirationDate()%></td>
                    <td><%= surplusfood.getDiscountRate()%></td>
                    <td>
                        <input
                            type="checkbox"
                            id="donation"
                            name="donation" 
                            onclick="return false;"
                            <% if (surplusfood.isIsForDonation()) {
                            %>
                            checked
                            <% }%>
                            />
                    </td>
                </tr>
                <% }%>
            </tbody>
        </table>
        <p>Back to &#128073; <a href="${pageContext.request.contextPath}/views/admin/admin_dashboard.jsp">DashBoard</a></p>
    </body>
</html>
