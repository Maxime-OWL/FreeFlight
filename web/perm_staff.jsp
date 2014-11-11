<%-- 
    Document   : perm_admin
    Created on : Aug 6, 2014, 3:08:10 PM
    Author     : Maxime
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("role") == null || (Integer.parseInt( (String) session.getAttribute("role")) != 1)) {
                response.sendRedirect("notification.jsp");
            }
        %>

    </body>
</html>
