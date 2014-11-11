<%--
    Document   : ViewDetailAuction
    Created on : Jul 17, 2014, 11:58:55 PM
    Author     : MrTu
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Category"%>
<%@page import="Entity.Auction"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Auction information</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <link rel="stylesheet" href="css/jquery.rondell.css" type="text/css">
        <script src="JavaScript/modernizr-2.0.6.min.js"></script>   
    </head>
    <body>
        <%@ include file="perm_staff.jsp" %>
        <div id="edit_profile_page" class="my_message_right">
            <div class="message_common_border">
                <h1 title="EDIT PROFILE">Auction information</h1>
                <p>&nbsp;</p>
            </div>       
            <form name="form1" method="post" action="AuctionController">
                <%                    Auction auction = (Auction) request.getAttribute("auction");
                %> 
                <div class="message_common">					 
                    <div class="login_middle_common_profil">
                        <input type="hidden" id="auctionid" name="auctionid" value="<%=auction.getId()%>">
                        <table border="1"  cellpadding="20" id="viewInfoTable">                            
                            <tr>
                                <td>Title:</td>
                                <td><%=auction.getTitle()%></td>
                            </tr>

                            <tr>
                                <td>Category:</td>
                                <td>
                                    <select name="categoryId" id="categoryId">
                                        <%
                                            ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                                            for (int i = 0; i < categories.size(); i++) {
                                        %>                                
                                        <option value="<%=categories.get(i).getId()%>" <% if (auction.getCategoryId() == categories.get(i).getId()) { %> selected="selected" <% }%> ><%=categories.get(i).getName()%></option>                                
                                        <%}%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td >Description:</td>
                                <td><%=auction.getDescription()%></td>
                            </tr>
                            <tr>
                                <td >Starting price:</td>
                                <td><%=auction.getStartPriceString()%></td>
                            </tr>      
                            <tr>
                                <td>Buy now price:</td>
                                <td><%=auction.getBuynowPriceString()%></td>
                            </tr>
                            <tr>
                                <td>Increment by:</td>
                                <td><%=auction.getIncreaseByString()%></td>
                            </tr>
                            <tr>
                                <td >Starting date:</td>
                                <td><%=auction.getFormattedStartDate()%></td>
                            </tr>

                            <tr>
                                <td>Closing date:</td>
                                <td><%=auction.getFormattedEndDate()%></td>
                            </tr>
                            <tr>
                                <td>Image:</td>
                                <td>
                                    <div id="rondellTopDemo" class="showOnJS hidden"> 
                                        <%if (auction.getImgCover() != null && auction.getImgCover().length()>0) { %>
                                        <a target="_blank" rel="rondell_1" href="${auction.getImgCover()}">
                                            <img src="${auction.getImgCover()}" >
                                        </a>   
                                        <% }
                                            if (auction.getImg1() != null && auction.getImg1().length()>0) {%>
                                        <a target="_blank" rel="rondell_1" href="${auction.getImg1()}" >
                                            <img src="${auction.getImg1()}" >
                                        </a>
                                        <%}
                                            if (auction.getImg2() != null && auction.getImg2().length()>0) {%>
                                        <a target="_blank" rel="rondell_1" href="${auction.getImg2()}" >
                                            <img src="${auction.getImg2()}" >
                                        </a>
                                        <%}
                                            if (auction.getImg3() != null && auction.getImg3().length()>0) {%>
                                        <a target="_blank" rel="rondell_1" href="${auction.getImg3()}" >
                                            <img src="${auction.getImg3()}" >
                                        </a>
                                        <%}
                                            if (auction.getImg4() != null && auction.getImg4().length()>0) {%>
                                        <a target="_blank" rel="rondell_1" href="${auction.getImg4()}" >
                                            <img src="${auction.getImg4()}" >
                                        </a>
                                        <%}
                                            if (auction.getImg5() != null && auction.getImg5().length()>0) {%>
                                        <a target="_blank" rel="rondell_1" href="${auction.getImg5()}" >
                                            <img src="${auction.getImg5()}" >
                                        </a>
                                        <%}%>

                                    </div> 
                                    <script src="JavaScript/jquery-1.10.2.min.js"></script>
                                    <script src="JavaScript/jquery.mousewheel-3.0.6.min.js"></script>
                                    <script src="JavaScript/jquery.rondell.js"></script><script type="text/javascript">
                                        (function() {
                                            $("#rondellTopDemo").rondell({
                                                preset: "carousel",
                                                radius: {
                                                    x: 130
                                                },
                                                center: {
                                                    left: 200,
                                                    top: 110
                                                },
                                                itemProperties: {
                                                    size: {
                                                        width: 100,
                                                        height: 100
                                                    }
                                                }
                                            });

                                        }).call(this);
                                    </script>                                  

                                </td>
                            </tr>
                            <% if (!auction.getvYoutube().isEmpty()) {%>
                            <tr>
                                <td>Video:</td>
                                <td>
                                    <iframe width="530" height="315" src="//www.youtube.com/embed/<%=auction.getvYoutube()%>" frameborder="0" allowfullscreen></iframe>
                                </td>
                            </tr>
                            <% }%>
                            </tr>



                            <tr>
                                <td>Status:</td>
                                <td>
                                    <%=auction.getStatus()%>
                                </td>
                            </tr>

                            <tr>
                                <td>Moderate status:</td>
                                <td>
                                    <select name="moderateStatus" id="moderateStatus">              
                                        <option value="0" <% if (0 == auction.getModerateStatus()) { %> selected="selected" <% }%> >At good standing</option>                                
                                        <option value="1" <% if (1 == auction.getModerateStatus()) { %> selected="selected" <% }%> >Banned</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div class="no_img">
                            <div class="buton_green">
                                <div class="profil_butoon">
                                    <div class="res_left"></div>
                                    <div class="res_mid">
                                        <input type="hidden" name="service" id="service" value="moderator_update">
                                        <input type="hidden" name="auctionId" id="auctionId" value="<%=auction.getId()%>">
                                        <input type="submit" value="Save" name="save">
                                    </div>                  
                                    <div class="res_right"></div>
                                </div>
                                <!--
                            <span></span>				
                            <div class="grand_total_btn_cp">
                                <div class="save_left"></div>
                                <div class="save_mid">
                                    <a title="Delete">
                                        <input type="submit" value="Delete" name="Delete" onclick="return confirm('Are you sure?');">  
                                        <input type="hidden" id="service" name="service" value="deleteAuction">
                                    </a>
                                </div>
                                <div class="save_right"></div>
                            </div>
                                -->
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
