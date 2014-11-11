function calcage(secs, num1, num2) {
    s = ((Math.floor(secs / num1)) % num2).toString();
    if (LeadingZero && s.length < 2)
        s = "0" + s;
    return "<b>" + s + "</b>";
}

function CountBack(secs, auctionId) {
    if (secs < 0) {
        document.getElementById("countdown" + auctionId).innerHTML = "00:00:00:00";
        return;
    }
    var DisplayFormat = "%%D%%:%%H%%:%%M%%:%%S%%";
    DisplayStr = DisplayFormat.replace(/%%D%%/g, calcage(secs, 86400, 100000));
    DisplayStr = DisplayStr.replace(/%%H%%/g, calcage(secs, 3600, 24));
    DisplayStr = DisplayStr.replace(/%%M%%/g, calcage(secs, 60, 60));
    DisplayStr = DisplayStr.replace(/%%S%%/g, calcage(secs, 1, 60));
    document.getElementById("countdown" + auctionId).innerHTML = DisplayStr;
    if (CountActive) {
        var SetTimeOutPeriod = (Math.abs(CountStepper) - 1) * 1000 + 990;
        setTimeout("CountBack(" + (secs + CountStepper) + ", " + auctionId + ")", SetTimeOutPeriod);
    }
}



if (typeof (CountActive) == "undefined")
    CountActive = true;
if (typeof (FinishMessage) == "undefined")
    FinishMessage = "Auction Closed";
if (typeof (CountStepper) != "number")
    CountStepper = -1;
if (typeof (LeadingZero) == "undefined")
    LeadingZero = true;
function startCountdown(TargetDate, auctionId, css) {
    if (!TargetDate) {
        TargetDate = "12/31/2020 5:00 AM";
    }
    putspan(auctionId, css);
    CountStepper = Math.ceil(CountStepper);
    if (CountStepper == 0)
        CountActive = false;
    var dthen = new Date(TargetDate);
    var dnow = new Date();
    if (CountStepper > 0)
        ddiff = new Date(dnow - dthen);
    else
        ddiff = new Date(dthen - dnow);
    var gsecs = Math.floor(ddiff.valueOf() / 1000);
    /*
    if (auctionId==18){
        alert(TargetDate);
    }
    */
    CountBack(gsecs, auctionId);
}

function putspan(auctionId, css) {
    if (css == "countdown_auction_detail") {
        document.write("<span id='countdown" + auctionId + "' style='background-color:#F7F7F7" +
                "; color:black; border-bottom-color: rgb(52, 52, 52);border-bottom-style: none;border-bottom-width: 0px;border-image-outset: 0px;"
                + "border-image-repeat: stretch;border-image-slice: 100%;border-image-source: none;border-image-width: 1;border-left-color: rgb(52, 52, 52);"
                + "border-left-style: none;border-left-width: 0px;border-right-color: rgb(52, 52, 52);border-right-style: none;border-right-width: 0px;"
                + "border-top-color: rgb(52, 52, 52);border-top-style: none;border-top-width: 0px;color: rgb(52, 52, 52);display: block;float: left;font-family: arial;"
                + "font-size: 24px;font-style: normal;font-variant: normal;font-weight: bold;height: 24px;line-height: 24px;list-style-image: none;list-style-position: outside;"
                + "list-style-type: none;margin-bottom: 0px;margin-left: 0px;margin-right: 0px;margin-top: 0px;outline-color: rgb(52, 52, 52);outline-style: none;outline-width: 0px;"
                + "padding-bottom: 0px;padding-left: 0px;padding-right: 0px;padding-top: 0px;text-align: center;width: 271px;zoom: 1;'></span>");
    } else {
        document.write("<span id='countdown" + auctionId + "' style='background-color:#F7F7F7" +
                "border-bottom-color: rgb(0, 0, 0);"
                + "border-bottom-style: none;"
                + "border-bottom-width: 0px;"
                + "border-image-outset: 0px;"
                + "border-image-repeat: stretch;"
                + "border-image-slice: 100%;"
                + "border-image-source: none;"
                + "border-image-width: 1;"
                + "border-left-color: rgb(0, 0, 0);"
                + "border-left-style: none;"
                + "border-left-width: 0px;"
                + "border-right-color: rgb(0, 0, 0);"
                + "border-right-style: none;"
                + "border-right-width: 0px;"
                + "border-top-color: rgb(0, 0, 0);"
                + "border-top-style: none;"
                + "border-top-width: 0px;"
                + "color: rgb(0, 0, 0);"
                + "display: block;"
                + "float: left;"
                + "font-family: Arial, Helvetica, sans-serif;"
                + "font-size: 12px;"
                + "font-style: normal;"
                + "font-variant: normal;"
                + "font-weight: bold;"
                + "height: 15px;"
                + "line-height: normal;"
                + "margin-bottom: 0px;"
                + "margin-left: 0px;"
                + "margin-right: 0px;"
                + "margin-top: 0px;"
                + "outline-color: rgb(0, 0, 0);"
                + "outline-style: none;"
                + "outline-width: 0px;"
                + "padding-bottom: 0px;"
                + "padding-left: 0px;"
                + "padding-right: 0px;"
                + "padding-top: 8px;"
                + "width: auto;'></span>");
    }
}