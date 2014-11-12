<%-- 
    Document   : index
    Created on : Jul 6, 2014, 10:38:08 PM
    Author     : MrDuc
--%>

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
            ArrayList<Plane> planes = (ArrayList<Plane>) request.getAttribute("planes");

            if (planes == null) {
                response.sendRedirect("PlaneController?service=list");
            } else {

        %>
        <%@ include file="top.jsp" %> 
        <div class="header2">  
            <table id="logtable" border="1" width="100%">
                <tr>
                    <th width="10">ID</th>
                    <th>Model</th>
                    <th>Capacity</th>
                    <th>Actions</th>
                </tr>
                <%                for (int i = 0; i < planes.size(); i++) {%>
                <tr>
                    <td >
                        <%=planes.get(i).getPlaneId()%> 
                    </td>
                    <td>
                        <%=planes.get(i).getModel() %> 
                    </td>
                    <td>
                        <%=planes.get(i).getCapacity()%> 
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
