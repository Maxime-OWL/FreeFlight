<%-- 
    Document   : top
    Created on : Jul 19, 2014, 6:38:42 PM
    Author     : Maxime
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.joda.time.DateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%
            DateTime serverTime = DateTime.now();
            long serverTimeLong = serverTime.getMillis();
        %>
        <script>

            //alert(serverTime);
            //alert(clientTime);
            //alert(diffTime);
            function startTime() {
                var serverTime = new Date(<%=serverTimeLong%>);
                var clientTime = new Date();
                var diffTime = Date(serverTime - clientTime);
                var currentTime = new Date(diffTime);//new Date();
                var h = currentTime.getHours();
                var m = currentTime.getMinutes();
                var s = currentTime.getSeconds();
                m = checkTime(m);
                s = checkTime(s);
                document.getElementById('hour').innerHTML = h;
                document.getElementById('min').innerHTML = m;
                document.getElementById('sec').innerHTML = s;
                var t = setTimeout(function() {
                    startTime();
                }, 500);
                //startTime();
            }

            function checkTime(i) {
                if (i < 10) {
                    i = "0" + i
                }
                ;
                return i;
            }
        </script>
        <%
            String userName = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            
        %>
        <script type="text/javascript" src="JavaScript/ajax_top.js"></script>
        <script type="text/javascript" src="JavaScript/validate_auction_search.js"></script>
    </head>
    <body onload="startTime()">
        <div id="header">
            <div class="header_inner">
                <div class="header_lft">
                    <ul>
                        <p>Server time:</p>
                        <li><img src="images/time_bg.png" width="18" height="18"></li>
                        <li><a title="Peak Auction" id="hour"></a> <a title="">Hours</a></li>
                        <li><a title="Peak Auction" id="min"></a> <a title="">Minute</a></li>
                        <li><a title="Peak Auction" id="sec"></a> <a title="">Second</a></li>
                    </ul> 
                </div>
                <div class="header_rgt">
                    <div class="header_right">
                        <ul>

                            <%
                                if (userName == null || role == null) {
                            %>

                            <li><a href="login.jsp" title="Sign in">Sign in</a></li>
                            <li class="active"><a href="register.jsp" title="Register">Register</a></li>                           
                                <%} else {%>

                            <li><a href="#" title="Customer"><%if (role.equals("0")) { %>User<% } else if (role.equals("1")) { %>Staff<% } else if (role.equals("2")) { %>Admin<% } %> - <%=userName%></a></li>
                            <li class="active"><a href="UserController?service=logout" title="Signout" onclick="return confirm('Are you sure?')">Sign out</a></li>

                            <%}%>

                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="header2">
            <div class="header2_lft" id="header2_lft">
                <h1 title="Online Auction System">
                    <a href="index.jsp" title="Online Auction System" class="fl">
                        <img src="images/logo.jpg" alt="Online Auction System" title="Online Auction System" border="0" > 
                    </a>
                </h1>	
            </div>

            <div class="header2_rgt">
                <div class="dash_rgt">
                    <ul id="ajax_load_top_balance">
                    </ul>
                    <ul> 
                        <li>
                            <!--
                            <div class="search_total">
                                <div class="search_total_lft"> </div>
                                <div class="search_total_midd">
                                    <div class="search_lft">
                                        <div class="search_icon"></div>


                                        <form action="AuctionController" id="user_search" name="user_search" method="get" onSubmit="return isSearchKeywordValid()">
                                            <input type="hidden" name="service" value="search_auction">
                                            <input type="text" value="" name="keyword" id="keyword" class="fl" maxlength="300">
                                            </div>
                                            <div class="search_rgt">
                                                <div class="search_button">
                                                    <div class="search_button_lft"></div>
                                                    <div class="search_button_mid" style="cursor:pointer;">
                                                        <p style="cursor:pointer;"><a class="fl" onclick="return isSearchKeywordValid()" title="SEARCH">SEARCH</a></p>
                                                    </div>
                                                    <div class="search_button_rgt"></div>
                                                </div>
                                            </div>
                                        </form>


                                    </div>

                                    <div class="search_total_rgt"></div>
                                </div>
                                            -->
                        </li>
                    </ul>
                </div>
            </div>

        </div>


        <div id="header_menu">
            <div class="header_menu_inner">
                
                <ul>
                    <li id="home_menu"> <a href="index.jsp" title="Flights"> Flights</a></li>
                    <%if (role != null ) { %>
                        <li id="home_menu"> <a href="locations.jsp" title="Locations"> Locations</a></li>
                        <li id="home_menu"> <a href="planes.jsp" title="Planes"> Planes</a></li>
                        <% if (role.equals("1")) { %>
                            <li id="home_menu"> <a href="tickets.jsp" title="Tickets"> Tickets</a></li>
                        <% } %>
                    <% } %>
                </ul>
            </div>
        </div>
        <br>
        <br>
        <br>

    </body>
</html>
