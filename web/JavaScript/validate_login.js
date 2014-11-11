/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateLogin() {
    var username = document.forms["loginForm"]["username"].value;
    var password = document.forms["loginForm"]["password"].value;
    var regcharacter = /^[a-zA-Z0-9- ]*$/;
    if (username == null || username == "" || username == "Enter username..." || password == null || password == "" || password == "********") {
        showError(8);
        return false;
    } else if (username.length < 3 || username.length > 20) {
        showError(9);
        return false;
    } else if (password.length < 6 || password.length > 20) {
        showError(10);
        return false;
    }else if(!regcharacter.test(username)){
        showError(11);
        return false;
    }else if(username.indexOf(' ') != -1){
        showError(12);
        return false;
    }else if(password.indexOf(' ') != -1){
        showError(13);
        return false;
    }
    remember_me();
}

function showError(errorCode, username) {
    if (errorCode == 1) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>User name or password does not exist.</p></li></ul>';
    } else if (errorCode == 2) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Please login to access!</p></li></ul>';
    } else if (errorCode == 3) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="success_msg"><li><p>You have been logged out, session terminated.</p></li></ul>';
    } else if (errorCode == 4) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="success_msg"><li><p>Change password successful. Please to login!.</p></li></ul>';
    } else if (errorCode == 5) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Account is not activated. Please check your email\'s inbox or contact an administrator for support.</p></li></ul>';
    } else if (errorCode == 6) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Incorrect username or password.</p></li></ul>';
    } else if (errorCode == 7) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="success_msg"><li><p>Account ' + username + ' has been created successfully, account\'s password has been dispatched to your email address!</p></li></ul>';
    } else if (errorCode == 8) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Please enter username or pasword.</p></li></ul>';
    } else if (errorCode == 9) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Username must contains at least 3 characters and at most 20 characters!</p></li></ul>';
    }else if (errorCode == 10) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Password must contains at least 3 characters and at most 20 characters!</p></li></ul>';
    }else if (errorCode == 11) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Username contains illegal characters!</p></li></ul>';
    }else if (errorCode == 12) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Username can not contain space character!</p></li></ul>';
    }else if (errorCode == 13) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Password can not contain space character!</p></li></ul>';
    }else{
        document.getElementById("errorArea").innerHTML = '';
    }
}



