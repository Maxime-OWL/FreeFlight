<%-- 
    Document   : cp_customer_my_watchlist_home
    Created on : Aug 20, 2014, 12:18:11 AM
    Author     : ducfpt
--%>

<%@page import="Entity.Auction"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />  
        <link type="text/css" href="css/en_style.css" rel="stylesheet" media="screen" />
        <%
            ArrayList<Auction> auctions = (ArrayList<Auction>) request.getAttribute("auctions");
        %>
    </head>
    <body>
        <div class="my_message_right" id="watchlist_page">
            <div class="message_common_border">
                <h1 title="MY WATCHLIST">MY WATCHLIST</h1>
                <p>&nbsp;</p>
            </div>
            <div class="message_common">
                <div class="forms_common">
                    <div class="title_cont_watchilist">
                        <%
                            

                            if (auctions.size() <= 0 ) {
                        %>
                        <h4 class="">No items</h4>

                        <%
                        } else {

                        %>
                        <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table-top">
                            <thead>
                                <tr>
                                    <th width="100" align="center">
                                        <b>Image</b>
                                    </th>
                                    <th width="100" align="center">
                                        <b>Title</b>
                                    </th>
                                    <th width="100" align="center">
                                        <b>End Time</b>
                                    </th>
                                    <th width="100" align="center">
                                        <b>Actions</b>
                                    </th>
                                </tr>
                            </thead>
                            <% for (int i = 0; i < auctions.size(); i++) {
                            %>
                            <tbody><tr>
                                    <td width="100" align="center">
                                        <h3><a href="#" title="<%=auctions.get(i).getTitle()%>">



                                                <img src="<%=auctions.get(i).getImgCover()%>" width="65" height="65" alt="Keyboard"></a></h3>
                                    </td>

                                    <td width="100" align="center">
                                        <a title="<%=auctions.get(i).getTitle()%>" href="AuctionController?service=view&auctionId=<%=auctions.get(i).getId()%>"><%=auctions.get(i).getTitle()%></a>
                                    </td>
                                    <td width="100" align="center">
                                        <%=auctions.get(i).getFormattedEndDate()%>		</td>
                                    <td width="100" align="center">
                                        <a href="WatchlistController?service=delwatchlist&auction_id=<%=auctions.get(i).getId()%>" title="Delete" onclick="return confirm('Are you sure want to delete this auction from your watchlist?')">
                                            <img src="images/delete.png" alt="Delete">
                                        </a>
                                    </td>
                                </tr>

                            </tbody>
                            <%
                                }
                            %>
                        </table>
                        <%
                            }
                        %>
                    </div>

                    <div class="user" style="display:none;">344</div>


                </div>
            </div>

        </div>
    </body>
</html>
