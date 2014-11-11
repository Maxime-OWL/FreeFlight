<%-- 
    Document   : EditCategory
    Created on : Jul 22, 2014, 11:32:41 PM
    Author     : MrTu
--%>
<%@page import="Entity.Category"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAO.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*"%>
<%response.setContentType("text/html; charset=UTF-8");%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit category</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <%
            Category category = (Category) request.getAttribute("category");
        %>
        <script type="text/javascript" src="JavaScript/validate_category_edit.js"></script>
    </head>
    <body> 
        <%@ include file="perm_staff.jsp" %>
        <div class="my_message_right" id="edit_profile_page">
            <div class="message_common_border">
                <h1 title="User Manager">Edit category</h1>
                <p>&nbsp;</p>
            </div>    
            <form name="EditCategoryForm" method="post" action="CategoryController" onsubmit="return validateEditCategory();"> 
                <div class="message_common">
                    <div class="login_middle_common_profil">
                        <table border="0" cellspacing="10" cellpadding="10">
                            <tr>
                                <td width="550">
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
                                                        <h2><input type="text" name="name" id="name" value="<%=category.getName()%>"></h2>

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
                                                        <h2><input type="text" name="description" id="description" value="<%=category.getDescription()%>"></h2>

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
                                                <input type="hidden" name="service" id="service" value="update">
                                                <input type="hidden" name="id" id="id" value="<%=category.getId()%>">
                                                <input type="submit" value="Save" name="save">
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


