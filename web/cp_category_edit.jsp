<%-- 
    Document   : cp_edit_category
    Created on : Aug 8, 2014, 2:15:11 PM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit category</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script type="text/javascript" src="JavaScript/validate_category_edit.js"></script>
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <div id="error">
            </div>
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_category_edit_home.jsp" %>                    
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
