/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateEditProfile() {
    
    var total = 0;
    
    if (checkFullname()){
        total ++;
    }
    if (checkPhonenumber()){
        total ++;
    }
    if (checkAddress()){
        total ++;
    }
    if (total < 3) {
        return false;
    }
    return true;
}

function checkFullname(){
    var fullname = document.forms["editProfileForm"]["fullname"].value;
    if (fullname.length > 50) {
        document.getElementById("errorFullname").innerHTML = '<font color="red">Fullname must contains at most 50 characters.</font>';
        return false;
    }
    document.getElementById("errorFullname").innerHTML = '';
    return true;
}
function checkPhonenumber(){
    var phone = document.forms["editProfileForm"]["phonenumber"].value;
    if (phone.length > 50) {
        document.getElementById("errorPhonenumber").innerHTML = '<font color="red">Phonenumber must contains at most 50 characters.</font>';
        return false;
    }
    document.getElementById("errorPhonenumber").innerHTML = '';
    return true;
}
function checkAddress(){
    var address = document.forms["editProfileForm"]["address"].value;
    if (address.length > 50) {
        document.getElementById("errorAddress").innerHTML = '<font color="red">Address must contains at most 50 characters.</font>';
        return false;
    }
    document.getElementById("errorAddress").innerHTML = '';
    return true;
}