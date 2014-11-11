<%-- 
    Document   : cp_add_flight
    Created on : Nov 11, 2014, 6:36:46 PM
    Author     : Khiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Location</title>
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
                    <h1 style="font-weight:bold;">Add New Location</h1>
                    <div class="message_common">
                        <div class="login_middle_common_profil">
                            <form name="addNewLocation" method="post" action="UserController">
                                <table border="0" cellspacing="15" cellpadding="15">
                                    <tr>
                                        <td width="669">
                                            <table border="0" cellspacing="0" cellpadding="0" height="100%">
                                                <tr>
                                                    <td>
                                                        <div class="user_name_common">
                                                            <b style="width:130px">
                                                                Location Name<span class="red">*</span>: 
                                                            </b>                                        		
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="user_name_common" style="width:400px;">
                                                            <div class="user_name_common" style="width:400px;">
                                                                <div class="text_feeld">
                                                                    <h2><input type="text" name="location" id="title" class="textbox" maxlength="100" value=""></h2>
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
                                                                    <input type="hidden" name="service" id="service" value="add_new_location" />
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
                                                                    <input type="reset" name="reset" id="reset" value="Reset" >
                                                                </a>
                                                            </div>
                                                            <div class="res_right"></div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            </form>
                                            </div>
                                            <jsp:include page="footer.jsp" />   
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                            </body>
                                            </html>
