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
        <title>Manage category</title>
        <%            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            if (name == null) {
                name = "";
            }
            if (desc == null) {
                desc = "";
            }
        %>
        <script type="text/javascript" src="JavaScript/validate_category_add.js"></script>
    </head>
    <body>
        <%@ include file="perm_staff.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Add new category</h1>
                <p>&nbsp;</p>
            </div>

            <form name="AddCategoryForm" method="post" action="CategoryController" onsubmit="return validateAddCategory();">
                <div class="message_common">
                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="10" cellpadding="10">
                            <tr>
                                <td width="700">
                                    <table border="0" cellpadding="0" cellspacing="0">

                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width: 150px;">Category name <span class="red">*</span>:</b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="name" id="name" class="textbox" value="<%=name%>"></h2>

                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div  id="errorCategoryname" style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td>
                                                <div class="user_name_common">
                                                    <b style="width: 150px;">Description: </b>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="user_name_common" style="margin: 0px 0px 0px 0px">
                                                    <div class="text_feeld">
                                                        <h2><input type="text" name="description" id="description" maxlength="200" class="textbox" value="<%=desc%>"></h2>

                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <div  id="errorDescription" style="margin: 0px 0px 10px 0px">
                                                </div>
                                            </td>
                                        </tr>




                                    </table>  
                                </td>
                            </tr>
                        </table>




                        <table>
                            <tr>
                                <td width="140">

                                </td>
                                <td>
                                    <div class="profil_butoon" style="width:auto;">
                                        <div class="res_left"></div>
                                        <div class="res_mid" style="width:auto;">
                                            <a style="width:auto;">
                                                <input type="hidden" name="service" id="service" value="addcategory" >
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
                                                <input type="button" name="cancel" id="cancel" value="Cancel" onclick="window.location = 'CategoryController?service=category_manager';">
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
                                                <input type="reset" name="reset" id="reset" value="Reset" > </a>
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



