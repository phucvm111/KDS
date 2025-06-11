/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.kindergartner;

import dal.AccountDAO;
import dal.KindergartnerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Kindergartner;

/**
 *
 * @author Admin
 */
public class AddKindergartnerServlet extends HttpServlet {

    KindergartnerDAO acd = new KindergartnerDAO();

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
        KindergartnerDAO kd = new KindergartnerDAO();
        List<Kindergartner> listK = kd.getAllStudent();
        request.setAttribute("listK", listK);

        
        request.getRequestDispatcher("admin/kinder/kinderAdminAdd.jsp").forward(request, response);
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
        Kindergartner k = new Kindergartner();
        AccountDAO acdao = new AccountDAO();

//        a.getAccountID(request.getParameter("txtPrId")
        int accountId = Integer.parseInt(request.getParameter("txtPrId"));
        Account a = acdao.getAccountByID(accountId);
        k.setParentAccount(a);
        k.setFirst_name(request.getParameter("txtFsName"));
        k.setLast_name(request.getParameter("txtLsName"));
        k.setDob(request.getParameter("dtDob"));
        k.setGender(request.getParameter("flexRadioDefault").equals("male"));
        k.setImg(request.getParameter("txtImg"));
        acd.insertKinder(k);

        response.sendRedirect("listkinder");
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
