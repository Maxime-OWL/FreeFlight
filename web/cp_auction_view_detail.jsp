<%-- 
    Document   : cp_view_detail_auction
    Created on : Aug 7, 2014, 11:10:07 PM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Auction information</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%            String errorCode = request.getParameter("errorCode");
        %>
    </head>
    <body>        
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%if (errorCode != null) {
                    if (errorCode.equals("1")) { %>

            <ul id="message" class="success_msg">
                <li><p>Auction has been successfully updated!</p></li>
            </ul>
            <% } else if (errorCode.equals("2")) { %>
            <ul id="message" class="error_msg">
                <li><p>Invalid category.</p></li>
            </ul>   

            <% }else if (errorCode.equals("3")) { %>
            <ul id="message" class="error_msg">
                <li><p>Interal Error! Could not update auction status.</p></li>
            </ul>   

            <% } %>
            <br>
            <% } %> 
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_auction_view_detail_home.jsp" %> 
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
