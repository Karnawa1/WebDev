<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Double Number Result</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Result</h1>
<p>Original Number: ${originalNumber}</p> <!-- Using Expression Language to display the attribute -->
<p>Doubled Number: ${doubledNumber}</p>
<a href="${pageContext.request.contextPath}/home" class="home-button">Go Home</a>
</body>
</html>

