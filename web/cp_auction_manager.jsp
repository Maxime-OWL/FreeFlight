<%-- 
    Document   : cp_auction_manager
    Created on : Aug 6, 2014, 10:33:20 PM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage action</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%            
                String errorCode = request.getParameter("errorCode");
                String auctionProcessingResult = (String) request.getAttribute("result");
                if (auctionProcessingResult==null || auctionProcessingResult.isEmpty()){
                    auctionProcessingResult = "System is up to date. All auctions are already processed. Nothing to do..";
                }
        %>
    </head>
    <body>
        <%@ include file="perm_staff.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%if (errorCode != null) {
                    if (errorCode.equals("1")) { %>

            <ul id="message" class="success_msg">
                <li><p>Your auction has been successfully created!</p></li>
            </ul>
            <% } else if (errorCode.equals("2")) { %>
            <ul id="message" class="error_msg">
                <li><p>Invalid category.</p></li>
            </ul>   

            <% }else if (errorCode.equals("3")) { %>
            <ul id="message" class="error_msg">
                <li><p>Interal Error! Could not update auction status.</p></li>
            </ul>   

            <% }else if (errorCode.equals("4")) { %>
            <ul id="message" class="success_msg">
                <li><p><%=auctionProcessingResult%></p></li>
            </ul>   

            <% } %>
            <br>
            <% } %> 
            <jsp:include page="cp_cols.jsp" />  
            <jsp:include page="cp_auction_manager_home.jsp" />
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
