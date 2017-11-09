<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th/>
        <th/>
    </tr>

    <c:forEach var="exceedMeals" items="${list}">
        <c:choose>
            <c:when test="${exceedMeals.exceed}">
                <tr style="color: red">
            </c:when>
            <c:otherwise>
                <tr style="color: green">
            </c:otherwise>
        </c:choose>
        <td style="color: darkgrey">${exceedMeals.id}</td>
        <fmt:parseDate value="${exceedMeals.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime }" var="parsedDate"/>
        <td>${parsedDate}</td>
        <td>${exceedMeals.description}</td>
        <td>${exceedMeals.calories}</td>
        <td style="color: black">
            <a href="meal.jsp"/>Edit</a>
        </td>
        <td style="color: black">
            <a href="meals?action=delete&mealId=<c:out value="${exceedMeals.id}"/>">Delete</a>
        </td>
        </tr>

    </c:forEach>
</table>
<br/>
<br/>
<h3><a href="meal.jsp">Add</a></h3>
</body>
</html>
