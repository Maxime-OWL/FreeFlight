<%-- 
    Document   : cp_customer_my_bids_home
    Created on : Aug 19, 2014, 10:49:28 PM
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
    </head>
    <body>
        <div class="my_message_right" id="mybids_page">
            <div class="message_common_border">
                <h1 title="MY BIDS">My Bids</h1>
                <p>&nbsp;</p>
            </div>
            <div class="message_common">
                <div class="forms_common">
                    <div class="title_cont_watchilist">
                        <div class="title_cont_new_watch">
                            <%
                                ArrayList<Auction> auction = (ArrayList<Auction>) request.getAttribute("auctions");

                                if (auction.size()<=0) {
                            %>
                            <h4 class="">No items</h4>

                            <%
                            } else {

                            %>
                            
                            
                            <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table-top">
                                <thead>
                                    <tr>
                                        <th width="100" align="center"><b>Product</b></th>                                        
                                        <th width="100" align="center"><b>End Time</b></th>
                                        <th width="100" align="center"><b>Top Bidder</b></th>
                                        <th width="100" align="center"><b>Status</b></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (int i=0;i<auction.size();i++) { %>
                                    <tr>
                                        <td width="100" align="center">
                                            <a href="AuctionController?service=view&auctionId=<%=auction.get(i).getId()%>" title="">
                                                <img src="<%=auction.get(i).getImgCover()%>" width="65" height="65" title="<%=auction.get(i).getTitle()%>">
                                                <br>
                                                <%=auction.get(i).getTitle()%>
                                            </a></td>
                                        
                                        <td width="100" align="center">
                                            <h2 style="width:122px;"><%=auction.get(i).getFormattedEndDate()%></h2>
                                        </td>
                                        
                                        <td width="100" align="center">
                                            <%=auction.get(i).getTopBidderName()%>
                                        </td>

                                        <td width="100" align="center">
                                            <h2><%=auction.get(i).getStatus()%></h2>		
                                        </td>
                                    </tr> 
                                    <% } %>
                                </tbody>
                            </table>
                            <%
                                }
                            %>
                        </div>	
                    </div>
                </div>
                <div class="user" style="display:none;">344</div>
            </div>
        </div>
    </body>
</html>
