<%@ page import="com.sdc.webdev.model.User" %>
<%@ page import="com.sdc.webdev.model.PhoneNumber" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Welcome, <%= ((User) session.getAttribute("user")).getUsername() %></h1>
<form action="${pageContext.request.contextPath}/signout" method="post">
    <button type="submit">Sign Out</button>
</form>

<h1>Enter a Number</h1>
<form action="doubleNumber" method="get">
    <label for="number">Number:</label>
    <input type="text" id="number" name="number">
    <button type="submit">Send</button>
</form>

<h2>Phone Book</h2>
<form method="POST" action="${pageContext.request.contextPath}/phonebook">
    <div>
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required>
    </div>
    <div>
        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname" required>
    </div>
    <div>
        <button type="submit">Add Phone Number</button>
    </div>
</form>

<form action="${pageContext.request.contextPath}/editNumber" method="get">
    <button type="submit">Edit Number</button>
</form>

<% if (request.getAttribute("success") != null) { %>
<div><strong><%= request.getAttribute("success") %></strong></div>
<% } %>

<h3>Phone Numbers List</h3>
<ul>
    <% List<PhoneNumber> phoneNumbers = (List<PhoneNumber>) request.getAttribute("phoneNumbers");
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            for (PhoneNumber number : phoneNumbers) {
    %>
    <li><%= number.getPhoneNumber() %> - <%= number.getSurname() %></li>
    <%   }
    } else {
    %>
    <li>No phone numbers added yet.</li>
    <%   }
    %>
</ul>


</body>
</html>

