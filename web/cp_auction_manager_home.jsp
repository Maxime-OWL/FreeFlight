<%-- 
    Document   : ListAllAuction
    Created on : Jul 17, 2014, 4:15:07 PM
    Author     : MrTu
--%>

<%@page import="Entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Auction</title>
        <%
            String keyword = request.getParameter("keyword");
            String statusString = request.getParameter("status");
            int status = -1;
            String categoryString = request.getParameter("category");
            int category = -1;
            if (keyword==null) {
                keyword = "";
            }
            if (statusString!=null) {
                status = Integer.parseInt(statusString);
            }
            if (categoryString!=null) {
                category = Integer.parseInt(categoryString);
            }
        %>
    </head>
    <body>
        <%@ include file="perm_staff.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="Auction Manager">Manage Auction</h1>
                <p>&nbsp;</p>
            </div>

            <form name="form1" method="post" action="AuctionController">

                <div class="message_common">
                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="10" cellpadding="10">
                            <tr>
                                <td width="500">

                                    <div class="user_name_common">
                                        <b>Auction status: </b>
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
                                                    <option value="0" <%if (status == 0) { %> selected="selected" <% } %> >Invalid</option>
                                                </select>
                                            </h2>
                                        </div>
                                    </div>

                                    <div class="user_name_common">
                                        <b>Category: </b>
                                    </div>

                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2>
                                                <select name="category" class=" text_bg select" id="category" style="">
                                                    <option value="-1" <%if (category == -1) { %> selected="selected" <% } %>>Select category</option>
                                                    <%
                                                        ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                                                        for (int i = 0; i < categories.size(); i++) {
                                                    %>                                
                                                    <option value="<%=categories.get(i).getId()%>" <%if (category == categories.get(i).getId()) { %> selected="selected" <% } %> ><%=categories.get(i).getName()%></option>                                
                                                    <%}%>
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

                        <br>

                        <table  cellspacing="10" cellpadding="10">
                            <tr>
                                <td width="91">

                                </td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="submit" name="search" id="search" value="SEARCH" title="SEARCH">
                                                <input type="hidden" name="service" id="service" value="search" >
                                            </a>
                                        </div>
                                        <div class="res_right"></div>
                                    </div>
                                </td>

                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="button" name="listall" id="listall" value="List all" onclick="window.location = 'AuctionController?service=listall'" >
                                            </a>
                                        </div>
                                        <div class="res_right"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="button" name="add" id="add" value="Process auctions" onclick="window.location = 'AuctionController?service=start_processing_auction';">    
                                            </a>
                                        </div>
                                        <div class="res_right"></div>
                                    </div>
                                </td>
                            </tr>
                        </table>



                    </div>
                </div>
            </form>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
            <jsp:include page="table_auction.jsp" />   
        </div>
    </body>
</html>
