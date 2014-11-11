/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OtherDAO;
import DAO.UserDAO;
import Entity.User;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

/**
 *
 * @author Duc
 */
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO dao = new UserDAO();
        OtherDAO otherDAO = new OtherDAO();
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
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else if (service.equalsIgnoreCase("user_manager")) {
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
        } else if (service.equalsIgnoreCase("change_password")) {
            int userId = Integer.parseInt(request.getParameter("userid"));
            String oldPass = request.getParameter("old_password");
            String newPass = request.getParameter("new_password");
            String confirmPass = request.getParameter("confirm_password");
            if (oldPass == null || newPass == null || confirmPass == null || oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty() || newPass.length() < 6 || confirmPass.length() < 6) {
                rd = request.getRequestDispatcher(change_pass + "&errorCode=1");
                rd.forward(request, response);
                return;
            } else if (!newPass.equals(confirmPass)) {
                rd = request.getRequestDispatcher(change_pass + "&errorCode=2");
                rd.forward(request, response);
                return;
            } else if (oldPass.equals(newPass)) {
                rd = request.getRequestDispatcher(change_pass + "&errorCode=3");
                rd.forward(request, response);
                return;
            } else {
                String errorCode = dao.changePassword(oldPass, newPass, userId);
                rd = request.getRequestDispatcher(change_pass + "&errorCode=" + errorCode);
                rd.forward(request, response);
                return;
            }

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
        } else if (service.equalsIgnoreCase("add_user")) {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String phonenumber = request.getParameter("phonenumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            int status = Integer.parseInt(request.getParameter("cb2"));
            int role = Integer.parseInt(request.getParameter("cb1"));
            String url = user_add + "?username=" + username + "&fullname=" + fullname + "&phonenumber=" + phonenumber + "&email=" + email + "&address=" + address + "&cb1=" + role + "&cb2=" + status + "&errorCode=";
            if (username == null || username.isEmpty() || username.length() < 3 || username.length() > 20) {
                url = url + "1";
                rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            } else if (email == null || email.isEmpty() || email.length() < 3 || email.length() > 50 || !email.contains("@") || !email.contains(".")) {
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

            User user = new User(username, email, status, role);

            user.setFullname(request.getParameter("fullname"));
            user.setPhonenumber(request.getParameter("phonenumber"));
            user.setAddress(request.getParameter("address"));

            String madeRawPassword = user.makePassword();

            if (dao.addUser(user) > 0) {
                //Start sending email to user.
                String subject = "Online Auction System - Account Information";
                String body = "Dear " + username + ",\n"
                        + "\n"
                        + "Thank you for using OAS! Your account has been successfully created, you can now log into our system with the following details:\n"
                        + "Username: " + username + "\n"
                        + "Password: " + madeRawPassword + "\n"
                        + "\n"
                        + "Happy bidding,\n"
                        + "Your friends at OAS.";
                OtherDAO other = new OtherDAO();
                other.sendMail(email, subject, body);
                //Finish sending email
                url = url + "0";
                rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            } else {
                response.sendRedirect("notification.jsp?errorCode=1");
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
        } else if (service.equalsIgnoreCase("forgot_password")) {
            rd = request.getRequestDispatcher(forgot_password + "?errorCode=0");
            String usernameEmail = request.getParameter("username_email");
            if (usernameEmail == null || usernameEmail.isEmpty()) {
                rd = request.getRequestDispatcher(forgot_password + "?errorCode=1");
            } else if (!dao.isUserExisted(usernameEmail)) {
                rd = request.getRequestDispatcher(forgot_password + "?errorCode=2");
            } else {
                User user = dao.getUser(usernameEmail);
                String email = user.getEmail();
                if (email == null || email.isEmpty()) {
                    rd = request.getRequestDispatcher(forgot_password + "?errorCode=3");
                } else {
                    OtherDAO other = new OtherDAO();
                    String madeToken = other.makeTokenForUser(user.getId(), 24);
                    if (madeToken.equalsIgnoreCase("existed")) {
                        rd = request.getRequestDispatcher(forgot_password + "?errorCode=4");
                    } else {
                        //Start sending email to user.
                        String domain = getServletContext().getInitParameter("domain");
                        String subject = "Online Auction System - Account Password Recovery";
                        String body = "Hi " + user.getUsername() + ",\n"
                                + "\n"
                                + "You're receiving this email because you requested a password reset for your OAS Account. If you did not request this change, you can safely ignore this email.\n"
                                + "\n"
                                + "To choose a new password and complete your request, please follow the link below:\n"
                                + domain + "UserController?service=reset_password&token=" + madeToken + " \n"
                                + "\n"
                                + "You can change your password again at any time from within the OAS control panel by selecting My account > Change password.\n"
                                + "\n"
                                + "Thanks,\n"
                                + "The OAS Team ";
                        other.sendMail(email, subject, body);
                        //Finish sending email
                    }
                }
            }
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("reset_password")) {
            String token = (String) request.getParameter("token");
            if (token == null || token.isEmpty()) {
                rd = request.getRequestDispatcher(forgot_password + "?errorCode=5");
                rd.forward(request, response);
                return;
            } else {
                OtherDAO other = new OtherDAO();
                String[] tokenData = new String[4];
                tokenData = other.getTokenData(token);
                String userIdString = tokenData[2];
                if (userIdString == null || userIdString.isEmpty()) {
                    rd = request.getRequestDispatcher(forgot_password + "?errorCode=5");
                    rd.forward(request, response);
                    return;
                } else {
                    int userid = Integer.parseInt(userIdString);
                    User user = dao.getUser(userid);
                    //System.out.println(user.getUsername());
                    String newPassword = user.makePassword();
                    //System.out.println(newPassword+" - "+user.getPassword());
                    user.setStatus(0);
                    //System.out.println(user.getStatus());
                    if (dao.update(user)) {
                        rd = request.getRequestDispatcher(reset_password + "?errorCode=0&token=" + token);
                        rd.forward(request, response);
                        return;
                    } else {
                        rd = request.getRequestDispatcher(forgot_password + "?errorCode=6");
                        rd.forward(request, response);
                        return;
                    }
                }
            }
        } else if (service.equalsIgnoreCase("reset_password_finish")) {
            String tokenFinish = (String) request.getParameter("tokenFinish");

            //Validate passwords first
            String password1 = (String) request.getParameter("password1");
            String password2 = (String) request.getParameter("password2");
            //System.out.println(password1+"-"+password2);
            OtherDAO other = new OtherDAO();
            if (!other.isPasswordValid(password1) || !other.isPasswordValid(password2)) {
                rd = request.getRequestDispatcher(reset_password + "?errorCode=1&token=" + tokenFinish);
                rd.forward(request, response);
                return;
            } else if (!password1.equals(password2)) {
                rd = request.getRequestDispatcher(reset_password + "?errorCode=2&token=" + tokenFinish);
                rd.forward(request, response);
                return;
            } else {
                if (tokenFinish == null || tokenFinish.isEmpty()) {
                    rd = request.getRequestDispatcher(forgot_password + "?errorCode=7");
                    rd.forward(request, response);
                    return;
                } else {
                    String[] tokenData = new String[4];
                    tokenData = other.getTokenData(tokenFinish);
                    String userIdString = tokenData[2];
                    if (userIdString == null || userIdString.isEmpty()) {
                        rd = request.getRequestDispatcher(forgot_password + "?errorCode=7");
                        rd.forward(request, response);
                        return;
                    } else {
                        int userid = Integer.parseInt(userIdString);
                        User user = dao.getUser(userid);
                        //System.out.println(user.getUsername());
                        String newPassword = other.getEncryptedPassword(password1, user.getSalt());
                        user.setPassword(newPassword);
                        //System.out.println(newPassword+" - "+user.getPassword());
                        user.setStatus(1);
                        //System.out.println(user.getStatus());
                        if (dao.update(user) && other.cleanUserToken(userid)) { // Update everything and then clean the token.
                            //All should be done by now, let's send some mails.
                            String domain = getServletContext().getInitParameter("domain");
                            String subject = "Online Auction System - Your Password has been changed successfully";
                            String body = "Hi " + user.getUsername() + ",\n"
                                    + "\n"
                                    + "Your OAS password has been successfully updated\n"
                                    + "\n"
                                    + "If you did not request this password change or believe you're receiving this email in error, please contact OAS Administration Team for immediate assistance.\n"
                                    + "\n"
                                    + "Regards,\n"
                                    + "Your friends at OAS ";
                            other.sendMail(user.getEmail(), subject, body);
                            //Finish sending email
                            rd = request.getRequestDispatcher(reset_password);
                            rd.forward(request, response);
                            return;
                        } else {
                            rd = request.getRequestDispatcher(forgot_password + "?errorCode=6");
                            rd.forward(request, response);
                            return;
                        }
                    }
                }
            }
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
                        break;
                    }
                    if (!found) {
                        rd = request.getRequestDispatcher("login.jsp?errorCode=1");
                        rd.forward(request, response);
                    }
                }
            } catch (DocumentException ex) {
                System.out.println("Login Failed!");
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
            String madePassword = user.makePassword();
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
                OtherDAO other = new OtherDAO();
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
            Logger.getLogger(CategoryController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class
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
            Logger.getLogger(UserController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class
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
