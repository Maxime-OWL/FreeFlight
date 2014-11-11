/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AuctionDAO;
import DAO.BidDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Entity.Auction;
import Entity.Bid;
import Entity.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Duc
 */
public class BidController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        final String auction_manager = "cp_auction_manager.jsp?current_page=auction_manager";
        final String bidding_detail = "cp_bidding_detail.jsp";
        final String add_auction = "cp_auction_add.jsp?current_page=auction_manager";
        final String view_detail_auction = "cp_auction_view_detail.jsp?current_page=auction_manager";
        final String edit_auction = "cp_edit_auction.jsp";
        final String add_new_auction = "cp_auction_add.jsp";
        final String view_auction = "auction_detail.jsp";
        ResultSet rs, rss, rst;
        RequestDispatcher rd;

        final String auction_detail_loading = "auction_detail_ajax.jsp";

        if (service.equalsIgnoreCase("ajax_place_bid_area")) {
            String auctionId = request.getParameter("auctionId");
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            String userId = (String) session.getAttribute("userid");
            if (roleString == null) {
                session.setAttribute("last_page", "AuctionController?service=view&auctionId=" + auctionId);
                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                if (Integer.parseInt(roleString) != 0) {
                    //session.setAttribute("last_page", "AuctionController?service=view&auctionId="+auctionId);
                    rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=2");
                    rd.forward(request, response);
                    return;
                } else {
                    AuctionDAO auctionDAO = new AuctionDAO();
                    Auction auction = auctionDAO.getAuction(Integer.parseInt(auctionId));
                    String auctionStatus = auction.getStatus();
                    if (auction.getSellerId() == Integer.parseInt(userId)) {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=3");
                        rd.forward(request, response);
                        return;
                    } else if (auctionStatus.equals("On-going")) {
                        BidDAO bidDao = new BidDAO();
                        ArrayList<Bid> bids = bidDao.getBidFromAuctionId(Integer.parseInt(auctionId), 1);
                        String userBidValueString = (String) request.getParameter("userBidValue");
                        Double userBidValue = 0.0;
                        Double nextBidValue = auction.getStartPrice() + auction.getIncreaseBy();
                        if (userBidValueString == null) {
                            if (bids.size() > 0) {
                                nextBidValue = bids.get(0).getAmount() + auction.getIncreaseBy();
                                userBidValue = nextBidValue;
                            } else {
                                userBidValue = nextBidValue;
                            }
                        } else {
                            userBidValue = Double.parseDouble(userBidValueString);
                        }

                        UserDAO userDao = new UserDAO();
                        User user = userDao.getUser(Integer.parseInt(userId));
                        if (bids.size() > 0 && bids.get(0).getBidderId() == Integer.parseInt(userId)) {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=6");
                            rd.forward(request, response);
                            return;
                        } else if (user.getBalance() < userBidValue) {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=8");
                            rd.forward(request, response);
                            return;
                        } else if (userBidValue < nextBidValue) {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=11");
                            rd.forward(request, response);
                            return;
                        } else {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=0&auctionId=" + auctionId + "&userBidValue=" + userBidValue + "&nextBidValue=" + nextBidValue);
                            rd.forward(request, response);
                            return;
                        }
                    } else {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=14");
                        rd.forward(request, response);
                        return;
                    }
                }
            }
        } else if (service.equalsIgnoreCase("ajax_load_top_bidder")) {
            String auctionId = request.getParameter("auctionId");
            BidDAO bidDAO = new BidDAO();
            String topBidderName = bidDAO.getTopBidderNameFromAuctionId(Integer.parseInt(auctionId));
            //System.out.println(topBidderName);
            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=7&data1=" + topBidderName);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("ajax_load_current_bid")) {
            String auctionId = request.getParameter("auctionId");
            BidDAO bidDAO = new BidDAO();
            String currentBid = bidDAO.getCurrentBidFromAuctionIdString(Integer.parseInt(auctionId));
            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=10&data1=" + currentBid);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("ajax_load_detail_page_top_mid")) {
            String auctionId = request.getParameter("auctionId");
            BidDAO bidDAO = new BidDAO();
            ArrayList bids = bidDAO.getBidFromAuctionId(Integer.parseInt(auctionId), 7);
            request.setAttribute("bids", bids);
            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=16&data1=" + bidDAO.getTotalBidsOnAuctionId(Integer.parseInt(auctionId)));
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("place_bid")) {
            String auctionId = request.getParameter("auctionId");
            String userBidValue = request.getParameter("userBidValue");
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            String userId = (String) session.getAttribute("userid");
            if (roleString == null) {
                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=4");
                rd.forward(request, response);
                return;
            } else {
                if (Integer.parseInt(roleString) != 0) {
                    rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=4");
                    rd.forward(request, response);
                    return;
                } else {
                    AuctionDAO auctionDAO = new AuctionDAO();
                    Auction auction = auctionDAO.getAuction(Integer.parseInt(auctionId));
                    if (auction.getSellerId() == Integer.parseInt(userId)) {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=4");
                        rd.forward(request, response);
                        return;
                    } else if (auction.getStatus().equals("On-going")) {
                        BidDAO bidDao = new BidDAO();
                        Bid bid = new Bid();
                        UserDAO userDao = new UserDAO();
                        User user = userDao.getUser(Integer.parseInt(userId));
                        if (user.getBalance() < Double.parseDouble(userBidValue)) {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=9");
                            rd.forward(request, response);
                            return;
                        } else {
                            Double minBidRequired = bidDao.getCurrentBidFromAuctionId(Integer.parseInt(auctionId)) + auction.getIncreaseBy();
                            if (Double.parseDouble(userBidValue) < minBidRequired) {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=12");
                                rd.forward(request, response);
                                return;
                            }
                            bid.setBidderId(user.getId());
                            bid.setAuctionId(Integer.parseInt(auctionId));
                            bid.setAmount(Double.parseDouble(userBidValue));
                            bid.setAuctionName(auction.getTitle());
                            if (bidDao.placeBid(bid)) {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=5&auctionId=" + auctionId);
                                rd.forward(request, response);
                                return;
                            } else {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=99&auctionId=" + auctionId);
                                rd.forward(request, response);
                                return;
                            }
                        }
                    } else {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=12&auctionId=" + auctionId);
                        rd.forward(request, response);
                        return;
                    }
                }
            }
        } else if (service.equalsIgnoreCase("cancel_bid")) {
            String auctionIdString = request.getParameter("auctionId");
            int auctionId = Integer.parseInt(auctionIdString);
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            String userIdString = (String) session.getAttribute("userid");
            int userId = Integer.parseInt(userIdString);
            if (roleString == null) {
                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=19");
                rd.forward(request, response);
                return;
            } else {
                if (Integer.parseInt(roleString) != 0) {
                    rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=19");
                    rd.forward(request, response);
                    return;
                } else {
                    AuctionDAO auctionDAO = new AuctionDAO();
                    Auction auction = auctionDAO.getAuction(auctionId);
                    if (auction.getSellerId() == userId) {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=19");
                        rd.forward(request, response);
                        return;
                    } else if (auction.getStatus().equals("On-going")) {
                        BidDAO bidDao = new BidDAO();
                        ArrayList<Bid> bids = bidDao.getBidFromAuctionId(auctionId, 1);
                        if (bids.size() < 1 && bids.get(0).getBidderId() != userId) {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=19");
                            rd.forward(request, response);
                            return;
                        } else {
                            Bid bid = bids.get(0);
                            if (bidDao.cancelBid(bid)) {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=20");
                                rd.forward(request, response);
                                return;
                            } else {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=19");
                                rd.forward(request, response);
                                return;
                            }
                        }
                    } else {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=19");
                        rd.forward(request, response);
                        return;
                    }
                }
            }
        } else if (service.equalsIgnoreCase("buy_now")) {
            String auctionId = request.getParameter("auctionId");
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            String userId = (String) session.getAttribute("userid");
            if (roleString == null) {
                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18");
                rd.forward(request, response);
                return;
            } else {
                if (Integer.parseInt(roleString) != 0) {
                    rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18");
                    rd.forward(request, response);
                    return;
                } else {
                    AuctionDAO auctionDAO = new AuctionDAO();
                    Auction auction = auctionDAO.getAuction(Integer.parseInt(auctionId));
                    if (auction.getSellerId() == Integer.parseInt(userId)) {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18");
                        rd.forward(request, response);
                        return;
                    } else if (auction.getStatus().equals("On-going")) {
                        UserDAO userDao = new UserDAO();
                        User user = userDao.getUser(Integer.parseInt(userId));
                        if (user.getBalance() < auction.getBuynowPrice()) {
                            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18");
                            rd.forward(request, response);
                            return;
                        } else {
                            BidDAO bidDao = new BidDAO();
                            Bid bid = new Bid();
                            bid.setBidderId(user.getId());
                            bid.setAuctionId(auction.getId());
                            bid.setAmount(auction.getBuynowPrice());
                            bid.setAuctionName(auction.getTitle());
                            if (bidDao.placeBid(bid)) {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18&data1=ok");
                                rd.forward(request, response);
                                return;
                            } else {
                                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18");
                                rd.forward(request, response);
                                return;
                            }
                        }
                    } else {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=18");
                        rd.forward(request, response);
                        return;
                    }
                }
            }
        } else if (service.equalsIgnoreCase("mybids")) {
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int user_id = Integer.parseInt(userIdString);

            BidDAO dao = new BidDAO();
            ArrayList<Auction> auctions = dao.getBiddedAuctionsFromUserId(user_id);
            request.setAttribute("auctions", auctions);
            rd = request.getRequestDispatcher("cp_customer_my_bids.jsp?current_page=my_bids");
            rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
