<%-- 
    Document   : change_password
    Created on : Aug 7, 2014, 12:31:27 AM
    Author     : MrTu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="JavaScript/validate_password.js"></script>
    </head>
    <body>
        <%@ include file="perm_customer.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Change password</h1>
                <p>&nbsp;</p>
            </div>
            <form name="change_password" id="change_password" method="post" action="UserController" onsubmit=" return validatePassword();">
                <div class="message_common">
                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="10" cellpadding="10">
                            <tr>
                                <td width="700">
                                    <table border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td>
                                                <div class="user_name_common" style="margin-bottom: 0px">
                                                    <b style="width:170px">Current password <span class="red">*</span>:</b>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin-bottom: 0px">
                                                    <div class="text_feeld" >
                                                        <h2><input type="password" maxlength="200" title="Old Password" name="old_password" id="old_password" style="width:200px;height:25px;"></h2>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorCurrentPassword" style="margin-bottom: 15px"><br>
                                                </div> 
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="user_name_common" style="margin-bottom: 0px">
                                                    <b style="width:170px">New password <span class="red">*</span>:</b>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin-bottom: 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="password" maxlength="200" title="New Password" name="new_password" id="new_password" style="width:200px;height:25px;"></h2>
                                                    </div>		
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorNewPassword" style="margin-bottom: 15px"><br>
                                                </div> 
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="user_name_common" style="margin-bottom: 0px">
                                                    <b style="width:170px">Re-type new password <span class="red">*</span>:</b>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin-bottom: 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="password" maxlength="200" title="Confirm Password" name="confirm_password" id="confirm_password" style="width:200px;height:25px;"></h2>
                                                    </div>		
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorRetypePassword" style="margin-bottom: 15px"><br>
                                                </div> 
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <table>
                            <tr>
                                <td width="160">
                                </td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="hidden" name="service" id="service" value="change_password">
                                                <input type="hidden" name="userid" id="userid" value="<%=session.getAttribute("userid")%>">
                                                <input type="submit" value="save" name="submit_change_pass" onclick=" return validatePassword();">
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

