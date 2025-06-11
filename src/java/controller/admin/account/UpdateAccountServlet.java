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
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class UpdateAccountServlet extends HttpServlet {

   
    AccountDAO ad = new AccountDAO();
    RoleDAO rd = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        List<Role> listR = rd.getAllRoles();
      
        Account s = ad.getAccountByID(id);
        request.setAttribute("s", s);
        request.setAttribute("listR", listR);
        
        request.getRequestDispatcher("admin/account/adminAccountUpdate.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account ac = new Account();

        ac.setFirstName(request.getParameter("txtFirstName"));
        ac.setLastName(request.getParameter("txtLastName"));
        ac.setGender(request.getParameter("gender").equals("male"));
        ac.setEmail(request.getParameter("txtEmail"));
        ac.setPassword(request.getParameter("txtPassword"));
        ac.setDob(request.getParameter("dob"));
        ac.setPhoneNumber(request.getParameter("txtPhone"));
        ac.setAddress(request.getParameter("ttAddress"));
        ac.setImg(request.getParameter("txtImg"));
//        ac.setRoleId(Integer.parseInt(request.getParameter("slRole")));
        Role r = rd.getRoleByID(Integer.parseInt(request.getParameter("slRole")));
        ac.setRole(r);
        ac.setAccountID(Integer.parseInt(request.getParameter("ida")));
        RoleDAO rdao = new RoleDAO();
        List<Role> roles = rdao.getAllRoles();
        PrintWriter out = response.getWriter();
        request.setAttribute("listR", roles);

        
        out.println(ac);
        ad.updateAccount(ac);
        response.sendRedirect("listaccount");
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
