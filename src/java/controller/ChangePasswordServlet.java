/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ACE
 */
public class ChangePasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        AccountDAO d = new AccountDAO();
        String email = acc.getEmail();
        String pass = acc.getPassword();
        String currentpassword = request.getParameter("currentPassword");
        String newpassword = request.getParameter("newPassword");
        String confirmpassword = request.getParameter("confirmPassword");

        if (!currentpassword.equals(pass)) {
            request.setAttribute("changepassfalse", "Current password incorrect!");
        } else if (!(newpassword.equals(confirmpassword))) {
            request.setAttribute("changepassfalse", "New password and confirm password must match.");
        } else if (newpassword.length() < 8) {
            request.setAttribute("changepassfalse", "Please input new password >=8 character!");
        } else if (!newpassword.matches(".*[A-Z].*")) {
            request.setAttribute("changepassfalse", "Password must contain at least one uppercase letter.");
        } else if (!newpassword.matches(".*[a-z].*")) {
            request.setAttribute("changepassfalse", "Password must contain at least one lowercase letter.");
        } else if (!newpassword.matches(".*[0-9].*")) {
            request.setAttribute("changepassfalse", "Password must contain at least one number letter.");
        } else if (!newpassword.matches(".*[!@#$%^&*()_+=\\[\\]{}|;:'\",.<>?/`~\\\\-].*")) {
            request.setAttribute("changepassfalse", "Password must contain at least one special character.");
        } else {
            d.changePassword(email, newpassword);
            request.setAttribute("changepasssucess", "Change password sucessful.");
        }

        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
