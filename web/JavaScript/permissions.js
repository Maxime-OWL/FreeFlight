/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function isUserAdmin(redirect){
    var role = '<%= Session["role"] %>';
    if (role === null || role !== "Admin") {
        
        if (redirect) {
            window.location="fail.jsp";
        }
        return false;
    }
    return true;
}

function isUserStaff(redirect){
    var role = '<%= Session["role"] %>';
    if (role === null || role !== "Staff") {
        if (redirect) {
            window.location="fail.jsp";
        }
        return false;
    }
    return true;
}

function isUserCustomer(redirect){
    var role = Session["role"];
    document.write('role');
    if (role === null || role !== "Customer") {
        if (redirect) {
            document.write(role);
            //window.location="fail.jsp";
        }
        return false;
    }
    return true;
}


