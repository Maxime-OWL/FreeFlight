/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ajax_load_top_balance(role){
    if (role!=0) {
        //document.getElementById("ajax_load_buy_now").innerHTML = 'Buy now is unavailable';
        return;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
        {
            document.getElementById("ajax_load_top_balance").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", "UserController?service=ajax_load_top_balance&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}


function ajax_load_top_categories(){
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
        {
            document.getElementById("ajax_load_top_categories").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", "CategoryController?service=ajax_load_top_categories&random=" + Math.random(), true);
    xmlhttp.send();
    //alert("ok");
    return false;
}