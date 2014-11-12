/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import DAO.FormatMoney;
import DAO.FlightDAO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Duc
 */
public class Bid {
    int bidId;
    int bidderId;
    String bidderName;
    int auctionId;
    String auctionName;
    double amount;
    DateTime date;

    public Bid() {
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public double getAmount() {
        return amount;
    }
    
    public String getAmountString(){
        FormatMoney formatMoney = new FormatMoney();
        return formatMoney.showPriceInUSD(this.amount, 1);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DateTime getDate() {
        return date;
    }
    
    public String getDateFormatted(){
        if (this.date==null) {
            return "Unknown";
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy 'at' HH:mm");
        return this.date.toString(fmt);
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }
    
    
    
}
