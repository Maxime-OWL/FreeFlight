<%-- 
    Document   : cp_edit_user
    Created on : Aug 7, 2014, 10:38:53 PM
    Author     : Duc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit user information</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
    </head>
    <body>
        <%@ include file="perm_admin.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_user_edit_home.jsp" %>             
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
