<%-- 
    Document   : cp_auction_add
    Created on : Aug 6, 2014, 11:41:59 PM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new auction</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        
        <%
            String errorCode = request.getParameter("errorCode");
        %>
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%if (errorCode != null) {
                    if (errorCode.equals("1")) { %>

            <ul id="message" class="error_msg">
                <li><p>Title must contains at least 3 characters.</p></li>
            </ul>
            <% } else if (errorCode.equals("2")) { %>
            <ul id="message" class="error_msg">
                <li><p>Invalid category.</p></li>
            </ul>   

            <% } else if (errorCode.equals("3")) { %>
            <ul id="message" class="error_msg">
                <li><p>Invalid starting price or buy now price. Buy now price must be greater than starting price.</p></li>
            </ul>   

            <% } else if (errorCode.equals("4")) { %>
            <ul id="message" class="error_msg">
                <li><p>Invalid increment price.</p></li>
            </ul>   

            <% } else if (errorCode.equals("5")) { %>
            <ul id="message" class="error_msg">
                <li><p>Cover image is mandatory.</p></li>
            </ul>   

            <% } else if (errorCode.equals("6")) { %>
            <ul id="message" class="error_msg">
                <li><p>Youtube link is broken.</p></li>
            </ul>   

            <% } else if (errorCode.equals("7")) { %>
            <ul id="message" class="error_msg">
                <li><p>Starting date must be before closing date.</p></li>
            </ul>   

            <% } else if (errorCode.equals("8")) { %>
            <ul id="message" class="error_msg">
                <li><p>Starting date must be in future.</p></li>
            </ul>   

            <% } else if (errorCode.equals("9")) { %>
            <ul id="message" class="error_msg">
                <li><p>Starting date and closing date must be in at least 60 minutes difference.</p></li>
            </ul>   

            <% } else  { %>
            <ul id="message" class="error_msg">
                <li><p>Internal error!</p></li>
            </ul>   

            <% }  %>
            <br>

            <% }%>
           
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_auction_add_home.jsp" %>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
