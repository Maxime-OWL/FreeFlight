/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AuctionDAO;
import DAO.WatchlistDAO;
import Entity.Auction;
import Entity.Watchlist;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maxime
 */
public class WatchlistController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        RequestDispatcher rd;
        WatchlistDAO wdao = new WatchlistDAO();
        if (service.equalsIgnoreCase("add_to_watchlist")) {
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            String roleString = (String) session.getAttribute("role");
            if (roleString == null || !roleString.equals("0")) {
                rd = request.getRequestDispatcher("ajax_watchlist.jsp?errorCode=3");
                rd.forward(request, response);
                return;
            }
            int userId = Integer.parseInt(userIdString);
            String auctionIdString = (String) request.getParameter("auctionId");
            int auctionId = Integer.parseInt(auctionIdString);
            Watchlist wl = new Watchlist();
            wl.setAuctionId(auctionId);
            wl.setUserId(userId);
            rd = request.getRequestDispatcher("ajax_watchlist.jsp?errorCode=" + wdao.add(wl));
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("viewwatchlist")) {
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            String errorCode = request.getParameter("errorCode");
            ArrayList<Watchlist> watchlists = (ArrayList<Watchlist>) wdao.list(Integer.parseInt(userIdString));
            ArrayList<Auction> auctions = new ArrayList<>();
            AuctionDAO auctionDAO = new AuctionDAO();
            for (int i = 0; i < watchlists.size(); i++) {
                int auctionid = watchlists.get(i).getAuctionId();
                auctions.add(auctionDAO.getAuction(auctionid));
            }
            request.setAttribute("auctions", auctions);
            rd = request.getRequestDispatcher("cp_customer_my_watchlist.jsp?current_page=my_watchlist&errorCode=" + errorCode);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("delwatchlist")) {
            String id = request.getParameter("auction_id");
            int auction_id = Integer.parseInt(id);
            int n = wdao.delete(auction_id);
            if (n > 0) {
                response.sendRedirect("WatchlistController?service=viewwatchlist&errorCode=1&current_page=my_watchlist");
            } else {
                response.sendRedirect("WatchlistController?service=viewwatchlist&errorCode=0&current_page=my_watchlist");
            }
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
            Logger.getLogger(WatchlistController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WatchlistController.class.getName()).log(Level.SEVERE, null, ex);
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
