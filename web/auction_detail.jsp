<%-- 
    Document   : cp_detail_bidding
    Created on : Aug 15, 2014, 10:32:41 AM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="top.jsp" />
        <div class="header2">  
            <div id="top_message">
                <!--<ul id="message" class="success_msg">
                    <li>Add to watchlist fail.</li>
                </ul>-->
            </div>
            <%@ include file="auction_detail_home.jsp" %>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
