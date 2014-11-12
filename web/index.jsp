<%-- 
    Document   : index
    Created on : Jul 6, 2014, 10:38:08 PM
    Author     : MrDuc
--%>

<%@page import="Entity.Flight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Free Flight System</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script language="JavaScript" type="text/javascript" src="JavaScript/permissions.js"></script>
    </head>
    <body>
        <%
            ArrayList<Flight> flights = (ArrayList<Flight>) request.getAttribute("flights");

            if (flights == null) {
                response.sendRedirect("FlightController?service=index");
            } else {

        %>
        <%@ include file="top.jsp" %> 
        <div class="header2">  
            <%@ include file="home.jsp" %>
        </div>
        <jsp:include page="footer.jsp" />
        <%            }
        %>
     
    </body> 

</html>
