<%--
  Created by IntelliJ IDEA.
  User: kiv
  Date: 11.04.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8" http-equiv="content-type">
    <link rel="stylesheet" href="views/style.css">
    <title>Create user</title>
</head>
<body>
<div id="wrapper">

    <div id="header">
        <p>Welcome to my first web-project!!!</p>
    </div>

    <div id="menu">
        <ul>
            <li><a onclick="location.href='/create'">Create user</a></li>
            <li><a onclick="location.href='/read'">List users</a></li>
            <li><a onclick="location.href='/update'">Update users</a></li>
            <li><a onclick="location.href='/delete'">Delete users</a></li>
        </ul>
    </div>

    <div id="mainBlock">
                <form method="post">
            <label><p>User name:</p>
                <input type="text" name="name" placeholder="Enter your name" required>
            </label>
            <label><p>User age:</p>
                <input type="number" name="age" value="1" min="1" max="100">
            </label>
            <button type="submit">Create user</button>
        </form>
        <%
            if (request.getAttribute("userName") != null) {
                out.println("<p>User '" + request.getAttribute("userName") + "' was created!!! " +
                        "You can check it in data base or create new user.</p>");
            }
        %>
        <p><a href="../index.html">Main page</a></p>
    </div>

</div>
</body>
</html>
