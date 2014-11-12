<%-- 
    Document   : home
    Created on : Jul 19, 2014, 10:37:29 PM
    Author     : Maxime
--%>

<%@page import="Entity.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Free Flight System</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="stylesheet" href="css/table.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script language="JavaScript" src="JavaScript/countdown.js"></script>
    </head>
    <body>
        <table id="logtable" border="1" width="100%">
            <tr>
                <th>Flight ID</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Fee</th>
                <th>Plane</th>
                <th>Departure Date</th>
                <th>Arrival Date</th>
            </tr>
            <%
            for (int i = 0; i < flights.size(); i++) {%>
            <tr>
                <td>
                    <%=flights.get(i).getFlightId()%> 
                </td>
                <td>
                    <%=flights.get(i).getOrigin()%> 
                </td>
                <td>
                    <%=flights.get(i).getDestination()%> 
                </td>
                <td>
                    <%=flights.get(i).getFee()%> 
                </td>
                <td>
                    <%=flights.get(i).getPlane()%> 
                </td>
                <td>
                    <%=flights.get(i).getDepartureDate()%> 
                </td>
                <td>
                    <%=flights.get(i).getArrivalDate()%> 
                </td>
            </tr>
            
            <% }
            %>
        </table>
    </body>
</html>
