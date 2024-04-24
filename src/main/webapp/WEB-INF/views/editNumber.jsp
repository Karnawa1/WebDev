<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Phone Number</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Edit Phone Number</h2>

<% if (request.getAttribute("error") != null) { %>
<div style="color: red;"><%= request.getAttribute("error").toString() %></div>
<% } %>

<form action="${pageContext.request.contextPath}/editNumber" method="post">
    <input type="hidden" name="action" value="update">
    <label for="number">Number:</label>
    <input type="text" id="number" name="Number" required><br>
    <label for="surname">Surname:</label>
    <input type="text" id="surname" name="surname" required><br>
    <button type="submit" class="button">Update Number</button>
</form>
<form action="${pageContext.request.contextPath}/editNumber" method="post">
    <input type="hidden" name="action" value="delete">
    <label for="numberDelete">Number to delete:</label>
    <input type="text" id="numberDelete" name="Number" required><br>
    <button type="submit" class="button" onclick="return confirm('Are you sure you want to delete this number?');">Delete Number</button>
</form>
<a href="${pageContext.request.contextPath}/home">Return to Home</a>
</body>
</html>