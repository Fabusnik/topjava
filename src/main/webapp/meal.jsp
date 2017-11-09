<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<h3><a href="meals">Add</a></h3>
<form action="meals" method="post">
    <table>
        <tr>
            <td>Date:</td>
            <td>
                <input type="datetime-local" name="date"/>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <input type="text" name="description"/>
            </td>
        </tr>
        <tr>
            <td>Calories</td>
            <td>
                <input type="text" name="calories"/>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" value="add">Add</button>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
