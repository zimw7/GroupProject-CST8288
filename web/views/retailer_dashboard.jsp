
<%@page import="java.util.List" %>
<%@page import="entity.Food" %>
<%@page import="entity.SurplusFood" %>
<%@page import="entity.User"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Retailer Dashboard</title>
    </head>
    <body>
        <h2>&#129386; FoodSaver &#129386;</h2><br>
        <h2>Welcome, <%= ((User) session.getAttribute("user")).getUserName()%>!</h2>
        <h2 style="color:darkblue;"><i>Retailer Dashboard</i></h2>

        <div>
            <h3>Manage Inventory</h3>
            <% if (request.getAttribute("errorMessage") != null) {%>
            <p style="color:red;"><%= request.getAttribute("errorMessage")%></p>
            <% } %>

            <% if (request.getAttribute("successMessage") != null) {%>
            <p style="color:green;"><%= request.getAttribute("successMessage")%></p>
            <% }%>
            <% List<Food> foodsInventory = (List<Food>) request.getAttribute("foodsInventory"); %>
            <br>
            <form action="addFood.do" id="addFoodForm" method="POST"></form>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>quantity</th>
                        <th>price</th>
                        <th>Food type</th>
                        <th>Expiration</th>
                        <th colspan="3">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Food food : foodsInventory) {%>
                    <tr>
                        <td><%= food.getId()%></td>
                        <td><%= food.getName()%></td>
                        <td><%= food.getQuantity()%></td>
                        <td><%= food.getPrice()%></td>
                        <td><%= food.getFoodType().toString()%></td>
                        <%
                            if (food.getExpirationDate().getTime() < (new Date().getTime() + 7 * 24 * 60 * 60 * 1000)) {
                        %>
                        <td bgcolor="#DC143C"><%= food.getExpirationDate()%></td>
                        <%
                        } else {
                        %>
                        <td><%= food.getExpirationDate()%></td>
                        <%
                            }
                        %>
                        <td>
                            <form action="getFoodDetail.do" method="post">
                                <input type="hidden" name="selectOption" value="<%=food.getId()%>">
                                <input type="hidden" name="updateFlag" value="1"> 
                                <button class="but" type="submit">Update</button>
                            </form>
                        </td>
                        <td>
                            <form action="getFoodDetail.do" method="post">
                                <input type="hidden" name="selectOption" value="<%=food.getId()%>">
                                <input type="hidden" name="updateFlag" value="0"> 
                                <button class="but" type="submit">Surplus</button>
                            </form>
                        </td>
                        <td>
                            <form action="deleteFood.do" method="post">
                                <input type="hidden" name="selectOption" value="<%=food.getId()%>">
                                <button class="but" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% }%>
                    <tr>
                        <td></td>
                        <td>
                            <input type="text" name="name" required form="addFoodForm">
                        </td>
                        <td>
                            <input type="number" name="quantity" required form="addFoodForm">
                        </td>
                        <td>
                            <input type="number" name="price" step=0.01 required form="addFoodForm">
                        </td>
                        <td>
                            <select name="foodType" id="foodType" form="addFoodForm">
                                <option value="DAIRY"> DAIRY </option>
                                <option value="PERISHABLE"> PERISHABLE </option>
                                <option value="CARBOHYDRATE"> CARBOHYDRATE </option>
                                <option value="DRINK"> DRINK </option>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="expiration" id="datepicker" form="addFoodForm">
                            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
                            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
                            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
                            <script>
                                $("#datepicker").datepicker();
                            </script>
                        </td>
                        <td>
                            <button class="but" type="submit" form="addFoodForm">Add item</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div>
            <h3>List Surplus Food Items</h3>
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
                        <th colspan="3">Actions</th>
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
                        <td>
                            <form action="getSurplusFoodDetail.do" method="post">
                                <input type="hidden" name="selectOption" value="<%=surplusfood.getId()%>">
                                <button class="but" type="submit">Update</button>
                            </form>
                        </td>
                        <td>
                            <form action="unSurplusFood.do" method="post">
                                <input type="hidden" name="selectOption" value="<%=surplusfood.getId()%>">
                                <button class="but" type="submit">Unsurplus</button>
                            </form>
                        </td>
                        <td>
                            <form action="deleteSurplusFood.do" method="post">
                                <input type="hidden" name="selectOption" value="<%=surplusfood.getId()%>">
                                <button class="but" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <br>
            <form action= "report.do" method="post">
                <input type="hidden" name="retailer" value="<%= ((User) session.getAttribute("user")).getUserName()%>">

                <% for (SurplusFood surplusfood : surplusfoods) {%>
                <input type="hidden" name="selectOption" value="<%= surplusfood.getId()%>">
                <% } %>
                <button type="submit">Save All Surplus Food Reports</button>
            </form>    

            <% if (request.getAttribute("errorReportMessage") != null) {%>
            <p style="color:red;"><%= request.getAttribute("errorReportMessage")%></p>
            <% } %>

            <% if (request.getAttribute("successReportMessage") != null) {%>
            <p style="color:green;"><%= request.getAttribute("successReportMessage")%></p>
            <% }%>
            <% if (request.getAttribute("locationMessage") != null) {%>
            <p style="color:green;"><%= request.getAttribute("locationMessage")%></p>
            <% }%>
        </div>
        <br>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">&#128075; Log out</button>
        </form>

    </body>
</html>
