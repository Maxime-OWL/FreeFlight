/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import Entity.Category;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author MrTu
 */
public class CategoryController extends HttpServlet {

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
        CategoryDAO dao = new CategoryDAO();
        String service = request.getParameter("service");
        final String category_manager = "cp_category_manager.jsp?current_page=category_manager";
        final String category_edit = "cp_category_edit.jsp?current_page=category_manager";
        final String category_add = "cp_category_add.jsp?current_page=category_manager";

        RequestDispatcher rd;
        if (service.equalsIgnoreCase("category_manager")) {
            rd = request.getRequestDispatcher(category_manager);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("listall")) {
            ArrayList<Category> arr = dao.list();
            request.setAttribute("arr", arr);
            rd = request.getRequestDispatcher(category_manager);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("addcategory")) {
            String name = request.getParameter("name");
            if (name == null || name.isEmpty() || name.length() < 3) {
                rd = request.getRequestDispatcher(category_add+"&errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                String description = request.getParameter("description");
                Category category = new Category();
                category.setName(name);
                category.setDescription(description);
                if (dao.add(category)) {
                    rd = request.getRequestDispatcher(category_manager+"errorCode=3");
                    rd.forward(request, response);
                    return;
                } else {
                    rd = request.getRequestDispatcher(category_add+"&errorCode=2");
                    rd.forward(request, response);
                    return;
                }
            }
        } else if (service.equalsIgnoreCase("delete")) {
            String id = request.getParameter("categoryid");
            if (dao.delete(Integer.parseInt(id))) {
                rd = request.getRequestDispatcher(category_manager + "&errorCode=4");
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher(category_manager + "&errorCode=3");
                rd.forward(request, response);
                return;
            }
        } else if (service.equalsIgnoreCase("edit")) {
            String id = request.getParameter("categoryid");
            int categoryid = Integer.parseInt(id);
            Category category = dao.getCategory(categoryid);
            request.setAttribute("category", category);
            rd = request.getRequestDispatcher(category_edit);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("update")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String desc = request.getParameter("description");
            int categoryid = Integer.parseInt(id);
            if (name == null || name.isEmpty() || name.length() <= 0 || name.length() > 20) {
                rd = request.getRequestDispatcher(category_manager + "&errorCode=1");
                rd.forward(request, response);
                return;
            } else {
                Category category = dao.getCategory(categoryid);
                category.setName(name);
                category.setDescription(desc);
                if (dao.update(category)) {
                    rd = request.getRequestDispatcher(category_manager + "&errorCode=0");
                    rd.forward(request, response);
                    return;
                } else {
                    rd = request.getRequestDispatcher(category_manager + "&errorCode=2");
                    rd.forward(request, response);
                    return;
                }
            }
        } else if (service.equalsIgnoreCase("search")) {
            String search = request.getParameter("txtsearch");
            ArrayList<Category> arr = dao.searchCategory(search);
            request.setAttribute("arr", arr);
            rd = request.getRequestDispatcher(category_manager + "&keyword=" + search);
            rd.forward(request, response);
            return;
        } else if (service.equalsIgnoreCase("load_top")) {
            ArrayList<Category> categories = dao.getTop(1000);
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("top.jsp");
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
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
