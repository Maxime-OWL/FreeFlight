/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FlightDAO;
import DAO.UserDAO;
import Entity.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Duc
 */
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO dao = new UserDAO();
        FlightDAO otherDAO = new FlightDAO();
        ResultSet rs = null;
        String service = request.getParameter("service");
        if (service == null) {
            service = "-1";
        }
        final String userManager = "cp_user_manager.jsp?current_page=user_manager";
        final String view_detail_user = "cp_user_view_detail.jsp?current_page=user_manager";
        final String TableUser = "table_user.jsp";
        final String loginPage = "login.jsp";
        final String cp = "cp.jsp?current_page=dashboard";
        final String edit_user = "cp_user_edit.jsp?current_page=user_manager";
        final String change_pass = "cp_change_password.jsp?current_page=my_account";
        final String user_view_detail = "cp_user_view_detail.jsp";
        final String controller_view_detail = "UserController?service=view_detail";
        final String user_register = "register.jsp";
        final String edit_profile = "cp_edit_profile.jsp?current_page=my_account";
        final String forgot_password = "forgot_password.jsp";
        final String reset_password = "reset_password.jsp";
        final String user_add = "cp_user_add.jsp?current_page=user_manager";
        RequestDispatcher rd;
        if (service.equalsIgnoreCase("add_new_location")) {
            String location = request.getParameter("location");
            try {
                SAXReader reader = new SAXReader();
                String webAppPath = getServletContext().getRealPath("/");
                Document document = reader.read(webAppPath + "/xml/Locations.xml");
                Element rootElement = document.getRootElement();
                Node node = document.selectSingleNode("/Locations/Location[not(../Location/LocationId > Id)]");
                String id = node.valueOf("LocationId");
                System.out.println(id);
            } catch (DocumentException ex) {
                System.out.println("Add New Location Failed!");
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (service.equalsIgnoreCase("user_manager")) {
            rd = request.getRequestDispatcher(userManager);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("delete")) {
            String id = request.getParameter("no");
            int n = dao.delete(Integer.parseInt(id));
            if (n > 0) {
                rd = request.getRequestDispatcher(userManager + "&errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher(userManager + "&errorCode=2");
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("edit_profile")) {
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int userId = Integer.parseInt(userIdString);
            User user = dao.getUser(userId);
            request.setAttribute("requestedUser", user);
            rd = request.getRequestDispatcher(edit_profile);
            rd.forward(request, response);
            return;

        } else if (service.equalsIgnoreCase("update_profile")) {
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int userId = Integer.parseInt(userIdString);

            User user = dao.getUser(userId);
            user.setFullname(request.getParameter("fullname"));
            user.setPhonenumber(request.getParameter("phonenumber"));
            user.setAddress(request.getParameter("address"));
            request.setAttribute("requestedUser", user);
            if (dao.update(user)) {
                rd = request.getRequestDispatcher(edit_profile + "&errorCode=0");
            } else {
                rd = request.getRequestDispatcher(edit_profile + "&errorCode=1");
            }
            rd.forward(request, response);
            return;

        } else if (service.equalsIgnoreCase("listall")) {
            ArrayList<User> arr = dao.list();
            request.setAttribute("arr", arr);
            rd = request.getRequestDispatcher(userManager);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("view_detail")) {
            String userId = request.getParameter("userid");
            String username = request.getParameter("username");
            if (userId == null) {
                request.setAttribute("requestedUser", dao.getUser(username));
            } else {
                request.setAttribute("requestedUser", dao.getUser(Integer.parseInt(userId)));
            }
            rd = request.getRequestDispatcher(user_view_detail);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("edit_user")) {
            String userId = request.getParameter("userid");
            request.setAttribute("requestedUser", dao.getUser(Integer.parseInt(userId)));
            rd = request.getRequestDispatcher(edit_user);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("update_user")) {
            int userId = Integer.parseInt(request.getParameter("userid"));
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String phonenumber = request.getParameter("phonenumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            int status = Integer.parseInt(request.getParameter("cb2"));
            int role = Integer.parseInt(request.getParameter("cb1"));

            User user = dao.getUser(userId);
            user.setId(userId);
            user.setFullname(fullname);
            user.setPhonenumber(phonenumber);
            user.setAddress(address);
            user.setStatus(status);
            user.setRole(role);
            if (dao.update(user)) {
                response.sendRedirect(controller_view_detail + "&errorCode=1&username=" + username);
            } else {
                response.sendRedirect("notification.jsp?errorCode=3");
            }
        } else if (service.equalsIgnoreCase("dashboard")) {
            HttpSession session = request.getSession(true);
            int userid = Integer.parseInt((String) session.getAttribute("userid"));
            User user = dao.getUser(userid);
            String balance = user.getFormattedBalance();
            //System.out.println(balance);
            request.setAttribute("balance", balance);
            rd = request.getRequestDispatcher(cp);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("search")) {
            String search = request.getParameter("txtsearch");
            String role = request.getParameter("cb1");
            String status = request.getParameter("cb2");
            ArrayList<User> arr = dao.list(search, role, status);
            request.setAttribute("arr", arr);
            rd = request.getRequestDispatcher(userManager + "&keyword=" + search + "&role=" + role + "&status=" + status);
            rd.forward(request, response);
        } else if (service.equalsIgnoreCase("login")) {
            String username1 = request.getParameter("username");
            String password1 = request.getParameter("password");
            SAXReader reader = new SAXReader();
            Document document;
            boolean found = false;
            String webAppPath = getServletContext().getRealPath("/");
            try {
                document = reader.read(webAppPath + "/xml/Customers.xml");
                Element root = document.getRootElement();
                for (Iterator i = root.elementIterator("Customer"); i.hasNext();) {
                    Element elt = (Element) i.next();
                    String username = elt.element("Name").getText();
                    String password = elt.element("Password").getText();
                    if (username.equalsIgnoreCase(username1) && password.equalsIgnoreCase(password1)) {
                        String role = elt.element("Role").getText();
                        String userid = elt.element("CustomerId").getText();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("role", role);
                        session.setAttribute("username", username);
                        session.setAttribute("userid", userid);
                        rd = request.getRequestDispatcher("index.jsp");
                        rd.forward(request, response);
                        found = true;
                    }
                }
                if (!found) {
                    rd = request.getRequestDispatcher("login.jsp?errorCode=1");
                    rd.forward(request, response);
                }
            } catch (DocumentException ex) {
                System.out.println("Login Failed!");
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (service.equalsIgnoreCase("logout")) {
            String errorCode = "3";//(String) request.getParameter("errorCode");
            HttpSession session = request.getSession();
            if (session != null) {
                session.invalidate();
            }
            rd = request.getRequestDispatcher("login.jsp?errorCode=" + errorCode);
            rd.forward(request, response);
        } else if (service.equals(
                "register")) {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String phonenumber = request.getParameter("phonenumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String url = user_register + "?username=" + username + "&fullname=" + fullname + "&phonenumber=" + phonenumber + "&email=" + email + "&address=" + address + "&errorCode=";
            if (username == null || username.isEmpty() || username.length() < 3) {
                url = url + "1";
                rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            } else if (email == null || email.isEmpty() || email.length() < 3 || !email.contains("@") || !email.contains(".")) {
                url = url + "2";
                rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            } else {
                if (dao.isUserExisted(username) || dao.isUserExisted(email)) {
                    url = url + "3";
                    rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                    return;
                }
            }

            User user = new User(username, email, 1, 0);
            user.setAddress(address);
            user.setFullname(fullname);
            user.setPhonenumber(phonenumber);
            String madePassword = "adasdasd";//user.makePassword();
            System.out.println(madePassword);
            int n = dao.addUser(user);
            if (n > 0) {
                //Start sending email to user.
                String subject = "Online Auction System - Account Information";
                String body = "Dear " + username + ",\n"
                        + "\n"
                        + "Thank you for using OAS! Your account has been successfully created, you can now log into our system with the following details:\n"
                        + "Username: " + username + "\n"
                        + "Password: " + madePassword + "\n"
                        + "\n"
                        + "Happy bidding,\n"
                        + "Your friends at OAS.";
                FlightDAO other = new FlightDAO();
                other.sendMail(email, subject, body);
                //Finish sending email
                rd = request.getRequestDispatcher(loginPage + "?errorCode=7&username=" + username);
                rd.forward(request, response);
                return;
            } else {
                url = url + "4";
                rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            }

        } else if (service.equals(
                "ajax_load_top_balance")) {
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            if (roleString != null) {
                int role = Integer.parseInt(roleString);
                if (role == 0) {
                    String userId = (String) session.getAttribute("userid");
                    User user = dao.getUser(Integer.parseInt(userId));
                    //System.out.println(user.getBalanceString());
                    rd = request.getRequestDispatcher("top_ajax.jsp?errorCode=1" + "&data1=" + user.getUsername() + "&data2=" + user.getBalanceString());
                    rd.forward(request, response);
                }
            }
            return;
        } else {
            response.sendRedirect("notification.jsp?errorCode=2");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
