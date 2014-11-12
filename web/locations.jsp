<%-- 
    Document   : index
    Created on : Jul 6, 2014, 10:38:08 PM
    Author     : MrDuc
--%>

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
            ArrayList<Location> locations = (ArrayList<Location>) request.getAttribute("locations");

            if (locations == null) {
                response.sendRedirect("LocationController?service=list");
            } else {

        %>
        <%@ include file="top.jsp" %> 
        <div class="header2">  
            <table id="logtable" border="1" width="100%">
                <tr>
                    <th width="10">ID</th>
                    <th>Location Name</th>
                    <th>Actions</th>
                </tr>
                <%                for (int i = 0; i < locations.size(); i++) {%>
                <tr>
                    <td >
                        <%=locations.get(i).getLocationId()%> 
                    </td>
                    <td>
                        <%=locations.get(i).getName() %> 
                    </td>
                    <td>
                        <input type="button" value="Book" class="blue">
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
