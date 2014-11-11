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
        <title>Control panel</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%
            String balance = (String) request.getAttribute("balance");
        %>
    </head>
    <body>
             
        <div class="my_message_right">
            <div class="message_common_border">
                <h1 title="mydashboard">Dashboard</h1>
                <p>&nbsp;</p>
            </div>
            <div class="message_common">
                <div class="dasbord_common">
                    <div class="das_inner">
                        <!--
                        <div class="das_inner_text">
                            <h3>Things to do :</h3>
                        </div>
                        -->
                        <%                            if (session.getAttribute("role") != null) {
                                if (Integer.parseInt((String) session.getAttribute("role")) == 2) {
                        %>
                        <div class="roled_images">
                            <div class="inner_rol_img">
                                <div class="box_top"></div>
                                <div class="box_mid">
                                    <div class="bot_img">
                                        <div class="bot_com">&nbsp;</div>
                                        <a href="cp_user_manager.jsp" title="Manage Users"><h4>Manage Users</h4></a>
                                    </div>
                                </div>
                                <div class="box_bot"></div>
                            </div>
                            <%
                            } else if (Integer.parseInt((String) session.getAttribute("role")) == 1) {
                            %>

                            <div class="inner_rol_img">
                                <div class="box_top"></div>
                                <div class="box_mid">
                                    <div class="bot_img">
                                        <div class="bot_com1">&nbsp;</div>
                                        <a href="AuctionController?service=auction_manager" title="Manage Auction"><h4>Manage Auction</h4></a>
                                    </div>
                                </div>
                                <div class="box_bot"></div>
                            </div>

                            <div class="inner_rol_img">
                                <div class="box_top"></div>
                                <div class="box_mid">
                                    <div class="bot_img">
                                        <div class="bot_com2">&nbsp;</div>
                                        <a href="CategoryController?service=category_manager" title="View My Watchlist"><h4>Manage Category</h4></a>
                                    </div>
                                </div>
                                <div class="box_bot"></div>
                            </div>
                            <%
                            } else if (Integer.parseInt((String) session.getAttribute("role")) == 0) {
                            %>
                            <div class="common_bounce">
                                <div class="common_bounce_text">
                                    <h2 title="My Dashboard">My Dashboard</h2>                                   
                                    <p>You currently have<span class="red_col"> <font class=""></font><%=balance%></span> in your account.</p>            
                                </div>
                            </div>
                            <%                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body> 

</html>
