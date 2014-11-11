<%-- 
    Document   : edit_profile
    Created on : Aug 4, 2014, 10:42:08 AM
    Author     : MrTu
--%>

<%@page import="Entity.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit profile</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%            String errorCode = request.getParameter("errorCode");
        %>
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%
                if (errorCode != null) {
                    if (errorCode.equals("0")) {%>
            <ul id="message" class="success_msg">
                <li><p>User profile has been updated!</p></li>
            </ul>
            <%} else if (errorCode.equals("1")) {%>
            <ul id="message" class="error_msg">
                <li><p>Failed to update user profile.</p></li>
            </ul>
            <%}
            %>
            <br>
            <%
                }
            %>
            <jsp:include page="cp_cols.jsp" />
            <jsp:include page="cp_edit_profile_home.jsp" />             
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
