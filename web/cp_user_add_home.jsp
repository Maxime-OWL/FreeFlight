<%-- 
    Document   : ListAllUser
    Created on : Jul 8, 2014, 3:31:20 PM
    Author     : MrTu
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.User"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add new user</title>
        <%            String errorCode = request.getParameter("errorCode");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String phonenumber = request.getParameter("phonenumber");
            String address = request.getParameter("address");
            String statusString = request.getParameter("cb2");
            String roleString = request.getParameter("cb1");
            int status = 0;
            int role = 0;
            if (errorCode == null) {
                username = "";
                email = "";
                fullname = "";
                phonenumber = "";
                address = "";
            } else {
                status = Integer.parseInt(statusString);
                role = Integer.parseInt(roleString);
            }
        %>
        <script type="text/javascript" src="JavaScript/validate_user_add.js"></script>
    </head>
    <body>
        <%@ include file="perm_admin.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Add new user</h1>
                <p>&nbsp;</p>
            </div>

            <form method="post" action="UserController" name="addUserForm" onsubmit="return validateAddUser();">

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
                                                        <h2><input type="text" maxlength="20" name="username" id="username" class="textbox" value="<%=username%>"></h2>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorUsername" style="margin: 0px 0px 10px 0px">
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
                                                        <h2><input type="text" maxlength="50" name="email" id="email" class="textbox" value="<%=email%>"></h2>
                                                    </div>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div id="errorEmail" style="margin: 0px 0px 10px 0px">
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
                                                                <option value="0" <% if (role == 0) { %> selected="selected" <% } %> >Customer</option>
                                                                <option value="1" <% if (role == 1) { %> selected="selected" <% } %> >Auction staff</option>
                                                                <option value="2" <% if (role == 2) { %> selected="selected" <% } %> >Administrator</option>
                                                            </select>
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
                                                                <option value="1" <% if (status == 1) { %> selected="selected" <% } %> >Active</option>
                                                                <option value="0" <% if (status == 0) { %> selected="selected" <% }%> >Inactive</option>
                                                            </select>
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
                                                    <b>Full name: </b>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" maxlength="100" name="fullname" id="fullname" class="textbox" value="<%=fullname%>"></h2>
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
                                                        <h2><input type="text" maxlength="100" name="address" id="address" class="textbox" value="<%=address%>"></h2>
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
                                                        <h2><input type="text" maxlength="100" name="phonenumber" id="phonenumber" class="textbox" value="<%=phonenumber%>"></h2>
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
                                <td width="140"></td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="hidden" name="service" id="service" value="add_user" >
                                                <input type="submit" name="add" value="Add">
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
                                                <input type="button" name="cancel" id="cancel" value="Cancel" onclick="window.location = 'UserController?service=user_manager';">
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
</html>
