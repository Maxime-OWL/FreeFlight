/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateRegister() {
    document.getElementById("error").innerHTML = '';
    var total = 0;
    if (checkUsername()){
        total ++;
    }
    if (checkEmail()){
        total ++;
    }
    if (checkFullname()){
        total ++;
    }
    if (checkPhonenumber()){
        total ++;
    }
    if (checkAddress()){
        total ++;
    }
    if (total < 5) {
        return false;
    }
    return true;
}
function checkUsername() {
    var username = document.forms["registerForm"]["username"].value;
    if (username == "" || username == null) {
        document.getElementById("errorUsername").innerHTML = '<div id="errorUsername"><label><span class="red">Please enter Username!</span></label></div>';
        return false;
    } 
    if (username.length < 3 || username.length > 20) {
        document.getElementById("errorUsername").innerHTML = '<div id="errorUsername"><label><span class="red">Username must contains at least 3 characters and at most 20 characters.</span></label></div>';
        return false;
    }
    if (/^[a-zA-Z0-9- ]*$/.test(username) == false) {
        document.getElementById("errorUsername").innerHTML = '<div id="errorUsername"><label><span class="red">Username contains illegal characters!</span></label></div>';
        return false;
    }
    if (username.indexOf(' ') !== -1) {
        document.getElementById("errorUsername").innerHTML = '<div id="errorUsername"><label><span class="red">Username contains space characters!</span></label></div>';
        return false;
    }
    document.getElementById("errorUsername").innerHTML = '';
    return true;
}
function checkEmail() {
    var email = document.forms["registerForm"]["email"].value;
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    if (email == "" || email == null) {
        document.getElementById("errorEmail").innerHTML = '<div id="errorEmail"><label><span class="red">Please enter Email!</span></label></div>';
        return false;
    }
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
        document.getElementById("errorEmail").innerHTML = '<div id="errorEmail"><label><span class="red">Please enter valid Email!</span></label></div>';
        return false;
    }
    document.getElementById("errorEmail").innerHTML = '';
    return true;
}
function checkFullname(){
    var fullname = document.forms["registerForm"]["fullname"].value;
    if (fullname.length > 50) {
        document.getElementById("errorFullname").innerHTML = '<div id="errorFullname"><label><span class="red">Fullname must contains at most 50 characters.</span></label></div>';
        return false;
    }
    document.getElementById("errorFullname").innerHTML = '';
    return true;
}
function checkPhonenumber(){
    var phone = document.forms["registerForm"]["phonenumber"].value;
    if (phone.length > 50) {
        document.getElementById("errorPhonenumber").innerHTML = '<div id="errorPhonenumber"><label><span class="red">Phonenumber must contains at most 50 characters.</span></label></div>';
        return false;
    }
    document.getElementById("errorPhonenumber").innerHTML = '';
    return true;
}
function checkAddress(){
    var address = document.forms["registerForm"]["address"].value;
    if (address.length > 50) {
        document.getElementById("errorAddress").innerHTML = '<div id="errorAddress"><label><span class="red">Address must contains at most 50 characters.</span></label></div>';
        return false;
    }
    document.getElementById("errorAddress").innerHTML = '';
    return true;
}