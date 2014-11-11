<%-- 
    Document   : cp_customer_my_watchlist
    Created on : Aug 20, 2014, 12:17:40 AM
    Author     : ducfpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My watchlist</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" /> 
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <%            String errorCode = request.getParameter("errorCode");
        %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%if (errorCode.equals("0")) {%>
            <ul id="message" class="error_msg">
                <li><p></p>Could not delete this item from watchlist.</li>
            </ul>
            <br>
            <%} else if (errorCode.equals("1")) {%>
            <ul id="message" class="success_msg">
                <li><p></p>Deleted item from watchlist successfully!</li>
            </ul>
            <br>
            <%} else {%>

            <%}%>
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_customer_my_watchlist_home.jsp" %>   
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
