/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validatePassword() {
    var total = 0;
    if (checkCurrentPassword()) {
        total++;
    }
    if (checkNewPassword()) {
        total++;
    }
    if(checkRetypePassword()){
        total++;
    }
    if (total < 3) {
        return false;
    }
    return true;
}

function checkCurrentPassword() {
    var currentPassword = document.forms["change_password"]["old_password"].value;
    if (currentPassword == null || currentPassword == "") {
        document.getElementById("errorCurrentPassword").innerHTML = '<font color="red">Please enter current password.</font>';
        return false;
    }
    if (currentPassword.length < 6 || currentPassword.length > 20) {
        document.getElementById("errorCurrentPassword").innerHTML = '<font color="red">Current password must contains at least 3 characters and at most 20 characters.</font>';
        return false;
    }
    if (currentPassword.indexOf(' ') != -1) {
        document.getElementById("errorCurrentPassword").innerHTML = '<font color="red">Current password can not contain space character.</font>';
        return false;
    }
    document.getElementById("errorCurrentPassword").innerHTML = '';
    return true;
}
function checkNewPassword() {
    var newPassword = document.forms["change_password"]["new_password"].value;
    if (newPassword == null || newPassword == "") {
        document.getElementById("errorNewPassword").innerHTML = '<font color="red">Please enter new password.</font>';
        return false;
    }
    if (newPassword.length < 6 || newPassword.length > 20) {
        document.getElementById("errorNewPassword").innerHTML = '<font color="red">New password must contains at least 3 characters and at most 20 characters.</font>';
        return false;
    }
    if (newPassword.indexOf(' ') != -1) {
        document.getElementById("errorNewPassword").innerHTML = '<font color="red">New password can not contain space character.</font>';
        return false;
    }
    document.getElementById("errorNewPassword").innerHTML = '';
    return true;
}
function checkRetypePassword() {
    var newPassword = document.forms["change_password"]["new_password"].value;
    var retypePassword = document.forms["change_password"]["confirm_password"].value;
    if (retypePassword == null || retypePassword == "") {
        document.getElementById("errorRetypePassword").innerHTML = '<font color="red">Please enter confirm password.</font>';
        return false;
    }
    if (retypePassword.length < 6 || retypePassword.length > 20) {
        document.getElementById("errorRetypePassword").innerHTML = '<font color="red">Confirm password must contains at least 3 characters and at most 20 characters.</font>';
        return false;
    }
    if (retypePassword.indexOf(' ') != -1) {
        document.getElementById("errorRetypePassword").innerHTML = '<font color="red">Confirm password can not contain space character.</font>';
        return false;
    }
    if (newPassword != retypePassword) {
        document.getElementById("errorRetypePassword").innerHTML = '<font color="red">Confirm password is not match.</font>';
        return false;
    }
    document.getElementById("errorRetypePassword").innerHTML = '';
    return true;
}