/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.account;

import dal.AccountDAO;
import dal.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
<<<<<<< HEAD
 * @author Vu Tuan Hai <HE176383>
 */
@WebServlet(name = "ListAccountServlet_1", urlPatterns = {"/ListAccountServlet_1"})
public class ListAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    AccountDAO a = new AccountDAO();
    RoleDAO rd = new RoleDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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




  
    RoleDAO rd = new RoleDAO();

   

        List<Account> ac = a.getAllAccounts();
        List<Role> roles = rd.getAllRoles();
        request.setAttribute("roles", roles);

        request.setAttribute("ac", ac);
        request.getRequestDispatcher("admin/account/adminAccountPage.jsp").forward(request, response);
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


        String txtSearch = request.getParameter("search");
        AccountDAO ac = new AccountDAO();
        ArrayList<Account> list = new ArrayList<>();
        Account account = new Account();
        String slRole = request.getParameter("slRole");
        if (slRole != null) {
            if (slRole.equals("0")) {
                list = ac.getAccountByName(txtSearch);
            } else {
                list = ac.getAccountByAcId(Integer.parseInt(slRole));
            }
        }

        List<Role> roles = rd.getAllRoles();

        request.setAttribute("roles", roles);

//        ArrayList<Account> listAccById = ac.getAccountById(0)
        request.setAttribute("ac", list);
        request.setAttribute("searchName", txtSearch);
        request.setAttribute("searchRole", slRole);
        request.setAttribute("account", account);
        request.getRequestDispatcher("admin/account/adminAccountPage.jsp").forward(request, response);

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
