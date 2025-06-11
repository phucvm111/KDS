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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class ListAccountServlet extends HttpServlet {

   AccountDAO a = new AccountDAO();
    RoleDAO rd = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

}
