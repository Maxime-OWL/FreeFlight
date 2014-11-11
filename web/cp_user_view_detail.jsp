<%-- 
    Document   : view_detail_user
    Created on : Aug 7, 2014, 10:33:15 PM
    Author     : Duc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User information</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%            String errorCode = request.getParameter("errorCode");
        %>
    </head>
    <body>
        <%@ include file="perm_admin.jsp" %>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <%if (errorCode != null) {
                    if (errorCode.equals("1")) { %>

            <ul id="message" class="success_msg">
                <li><p>User '<%=request.getParameter("username")%>' has been successfully updated!</p></li>
            </ul>
            <% } 
                    else if (errorCode.equals("2")) { %>
            <ul id="message" class="success_msg">
                <li><p>User '<%=request.getParameter("username")%>' has been successfully created!</p></li>
            </ul>   
                    
                    <% } %>
            <br>

            <%
                }
            %>
            <jsp:include page="cp_cols.jsp" /> 
            <%@ include file="cp_user_view_detail_home.jsp" %>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
