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
            int rank = -1;
            if (session.getAttribute("role") != null) {
                rank = Integer.parseInt((String) session.getAttribute("role"));
            }

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
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="cp_my_account.jsp?current_page=my_account" title="My account">My account</a></li>
                        <% }
                            }
                            if (rank == 0) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("my_watchlist")) { %>
                    <li class="act_class" id="dashboard_active"><a href="WatchlistController?service=viewwatchlist" title="My watchlist">My watchlist</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="WatchlistController?service=viewwatchlist" title="My watchlist">My watchlist</a></li>
                        <% }
                            }
                            if (rank == 0) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("my_product")) { %>
                    <li class="act_class" id="dashboard_active"><a href="AuctionController?service=myproduct" title="My auctions">My auctions</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="AuctionController?service=myproduct" title="My auctions">My auctions</a></li>
                        <% }
                            }
                            if (rank == 0) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("my_bids")) { %>
                    <li class="act_class" id="dashboard_active"><a href="BidController?service=mybids" title="My bids">My bids</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="BidController?service=mybids" title="My bids">My bids</a></li>
                        <% }
                            }
                            if (rank == 0) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("my_transactions")) { %>
                    <li class="act_class" id="dashboard_active"><a href="TransactionController?service=mytransaction" title="My transactions">My transactions</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="TransactionController?service=mytransaction" title="My transactions">My transactions</a></li>
                        <% }
                            }
                            if (rank == 0) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("won_auction")) { %>
                    <li class="act_class" id="dashboard_active"><a href="cp_customer_auction_won.jsp?current_page=won_auction" title="Won auction">Won auction</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="cp_customer_auction_won.jsp?current_page=won_auction" title="Won auction">Won auction</a></li>
                        <% }
                            }
                            if (rank == 2) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("user_manager")) { %>
                    <li class="act_class" id="dashboard_active"><a href="UserController?service=user_manager" title="Manage User">Manage user</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="UserController?service=user_manager" title="Manage User">Manage user</a></li>
                        <% }
                            }
                            if (rank == 1) {
                                if ((current_page != null) && current_page.equalsIgnoreCase("category_manager")) { %>
                    <li class="act_class" id="dashboard_active"><a href="CategoryController?service=category_manager" title="Manage Category">Manage category</a></li>
                        <% } else { %>
                    <li class="" id="edit_profile_active"><a href="CategoryController?service=category_manager" title="Manage Category">Manage category</a></li>
                        <% }
                            } %>

                    <%
                        if (rank == 1) {
                            if ((current_page != null) && current_page.equalsIgnoreCase("auction_manager")) { %>
                    <li class="act_class" id="dashboard_active"><a href="AuctionController?service=auction_manager" title="Manage Auction">Manage auction</a></li>
                        <% } else { %>
                    <li class="" id="dashboard_active"><a href="AuctionController?service=auction_manager" title="Manage Auction">Manage auction</a></li>
                        <% }
                            }
                        %>                   
                </ul>
            </div>
        </div>
    </body> 

</html>
