/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import DAO.BidDAO;
import DAO.FormatMoney;
import DAO.OtherDAO;
import java.sql.Date;
import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Duc
 */
public class Auction {

    private int id;
    private int categoryId;
    private String categoryName;
    private int sellerId;
    private String sellerName;
    private String title;
    private String description;
    private DateTime startDate;
    private DateTime endDate;
    private DateTime closeDate;
    private double startPrice;
    private double buynowPrice;
    private double increaseBy;
    private int moderateStatus;
    private String imgCover;
    private String vYoutube;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    int views;
    private String buyerConfirm;

    public DateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(DateTime closeDate) {
        this.closeDate = closeDate;
    }

    
    
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    
    public void addView(){
        this.views++;
    }

    public Auction() {
        this.views = 0;
        this.buyerConfirm = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getModerateStatus() {
        return moderateStatus;
    }

    public void setModerateStatus(int moderateStatus) {
        this.moderateStatus = moderateStatus;
    }

    public DateTime getStartDate() {
        return startDate;
    }
    
    public String getFormattedStartDate() {
        if (this.startDate==null) {
            return "Unknown";
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy HH:mm:ss");
        return this.startDate.toString(fmt);
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public String getFormattedEndDate() {
        if (this.endDate==null) {
            return "Unknown";
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy HH:mm:ss");
        return this.endDate.toString(fmt);
    }
    
    public String getFormattedEndDate(int type) {
        if (this.endDate==null) {
            return "Unknown";
        }
        String format = "d MMMM, yyyy HH:mm:ss";
        if (type == 1) {
            format = "MM/dd/yyyy HH:mm:ss";
        }
        if(type==2){
            format =  "yyyy-MM-ddTHH:mm:ss";
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return this.endDate.toString(fmt);
    }
    
    public String getFormattedStartDate(int type) {
        if (this.startDate==null) {
            return "Unknown";
        }
        String format = "d MMMM, yyyy HH:mm:ss";
        if (type == 1) {
            format = "MM/dd/yyyy HH:mm:ss";
        }
        if(type==2){
            format =  "yyyy-MM-ddTHH:mm:ss";
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return this.startDate.toString(fmt);
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public double getStartPrice() {
        return startPrice;
    }
    
    public String getStartPriceString(){
        FormatMoney formatMoney = new FormatMoney();
        return formatMoney.showPriceInUSD(startPrice, 1);
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getBuynowPrice() {
        return buynowPrice;
    }
    
    public String getBuynowPriceString(){
        FormatMoney formatMoney = new FormatMoney();
        return formatMoney.showPriceInUSD(buynowPrice, 1);
    }

    public void setBuynowPrice(double buynowPrice) {
        this.buynowPrice = buynowPrice;
    }

    public double getIncreaseBy() {
        return increaseBy;
    }
    public String getIncreaseByString(){
        FormatMoney formatMoney = new FormatMoney();
        return formatMoney.showPriceInUSD(increaseBy, 1);
    }

    public void setIncreaseBy(double increaseBy) {
        this.increaseBy = increaseBy;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getvYoutube() {
        OtherDAO other = new OtherDAO();
        return other.getValidYoutubeUrl(vYoutube);
    }

    public String getvYoutubeFull() {
        return vYoutube;
    }

    public void setvYoutube(String vYoutube) {
        this.vYoutube = vYoutube;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public int getStatusId() {
        if (startDate == null || endDate == null) {
            return 0; //invalid
        } else if (moderateStatus == 0) { // Active
            if (startDate.isAfterNow()) {
                return 1; //Future auctions
            } else if (startDate.isBeforeNow() && endDate.isAfterNow()) {
                return 2; // On-going auctions.
            } else if (endDate.isBeforeNow()) { // Closed auctions.
                return 3;
            }
            return 0; // Invalid auctions.
        } else if (moderateStatus == 1) { // Banned auctions
            return 4; // Banned acutions
        } else if (moderateStatus == 2) { // Processed auctions.
            return 5; // Finished/Processed auctions.
        } else if (moderateStatus == 3) { // Awaiting for buyer's confirmation
            if (buyerConfirm==null || buyerConfirm.equals("") || buyerConfirm.isEmpty()){
                return 6; // Pending approval
            } else {
                return 7; // On-hold
            }
        } else {
            return 0; // Invalid auctions.
        }
    }

    public String getStatus() {
        int statusId = this.getStatusId();
        if (statusId == 0) {
            return "Invalid";
        } else if (statusId == 1) {
            return "Future";
        } else if (statusId == 2) {
            return "On-going";
        } else if (statusId == 3) {
            return "Closed";
        } else if (statusId == 4) {
            return "Banned";
        } else if (statusId == 5) {
            return "Finished";
        } else if (statusId == 6) {
            return "Pending approval";
        } else if (statusId == 7) {
            return "On-hold";
        } else {
            return "Invalid";
        }
    }
    
    public Double getCurrentBid(){
        BidDAO bidDao = new BidDAO();
        ArrayList bids = bidDao.getBidFromAuctionId(this.id, 1);
        if (bids.size() >0) {
            Bid topBidder = (Bid) bids.get(0);
            return topBidder.getAmount();
        } else {
            return this.startPrice;
        }
    }
    
    public String getCurrentBidString(){
        FormatMoney formartMoney = new FormatMoney();
        return formartMoney.showPriceInUSD(this.getCurrentBid(), 1);
    }
    
    public Double getBidToBeat(){
        return this.getCurrentBid()+this.increaseBy;
    }
    
    public String getBidToBeatString(){
        FormatMoney fm = new FormatMoney();
        return fm.showPriceInUSD(this.getBidToBeat(), 1);
    }

    public String getBuyerConfirm() {
        return buyerConfirm;
    }

    public void setBuyerConfirm(String buyerConfirm) {
        this.buyerConfirm = buyerConfirm;
    }
    
    public String getTopBidderName(){
        BidDAO bidDao = new BidDAO();
        return bidDao.getTopBidderNameFromAuctionId(id);
    }
    
}
