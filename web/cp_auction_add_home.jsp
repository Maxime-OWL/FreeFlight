<%-- 
    Document   : AddAuction
    Created on : Jul 17, 2014, 9:11:06 PM
    Author     : MrTu 2
--%>
<%@page import="DAO.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Auction"%>
<%@page import="Entity.Category"%>
<%@page import="DAO.AuctionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new auction</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />      
        <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
        <!--<script src="JavaScript/validate_auction_add.js"></script>-->
        <style type="text/css">
            .custom-date-style {
                background-color: red !important;
            }
        </style>
        <%            String title = request.getParameter("title");
            if (title == null) {
                title = "";
            }
            String categoryIdString = request.getParameter("categoryId");
            int categoryId = 0;
            if (categoryIdString != null) {
                categoryId = Integer.parseInt(categoryIdString);
            }
            String description = request.getParameter("description");
            if (description == null) {
                description = "";
            }
            String startingPrice = request.getParameter("startingPrice");
            if (startingPrice == null) {
                startingPrice = "0";
            }
            String buynowPrice = request.getParameter("buynowPrice");
            if (buynowPrice == null) {
                buynowPrice = "1";
            }
            String increaseBy = request.getParameter("increaseBy");
            if (increaseBy == null) {
                increaseBy = "1";
            }
            String startDate = request.getParameter("startDate");
            if (startDate == null) {
                startDate = "";
            }
            String endDate = request.getParameter("endDate");
            if (endDate == null) {
                endDate = "";
            }
            String img_1 = request.getParameter("img_1");
            String img_2 = request.getParameter("img_2");
            String img_3 = request.getParameter("img_3");
            String img_4 = request.getParameter("img_4");
            String img_5 = request.getParameter("img_5");
            String v_youtube = request.getParameter("v_youtube");
            String img_cover = request.getParameter("img_cover");
            if (img_1 == null) {
                img_1 = "";
            }
            if (img_2 == null) {
                img_2 = "";
            }
            if (img_3 == null) {
                img_3 = "";
            }
            if (img_4 == null) {
                img_4 = "";
            }
            if (img_5 == null) {
                img_5 = "";
            }
            if (img_cover == null) {
                img_cover = "";
            }
            if (v_youtube == null) {
                v_youtube = "";
            }


        %>
    </head>
    <body>            
        <%@ include file="perm_customer.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Create new auction</h1>
                <p>&nbsp;</p>
            </div>
            <form method="post" action="AuctionController" name="addAuctionForm" <!--onsubmit=" return validateAuction();"-->>

                <div class="message_common">

                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="15" cellpadding="15">
                            <tr >
                                <td width="669">
                                    <table border="0" cellspacing="0" cellpadding="0" height="100%">
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">
                                                        Title <span class="red">*</span>: 
                                                    </b>                                        		
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2>
                                                            <input type="text" name="title" id="title" class="textbox" maxlength="100" value="<%=title%>">
                                                        </h2>
                                                    </div>

                                                    <div id="errorTitle" style="width:500px;padding-top:4px"><br>

                                                    </div>    
                                                </div>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Category:</b>                                      
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <select name="categoryId" id="categoryId">
                                                            <%
                                                                ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                                                                for (int i = 0; i < categories.size(); i++) {
                                                            %>                                
                                                            <option value="<%=categories.get(i).getId()%>" <% if (categoryId == categories.get(i).getId()) { %> selected="selected" <% }%> ><%=categories.get(i).getName()%></option>                                
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Description: </b>		
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="description" id="description" class="textbox" value="<%=description%>"></h2>
                                                    </div>
                                                    <div id="errorDescription" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Starting price  <span class="red">*</span>: </b>		
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2>$ <input type="number" name="startingPrice" id="startingprice" value="<%=startingPrice%>"></h2>
                                                    </div>
                                                    <div id="errorStartingprice" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Buy now price<span class="red">*</span>: </b>                                        		
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2>$ <input type="number" name="buynowPrice" id="buynowprice" value="<%=buynowPrice%>"></h2>
                                                    </div>
                                                    <div id="errorBuynowprice" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Increment price<span class="red">*</span>: </b>                                        		
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2>$ <input type="number" name="increaseBy" id="incrementprice" value="<%=increaseBy%>"></h2>
                                                    </div>
                                                    <div id="errorIncrementprice" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <table border="0" cellspacing="0" cellpadding="0" height="100%">
                                        <tr>
                                            <td >
                                                <div class="user_name_common">
                                                    <b style="width:130px">Starting date: <span class="red">*</span>: </b>	
                                                </div>
                                                <div class="user_name_common" style="width:340px;">
                                                    <div class="text_feeld">
                                                        <input type="text" id="startdate" name="startDate" value="<%=startDate%>">
                                                    </div>
                                                    <div id="Startingdate" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Closing date  <span class="red">*</span>: </b>		
                                                </div>
                                                <div class="user_name_common" style="width:340px;">
                                                    <div class="text_feeld">
                                                        <input type="text" id="enddate" name="endDate" value="<%=endDate%>">
                                                    </div>
                                                    <div id="Closingdate" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <table border="0" cellspacing="0" cellpadding="0" height="100%">
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Cover image <span class="red">*</span>:</b>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="img_cover" id="img_cover" class="textbox" value="<%=img_cover%>"></h2>
                                                    </div>
                                                    <div id="errorCoverimage" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Images (Optional):</b>                                        
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="img_1" id="img_1" class="textbox" value="<%=img_1%>" ></h2>
                                                    </div>
                                                    <div id="errorImage1" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">   
                                                    <b style="width:130px"></b>                                         
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="img_2" id="img_2" class="textbox" value="<%=img_2%>" ></h2>
                                                    </div>
                                                    <div id="errorImage2" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">  
                                                    <b style="width:130px"></b>                                         
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="img_3" id="img_3" class="textbox" value="<%=img_3%>" ></h2>
                                                    </div>
                                                    <div id="errorImage3" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">       
                                                    <b style="width:130px"></b>                                        
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="img_4" id="img_4" class="textbox" value="<%=img_4%>" ></h2>
                                                    </div>
                                                    <div id="errorImage4" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">       
                                                    <b style="width:130px"></b>                                        
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="img_5" id="img_5" class="textbox" value="<%=img_5%>" ></h2>
                                                    </div>
                                                    <div id="errorImage5" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width:130px">Video (Youtube):</b>                                        		
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="width:400px;">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="v_youtube" id="v_youtube" class="textbox" value="<%=v_youtube%>" ></h2>
                                                    </div>
                                                    <div id="errorVideo" style="width:500px;padding-top:4px"><br>

                                                    </div> 
                                                </div>
                                            </td>
                                        </tr>

                                    </table>
                                </td>
                            </tr>                           
                        </table>
                        <br>
                        <table align="center">
                            <tr>
                                <td width="120"></td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="hidden" name="service" id="service" value="add_new_auction" >
                                                <input type="submit" name="add" value="Add" id="add">
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
                                                <input type="button" name="cancel" id="cancel" value="Cancel" onclick="window.location = 'AuctionController?service=auction_manager';">
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
                                                <input type="reset" name="reset" id="reset" value="Reset" >
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
        </div>       
    </body>
    <script type="text/javascript" src="JavaScript/jquery_1.js"></script>
    <script type="text/javascript" src="JavaScript/jquery.datetimepicker.js"></script>
    <script>
                                                    $('#startdate').datetimepicker({
                                                        inline: true
                                                    });
                                                    $('#enddate').datetimepicker({
                                                        inline: true
                                                    });
    </script>
</html>
