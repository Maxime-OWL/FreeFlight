/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateResetPassword() {
    var password1 = document.forms["reset_password"]["password1"].value;
    var password2 = document.forms["reset_password"]["password2"].value;   
    
    if (password1 == null || password1 == "" || password2 == null || password2 == "") {
        showError(8);
        return false;
    } else if(password1.length <6 || password1.length>20 ||password1.length <6 || password1.length>20){
        showError(9);
        return false;
    }else if(password1.indexOf(' ') != -1 || password2.indexOf(' ') != -1 ){
        showError(10);
        return false;
    }else if(password1==password2){
        showError(11);
        return false;
    }
}

function showError(errorCode) {
    if (errorCode == 8) {
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Please enter password.</p></li></ul><br>';
    }else if(errorCode == 9){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Passwords must contains at least 6 characters and at most 20 characters!</p></li></ul><br>';
    }else if(errorCode == 10){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Passwords can not contain space character!</p></li></ul><br>';
    }else if(errorCode == 11){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Re-type password does not match!</p></li></ul><br>';
    }else if(errorCode == 1){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Passwords must contains at least 6 characters or longer.</p></li></ul><br>';
    }else if(errorCode == 2){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Re-type password does not match.</p></li></ul><br>';
    }else if(errorCode == 4){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Internal error! Please try again later.</p></li></ul><br>';
    }else if(errorCode == 5){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>It looks like that link has expired. But don\'t worry, you can request a new one here.</p></li></ul><br>';
    }else if(errorCode == 6){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Internal Error! Could not reset your account password, please try again later.</p></li></ul><br>';
    }else if(errorCode == 7){
        document.getElementById("errorArea").innerHTML = '<ul id="message" class="error_msg"><li><p>Token is hacked! But don\'t worry, you can request a new one here.</p></li></ul><br>';
    }else if(errorCode == 0){
       
    }else{
        document.getElementById("errorArea").innerHTML = '';
    }
}



