/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FlightDAO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        FlightDAO otherDAO = new FlightDAO();
        ResultSet rs = null;
        String service = request.getParameter("service");
        if (service == null) {
            service = "-1";
        }
        final String userManager = "cp_user_manager.jsp?current_page=user_manager";
       
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
