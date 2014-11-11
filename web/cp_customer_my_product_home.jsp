<%-- 
    Document   : newjspcp_customer_my_product_home
    Created on : Aug 20, 2014, 12:37:17 AM
    Author     : ducfpt
--%>

<%@page import="Entity.Auction"%>
<%@page import="Entity.Category"%>
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
            String keyword = request.getParameter("keyword");
            String statusString = request.getParameter("status");
            int status = -1;
            String categoryString = request.getParameter("category");
            int category = -1;
            if (keyword == null) {
                keyword = "";
            }
            if (statusString != null) {
                status = Integer.parseInt(statusString);
            }
            if (categoryString != null) {
                category = Integer.parseInt(categoryString);
            }
        %>
    </head>
    <body>
        <div class="my_message_right" id="mybids_page">
            <div class="message_common_border">
                <h1 title="My auctions">My auctions</h1>
                <p>&nbsp;</p>
            </div>
            <div class="buton_green">		
                <div class="message_common">
                    <div class="form_cont_top">
                        <form method="post" class="admin_form" name="frmproduct" id="frmproduct" action="AuctionController">                
                            <table border="0" cellspacing="10" cellpadding="10">
                                <tr>
                                    <td width="200">
                                        <div class="user_name_common">
                                            <b>Status: </b>
                                        </div>

                                        <div class="user_name_common" style="width:400px;">
                                            <div class="text_feeld">
                                                <h2>
                                                    <select name="status" class=" text_bg select" id="auction_status">	
                                                        <option value="-1" <%if (status == -1) { %> selected="selected" <% } %> >Any auction status</option>
                                                        <option value="1" <%if (status == 1) { %> selected="selected" <% } %> >Future</option>
                                                        <option value="2" <%if (status == 2) { %> selected="selected" <% } %> >On-going</option>
                                                        <option value="3" <%if (status == 3) { %> selected="selected" <% } %> >Closed</option>
                                                        <option value="4" <%if (status == 4) { %> selected="selected" <% } %> >Banned</option>
                                                        <option value="5" <%if (status == 5) { %> selected="selected" <% } %> >Finished</option>
                                                        <option value="6" <%if (status == 6) { %> selected="selected" <% } %> >Pending approval</option>
                                                        <option value="7" <%if (status == 7) { %> selected="selected" <% } %> >On-hold</option>
                                                        <option value="0" <%if (status == 0) { %> selected="selected" <% }%> >Invalid</option>
                                                    </select>
                                                </h2>
                                            </div>
                                        </div>

                                        <div class="user_name_common">
                                            <b>Keyword: </b>
                                        </div>

                                        <div class="user_name_common" style="width:400px;">
                                            <div class="text_feeld" style="width:330px;">
                                                <h2><input type="text" name="keyword" maxlength="100" id="keyword" class="textbox" value="<%=keyword%>"></h2>
                                            </div>	
                                        </div>
                                    </td>
                                </tr>


                            </table>
                            <table  cellspacing="10" cellpadding="10">
                                <tr>

                                    <td>
                                        <div class="profil_butoon" style="width:auto;">
                                            <div class="res_left"></div>
                                            <div class="res_mid" style="width:auto;">
                                                <a style="width:auto;">
                                                    <input type="submit" value="Search" name="search_product" title="Search">
                                                    <input type="hidden" id="service" name="service" value="search_product">
                                                </a>
                                            </div>
                                            <div class="res_right"></div>
                                        </div>
                                    </td>

                                    <td>
                                        <div class="profil_butoon" style="width:auto;">
                                            <div class="res_left"></div>
                                            <div class="res_mid" style="width:auto;">
                                                <a title="Create new auction"><input type="button" name="submit_user" value="Create new auction" onclick="location.href = 'AuctionController?service=add_product';"></a>

                                            </div>
                                            <div class="res_right"></div>
                                        </div>
                                    </td>

                                </tr>
                            </table>

                        </form>
                    </div>
                    <div class="forms_common">
                        <div class="title_cont_watchilist">
                            <%
                                ArrayList<Auction> auctions = (ArrayList<Auction>) request.getAttribute("auctions");
                                if (auctions == null) {
                            %>

                            <%} else {
                                for (int i = 0; i < auctions.size(); i++) {
                            %>
                            <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table-top">
                                <thead>
                                    <tr><th width="180" align="center">
                                            <b>Product</b>
                                        </th>
                                        <th width="150" align="center">
                                            <b>Current bid</b>
                                        </th>
                                        <th width="150" align="center">
                                            <b>Start date / Closed date</b>
                                        </th>
                                        <th width="70" align="center" colspan="3">
                                            <b>Actions</b>
                                        </th>
                                        <th width="100" align="center">
                                            <b> Status</b>
                                        </th>
                                    </tr></thead>

                                <tbody><tr>

                                        <td align="center">
                                            <h3> 
                                                <a href="AuctionController?service=view&auctionId=<%=auctions.get(i).getId()%>">
                                                    <img src="<%=auctions.get(i).getImgCover()%>" title="<%=auctions.get(i).getTitle()%>" width="65" height="65">
                                                    <br>
                                                    <%=auctions.get(i).getTitle()%>
                                                </a>
                                            </h3>
                                        </td>

                                        <td align="center">	
                                            <h2><%=auctions.get(i).getCurrentBidString()%></h2>
                                        </td>

                                        <td align="center">	
                                            <h2><%=auctions.get(i).getFormattedStartDate()%></h2>
                                            <h2><%=auctions.get(i).getFormattedEndDate()%></h2>

                                        </td>

                                        <td  align="center">&nbsp;
                                        </td>
                                        <td  align="center"> 
                                            <a href="AuctionController?service=edit_myproduct&auctionid=<%=auctions.get(i).getId()%>" title="Product Edit" class="editicon"></a>  
                                            &nbsp;
                                        </td>
                                        <td  align="center">
                                            <a href="" title="Product Delete" onClick="return confirm('Are you sure you want to delete this product?\nThis action can not be undone!')" >
                                                <img src="images/delete.png" border="0">
                                            </a> 
                                            &nbsp;
                                        </td>
                                        <td  align="center">
                                            <%=auctions.get(i).getStatus()%>     
                                        </td>
                                    </tr>
                                </tbody></table>
                                <%
                                        }
                                    }
                                %>
                        </div>
                        <div class="user" style="display:none;">344</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
