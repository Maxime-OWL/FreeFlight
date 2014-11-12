/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FlightDAO;
import Entity.Flight;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MrDuc
 */
public class FlightController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        FlightDAO dao = new FlightDAO();
        String service = request.getParameter("service");
        final String auction_manager = "cp_auction_manager.jsp?current_page=auction_manager";
        final String bidding_detail = "cp_bidding_detail.jsp";
        final String add_auction = "cp_auction_add.jsp?current_page=auction_manager";
        final String view_detail_auction = "cp_auction_view_detail.jsp?current_page=auction_manager";
        final String edit_auction = "cp_edit_auction.jsp";
        final String add_new_auction = "cp_auction_add.jsp";
        final String view_auction = "auction_detail.jsp";
        final String product_edit = "cp_customer_product_edit.jsp";
        final String index = "index.jsp?errorCode=";
        final String myproduct = "cp_customer_my_product.jsp?current_page=my_product";
        final String add_product = "cp_customer_product_add.jsp?current_page=my_product";
        final String product_manager = "cp_customer_my_product.jsp?current_page=my_product";
        final String view_detail_product = "cp_customer_product_edit.jsp?current_page=my_product";
        final String save_product = "cp_customer_product_edit_home.jsp?current_page=my_product";
        ResultSet rs, rss, rst;
        RequestDispatcher rd;
        final String auction_detail_loading = "auction_detail_ajax.jsp";
        String webAppPath = getServletContext().getRealPath("/");
        if (service.equalsIgnoreCase("index")) {
            ArrayList<Flight> flights =  dao.fetchFlights(webAppPath);
            request.setAttribute("flights", flights);
            rd = request.getRequestDispatcher("index.jsp");
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
            Logger.getLogger(FlightController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FlightController.class
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
            Logger.getLogger(FlightController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FlightController.class
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
