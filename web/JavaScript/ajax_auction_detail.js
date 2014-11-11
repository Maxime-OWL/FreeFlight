/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function ajax_place_bid_area(auctionId)
{
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
            document.getElementById("ajax_place_bid_area").innerHTML = xmlhttp.responseText;
        }
    };
    if (document.getElementById("yourbidding")) {
        var userBidValue = document.getElementById("yourbidding").value;
        xmlhttp.open("GET", "BidController?service=ajax_place_bid_area&auctionId=" + auctionId + "&userBidValue=" + userBidValue + "&random=" + Math.random(), true);
    } else {
        xmlhttp.open("GET", "BidController?service=ajax_place_bid_area&auctionId=" + auctionId + "&random=" + Math.random(), true);
    }
    xmlhttp.send();
    return false;
}

function ajax_load_top_bidder(auctionId) {
//document.getElementById("ajax_load_top_bidder").innerHTML = '<img src="images/ajax-loader.gif" align="center"></img>';
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
            document.getElementById("ajax_load_top_bidder").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", "BidController?service=ajax_load_top_bidder&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

function ajax_load_current_bid(auctionId) {
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
            document.getElementById("ajax_load_current_bid").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", "BidController?service=ajax_load_current_bid&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

countdownFirstTime = 0;
function ajax_load_countdown(auctionId) {
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
            var rawData = xmlhttp.responseText;
            var data = rawData.split('|');
            if (data[3] == "On-going") {
                startCountdown(data[1], "Auction Closed");
            } else if (data[3] == "Future") {
                startCountdown(null, "Coming Soon..");
            } else {
                startCountdown(null, "Auction Closed");
            } 

        }
    };

    xmlhttp.open("GET", "AuctionController?service=ajax_load_countdown&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

function ajax_load_detail_bottom_outer_top(auctionId) {
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
            document.getElementById("ajax_load_detail_bottom_outer_top").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", "AuctionController?service=ajax_load_detail_bottom_outer_top&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

function ajax_load_detail_page_top_mid(auctionId) {
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
            document.getElementById("ajax_load_detail_page_top_mid").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", "BidController?service=ajax_load_detail_page_top_mid&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

function startBidding(auctionId, nextBidValue)
{
    var r = confirm("You are about to place a bid on this auction. Your money will be instantly held until the auction's closure or until another bidder beat you.\nAre you sure you want to continue?");
    if (!r) {
        return false;
    }
    document.getElementById("placebid_btn").innerHTML = '<p>PROCESSING..</p>';
    var userBidValue = document.getElementById("yourbidding").value;
    if (isNaN(userBidValue)) {
        document.getElementById("placebid_btn").innerHTML = '<p><a href="#" onClick="startBidding(' + auctionId + ', ' + nextBidValue + ')" title="BID ME" class="fl popup" id="dialog_link" data-rel="box">PLACE MY BID</a></p>';
        document.getElementById("yourbidding_noti").innerHTML = '<font color=red>Your bid must be in number!</font>';
        return false;
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
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("placebid_btn").innerHTML = xmlhttp.responseText;
            if (xmlhttp.responseText == "CHARGE MONEY") {
                document.getElementById("yourbidding_noti").innerHTML = '<font color=red>Your account balance is not sufficient!</font>';
            }
        }
    };
    xmlhttp.open("GET", "BidController?service=place_bid&auctionId=" + auctionId + "&userBidValue=" + userBidValue + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}
processingBuynow = false;
function ajax_load_buy_now(auctionId) {
    if (processingBuynow) {
        return false;
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
            document.getElementById("ajax_load_buy_now").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", "AuctionController?service=ajax_load_buy_now&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

function startBuying(auctionId) {
    processingBuynow = true;
    var r = confirm("You are about to pay the reserved price on this auction to buy it straightly.\nAre you sure you want to continue?");
    if (!r) {
        processingBuynow = false;
        return false;
    }
    document.getElementById("ajax_load_buy_now").innerHTML = "Processing.."

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            processingBuynow = false;
            document.getElementById("ajax_load_buy_now").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", "BidController?service=buy_now&auctionId=" + auctionId + "&random=" + Math.random(), true);
    xmlhttp.send();
    return false;
}

function cancelBid(auctionId) {
    processingBuynow = true;
    var r = confirm("You're about to withdraw/cancel your bid from this auction while being the highest bidder.\nBy doing this, you're facing a penalty of 30% of the bid.\n\nAre you sure you want to continue and take back 70% of your bid?");
    if (r == true) {
        document.getElementById("placebid_btn").innerHTML = "<p>CANCELLING..</p>";
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("placebid_btn").innerHTML = xmlhttp.responseText;
                processingBuynow = false;
            }
        };
        xmlhttp.open("GET", "BidController?service=cancel_bid&auctionId=" + auctionId + "&random=" + Math.random(), true);
        xmlhttp.send();
        return false;
    } else {
        processingBuynow = false;
        return false;
    }
}