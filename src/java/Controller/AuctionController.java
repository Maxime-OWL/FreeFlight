/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AuctionDAO;
import DAO.BidDAO;
import DAO.CategoryDAO;
import DAO.OtherDAO;
import DAO.UserDAO;
import Entity.Auction;
import Entity.Bid;
import Entity.Category;
import Entity.User;
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
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 *
 * @author MrTu
 */
public class AuctionController extends HttpServlet {

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
        AuctionDAO dao = new AuctionDAO();
        CategoryDAO cdao = new CategoryDAO();
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

        if (service.equalsIgnoreCase("auction_manager")) {
            ArrayList<Category> array = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", array);
            rd = request.getRequestDispatcher(auction_manager);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("bidnow")) {
            rd = request.getRequestDispatcher(bidding_detail);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("listall")) {
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            ArrayList<Auction> auctions = dao.list();
            request.setAttribute("auctions", auctions);

            rd = request.getRequestDispatcher(auction_manager);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("index")) {
            ArrayList<Category> categoryMenu = cdao.getTop(1000);
            request.setAttribute("categoryMenu", categoryMenu);

            ArrayList<Category> categories = cdao.getTop(5);
            ArrayList[] auctionsArray = dao.list(categories, 8);
            //request.setAttribute("categories", categories);
            String errorCode = request.getParameter("errorCode");
            request.setAttribute("auctionsArray", auctionsArray);
            rd = request.getRequestDispatcher("index.jsp?errorCode=" + errorCode);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("add_auction")) {
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher(add_auction);
            rd.forward(request, response);
            return;
        } else if (service.equals("view_details")) {
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            if (roleString == null) {
                rd = request.getRequestDispatcher("notification.jsp?errorCode=4");
                rd.forward(request, response);
                return;
            } else {
                if (Integer.parseInt(roleString) != 1) {
                    rd = request.getRequestDispatcher("notification.jsp?errorCode=4");
                    rd.forward(request, response);
                    return;
                }
            }

            String auctionIdString = request.getParameter("auctionId");
            int auctionId = Integer.parseInt(auctionIdString);

            Auction auction = dao.getAuction(auctionId);
            request.setAttribute("auction", auction);

            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher(view_detail_auction);
            rd.forward(request, response);
            return;

        } else if (service.equalsIgnoreCase("edit_myproduct")) {
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            if (roleString == null) {
                rd = request.getRequestDispatcher("notification.jsp?errorCode=4");
                rd.forward(request, response);
                return;
            }
            if (Integer.parseInt(roleString) != 0) {
                rd = request.getRequestDispatcher("notification.jsp?errorCode=4");
                rd.forward(request, response);
                return;
            }
            
            String auctionIdString = request.getParameter("auctionid");
            int auctionId = Integer.parseInt(auctionIdString);
            
            
            BidDAO bidDao = new BidDAO();
            ArrayList<Bid> bids = bidDao.getBidFromAuctionId(auctionId, 1);
            System.out.println(bids.size());
            if (bids.size() > 0) {
                rd = request.getRequestDispatcher(myproduct+"&errorCode=21");
                rd.forward(request, response);
                return;
            }

            

            Auction auction = dao.getAuction(auctionId);
            request.setAttribute("auction", auction);

            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher(view_detail_product);
            rd.forward(request, response);
            return;

        } else if (service.equals("view")) {
            String auctionIdString = request.getParameter("auctionId");
            int auctionId = Integer.parseInt(auctionIdString);
            Auction auction = dao.getAuction(auctionId);
            auction.addView();
            dao.update(auction);
            request.setAttribute("auction", auction);
            rd = request.getRequestDispatcher(view_auction);
            rd.forward(request, response);
            return;
        } else if (service.equals("moderator_update")) {
            String moderateStatus = request.getParameter("moderateStatus");
            String auctionId = request.getParameter("auctionId");
            String categoryId = request.getParameter("categoryId");
            //System.out.println(moderateStatus+"-"+auctionId+"-"+categoryId);
            Auction auction = dao.getAuction(Integer.parseInt(auctionId));
            auction.setCategoryId(Integer.parseInt(categoryId));
            auction.setModerateStatus(Integer.parseInt(moderateStatus));

            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            if (dao.update(auction)) {
                request.setAttribute("auction", auction);
                rd = request.getRequestDispatcher(view_detail_auction + "&errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher(auction_manager + "&errorCode=3");
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("search")) {
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            String keyword = request.getParameter("keyword");
            String statusString = request.getParameter("status");
            int status = -1;
            String categoryString = request.getParameter("category");
            int category = -1;
            if (keyword == null) {
                keyword = "";
            }
            if (statusString != null) {
                status = Integer.parseInt(statusString);
            }
            if (categoryString != null) {
                category = Integer.parseInt(categoryString);
            }
            //System.out.println(category);
            ArrayList<Auction> auctions = dao.list(keyword, status, category);
            request.setAttribute("auctions", auctions);
            rd = request.getRequestDispatcher(auction_manager + "&keyword=" + keyword + "&status=" + status + "&category=" + category);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("search_product")) {
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            //System.out.println(userIdString);
            if (userIdString == null) {
                rd = request.getRequestDispatcher("notification.jsp");
                rd.forward(request, response);
                return;
            } else {
                int userid = Integer.parseInt(userIdString);
                String keyword = request.getParameter("keyword");
                String statusString = request.getParameter("status");
                int status = -1;
                if (keyword == null) {
                    keyword = "";
                }
                if (statusString != null) {
                    status = Integer.parseInt(statusString);
                }

                ArrayList<Auction> auctions = dao.searchProduct(keyword, status, userid);
                request.setAttribute("auctions", auctions);
                rd = request.getRequestDispatcher(product_manager + "&keyword=" + keyword + "&status=" + status);
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("load_auctions_in_category")) {
            ArrayList<Category> categoryMenu = cdao.getTop(1000);
            request.setAttribute("categoryMenu", categoryMenu);

            String categoryIdString = request.getParameter("categoryId");
            int categoryId = Integer.parseInt(categoryIdString);
            ArrayList<Auction> auctions = dao.getAuctionsFromCategoryId(categoryId, 500);
            ArrayList<Auction> auctionsOnGoing = new ArrayList<>();
            ArrayList<Auction> auctionsFuture = new ArrayList<>();
            ArrayList<Auction> auctionsClosed = new ArrayList<>();
            for (int i = 0; i < auctions.size(); i++) {
                Auction auction = auctions.get(i);
                if (auction.getStatus().equals("On-going")) {
                    auctionsOnGoing.add(auction);
                } else if (auction.getStatus().equals("Future")) {
                    auctionsFuture.add(auction);
                } else {
                    auctionsClosed.add(auction);
                }
            }
            request.setAttribute("auctionsOnGoing", auctionsOnGoing);
            request.setAttribute("auctionsFuture", auctionsFuture);
            request.setAttribute("auctionsClosed", auctionsClosed);
            rd = request.getRequestDispatcher("category_detail.jsp" + "?categoryId=" + categoryId);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("search_auction")) {
            String keyword = request.getParameter("keyword");
            ArrayList<Category> categoryMenu = cdao.getTop(1000);
            request.setAttribute("categoryMenu", categoryMenu);

            ArrayList<Auction> auctions = dao.searchAuctionByTitle(keyword, 100);
            ArrayList<Auction> auctionsOnGoing = new ArrayList<>();
            ArrayList<Auction> auctionsFuture = new ArrayList<>();
            ArrayList<Auction> auctionsClosed = new ArrayList<>();
            for (int i = 0; i < auctions.size(); i++) {
                Auction auction = auctions.get(i);
                if (auction.getStatus().equals("On-going")) {
                    auctionsOnGoing.add(auction);
                } else if (auction.getStatus().equals("Future")) {
                    auctionsFuture.add(auction);
                } else {
                    auctionsClosed.add(auction);
                }
            }
            request.setAttribute("auctionsOnGoing", auctionsOnGoing);
            request.setAttribute("auctionsFuture", auctionsFuture);
            request.setAttribute("auctionsClosed", auctionsClosed);
            rd = request.getRequestDispatcher("auction_search.jsp" + "?keyword=" + keyword);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("add_new_auction")) {
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String categoryIdString = request.getParameter("categoryId");
            String startingPriceString = request.getParameter("startingPrice");
            String buynowPriceString = request.getParameter("buynowPrice");
            String increaseByString = request.getParameter("increaseBy");
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            String img_1 = request.getParameter("img_1");
            String img_2 = request.getParameter("img_2");
            String img_3 = request.getParameter("img_3");
            String img_4 = request.getParameter("img_4");
            String img_5 = request.getParameter("img_5");
            String v_youtube = request.getParameter("v_youtube");
            String img_cover = request.getParameter("img_cover");
            if (img_1 == null) {
                img_1 = "";
            }
            if (img_2 == null) {
                img_2 = "";
            }
            if (img_3 == null) {
                img_3 = "";
            }
            if (img_4 == null) {
                img_4 = "";
            }
            if (img_5 == null) {
                img_5 = "";
            }
            if (v_youtube == null) {
                v_youtube = "";
            }
            String savedValues = "&title=" + title
                    + "&description=" + description
                    + "&categoryId=" + categoryIdString
                    + "&startingPrice=" + startingPriceString
                    + "&buynowPrice=" + buynowPriceString
                    + "&increaseBy=" + increaseByString
                    + "&startDate=" + startDateString
                    + "&endDate=" + endDateString
                    + "&img_cover=" + img_cover
                    + "&v_youtube=" + v_youtube
                    + "&img_1=" + img_1
                    + "&img_2=" + img_2
                    + "&img_3=" + img_3
                    + "&img_4=" + img_4
                    + "&img_5=" + img_5;

            if (title == null || title.isEmpty() || title.length() < 3) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=1" + savedValues);
                rd.forward(request, response);
                return;
            }

            if (description == null) {
                description = "";
            }

            int categoryId = 0;
            if (categoryIdString == null) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=2" + savedValues);
                rd.forward(request, response);
                return;
            } else {
                categoryId = Integer.parseInt(categoryIdString);
            }
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int seller_id = Integer.parseInt(userIdString);

            OtherDAO other = new OtherDAO();

            double startPrice = other.getValidPrice(startingPriceString);
            double buynowPrice = other.getValidPrice(buynowPriceString);

            if (startPrice == -1 || buynowPrice == -1 || startPrice >= buynowPrice) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=3" + savedValues);
                rd.forward(request, response);
                return;
            }

            double increaseBy = other.getValidPrice(increaseByString);
            if (increaseBy == -1) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=4" + savedValues);
                rd.forward(request, response);
                return;
            }

