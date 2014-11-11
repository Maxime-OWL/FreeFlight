<%-- 
    Document   : cp_edit_auction
    Created on : Aug 8, 2014, 1:10:24 PM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit auction</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
    </head>
    <body>
        <%@ include file="perm_staff.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_edit_auction_home.jsp" %>                    
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
