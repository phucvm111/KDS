/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.classs;

import model.Class;
import dal.AccountDAO;
import dal.ClassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author Admin
 */
public class UpdateClassServlet extends HttpServlet {

    AccountDAO ad = new AccountDAO();
    ClassDAO cl = new ClassDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        Class c = cl.getClassByID(id);
        List<Account> a = ad.getAllAccounts();
        List<Account> listAc = ad.getAccountByAcId2();
        request.setAttribute("listAc", listAc);
//        request.setAttribute("listC", listC);
        request.setAttribute("aUpdate", a);
        request.setAttribute("clUpdate", c);
        request.getRequestDispatcher("admin/class/adminClasstUpdate.jsp").forward(request, response);
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
        Class cla = new Class();
//        String a = request.getParameter("txtClassName");
//        String b =request.getParameter("txtGrade");
//        String c = request.getParameter("taDesClass");
//        String d = request.getParameter("slRole");
//        PrintWriter out = response.getWriter();
//        out.println(a);
//        out.println(b);
//        out.println(c);
//        out.println(d);
        
        
        cla.setClass_name(request.getParameter("txtClassName"));
        cla.setGrade(Integer.parseInt(request.getParameter("txtGrade")));
        cla.setClass_description(request.getParameter("taDesClass"));
        Account aa = ad.getAccountByID(Integer.parseInt(request.getParameter("slRole")));
        cla.setClass_id(Integer.parseInt(request.getParameter("sid")));
        cla.setAcc(aa);
        cl.updateClass(cla);

        response.sendRedirect("listclass");

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
