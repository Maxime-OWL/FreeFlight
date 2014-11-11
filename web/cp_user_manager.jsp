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
        <title>Manage user</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%            
            String errorCode = request.getParameter("errorCode");
        %>
    </head>
    <body>
        <%@ include file="perm_admin.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%if (errorCode != null) {
                    if (errorCode.equals("1")) { %>

            <ul id="message" class="success_msg">
                <li><p>User successfully deleted.</p></li>
            </ul>
            <% } 
                    else if (errorCode.equals("2")) { %>
            <ul id="message" class="error_msg">
                <li><p>Failed to delete this user.</p></li>
            </ul>   
                    
                    <% } else if (errorCode.equals("3")) { %>
            <ul id="message" class="success_msg">
                <li><p>Category has been successfully added!</p></li>
            </ul>   

            <% } else if (errorCode.equals("4")) { %>
            <ul id="message" class="error_msg">
                <li><p>Username must contains at least 3 characters and at most 20 characters.</p></li>
            </ul>   

            <% } %>
            <br>

            <%
                }
            %>
            <jsp:include page="cp_cols.jsp" />
            <jsp:include page="cp_user_manager_home.jsp" />             
        </div>
        <jsp:include page="footer.jsp" />
    </body> 

</html>
