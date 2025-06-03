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

/**
 *
 * @author ACE
 */
public class ResetPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ResetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
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
        //  processRequest(request, response);
        AccountDAO d = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String newpassword = request.getParameter("newPassword");
        String confirmpassword = request.getParameter("confirmPassword");
        if (newpassword != null) {
            if (!newpassword.equals(confirmpassword)) {
                request.setAttribute("error", "Confirm password does not match new password");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            } else if (!Character.isUpperCase(newpassword.charAt(0))) {
                request.setAttribute("error", "The first letter must be upper case !");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            } else if (!newpassword.matches(".*[a-z].*")) {
                request.setAttribute("error", "new password least must contain one lowercase letter ! ");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            } else if (!newpassword.matches(".*[0-9].*")) {
                request.setAttribute("error", "new password least must contain one number letter ! ");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            } else if (!newpassword.matches(".*[!@#$%^&*()_+=\\[\\]{}|;:'\",.<>?/`~\\\\-].*")) {
                request.setAttribute("error", "Password must contain at least one special character.");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            } else if (newpassword.length() < 8) {
                request.setAttribute("error", "Password must >=8 letter !.");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            }
            else{
                d.changePassword(email, newpassword);
                response.sendRedirect("login");
            }

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
