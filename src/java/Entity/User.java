/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import DAO.FormatMoney;
import DAO.OtherDAO;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MrTu
 */
public class User {

    int id;
    String fullname;
    String username;
    String password;
    String phonenumber;
    String email;
    String address;
    double balance;
    int role;
    int status;
    String salt;
    String joinDate;

    public User() {
    }

    public User(String username, String email, int status, int role) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public User(String fullname, String username, String password, String phonenumber, String email, String address, String salt) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.salt = salt;
    }

    public User(String fullname, String username, String password, String phonenumber, String email, String address) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
    }

    public User(int id, String fullname, String username, String password, String phonenumber, String email, String address, String role, String status) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.setRole(role);
        this.setStatus(status);
    }

    public User(String fullname, String username, String password, String phonenumber, String email, String address, String role, String status, String salt) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.setRole(role);
        this.setStatus(status);
        this.salt = salt;
    }

    public User(int id, String fullname, String username, int status, int role) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public User(int id, String fullname, String phonenumber, String address) {
        this.id = id;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.address = address;
    }

    public String getSalt() {
        return salt;
    }

    public void makeSalt() {
        DAO.OtherDAO other = new OtherDAO();
        this.salt = other.makeRandomString(10, 10);
    }

    public String makePassword() throws NoSuchAlgorithmException {
        if (this.salt == null) {
            this.makeSalt();
        }
        DAO.OtherDAO other = new OtherDAO();
        String newPassword = other.makeRandomString(10, 10);
        String step1 = other.getMd5FromString(newPassword);
        String step2 = step1 + this.salt;
        String step3 = other.getMd5FromString(step2);
        this.password = step3;
        return newPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        if (fullname == null) {
            return "";
        }
        return fullname;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        if (phonenumber == null) {
            return "";
        }
        return phonenumber;
    }

    public double getBalance() {
        return balance;
    }
    public String getFormattedBalance() {
        FormatMoney fm = new FormatMoney();
        return fm.showPriceInUSD(balance,1);
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    public String getAddress() {
        if (address == null) {
            return "";
        }
        return address;
    }

    public int getStatusId() {
        return status;
    }

    public String getStatus() {
        if (this.status == 0) {
            return "Inactive";
        } else if (this.status == 1) {
            return "Active";
        } else {
            return "Error";
        }
    }

    public int getRoleId() {
        return role;
    }

    public String getRole() {
        if (this.role == 2) {
            return "Administrator";
        } else if (this.role == 1) {
            return "Auction staff";
        } else if (this.role == 0) {
            return "Customer";
        } else {
            return "Error";
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("Active")) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public void setRole(String role) {
        if (role.equalsIgnoreCase("Administrator")) {
            this.role = 2;
        } else if (role.equalsIgnoreCase("Auction staff")) {
            this.role = 1;
        } else {
            this.role = 0;
        }
    }
    
    public void setRole(int role) {
        this.role = role;
    }

    public String getFormattedJoinDate() throws ParseException {
        OtherDAO other = new OtherDAO();
        return other.formatDate(this.joinDate);
    }
    
    public String getJoinDate()  {
        return this.joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getBalanceString() {
        FormatMoney fm = new FormatMoney();
        return fm.showPriceInUSD(this.balance, 1);
    }
}
