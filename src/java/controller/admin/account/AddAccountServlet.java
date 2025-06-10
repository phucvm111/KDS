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
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Vu Tuan Hai <HE176383>
 */
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/AddAccountServlet"})
public class AddAccountServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAccountServlet at " + request.getContextPath() + "</h1>");
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
    AccountDAO ad = new AccountDAO();
    RoleDAO rd = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Account> acts = ad.getAllAccounts();
        List<Role> roles = rd.getAllRoles();
        request.setAttribute("acts", acts);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("admin/account/adminAccountAdd.jsp").forward(request, response);
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
        Account ac = new Account();
        ac.setAccountID(0);
        ac.setFirstName(request.getParameter("txtFirstName"));
        ac.setLastName(request.getParameter("txtLastName"));
        ac.setGender(request.getParameter("flexRadioDefault").equals("male"));
        ac.setEmail(request.getParameter("txtEmail"));
        ac.setPassword(request.getParameter("txtPassword"));
        ac.setDob((request.getParameter("dob")));
        ac.setPhoneNumber(request.getParameter("txtPhone"));
        ac.setAddress(request.getParameter("ttAddress"));
        ac.setImg(request.getParameter("txtImg"));
        // ac.setRoleId(Integer.parseInt(request.getParameter("slRole")));
        Role rr = rd.getRoleByID(Integer.parseInt(request.getParameter("slRole")));
//        Role r = new Role(Integer.parseInt(request.getParameter("slRole")));
        ac.setRole(rr);
        ad.addAccount(ac);
        PrintWriter out = response.getWriter();
        out.print(ac.toString());
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
