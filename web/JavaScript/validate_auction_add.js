/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validateAuction() {
    var total = 0;
    if (checkTitle()) {
        total++;
    }
    if (checkDescription()) {
        total++;
    }
    if (checkStartingPrice()) {
        total++;
    }
    if (checkBuyNowPrice()) {
        total++;
    }
    if(checkIncrementPrice()){
        total++;
    }
    if(checkCoverImage()){
        total++;
    }
    if(checkImage1()){
        total++;
    }
    if(checkImage2()){
        total++;
    }
    if(checkImage3()){
        total++;
    }
    if(checkImage4()){
        total++;
    }
    if(checkImage5()){
        total++;
    }
    if(checkVideo()){
        total++;
    }
    if (total < 12) {
        return false;
    }
    return true;
}

function checkTitle() {
    var title = document.forms["addAuctionForm"]["title"].value;
    if (title == null || title == "") {
        document.getElementById("errorTitle").innerHTML = '<font color="red">Please enter auction title.</font>';
        return false;
    }
    if (title.length < 3 || title.length > 20) {
        document.getElementById("errorTitle").innerHTML = '<font color="red">Title must contains at least 3 characters and at most 20 characters.</font>';
        return false;
    }
    document.getElementById("errorTitle").innerHTML = '';
    return true;
}
function checkDescription() {
    var description = document.forms["addAuctionForm"]["description"].value;
    if (description.length > 500) {
        document.getElementById("errorDescription").innerHTML = '<font color="red">Description must containsat most 500 characters.</font>';
        return false;
    }
    document.getElementById("errorDescription").innerHTML = '';
    return true;
}
function checkStartingPrice() {
    var startingPrice = document.forms["addAuctionForm"]["startingprice"].value;
    if (startingPrice == null || startingPrice == "") {
        document.getElementById("errorStartingprice").innerHTML = '<font color="red" >&nbsp;Please enter starting pricce.</font>';
        return false;
    }
    if (startingPrice < 0) {
        document.getElementById("errorStartingprice").innerHTML = '<font color="red" >&nbsp;Starting price must be greater than 0.</font>';
        return false;
    }
    document.getElementById("errorStartingprice").innerHTML = '';
    return true;
}
function checkBuyNowPrice() {
    var buyNowPrice = document.forms["addAuctionForm"]["buynowprice"].value;
    var startingPrice = document.forms["addAuctionForm"]["startingprice"].value;
    if (buyNowPrice == null || buyNowPrice == "") {
        document.getElementById("errorBuynowprice").innerHTML = '<font color="red" >&nbsp;Please enter buy now price.</font>';
        return false;
    }
    if (buyNowPrice < 0) {
        document.getElementById("errorBuynowprice").innerHTML = '<font color="red" >&nbsp;Buy now price must be greater than 0.</font>';
        return false;
    }
    if (startingPrice-buyNowPrice >=0 ) {
        document.getElementById("errorBuynowprice").innerHTML = '<font color="red" >&nbsp;Buy now price must be greater than starting price.</font>';
        return false;
    }
    document.getElementById("errorBuynowprice").innerHTML = '';
    return true;
}
function checkIncrementPrice() {
    var buyNowPrice = document.forms["addAuctionForm"]["buynowprice"].value;
    var incrementPrice = document.forms["addAuctionForm"]["incrementprice"].value;
    if (incrementPrice == null || incrementPrice == "") {
        document.getElementById("errorIncrementprice").innerHTML = '<font color="red" >&nbsp;Please enter increment price.</font>';
        return false;
    }
    else if(incrementPrice < 0){
        document.getElementById("errorIncrementprice").innerHTML = '<font color="red" >&nbsp;Increment price must be greater than 0.</font>';
        return false;
    }
    else if(incrementPrice-buyNowPrice >= 0 ){
        //alert(incrementPrice-buyNowPrice);
        document.getElementById("errorIncrementprice").innerHTML = '<font color="red" >&nbsp;Increment must be smaller than buy now price.</font>';
        return false;
    }
    document.getElementById("errorIncrementprice").innerHTML = '';
    return true;
}
function checkCoverImage(){
    var coverImage= document.forms["addAuctionForm"]["img_cover"].value;
    var regExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if(coverImage==null || coverImage==""){
        document.getElementById("errorCoverimage").innerHTML = '<font color="red" >&nbsp;Please enter cover image URL.</font>';
        return false;
    }
    if(coverImage.length > 200){
        document.getElementById("errorCoverimage").innerHTML = '<font color="red" >&nbsp;Cover image URL must contains at most 200 characters.</font>';
        return false;
    }
    if(!regExp.test(coverImage)){
        document.getElementById("errorCoverimage").innerHTML = '<font color="red" >&nbsp;Cover image URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorCoverimage").innerHTML = '';
    return true;
}
function checkImage1(){
    var image1= document.forms["addAuctionForm"]["img_1"].value;
    var regExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if(image1.length >200){
        document.getElementById("errorImage1").innerHTML = '<font color="red" >&nbsp;Image URL must contains at most 200 characters.</font>';
        return false;
    }
    if(image1 && image1.length>0 && !regExp.test(image1)){
        document.getElementById("errorImage1").innerHTML = '<font color="red" >&nbsp;Image URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorImage1").innerHTML = '';
    return true;
}
function checkImage2(){
    var image2= document.forms["addAuctionForm"]["img_2"].value;
    var regExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if(image2.length >200){
        document.getElementById("errorImage2").innerHTML = '<font color="red" >&nbsp;Image URL must contains at most 200 characters.</font>';
        return false;
    }
    if(image2 && image2.length>0 && !regExp.test(image2)){
        document.getElementById("errorImage2").innerHTML = '<font color="red" >&nbsp;Image URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorImage2").innerHTML = '';
    return true;
}
function checkImage3(){
    var image3= document.forms["addAuctionForm"]["img_3"].value;
    var regExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if(image3.length >200){
        document.getElementById("errorImage3").innerHTML = '<font color="red" >&nbsp;Image URL must contains at most 200 characters.</font>';
        return false;
    }
    if(image3 && image3.length>0 && !regExp.test(image3)){
        document.getElementById("errorImage3").innerHTML = '<font color="red" >&nbsp;Image URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorImage3").innerHTML = '';
    return true;
}
function checkImage4(){
    var image4= document.forms["addAuctionForm"]["img_4"].value;
    var regExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if(image4.length >200){
        document.getElementById("errorImage4").innerHTML = '<font color="red" >&nbsp;Image URL must contains at most 200 characters.</font>';
        return false;
    }
    if(image4 && image4.length>0 && !regExp.test(image4)){
        document.getElementById("errorImage4").innerHTML = '<font color="red" >&nbsp;Image URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorImage4").innerHTML = '';
    return true;
}
function checkImage5(){
    var image5= document.forms["addAuctionForm"]["img_5"].value;
    var regExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if(image5.length >200){
        document.getElementById("errorImage5").innerHTML = '<font color="red" >&nbsp;Image URL must contains at most 200 characters.</font>';
        return false;
    }
    if(image5 && image5.length>0 && !regExp.test(image5)){
        document.getElementById("errorImage5").innerHTML = '<font color="red" >&nbsp;Image URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorImage5").innerHTML = '';
    return true;
}
function checkVideo(){
    var video= document.forms["addAuctionForm"]["v_youtube"].value;
    var urlYoutube = /^(?:https?:\/\/)?(?:www\.)?youtube\.com\/watch\?(?=.*v=((\w|-){11}))(?:\S+)?$/;
    if(video.length > 200){
        document.getElementById("errorVideo").innerHTML = '<font color="red" >&nbsp;Video URL must contains at most 200 characters.</font>';
        return false;
    }
    if(video && video.length>0 && !urlYoutube.test(video)){
        document.getElementById("errorVideo").innerHTML = '<font color="red" >&nbsp;Video youtube URL is not valid.</font>';
        return false;
    }
    document.getElementById("errorVideo").innerHTML = '';
    return true;
}