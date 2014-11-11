/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import DAO.FormatMoney;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Maxime
 */
public class Transaction {
    private int id;
    private String desc;
    private Double amount;
    private DateTime date;
    private int userId;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getAmount() {
        return amount;
    }
    
    public String getAmountString() {
        FormatMoney fm = new FormatMoney();
        return fm.showPriceInUSD(amount,1);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DateTime getDate() {
        return date;
    }
    
    public String getDateString() {
        String format = "d MMMM, yyyy HH:mm:ss";
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return this.date.toString(fmt);
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
