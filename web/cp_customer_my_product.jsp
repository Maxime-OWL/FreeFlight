<%-- 
    Document   : cp_customer_my_product
    Created on : Aug 20, 2014, 12:37:01 AM
    Author     : ducfpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My auctions</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />  

    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <jsp:include page="top.jsp" />
        <%            String errorCode = request.getParameter("errorCode");
            if (errorCode == null) {
                errorCode = "";
            }
        %>
        <div class="header2" id="errorTop">
            <%if (errorCode.equals("1")) {%>
            <ul id="message" class="success_msg">
                <li><p></p>Add new auction successfully!</li>
            </ul>
            <br>
            <%} else if (errorCode.equals("0")) {%>
            <ul id="message" class="success_msg">
                <li><p></p>Auction saved successfully!</li>
            </ul>
            <br>
            <%}else if (errorCode.equals("21")) {%>
            <ul id="message" class="error_msg">
                <li><p></p>You can not edit or delete this auction!</li>
            </ul>
            <br>
            <%}else {%>

            <%}%>
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_customer_my_product_home.jsp" %>   
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
