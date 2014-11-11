<%-- 
    Document   : cp_change_password
    Created on : Aug 7, 2014, 12:33:29 AM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change password</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script src="JavaScript/validate_password.js"></script>
    </head>
    <body>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%                
                String errorCode = request.getParameter("errorCode");
            %>
            <% if (errorCode != null && errorCode.equals("0")) {%>
            <ul id="message" class="success_msg">
                <li><p>Account password has been successfully changed!</p></li>
            </ul>
            <br>
            <%} else if (errorCode != null && errorCode.equals("1")) {%>
            <ul id="message" class="error_msg">
                <li><p>Password must contains at least 6 characters.</p></li>
            </ul>
            <br>
            <%}else if(errorCode != null && errorCode.equals("2")){%>
                 <ul id="message" class="error_msg">
                <li><p>New password and re-type new password don't match.</p></li>                
            </ul>
                 <br>
            <%}else if(errorCode != null && errorCode.equals("3")){%>
                 <ul id="message" class="error_msg">
                <li><p>New password and current password can not be the same.</p></li>
            </ul>  
                 <br>
            <%}else if(errorCode != null && errorCode.equals("4")){%>
                 <ul id="message" class="error_msg">
                <li><p>Current password is incorrect.</p></li>
            </ul>
                 <br>
            <%}%>
            <jsp:include page="cp_cols.jsp" />
            <%@ include file="cp_change_password_home.jsp" %>             
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
