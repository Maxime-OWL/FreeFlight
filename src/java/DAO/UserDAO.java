/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.security.NoSuchAlgorithmException;
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

/**
 *
 * @author MrTu
 */
public class UserDAO {

    private Connection conn = null;
    private Statement state = null;
    private ResultSet rs = null;
    private PreparedStatement pre = null;
    private Session session = null;
    Message message = null;

    public UserDAO() {
        if (this.conn == null) {
            try {
                //System.out.println("Connecting to DB using the following details:");
                javax.naming.Context ctx = new javax.naming.InitialContext();
                String host = (String) ctx.lookup("java:comp/env/db-host");
                //System.out.println(host);
                String port = (String) ctx.lookup("java:comp/env/db-port");
                //System.out.println(port);
                String database = (String) ctx.lookup("java:comp/env/db-database");
                //System.out.println(database);
                String username = (String) ctx.lookup("java:comp/env/db-username");
                //System.out.println(username);
                String password = (String) ctx.lookup("java:comp/env/db-password");
                //System.out.println(password);
                connection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8", username, password);
            } catch (NamingException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void connection(String ulr, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(ulr, username, password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addUser(User user) {
        int n = 0;
        try {
            String sql = "INSERT INTO user (username, password, fullname, phonenumber, email, address, salt, role, status, balance, join_date) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            if (user.getJoinDate() == null) {
                sql = "INSERT INTO user (username, password, fullname, phonenumber, email, address, salt, role, status, balance) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }
            pre = conn.prepareStatement(sql);

            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFullname());
            pre.setString(4, user.getPhonenumber());
            pre.setString(5, user.getEmail());
            pre.setString(6, user.getAddress());
            pre.setString(7, user.getSalt());
            pre.setInt(8, user.getRoleId());
            pre.setInt(9, user.getStatusId());
            pre.setDouble(10, user.getBalance());
            if (user.getJoinDate() != null) {
                pre.setString(11, user.getJoinDate());
            }

            //System.out.println(user.getUsername()+user.getPassword());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return n;

    }

    public ArrayList<User> list(String keyword, String rol, String stt) {
        String sql = "SELECT * FROM user WHERE 1=1 ";
        String sqlRole = " AND role = '" + rol + "' ";
        String sqlStatus = " AND status = '" + stt + "' ";
        String sqlKeyword = " AND MATCH(fullname, username) AGAINST ('" + keyword + "')";

        ArrayList<User> arr = new ArrayList<>();
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (!rol.equals("")) {
                sql = sql + sqlRole;
            }
            if (!stt.equals("")) {
                sql = sql + sqlStatus;
            }
            if (!keyword.equals("")) {
                sql = sql + sqlKeyword;
            }
            sql = sql + " ORDER BY role DESC";

            //System.out.println(sql);
            rs = state.executeQuery(sql);
            String fullname, username;
            int id, role, status;
            while (rs.next()) {
                id = rs.getInt("id");
                fullname = rs.getString("fullname");
                username = rs.getString("username");
                status = rs.getInt("status");
                role = rs.getInt("role");
                User user = new User(id, fullname, username, status, role);
                arr.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<User> list() {
        return list("", "", "");
    }

    public User getUser(int userId) {
        String sql = "SELECT * FROM user WHERE id = ? LIMIT 1";
        User user = new User();
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                int status = rs.getInt("status");
                int role = rs.getInt("role");
                user = new User(username, email, status, role);
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setPhonenumber(rs.getString("phonenumber"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                user.setSalt(rs.getString("salt"));
                user.setJoinDate(rs.getString("join_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println(sql);
        }
        return user;
    }

    public User getUser(String usernameEmail) {
        String sql = "SELECT * FROM user WHERE username = ? OR email = ? LIMIT 1";
        User user = new User();
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setString(1, usernameEmail);
            pre.setString(2, usernameEmail);
            rs = pre.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                int status = rs.getInt("status");
                int role = rs.getInt("role");
                user = new User(rs.getString("username"), email, status, role);
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setPhonenumber(rs.getString("phonenumber"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                user.setSalt(rs.getString("salt"));
                user.setJoinDate(rs.getString("join_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println(sql);
        }
        //System.out.println(sql);
        return user;
    }

    public int delete(int id) {
        int n = 0;
        String sql = "DELETE FROM user WHERE id = " + id;
        try {
            state = (Statement) conn.createStatement();
            n = state.executeUpdate(sql);
            state.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public boolean update(User user) {
        String sql = "UPDATE user SET fullname = ?, username = ?, phonenumber = ?, email = ?, address = ?, role = ?, status = ?, balance = ?, password = ?, salt = ?, join_date = ? WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getFullname());
            pre.setString(2, user.getUsername());
            pre.setString(3, user.getPhonenumber());
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getAddress());
            pre.setInt(6, user.getRoleId());
            pre.setInt(7, user.getStatusId());
            pre.setDouble(8, user.getBalance());
            pre.setString(9, user.getPassword());
            pre.setString(10, user.getSalt());
            pre.setString(11, user.getJoinDate());
            pre.setInt(12, user.getId());
            pre.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int update_profile(User user) {
        int n = 0;
        String sql = "UPDATE user SET fullname = ?, phonenumber = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getFullname());
            pre.setString(2, user.getPhonenumber());
            pre.setString(3, user.getAddress());
            pre.setInt(4, user.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String getSalt(String username) throws SQLException {
        String salt = null;
        String sql = "SELECT salt FROM user WHERE username = '" + username + "'";
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                salt = rs.getString("salt");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salt;
    }

    public int getIdFromUsername(String username) throws SQLException {
        int id = 0;
        String sql = "SELECT id FROM user WHERE username = '" + username + "'";
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    

    public boolean isUserExisted(String clue) throws SQLException {
        if (clue == null || clue.isEmpty()) {
            return false;
        }
        String sql = "SELECT COUNT(*) AS count FROM user WHERE username = ? OR email = ?";
        state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pre = conn.prepareStatement(sql);
        pre.setString(1, clue);
        pre.setString(2, clue);
        rs = pre.executeQuery();
        rs.next();
        return rs.getInt("count") > 0;
    }

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        //dao.sendMail("tupvse02404@fpt.edu.vn","Auction","successfully");
    }

}
