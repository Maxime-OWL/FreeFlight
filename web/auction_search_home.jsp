<%-- 
    Document   : home
    Created on : Jul 19, 2014, 10:37:29 PM
    Author     : Maxime
--%>

<%@page import="Entity.Auction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Auction System</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script language="JavaScript" src="JavaScript/countdown.js"></script>
        <script language="JavaScript" src="JavaScript/ajax_watchlist.js"></script>
        <%
            ArrayList<Auction> auctionsOnGoing = (ArrayList<Auction>) request.getAttribute("auctionsOnGoing");
            ArrayList<Auction> auctionsFuture = (ArrayList<Auction>) request.getAttribute("auctionsFuture");
            ArrayList<Auction> auctionsClosed = (ArrayList<Auction>) request.getAttribute("auctionsClosed");
        %>
    </head>
    <body>

        <% int sum = auctionsClosed.size() + auctionsFuture.size() + auctionsOnGoing.size();
            if (sum <= 0) {%>
        <div class="">
            <div class="message_common">
                Your search <span style="font-size:18px;"><%=keyword%></span> did not match any auctions...Try different keywords				</h4> 
            </div>
        </div>
        <% } else {%>
        <div class="">
            <div class="message_common">
                Your search <span style="font-size:18px;"><%=keyword%></span> matched <%=sum%> auction(s). Here are the results...				</h4> 
            </div>
        </div>  

        <% }
            if (auctionsOnGoing.size() > 0) { %>
        <div class="today_head">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                <tr>
                    <td>
                        <div class="today_head_lft">
                            <h2 title="">On-going</h2>
                        </div>
                    </td>
                    <td>
                        <div class="arrow_one"></div>
                    </td>
                </tr>
            </table>
        </div>

        <div class="feature_total">
            <%
                for (int i = 0; i < auctionsOnGoing.size(); i++) {
            %>
            <div id="auction_502" class="auction_item auction_item_content" data-id="502">
                <div class="auction_type" data-auctiontype="25"></div>
                <div class="feature_box">
                    <div class="feature_lable"></div>
                    <div class="feature_lft">                        
                        <a href="AuctionController?service=view&auctionId=<%=auctionsOnGoing.get(i).getId()%>" title="<%=auctionsOnGoing.get(i).getTitle()%>">
                            <img src="<%=auctionsOnGoing.get(i).getImgCover()%>" width="82" title="" height="83" alt="Monitor">
                        </a>                           
                    </div>
                    <div class="feature_rgt">
                        <label><a href="AuctionController?service=view&auctionId=<%=auctionsOnGoing.get(i).getId()%>" title="<%=auctionsOnGoing.get(i).getTitle()%>">
                                <%=auctionsOnGoing.get(i).getTitle()%>
                            </a></label>
                        <ul style="width:130px;">  
                            <li>
                                <b>Bid to beat: </b><br>
                                <div style="color: rgb(238, 126, 63); font-weight: bold; display: block;" class="currentprice"><font class=""></font><%=auctionsOnGoing.get(i).getBidToBeatString()%></div>
                                <div style="color:#EE7E3F;font-weight:bold;" class="loader502"></div>

                                <p></p>
                            </li>

                            <li>
                                <b><span style="color:#000;">Buy now: <br><span><font class=""></font><%=auctionsOnGoing.get(i).getBuynowPriceString()%></span></span></b>

                                <div class="bid_price fl clr" style="display:none;"><p class="fl clr">Bid increment: <font class="">$</font> 2</p></div>
                            </li>
                        </ul>
                        <p class="countdown" style="display: block;">

                            <script>
                                startCountdown('<%=auctionsOnGoing.get(i).getFormattedEndDate(1)%>', <%=auctionsOnGoing.get(i).getId()%>, "countdown_auction_home");
                            </script>
                        </p>
                    </div>
                    <div class="feature_bott">
                        <div id="watchlist_area_<%=auctionsOnGoing.get(i).getId()%>" class="feature_bott_lft" >
                            <div class="sliders">
                                <label style="display:block;color:#666;padding-top: 3px;;padding-left: 10px ">
                                    <span>Add to watchlist</span>
                                </label>
                            </div>
                            <a title="+ Add to Watchlist" rel="502" class="addwatchlist" href="#" onClick="return addToWatchList(<%=auctionsOnGoing.get(i).getId()%>);"><img src="images/plus_bg.png" width="24" height="25" alt="+ Add to Watchlist"></a>
                        </div>
                        <div class="feature_bott_rgt" style="margin:0 10px 0 0;">
                            <div class="bidme_link">
                                <div class="bidnow_lft"></div>
                                <div class="bidnow_mid">
                                    <p>  <a href="AuctionController?service=view&auctionId=" class="bid" title="BID NOW" rel="" id="502" data-auctiontype="25">
                                            BID NOW                                        </a></p>
                                </div>
                                <div class="bidnow_rgt"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="notice_nsg fl clr" id="notice_msg502" style="display:none;"></div>
            </div>
            <% } %>
        </div>
        <% } %>




        <% if (auctionsFuture.size() > 0) { %>
        <div class="today_head">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                <tr>
                    <td>
                        <div class="today_head_lft">
                            <h2 title="">Future</h2>
                        </div>
                    </td>
                    <td>
                        <div class="arrow_one"></div>
                    </td>

                </tr>
            </table>
        </div>

        <div class="feature_total">
            <%
                for (int i = 0; i < auctionsFuture.size(); i++) {
            %>
            <div id="auction_502" class="auction_item auction_item_content" data-id="502">
                <div class="auction_type" data-auctiontype="25"></div>
                <div class="feature_box">
                    <div class="feature_lable"></div>
                    <div class="feature_lft">                        
                        <a href="AuctionController?service=view&auctionId=<%=auctionsFuture.get(i).getId()%>" title="<%=auctionsFuture.get(i).getTitle()%>">
                            <img src="<%=auctionsFuture.get(i).getImgCover()%>" width="82" title="" height="83" alt="Monitor">
                        </a>                           
                    </div>
                    <div class="feature_rgt">
                        <label><a href="AuctionController?service=view&auctionId=<%=auctionsFuture.get(i).getId()%>" title="<%=auctionsFuture.get(i).getTitle()%>">
                                <%=auctionsFuture.get(i).getTitle()%>
                            </a></label>
                        <ul style="width:130px;">  
                            <li>
                                <b>Bid to beat: </b><br>
                                <div style="color: rgb(238, 126, 63); font-weight: bold; display: block;" class="currentprice"><font class=""></font><%=auctionsFuture.get(i).getBidToBeatString()%></div>
                                <div style="color:#EE7E3F;font-weight:bold;" class="loader502"></div>

                                <p></p>
                            </li>

                            <li>
                                <b><span style="color:#000;">Buy now: <br><span><font class=""></font><%=auctionsFuture.get(i).getBuynowPriceString()%></span></span></b>

                                <div class="bid_price fl clr" style="display:none;"><p class="fl clr">Bid increment: <font class="">$</font> 2</p></div>
                            </li>
                        </ul>
                        <p class="countdown" style="display: block;">

                            <script>
                                startCountdown('<%=auctionsFuture.get(i).getFormattedStartDate(1)%>', <%=auctionsFuture.get(i).getId()%>, "countdown_auction_home");
                            </script>
                        </p>
                    </div>
                    <div class="feature_bott">
                        <div id="watchlist_area_<%=auctionsFuture.get(i).getId()%>" class="feature_bott_lft" >
                            <div class="sliders">
                                <label style="display:block;color:#666;padding-top: 3px;;padding-left: 10px ">
                                    <span>Add to watchlist</span>
                                </label>
                            </div>
                            <a title="+ Add to Watchlist" rel="502" class="addwatchlist" href="#" onClick="return addToWatchList(<%=auctionsFuture.get(i).getId()%>);"><img src="images/plus_bg.png" width="24" height="25" alt="+ Add to Watchlist"></a>
                        </div>
                        <div class="feature_bott_rgt" style="margin:0 10px 0 0;">
                            <div class="bidme_link">
                                <div class="bidnow_lft"></div>
                                <div class="bidnow_mid">
                                    <p>  <a href="AuctionController?service=view&auctionId=<%=auctionsFuture.get(i).getId()%>" class="bid" title="BID NOW" rel="" id="502" data-auctiontype="25">
                                            BID NOW                                        </a></p>
                                </div>
                                <div class="bidnow_rgt"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="notice_nsg fl clr" id="notice_msg502" style="display:none;"></div>
            </div>
            <% } %>
        </div>
        <% } %>






        <% if (auctionsClosed.size() > 0) { %>
        <div class="today_head">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                <tr>
                    <td>
                        <div class="today_head_lft">
                            <h2 title="">Closed</h2>
                        </div>
                    </td>
                    <td>
                        <div class="arrow_one"></div>
                    </td>

                </tr>
            </table>
        </div>

        <div class="feature_total">
            <%
                for (int i = 0; i < auctionsClosed.size(); i++) {
            %>
            <div id="auction_502" class="auction_item auction_item_content" data-id="502">
                <div class="auction_type" data-auctiontype="25"></div>
                <div class="feature_box">
                    <div class="feature_lable"></div>
                    <div class="feature_lft">                        
                        <a href="AuctionController?service=view&auctionId=<%=auctionsClosed.get(i).getId()%>" title="<%=auctionsClosed.get(i).getTitle()%>">
                            <img src="<%=auctionsClosed.get(i).getImgCover()%>" width="82" title="" height="83" alt="Monitor">
                        </a>                           
                    </div>
                    <div class="feature_rgt">
                        <label><a href="AuctionController?service=view&auctionId=<%=auctionsClosed.get(i).getId()%>" title="<%=auctionsClosed.get(i).getTitle()%>">
                                <%=auctionsClosed.get(i).getTitle()%>
                            </a></label>
                        <ul style="width:130px;">  
                            <li>
                                <b>Bid to beat: </b><br>
                                <div style="color: rgb(238, 126, 63); font-weight: bold; display: block;" class="currentprice"><font class=""></font><%=auctionsClosed.get(i).getBidToBeatString()%></div>
                                <div style="color:#EE7E3F;font-weight:bold;" class="loader502"></div>

                                <p></p>
                            </li>

                            <li>
                                <b><span style="color:#000;">Buy now: <br><span><font class=""></font><%=auctionsClosed.get(i).getBuynowPriceString()%></span></span></b>

                                <div class="bid_price fl clr" style="display:none;"><p class="fl clr">Bid increment: <font class="">$</font> 2</p></div>
                            </li>
                        </ul>
                        <p class="countdown" style="display: block;">
                            Auction Closed
                        </p>
                    </div>
                    <div class="feature_bott">
                        <div id="watchlist_area_<%=auctionsClosed.get(i).getId()%>" class="feature_bott_lft" >
                            <div class="sliders">
                                <label style="display:block;color:#666;padding-top: 3px;;padding-left: 10px ">
                                    <span>Add to watchlist</span>
                                </label>
                            </div>
                            <a title="+ Add to Watchlist" rel="502" class="addwatchlist" href="#" onClick="return addToWatchList(<%=auctionsClosed.get(i).getId()%>);"><img src="images/plus_bg.png" width="24" height="25" alt="+ Add to Watchlist"></a>
                        </div>
                        <div class="feature_bott_rgt" style="margin:0 10px 0 0;">
                            <div class="bidme_link">
                                <div class="bidnow_lft"></div>
                                <div class="bidnow_mid">
                                    <p>  <a href="AuctionController?service=view&auctionId=<%=auctionsClosed.get(i).getId()%>" class="bid" title="BID NOW" rel="" id="502" data-auctiontype="25">
                                            BID NOW                                        </a></p>
                                </div>
                                <div class="bidnow_rgt"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="notice_nsg fl clr" id="notice_msg502" style="display:none;"></div>
            </div>
            <% } %>
        </div>
        <% }
        %>

    </body>
</html>
