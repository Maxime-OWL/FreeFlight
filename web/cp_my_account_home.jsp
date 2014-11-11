<%-- 
    Document   : index
    Created on : Jul 6, 2014, 10:38:08 PM
    Author     : MrDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My account</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <div class="my_message_right">
            <div class="message_common_border">
                <h1 title="mydashboard">My account</h1>
                <p>&nbsp;</p>
            </div>
            <div class="message_common">
                <div class="dasbord_common">
                    <div class="das_inner">
                        <!--
                        <div class="das_inner_text">
                            <h3>Things to do :</h3>
                        </div>
                                           -->
                        
                        <div class="roled_images">
                            <div class="inner_rol_img">
                                <div class="box_top"></div>
                                <div class="box_mid">
                                    <div class="bot_img">
                                        <div class="bot_com_change_password">&nbsp;</div>
                                        <a href="cp_change_password.jsp?current_page=my_account" title="Change password"><h4>Change password</h4></a>
                                    </div>
                                </div>
                                <div class="box_bot"></div>
                            </div>
                        
                            
                            <div class="inner_rol_img">
                                <div class="box_top"></div>
                                <div class="box_mid">
                                    <div class="bot_img">
                                        <div class="bot_com_edit_profile">&nbsp;</div>
                                        <a href="UserController?service=edit_profile" title="Edit profile"><h4>Edit profile</h4></a>
                                    </div>
                                </div>
                                <div class="box_bot"></div>
                            </div>
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body> 

</html>
