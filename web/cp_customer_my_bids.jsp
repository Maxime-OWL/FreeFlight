<%-- 
    Document   : cp_user_my_bids
    Created on : Aug 19, 2014, 10:47:21 PM
    Author     : ducfpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My bids</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />    
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_customer_my_bids_home.jsp"%>   
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
