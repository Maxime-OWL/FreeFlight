<%-- 
    Document   : ViewDetail
    Created on : Jul 9, 2014, 12:43:01 AM
    Author     : MrTu
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.User"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View user information</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/table.css"/>

    </head>
    <body>
        <%@ include file="perm_admin.jsp" %>
        <div id="edit_profile_page" class="my_message_right">
            <div class="message_common_border">
                <h1 title="EDIT PROFILE">User information</h1>
                <p>&nbsp;</p>
            </div>
            <form name="form1" method="post" action="UserController">
                <%                    User user = (User) request.getAttribute("requestedUser");
                %>
                <div class="message_common">					 
                    <div class="login_middle_common_profil">
                        <input type="hidden" name="no" id="no" value="<%=user.getId()%>">
                        <table border="1"  cellpadding="20" id="viewInfoTable">
                            <tr>                                
                                <td >User name:</td>
                                <td><%=user.getUsername()%></td>                    
                            </tr>                            
                            <tr>
                                <td>Email:</td>
                                <td><%=user.getEmail()%></td>
                            </tr>
                            <tr>
                                <td >Role:</td>
                                <td><%=user.getRole()%></td>
                            </tr>
                            <tr>
                                <td >Status:</td>
                                <td><%=user.getStatus()%></td>
                            </tr>
                            <tr>
                                <td >Full name:</td>
                                <td><%=user.getFullname()%></td>
                            </tr>
                            <tr>
                                <td >Phone number:</td>
                                <td><%=user.getPhonenumber()%></td>
                            </tr>
                            <tr>
                                <td >Address:</td>
                                <td><%=user.getAddress()%></td>
                            </tr>
                            <tr>
                                <td >Join date:</td>
                                <td><%=user.getFormattedJoinDate()%></td>
                            </tr>                            
                        </table>	

                        <br>

                        <table align="center">
                            <tr>
                                <td width="90"></td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <input type="button" value="Back" name="Back" onclick="window.location = 'UserController?service=user_manager';">
                                        </div>
                                        <div class="res_right"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="button" value="Edit" name="Edit" onclick="window.location = 'UserController?service=edit_user&userid=<%=user.getId()%>';">
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
                                                <input type="submit" value="Delete" name="Delete" onclick="return confirm('Are you sure?')">
                                                <input type="hidden" id="service" name="service" value="delete">
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
