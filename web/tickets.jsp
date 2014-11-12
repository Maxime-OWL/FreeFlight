<%-- 
    Document   : index
    Created on : Jul 6, 2014, 10:38:08 PM
    Author     : MrDuc
--%>

<%@page import="Entity.Ticket"%>
<%@page import="Entity.Plane"%>
<%@page import="Entity.Location"%>
<%@page import="Entity.Flight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Free Flight System</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="stylesheet" href="css/table.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script language="JavaScript" type="text/javascript" src="JavaScript/permissions.js"></script>
    </head>
    <body>
        <%
            ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("tickets");

            if (tickets == null) {
                response.sendRedirect("TicketController?service=list");
            } else {

        %>
        <%@ include file="top.jsp" %> 
        <div class="header2">  
            <table id="logtable" border="1" width="100%">
                <tr>
                    <th width="10">ID</th>
                    <th>Locations Info</th>
                    <th>Datelines</th>
                    <th>Customer Info</th>
                    <th>Actions</th>
                </tr>
                <%                for (int i = 0; i < tickets.size(); i++) {%>
                <tr>
                    <td >
                        <%=tickets.get(i).getTicketId()%> 
                    </td>
                    <td>
                        From <%=tickets.get(i).getOrigin()%> to <%=tickets.get(i).getDestination()%> 
                    </td>
                    <td>
                        Depart at: <%=tickets.get(i).getDepatureDate()%>, Arrive at: <%=tickets.get(i).getArrivalDate()%> 
                    </td>
                    <td>
                        <%=tickets.get(i).getCustomerName()%> 
                    </td>
                    <td>
                        <input type="button" value="Flights" class="blue"> <input type="button" value="Delete" class="blue">
                    </td>
                </tr>

                <% }
                %>
            </table>
        </div>
        <jsp:include page="footer.jsp" />
        <%            }
        %>

    </body> 

</html>
