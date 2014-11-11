/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForgotPassword() {
    var username_email = document.forms["forgot_password"]["username_email"].value;
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var regcharacter = /^[a-zA-Z0-9- ]*$/;
    if (username_email == null || username_email == "") {
        showError(1);
        return false;
    } else if (re.test(username_email) && username_email.length < 8 || re.test(username_email) && username_email.length > 50) {
        showError(8);
        return false;
    } else if(!re.test(username_email)&& username_email.length <3 || !re.test(username_email) && username_email.length > 20){
        showError(11);
        return false;
    }else if(!re.test(username_email)&& !regcharacter.test(username_email)){
        showError(12);
        return false;
    }else if (username_email.indexOf(' ') != -1) {
        showError(10);
        return false;
    }
}

function showError(errorCode) {
    if (errorCode == 1) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Please enter your username or the email address you used to set up your OAS account.</p></li></ul><br>';
    }else if(errorCode == 8) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Your email must contains at least 8 characters and at most 50 characters!</p></li></ul>';
    }else if(errorCode == 10){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Username can not contain space character!</p></li></ul>';
    }else if(errorCode == 11){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Username must contains at least 3 characters and at most 20 characters!</p></li></ul>';
    }else if(errorCode == 12){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Username can not contain special character!</p></li></ul>';
    }else if(errorCode == 0){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>We just sent instructions for completing your password reset to the email address you used to set up your OAS account.</p></li></ul>';
    }else if(errorCode == 2 || errorCode ==3){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Sorry, the username or email address your provided does not exist in our system.</p></li></ul>';
    }else if(errorCode == 4){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Internal error! Please try again later.</p></li></ul>';
    }else if(errorCode == 5){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>It looks like that link has expired. But don\'t worry, you can request a new one here.</p></li></ul>';
    }else if(errorCode == 6){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Internal Error! Could not reset your account password, please try again later.</p></li></ul>';
    }else if(errorCode == 7){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Token is hacked! But don\'t worry, you can request a new one here. </p></li></ul>';
    }else{
        document.getElementById("errorArea").innerHTML = '';
    }
}



