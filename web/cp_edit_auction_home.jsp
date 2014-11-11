<%-- 
    Document   : EditAuction
    Created on : Jul 25, 2014, 12:20:25 AM
    Author     : MrTu
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Auction"%>
<%@page import="Entity.Category"%>
<%@page import="DAO.AuctionDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit auction</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
    </head>
    <body>
        <%@ include file="perm_staff.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Add new auction</h1>
                <p>&nbsp;</p>
            </div>
            <form name="form1" method="post" action="AuctionController">
                <%                    String id = (String) session.getAttribute("userid");
                    int userid = Integer.parseInt(id);
                    ResultSet rs = (ResultSet) request.getAttribute("rs");
                    Auction auction = new Auction();
                    if (rs.next()) {
                        auction.setAuctionid(rs.getInt(1));
                        auction.setCategoryid(rs.getInt(2));
                        auction.setSellerid(rs.getInt(3));
                        auction.setTitle(rs.getString(4));
                        auction.setDescription(rs.getString(5));
                        auction.setStart_date(rs.getDate(6));
                        auction.setStart_time(rs.getTime(7));
                        auction.setEnd_date(rs.getDate(8));
                        auction.setEnd_time(rs.getTime(9));
                        auction.setStarting_price(rs.getDouble(10));
                        auction.setBuy_now_price(rs.getDouble(11));
                        auction.setStatus(rs.getInt(12));
                        auction.setVideo(rs.getString(13));
                        auction.setImage1(rs.getString(14));
                        auction.setImage2(rs.getString(15));
                        auction.setImage3(rs.getString(16));
                        auction.setImage4(rs.getString(17));
                        auction.setImage5(rs.getString(18));
                    }
                %>  
                <div class="message_common">
                    <input type="text" hidden="hidden" name="userid" id="userid" value="<%=userid%>" class="textbox">
                    <input type="text" hidden="hidden" name="auctionid" id="auctionid" value="<%=auction.getAuctionid()%>" class="textbox">                    
                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="15" cellpadding="15">
                            <tr>
                                <td width="500">
                                    <div class="user_name_common">
                                        <b>
                                            Title <span class="red">*</span>: 
                                        </b>		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2>
                                                <input type="text" name="title" id="title" class="textbox" value="<%=auction.getTitle()%>">
                                            </h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <%
                                            ResultSet rss = (ResultSet) request.getAttribute("rss");
                                            Category category1 = new Category();
                                            if (rss.next()) {
                                                category1.setCategoryid(rss.getInt(1));
                                                category1.setName(rss.getString(2));
                                            }
                                        %>
                                        <b>Category:</b>                                      
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <select name="cb1" id="cb1">
                                                <jsp:useBean id="array" class="java.util.ArrayList" scope="request">                               
                                                </jsp:useBean>              
                                                <c:forEach var="category" items="${array}">                                       
                                                    <option value="${category.categoryid}">${category.name}</option>
                                                    <script>
                                                        document.getElementById('cb1').value = '<%=category1.getCategoryid()%>';
                                                    </script>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Description <span class="red">*</span>: </b>	
                                    </div>  
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="description" id="description" class="textbox" value="<%=auction.getDescription()%>"></h2>
                                        </div>	
                                    </div>
                                    <div class="user_name_common">
                                        <b>Image <span class="red">*</span>:</b>                                       		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="image1" id="image1" class="textbox" value="<%=auction.getImage1()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b></b>                                       		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="image2" id="image2" class="textbox" value="<%=auction.getImage2()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b></b>                                       		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="image3" id="image3" class="textbox" value="<%=auction.getImage3()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b></b>                                       		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="image4" id="image4" class="textbox" value="<%=auction.getImage4()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b></b>                                       		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="image5" id="image5" class="textbox" value="<%=auction.getImage5()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Video  <span class="red">*</span>:</b>		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="video" id="video" class="textbox" value="https://www.youtube.com/watch?v=<%=auction.getVideo()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Plan date  <span class="red">*</span>: </b>                                       	
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <input type="date" name="startdate" id="startdate" value="<%=auction.getStart_date()%>" >
                                        </div>	
                                    </div>
                                    <div class="user_name_common">
                                        <b>Plan time  <span class="red">*</span>: </b>	
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <input type="time" name="starttime" id="starttime" value="<%=auction.getStart_time()%>" >
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Close date  <span class="red">*</span>: </b>		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <input type="date" name="enddate" id="enddate" value="<%=auction.getEnd_date()%>">
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Close time:<span class="red">*</span>: </b>		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <input type="time" name="endtime" id="endtime" value="<%=auction.getEnd_time()%>">
                                        </div>
                                    </div>

                                    <div class="user_name_common">
                                        <b>Status  <span class="red">*</span>:</b>
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <select name="cb2" id="cb2">
                                                <option value="0">Inactive</option>
                                                <option value="1">Active</option>
                                                <option value="2">Future</option>
                                                <option value="3">Closed</option>
                                            </select>
                                            <script>
                                                document.getElementById('cb2').value = '<%=auction.getStatusId()%>';
                                            </script>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Starting price  <span class="red">*</span>: </b>		
                                    </div>
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="startingprice" id="startingprice" value="<%=auction.getStarting_price()%>"></h2>
                                        </div>
                                    </div>
                                    <div class="user_name_common">
                                        <b>Buy now price<span class="red">*</span>: </b>		
                                    </div> 
                                    <div class="user_name_common" style="width:400px;">
                                        <div class="text_feeld">
                                            <h2><input type="text" name="buynowprice" id="buynowprice" value="<%=auction.getBuy_now_price()%>"></h2>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table align="center">
                            <tr>
                                <td width="90"></td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="submit" name="save" id="save" value="Save">
                                                <input type="hidden" name="service" id="service" value="updateauction">
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
                                                <input type="reset" name="reset" id="reset" value="Clear">
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
                                                <input type="button" name="reset" id="reset" value="Cancel" onclick="window.location = 'AuctionController?service=listall';">

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
</html>
