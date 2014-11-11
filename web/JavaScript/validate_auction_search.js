/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function isSearchKeywordValid(){
    var keyword = document.forms["user_search"]["keyword"].value;
    //alert(keyword);
    if (keyword && keyword.length >=3 ) {
        document.getElementById('user_search').submit();
        return true;
    } else if (keyword==''){
        alert("Your search keyword is empty...Try different keywords");
        return false;
    } else {
        alert("Your search keyword is too short...Try different keywords with at least 3 characters.");
        return false;
    }
}
