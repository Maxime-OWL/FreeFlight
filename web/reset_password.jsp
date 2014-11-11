<%-- 
    Document   : forgot_password
    Created on : Aug 5, 2014, 11:35:05 AM
    Author     : Duc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset your password</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen, projection" />
        <link rel="shortcut icon" href="images/fav-10.gif" type="image/x-icon" />
        <%
            String errorCode = request.getParameter("errorCode");

            String password1 = request.getParameter("password1");
            if (password1 == null) {
                password1 = "";
            }
            String password2 = request.getParameter("password2");
            if (password2 == null) {
                password2 = "";
            }
            String token = request.getParameter("token");
        %>
    </head>
    <body>
       
        <jsp:include page="top.jsp" />
        <%
            if (errorCode != null) {
                int error = Integer.parseInt(errorCode);
        %>
        <div class="header2"> 
            <div id="errorArea"> 
            </div>
            <script>
                showError('<%=errorCode%>');
            </script>

            <div class="login-part">
                <h2 title="Forgot_password">Please enter your new password below.</h2>
            </div>
            <div class="login_middle">
                <div class="login_lft">
                    <form accept-charset="utf-8" method="post" action="UserController" name="reset_password" onsubmit="return validateResetPassword();">	
                        <div class="login_form">
                            <div class="log_fields">
                                <p>New password <span class="red">*</span>:</p>
                                <input type="password"  name="password1" id="password1" value="<%=password1%>">				
                            </div>
                            <span class="red fl"></span>
                        </div>

                        <div class="login_form">
                            <div class="log_fields">
                                <p>Re-type new password <span class="red">*</span>:</p>
                                <input type="password"  name="password2" id="password2" value="<%=password2%>">				
                            </div>
                            <span class="red fl"></span>
                        </div>

                        <div class="login_button">
                            <div class="login_button_lft"></div>
                            <div class="login_button_midd">
                                <input type="hidden" id="tokenFinish" name="tokenFinish" value="<%=token%>">
                                <input type="hidden" id="service" name="service" value="reset_password_finish">
                                <input type="submit" title="Send" value="Submit" name="reset_password" id="reset_password" onclick="validateResetPassword();">
                            </div>
                            <div class="login_button_rgt"></div>
                        </div>
                    </form>
                </div>
            </div> 
        </div>
        <%
        } else {%>
        <div class="header2"> 

            <div class="login-part">
                <h2 title="Forgot_password">Success!</h2>
            </div>
            <div class="login_middle">
                <div class="login_lft">
                    <form accept-charset="utf-8" method="post" action="UserController">	

                        <div class="login_form">
                            <div class="log_fields">
                                <p>Your password has been changed.</p>				
                            </div>
                            <span class="red fl"></span>
                        </div>
                    </form>
                </div>
            </div> 
        </div>
        <%
            }
        %>
    </body>
</html>
