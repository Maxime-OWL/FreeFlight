<%-- 
    Document   : AddUser
    Created on : Jul 9, 2014, 10:48:39 PM
    Author     : MrTu
--%>

<%@page import="Entity.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*"%>
<%response.setContentType("text/html; charset=UTF-8");%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit user information</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <%
            User user = (User) request.getAttribute("requestedUser");
        %>
        <script type="text/javascript" src="JavaScript/validate_user_edit.js"></script>
    </head>
    <body>
        <%@ include file="perm_admin.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Edit user information</h1>
                <p>&nbsp;</p>
            </div>
            <form name="editUserForm" method="post" action="UserController" onsubmit="return validateEditUser();">
                <div class="message_common">
                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="10" cellpadding="10">
                            <tr>
                                <td width="100%">
                                    <table border="0" cellpadding="0" cellspacing="0">

                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Username <span class="red">*</span>: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" readonly="readonly" class="textbox" title="User Name" maxlength="20" value="<%=user.getUsername()%>" name="username"></h2>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div  style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Email <span class="red">*</span>: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" readonly="readonly" class="textbox" title="Email" maxlength="50" value="<%=user.getEmail()%>" name="email"></h2>

                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div  style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Role <span class="red">*</span>: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2>
                                                            <select name="cb1" id="cb1">
                                                                <option value="0" selected="selected">Customer</option>
                                                                <option value="1">Auction staff</option>
                                                                <option value="2">Administrator</option>
                                                            </select>
                                                            <script>
                                                                <%
                                                                    if (user.getRoleId() == 1) {
                                                                %>
                                                                document.getElementById("cb1").selectedIndex = "1";
                                                                <%
                                                                } else if (user.getRoleId() == 0) {
                                                                %>
                                                                document.getElementById("cb1").selectedIndex = "0";
                                                                <%
                                                                } else if (user.getRoleId() == 2) {
                                                                %>
                                                                document.getElementById("cb1").selectedIndex = "2";
                                                                <%
                                                                } else {
                                                                %>
                                                                document.getElementById("cb1").selectedIndex = "0";
                                                                <%
                                                                    }
                                                                %>
                                                            </script>
                                                        </h2>

                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div  style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Status <span class="red">*</span>: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2>
                                                            <select name="cb2" id="cb2">
                                                                <option value="1" selected="selected">Active</option>
                                                                <option value="0">Inactive</option>
                                                            </select>
                                                        </h2>
                                                        <script>
                                                            <%
                                                                if (user.getStatusId() == 0) {
                                                            %>
                                                            document.getElementById("cb2").selectedIndex = "1";
                                                            <%
                                                            } else {
                                                            %>
                                                            document.getElementById("cb2").selectedIndex = "0";
                                                            <%
                                                                }
                                                            %>
                                                        </script>

                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div  style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Full name: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" class="textbox" title="Full Name" value="<%=user.getFullname()%>" name="fullname"></h2>

                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorFullname" style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>



                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Address: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" class="textbox" title="Address"  value="<%=user.getAddress()%>" name="address"></h2>

                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorAddress" style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>



                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b>Phone number: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" class="textbox" title="Phone Number" value="<%=user.getPhonenumber()%>" name="phonenumber"></h2>


                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorPhonenumber" style="margin: 0px 0px 10px 0px">
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
                                <td width="100"></td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <script>
                                                document.write('<a style="width:auto;" href="' + document.referrer + '"><input type="button" id="Back" name="Back" value="Back"></a>');
                                            </script>
                                        </div>
                                        <div class="res_right"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="submit" value="Save" name="save">
                                                <input type="hidden" name="userid" id="userid" value="<%=user.getId()%>">
                                                <input type="hidden" name="service" id="service" value="update_user">
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
                                                <input type="reset" value="Reset" name="reset">
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