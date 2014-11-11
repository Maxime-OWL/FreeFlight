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
        <title>Admin Control Panel</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%
            String current_page = request.getParameter("current_page");
            //int rank = -1;
            //if (session.getAttribute("role") != null) {
            //rank = Integer.parseInt((String) session.getAttribute("role"));
            //}
            int rank = 1;
        %>
    </head>
    <body>
        <div class="banner_left">
            <div class="dash_tops">Control Panel</div>
            <div class="dash_lsd user_panel_list">
                <ul>
                    <%if ((current_page != null) && current_page.equalsIgnoreCase("dashboard")) { %>
                    <li class="act_class" id="dashboard_active"><a href="cp.jsp?current_page=dashboard" title="Manage User">Dashboard</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="cp.jsp?current_page=dashboard" title="Manage User">Dashboard</a></li>
                        <% }
                            if (rank >= 0) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("my_account")) { %>
                    <li class="act_class" id="dashboard_active"><a href="cp_my_account.jsp?current_page=my_account" title="My account">My account</a></li>
                        <%  } else { %>
                    <li class="" id="edit_profile_active"><a href="cp_my_account.jsp?current_page=my_account" title="My account">My account</a></li>
                        <%  }
                            }
                            if (rank == 1) {
                        %>
                    <li class="" id=""><a href="#" title="View Tickets">View Tickets</a></li>
                    <li class="" id=""><a href="cp_add_flight.jsp" title="Add Flight">Add Flight</a></li>
                    <li class="" id=""><a href="cp_add_location.jsp" title="Add Location">Add Location</a></li>
                    <li class="" id=""><a href="#" title="Edit Location">Edit Location</a></li>
                    <li class="" id=""><a href="cp_add_plane.jsp" title="Add Plane">Add Plane</a></li>
                    <li class="" id=""><a href="#" title="Edit Plane">Edit Plane</a></li>
                    <li class="" id=""><a href="#" title="Delay Flight">Delay Flight</a></li>
                        <%
                            }
                        %>                   
                </ul>
            </div>
        </div>
    </body> 

</html>
