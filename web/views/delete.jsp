<%--
  Created by IntelliJ IDEA.
  User: kiv
  Date: 11.04.2018
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8" http-equiv="content-type">
    <link rel="stylesheet" href="views/style.css">
    <title>Delete users</title>
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
            <p>Please, choose user's id for deleting</p>
            <form method="post">
                <label><p>User id:</p>
                    <input type="number" name="userId" value="0" min="1">
                </label>
                <button type="submit">Submit</button>
            </form>
            <%
                if (request.getAttribute("successDeletingId") != null) {
                    out.println("<p>User with id=" + request.getAttribute("successDeletingId") +
                        " was deleted</p>");
                }
            %>
            <p><a href="../index.html">Main page</a></p>
        </div>

    </div>
</body>
</html>
