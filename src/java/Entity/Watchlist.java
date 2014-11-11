/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import org.joda.time.DateTime;

/**
 *
 * @author MrTu
 */
public class Watchlist {
    int id;
    int userId;
    int auctionId;
    DateTime date;

    public Watchlist() {
    }

    public Watchlist(int user_id, int auction_id, DateTime date) {
        this.userId = user_id;
        this.auctionId = auction_id;
        this.date = date;
    }   

    public Watchlist(int user_id, int auction_id) {
        this.userId = user_id;
        this.auctionId = auction_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auction_id) {
        this.auctionId = auction_id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

}
