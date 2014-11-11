/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Category;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Duc
 */
public class CategoryDAO {

    private Connection conn = null;
    private Statement state = null;
    private ResultSet rs = null;
    private PreparedStatement pre = null;

    public CategoryDAO() {
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void connection(String ulr, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(ulr, username, password);
            //System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Category> list() {
        String sql = "SELECT * FROM category";
        ArrayList<Category> arr = new ArrayList<Category>();
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = state.executeQuery(sql);
            String name, description;
            int id;
            while (rs.next()) {
                id = rs.getInt("categoryid");
                name = rs.getString("name");
                description = rs.getString("description");
                Category category = new Category();
                category.setId(id);
                category.setName(name);
                category.setDescription(description);
                arr.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM category WHERE categoryid = " + id;
        try {
            state = (Statement) conn.createStatement();
            state.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean add(Category category) throws SQLException {
        try {
            String sql = "INSERT INTO category (name,description) VALUES (?,?)";

            pre = conn.prepareStatement(sql);
            pre.setString(1, category.getName());
            pre.setString(2, category.getDescription());
            pre.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ResultSet search(int id) throws SQLException {
        String sql = "SELECT * FROM category WHERE categoryid = ?";
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean update(Category category) throws SQLException {
        String sql = "UPDATE category SET name = ?, description= ? WHERE categoryid = ?";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, category.getName());
            pre.setString(2, category.getDescription());
            pre.setInt(3, category.getId());
            pre.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<Category> searchCategory(String search) throws SQLException {
        String sql = "SELECT * FROM category WHERE name LIKE '%" + search + "%'";
        ArrayList<Category> arr = new ArrayList<>();
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = state.executeQuery(sql);
            String name, description;
            int categoryid;
            while (rs.next()) {
                categoryid = rs.getInt("categoryid");
                name = rs.getString("name");
                description = rs.getString("description");
                Category category = new Category();
                category.setId(categoryid);
                category.setName(name);
                category.setDescription(description);
                arr.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public Category getCategory(int categoryid) {
        Category category = new Category();
        String sql = "SELECT * FROM category WHERE categoryid = ? LIMIT 1";
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setInt(1, categoryid);
            rs = pre.executeQuery();
            rs.next();
            category.setId(rs.getInt("categoryid"));
            category.setDescription(rs.getString("description"));
            category.setName(rs.getString("name"));
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    public ArrayList<Category> getTop(int i) {
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "SELECT category_id , name, category.description, count(category_id) AS count, SUM(views) AS sum FROM auction LEFT JOIN category ON auction.category_id=category.categoryid GROUP BY category_id ORDER BY sum DESC LIMIT ?";
        try {
            state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre = conn.prepareStatement(sql);
            pre.setInt(1, i);
            rs = pre.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("name");
                String categoryDesc = rs.getString("description");
                Category category = new Category();
                category.setId(categoryId);
                category.setName(categoryName);
                category.setDescription(categoryDesc);
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Get top categories failed.");
        }
        return categories;
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
    }

}
