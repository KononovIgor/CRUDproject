<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: kiv
  Date: 11.04.2018
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8" http-equiv="content-type">
    <link rel="stylesheet" href="views/style.css">
    <title>List users</title>
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
        <%
            List<User> listUsers = (List<User>) request.getAttribute("listUsers");
            if ( (listUsers != null) && (!listUsers.isEmpty()) ) {
                out.println("<table>");
                    out.println("<tr>");
                        out.println("<th>");
                            out.println("ID");
                        out.println("</th>");
                        out.println("<th>");
                            out.println("User");
                        out.println("</th>");
                        out.println("<th>");
                            out.println("Age");
                        out.println("</th>");
                    out.println("</tr>");
                    for (User user : listUsers) {
                        out.println("<tr>");
                            out.println("<td>");
                                out.println(user.getId());
                            out.println("</td>");
                            out.println("<td>");
                                out.println(user.getName());
                            out.println("</td>");
                            out.println("<td>");
                                out.println(user.getAge());
                            out.println("</td>");
                        out.println("</tr>");
                    }
                out.println("</table>");
            } else {
                out.println("Data base is empty!");
            }
        %>
        <p><a href="../index.html">Main page</a></p>
    </div>

</div>
</body>
</html>
