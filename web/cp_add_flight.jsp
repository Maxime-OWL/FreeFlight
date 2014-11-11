<%-- 
    Document   : cp_add_flight
    Created on : Nov 11, 2014, 6:36:46 PM
    Author     : Khiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control panel</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <script language="JavaScript" type="text/javascript" src="JavaScript/permissions.js"></script>
    </head>
    <body>
        <jsp:include page="top.jsp" />
        <div class="header2">
            <jsp:include page="cp_cols.jsp" />
            <div class="my_message_right" id="edit_profile_page">
                <div class="message_common_border">
                    <h1 style="font-weight:bold;">Add New Flight</h1>
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
                                                            Origin<span class="red">*</span>: 
                                                        </b>                                        		
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="user_name_common" style="width:400px;">
                                                        <div class="user_name_common" style="width:400px;">
                                                            <div class="text_feeld">
                                                                <c:import var="locations" url="xml/Locations.xml"/>
                                                                <x:parse xml="${locations}" var="output"/>
                                                                <select name="origin">
                                                                    <option value="0">Select a Location</option>
                                                                    <x:forEach select="$output/Locations/Location" var="location">
                                                                        <option value="<x:out select="$location/LocationId" />"><x:out select="$location/Name" /></option>
                                                                    </x:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div id="errorTitle" style="width:500px;padding-top:4px"><br>
                                                        </div>    
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="user_name_common">
                                                        <b style="width:130px">
                                                            Destination<span class="red">*</span>: 
                                                        </b>                                        		
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="user_name_common" style="width:400px;">
                                                        <div class="user_name_common" style="width:400px;">
                                                            <div class="text_feeld">
                                                                <x:parse xml="${locations}" var="output"/>
                                                                <select name="destination">
                                                                    <option value="0">Select a Location</option>
                                                                    <x:forEach select="$output/Locations/Location" var="location">
                                                                        <option value="<x:out select="$location/LocationId" />"><x:out select="$location/Name" /></option>
                                                                    </x:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div id="errorTitle" style="width:500px;padding-top:4px"><br>
                                                        </div>    
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="user_name_common">
                                                        <b style="width:130px">
                                                            Fee<span class="red">*</span>: 
                                                        </b>                                        		
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="user_name_common" style="width:400px;">
                                                        <div class="user_name_common" style="width:400px;">
                                                            <div class="text_feeld">
                                                                <h2><input type="text" name="fee" id="title" class="textbox" maxlength="100" value=""></h2>
                                                            </div>
                                                        </div>
                                                        <div id="errorTitle" style="width:500px;padding-top:4px"><br>
                                                        </div>    
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="user_name_common">
                                                        <b style="width:130px">
                                                            Plane<span class="red">*</span>: 
                                                        </b>                                        		
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="user_name_common" style="width:400px;">
                                                        <div class="user_name_common" style="width:400px;">
                                                            <div class="text_feeld">
                                                                <c:import var="planes" url="xml/Planes.xml"/>
                                                                <x:parse xml="${planes}" var="output"/>
                                                                <select name="plane">
                                                                    <option value="0">Select a Plane</option>
                                                                    <x:forEach select="$output/Planes/Plane" var="plane">
                                                                        <option value="<x:out select="$plane/PlaneId" />"><x:out select="$plane/Model" /></option>
                                                                    </x:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div id="errorTitle" style="width:500px;padding-top:4px"><br>
                                                        </div>    
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
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
                                        <jsp:include page="footer.jsp" />   
                                        </div>
                                        </div>
                                        </div>
                                        </div>
                                        </body>
                                        </html>
