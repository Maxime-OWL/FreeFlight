<%-- 
    Document   : notification
    Created on : Aug 7, 2014, 12:22:03 PM
    Author     : Maxime
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Opps! Something went wrong.</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%
            String errorCode = request.getParameter("errorCode");
            if (errorCode == null) {
                errorCode = "-1";
            }
        %>
    </head>
    <body>
        <jsp:include page="top.jsp" />
        <div class="header2">  
            <div id="ideal_page">

                <div class="ideal_outer">

                    <div class="ideal_top">  </div>
                    <div class="ideal_middle">
                        <% if (errorCode.equalsIgnoreCase("1")) { %>
                            <h1>Internal Error!</h1>
                            <div class="ideal_middle_bottom"><h2> Could not create new user.</h2>
                        <% } 
                        else if (errorCode.equalsIgnoreCase("2")) { %>
                            <h1>Page not found!</h1>
                            <div class="ideal_middle_bottom"><h2> Sorry, the page you requested may have been moved or deleted.</h2>
                        <% }
                        else if (errorCode.equalsIgnoreCase("3")) { %>
                            <h1>Internal Error!</h1>
                            <div class="ideal_middle_bottom"><h2> Could not update user information.</h2>
                        <% }
                        else { %>
                            <h1>Access denied!</h1>
                            <div class="ideal_middle_bottom"><h2> You have insufficient permission to perform this task</h2>
                        <% } %>

                        </div>

                        <div class="ideal_timer">

                            <div class="idal_timer_left"> </div>

                            <div class="idal_timer_middle"> 
                                <script>
                                    document.write('<a href="' + document.referrer + '">RETURN NOW</a>');
                                </script>
                            </div> 

                            <div class="idal_timer_right"> </div>


                        </div>   
                    </div>
                    <div class="ideal_bottom"> </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />

    </body>
</html>
