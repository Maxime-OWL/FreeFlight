/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Transaction;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.naming.NamingException;
import org.joda.time.DateTime;

/**
 *
 * @author Duc
 */
public class TransactionDAO {

    private Connection conn = null;
    private Statement state = null;
    private ResultSet rs = null;
    private PreparedStatement pre = null;
    private Session session = null;
    Message message = null;

    public TransactionDAO() {
        if (this.conn == null) {
            try {
                //System.out.println("Connecting to DB using the following details:");
                javax.naming.Context ctx = new javax.naming.InitialContext();
                String host = (String) ctx.lookup("java:comp/env/db-host"); //System.out.println(host);
                String port = (String) ctx.lookup("java:comp/env/db-port"); //System.out.println(port);
                String database = (String) ctx.lookup("java:comp/env/db-database"); //System.out.println(database);
                String username = (String) ctx.lookup("java:comp/env/db-username"); //System.out.println(username);
                String password = (String) ctx.lookup("java:comp/env/db-password"); //System.out.println(password);
                connection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8", username, password);
            } catch (NamingException ex) {
                Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void connection(String string, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(string, username, password);
            //System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TransactionDAO dao = new TransactionDAO();
    }

    public ArrayList<Transaction> getTransactionFromUserId(int user_id, int limit) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            String sql = "SELECT transaction_id, amount, description, user_id, UNIX_TIMESTAMP(date) AS date FROM transaction WHERE user_id = ? ORDER BY date DESC LIMIT ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, user_id);
            pre.setInt(2, limit);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt("transaction_id"));
                trans.setAmount(rs.getDouble("amount"));
                trans.setDesc(rs.getString("description"));
                long dateLong = rs.getLong("date") * 1000;
                DateTime date = new DateTime(dateLong);
                trans.setDate(date);
                transactions.add(trans);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Transaction DAO getTransactionFromUserId failed.");
        }
        return transactions;
    }
    
    public boolean makeTransaction(int userId, String desc, Double amount) {
        try {
            String sql = "INSERT INTO transaction (user_id, description, amount) VALUES (?, ?, ?) ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setString(2, desc);
            pre.setDouble(3, amount);
            pre.executeUpdate();
            
            sql = "UPDATE user SET balance = balance + ? WHERE id = ?";
            pre = conn.prepareStatement(sql);
            pre.setDouble(1, amount);
            pre.setInt(2, userId);
            pre.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Trans DAO, makeTransaction failed!");
            return false;
        }
    }

}
