/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class NewServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender_raw = request.getParameter("gender");
        boolean gender = true;
        if (gender_raw.equals("male")) {
            
        } else {
            gender = false;
        }
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        HttpSession session = request.getSession(true);
//        try (PrintWriter out = response.getWriter()) {
//            out.print(gender_raw);
//        }
//        session.setAttribute("fname", gender_raw);
//        response.sendRedirect("test.jsp");

        AccountDAO d = new AccountDAO();
        Account a = new Account();
        a.setFirstName(fname);
        a.setLastName(lname);
        a.setGender(gender);
        a.setEmail(email);
        a.setPassword(password);
        a.setDob(dob);
        a.setPhoneNumber(phone);
        a.setAddress(address);
        a.setImg(null);

        Role role = new Role(2, "parent");
        a.setRole(role);
        int check = 0;
        if (password.equals(password2)) {
            List<String> emailList = d.getAllEmail();
            for (String existingEmail : emailList) {
                if (existingEmail.equalsIgnoreCase(email)) {
                    check = 1;
                }
            }
            if (check == 0) {
                d.addAccount(a);
                session.setAttribute("account", a);
                response.sendRedirect("homepage.jsp");
            }
            else {
                request.setAttribute("error1", "This email has already been registered");
                request.getRequestDispatcher("new").forward(request, response);
            }
        } else {
            request.setAttribute("error4", "Retype password incorrect");
            request.getRequestDispatcher("new").forward(request, response);
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