            DateTime startDate = other.getDateTimeFromString(startDateString).plusMinutes(1);
            DateTime endDate = other.getDateTimeFromString(endDateString).plusMinutes(2);

            if (startDate.isBeforeNow()) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=8" + savedValues);
                rd.forward(request, response);
                return;
            }
            if (endDate.isBefore(startDate)) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=7" + savedValues);
                rd.forward(request, response);
                return;
            }
            Duration duration = new Duration(startDate, endDate);
            if (duration.getStandardMinutes() < 60) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=9" + savedValues);
                rd.forward(request, response);
                return;
            }
            if (img_cover == null || img_cover.isEmpty()) {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=5" + savedValues);
                rd.forward(request, response);
                return;
            }

            if (v_youtube.length() > 0) {
                if (other.getValidYoutubeUrl(v_youtube).isEmpty()) {
                    rd = request.getRequestDispatcher(add_auction + "&errorCode=6" + savedValues);
                    rd.forward(request, response);
                    return;
                }
            }

            Auction auction = new Auction();
            auction.setCategoryId(categoryId);
            auction.setSellerId(seller_id);
            auction.setTitle(title);
            auction.setDescription(description);
            auction.setStartPrice(startPrice);
            auction.setBuynowPrice(buynowPrice);
            auction.setIncreaseBy(increaseBy);
            auction.setImgCover(img_cover);
            auction.setImg1(img_1);
            auction.setImg2(img_2);
            auction.setImg3(img_3);
            auction.setImg4(img_4);
            auction.setImg5(img_5);
            auction.setvYoutube(v_youtube);
            auction.setStartDate(startDate);
            auction.setEndDate(endDate);

            if (dao.add(auction)) {
                rd = request.getRequestDispatcher(auction_manager + "&errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher(add_auction + "&errorCode=0" + savedValues);
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("myproduct")) {
            ArrayList<Category> array = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", array);
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int user_id = Integer.parseInt(userIdString);
            ArrayList<Auction> auction = (ArrayList<Auction>) dao.list(user_id);
            request.setAttribute("auction", auction);
            rd = request.getRequestDispatcher(myproduct);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("save_myproduct")) {
            String auctionid = request.getParameter("auctionid");

            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String categoryIdString = request.getParameter("categoryId");
            String startingPriceString = request.getParameter("startingPrice");
            String buynowPriceString = request.getParameter("buynowPrice");
            String increaseByString = request.getParameter("increaseBy");
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            System.out.println(startDateString);
            String img_1 = request.getParameter("img_1");
            String img_2 = request.getParameter("img_2");
            String img_3 = request.getParameter("img_3");
            String img_4 = request.getParameter("img_4");
            String img_5 = request.getParameter("img_5");
            String v_youtube = request.getParameter("v_youtube");
            String img_cover = request.getParameter("img_cover");
            if (img_1 == null) {
                img_1 = "";
            }
            if (img_2 == null) {
                img_2 = "";
            }
            if (img_3 == null) {
                img_3 = "";
            }
            if (img_4 == null) {
                img_4 = "";
            }
            if (img_5 == null) {
                img_5 = "";
            }
            if (v_youtube == null) {
                v_youtube = "";
            }
            String savedValues = "&title=" + title
                    + "&description=" + description
                    + "&categoryId=" + categoryIdString
                    + "&startingPrice=" + startingPriceString
                    + "&buynowPrice=" + buynowPriceString
                    + "&increaseBy=" + increaseByString
                    + "&startDate=" + startDateString
                    + "&endDate=" + endDateString
                    + "&img_cover=" + img_cover
                    + "&v_youtube=" + v_youtube
                    + "&img_1=" + img_1
                    + "&img_2=" + img_2
                    + "&img_3=" + img_3
                    + "&img_4=" + img_4
                    + "&img_5=" + img_5;
            if (title == null || title.isEmpty() || title.length() < 3) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=1" + savedValues);
                rd.forward(request, response);
                return;
            }

            if (description == null) {
                description = "";
            }

            int categoryId = 0;
            if (categoryIdString == null) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=2" + savedValues);
                rd.forward(request, response);
                return;
            } else {
                categoryId = Integer.parseInt(categoryIdString);
            }
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int seller_id = Integer.parseInt(userIdString);

            OtherDAO other = new OtherDAO();

            double startPrice = other.getValidPrice(startingPriceString);
            double buynowPrice = other.getValidPrice(buynowPriceString);

            if (startPrice == -1 || buynowPrice == -1 || startPrice >= buynowPrice) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=3" + savedValues);
                rd.forward(request, response);
                return;
            }

            double increaseBy = other.getValidPrice(increaseByString);
            if (increaseBy == -1) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=4" + savedValues);
                rd.forward(request, response);
                return;
            }
            DateTime startDate = other.getDateTimeFromString2(startDateString);
            DateTime endDate = other.getDateTimeFromString2(endDateString);

            if (startDate.isBeforeNow()) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=8" + savedValues);
                rd.forward(request, response);
                return;
            }
            if (endDate.isBefore(startDate)) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=7" + savedValues);
                rd.forward(request, response);
                return;
            }
            Duration duration = new Duration(startDate, endDate);
            if (duration.getStandardMinutes() < 60) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=9" + savedValues);
                rd.forward(request, response);
                return;
            }
            if (img_cover == null || img_cover.isEmpty()) {
                rd = request.getRequestDispatcher(save_product + "&errorCode=5" + savedValues);
                rd.forward(request, response);
                return;
            }

            if (v_youtube.length() > 0) {
                if (other.getValidYoutubeUrl(v_youtube).isEmpty()) {
                    rd = request.getRequestDispatcher(save_product + "&errorCode=6 " + savedValues);
                    rd.forward(request, response);
                    return;
                }
            }

            Auction auction = dao.getAuction(Integer.parseInt(auctionid));
            auction.setCategoryId(categoryId);
            auction.setSellerId(seller_id);
            auction.setTitle(title);
            auction.setDescription(description);
            auction.setStartPrice(startPrice);
            auction.setBuynowPrice(buynowPrice);
            auction.setIncreaseBy(increaseBy);
            auction.setImgCover(img_cover);
            auction.setImg1(img_1);
            auction.setImg2(img_2);
            auction.setImg3(img_3);
            auction.setImg4(img_4);
            auction.setImg5(img_5);
            auction.setvYoutube(v_youtube);
            auction.setStartDate(startDate);
            auction.setEndDate(endDate);

            if (dao.update(auction)) {
                rd = request.getRequestDispatcher(myproduct + "&errorCode=0");
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher(save_product + "&errorCode=0" + savedValues);
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("add_product")) {
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher(add_product);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("add_new_product")) {
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String categoryIdString = request.getParameter("categoryId");
            String startingPriceString = request.getParameter("startingPrice");
            String buynowPriceString = request.getParameter("buynowPrice");
            String increaseByString = request.getParameter("increaseBy");
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            String img_1 = request.getParameter("img_1");
            String img_2 = request.getParameter("img_2");
            String img_3 = request.getParameter("img_3");
            String img_4 = request.getParameter("img_4");
            String img_5 = request.getParameter("img_5");
            String v_youtube = request.getParameter("v_youtube");
            String img_cover = request.getParameter("img_cover");
            if (img_1 == null) {
                img_1 = "";
            }
            if (img_2 == null) {
                img_2 = "";
            }
            if (img_3 == null) {
                img_3 = "";
            }
            if (img_4 == null) {
                img_4 = "";
            }
            if (img_5 == null) {
                img_5 = "";
            }
            if (v_youtube == null) {
                v_youtube = "";
            }
            String savedValues = "&title=" + title
                    + "&description=" + description
                    + "&categoryId=" + categoryIdString
                    + "&startingPrice=" + startingPriceString
                    + "&buynowPrice=" + buynowPriceString
                    + "&increaseBy=" + increaseByString
                    + "&startDate=" + startDateString
                    + "&endDate=" + endDateString
                    + "&img_cover=" + img_cover
                    + "&v_youtube=" + v_youtube
                    + "&img_1=" + img_1
                    + "&img_2=" + img_2
                    + "&img_3=" + img_3
                    + "&img_4=" + img_4
                    + "&img_5=" + img_5;

            if (title == null || title.isEmpty() || title.length() < 3) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=1" + savedValues);
                rd.forward(request, response);
                return;
            }

            if (description == null) {
                description = "";
            }

            int categoryId = 0;
            if (categoryIdString == null) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=2" + savedValues);
                rd.forward(request, response);
                return;
            } else {
                categoryId = Integer.parseInt(categoryIdString);
            }
            HttpSession session = request.getSession(true);
            String userIdString = (String) session.getAttribute("userid");
            int seller_id = Integer.parseInt(userIdString);

            OtherDAO other = new OtherDAO();

            double startPrice = other.getValidPrice(startingPriceString);
            double buynowPrice = other.getValidPrice(buynowPriceString);

            if (startPrice == -1 || buynowPrice == -1 || startPrice >= buynowPrice) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=3" + savedValues);
                rd.forward(request, response);
                return;
            }

            double increaseBy = other.getValidPrice(increaseByString);
            if (increaseBy == -1) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=4" + savedValues);
                rd.forward(request, response);
                return;
            }

            DateTime startDate = other.getDateTimeFromString(startDateString).plusMinutes(1);
            DateTime endDate = other.getDateTimeFromString(endDateString).plusMinutes(2);

            if (startDate.isBeforeNow()) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=8" + savedValues);
                rd.forward(request, response);
                return;
            }
            if (endDate.isBefore(startDate)) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=7" + savedValues);
                rd.forward(request, response);
                return;
            }
            Duration duration = new Duration(startDate, endDate);
            if (duration.getStandardMinutes() < 60) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=9" + savedValues);
                rd.forward(request, response);
                return;
            }
            if (img_cover == null || img_cover.isEmpty()) {
                rd = request.getRequestDispatcher(add_product + "&errorCode=5" + savedValues);
                rd.forward(request, response);
                return;
            }

            if (v_youtube.length() > 0) {
                if (other.getValidYoutubeUrl(v_youtube).isEmpty()) {
                    rd = request.getRequestDispatcher(add_product + "&errorCode=6" + savedValues);
                    rd.forward(request, response);
                    return;
                }
            }

            Auction auction = new Auction();
            auction.setCategoryId(categoryId);
            auction.setSellerId(seller_id);
            auction.setTitle(title);
            auction.setDescription(description);
            auction.setStartPrice(startPrice);
            auction.setBuynowPrice(buynowPrice);
            auction.setIncreaseBy(increaseBy);
            auction.setImgCover(img_cover);
            auction.setImg1(img_1);
            auction.setImg2(img_2);
            auction.setImg3(img_3);
            auction.setImg4(img_4);
            auction.setImg5(img_5);
            auction.setvYoutube(v_youtube);
            auction.setStartDate(startDate);
            auction.setEndDate(endDate);

            if (dao.add(auction)) {
                rd = request.getRequestDispatcher(myproduct + "&errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher(add_product + "&errorCode=0" + savedValues);
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("ajax_load_countdown")) {
            String auctionId = request.getParameter("auctionId");
            Auction auction = dao.getAuction(Integer.parseInt(auctionId));
            String status = auction.getStatus();
            String endDate = auction.getFormattedEndDate(1);
            String buynowString = auction.getBuynowPriceString();
            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=13&auctionId=" + auctionId + "&data1=" + status + "&data2=" + endDate + "&data3=" + buynowString);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("ajax_load_detail_bottom_outer_top")) {
            String auctionId = request.getParameter("auctionId");
            Auction auction = dao.getAuction(Integer.parseInt(auctionId));
            request.setAttribute("auction", auction);
            rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=15&auctionId=" + auction.getId());
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("ajax_load_buy_now")) {
            String auctionId = request.getParameter("auctionId");
            HttpSession session = request.getSession(true);
            String roleString = (String) session.getAttribute("role");
            String userId = (String) session.getAttribute("userid");
            if (roleString == null || !roleString.equals("0")) {

                rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=17");
                rd.forward(request, response);
                return;
            } else {
                Auction auction = dao.getAuction(Integer.parseInt(auctionId));
                if (!auction.getStatus().equals("On-going")) {
                    rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=17");
                    rd.forward(request, response);
                    return;
                } else if (auction.getSellerId() == Integer.parseInt(userId)) {
                    rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=17");
                    rd.forward(request, response);
                    return;
                } else {
                    UserDAO userDao = new UserDAO();
                    User user = userDao.getUser(Integer.parseInt(userId));
                    if (user.getBalance() < auction.getBuynowPrice()) {
                        //System.out.println("Checked.");
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=17");
                        rd.forward(request, response);
                        return;
                    } else {
                        rd = request.getRequestDispatcher(auction_detail_loading + "?errorCode=17&data1=" + auction.getBuynowPriceString());
                        rd.forward(request, response);
                        return;
                    }
                }
            }
        } else if (service.equals("start_processing_auction")) {
            String result = dao.processAuctions();
            System.out.println(result);
            request.setAttribute("result", result);
            ArrayList<Category> categories = (ArrayList<Category>) cdao.list();
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher(auction_manager + "&errorCode=4");
            rd.forward(request, response);
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
            Logger.getLogger(AuctionController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AuctionController.class
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
            Logger.getLogger(AuctionController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AuctionController.class
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
