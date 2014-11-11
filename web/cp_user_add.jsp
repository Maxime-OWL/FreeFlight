<%-- 
    Document   : index
    Created on : Jul 6, 2014, 10:38:08 PM
    Author     : MrDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new user</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%
            String errorCode = request.getParameter("errorCode");
        %>
        <script type="text/javascript" src="JavaScript/validate_user_add.js"></script>
    </head>
    <body>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <div id = "error">
            <%if (errorCode != null) {
                   if (errorCode.equals("3")) { %>
            <ul id="message" class="error_msg">
                <li><p>Username or email address is already in use.</p></li>
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

            <% } else if (errorCode.equals("0")) { %>
            <ul id="message" class="success_msg">
                <li><p>User has been successfully created, account password has been sent to registered email address!</p></li>
            </ul>   

            <% } else  { %>
            <ul id="message" class="error_msg">
                <li><p>Internal error!</p></li>
            </ul>   

            <% }  %>
            <br>

            <% }%>
            </div>
            <jsp:include page="cp_cols.jsp" />
            <jsp:include page="cp_user_add_home.jsp" />             
        </div>
        <jsp:include page="footer.jsp" />
    </body> 

</html>
