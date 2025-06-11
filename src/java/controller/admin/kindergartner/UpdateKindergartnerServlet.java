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
public class UpdateKindergartnerServlet extends HttpServlet {

    KindergartnerDAO kd = new KindergartnerDAO();
    AccountDAO ad = new AccountDAO();

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
        int id = Integer.parseInt(request.getParameter("sid"));
        request.setAttribute("sid", id);
        Kindergartner kinder = kd.getKinderById(id);
        request.setAttribute("kinder", kinder);
        List<Kindergartner> listK = kd.getAllStudent();
        request.setAttribute("listK", listK);
        
        request.getRequestDispatcher("admin/kinder/kinderAdminUpdate.jsp").forward(request, response);
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
        String kid = request.getParameter("kid");
        String fname = request.getParameter("txtFsName");
        String lname = request.getParameter("txtLsName");
        String dob = request.getParameter("dob");
        String pid = request.getParameter("txtPrId");
        Boolean gender = request.getParameter("flexRadioDefault").equals("male");
        String image = request.getParameter("txtImg");

//        PrintWriter out = response.getWriter();
//        out.println(kid);
//        out.println(fname);
//        out.println(lname);
//        out.println(dob);
//        out.println(pid);
//        out.println(gender);
//        out.println(image);
//        out.println(kid);

        Kindergartner k = new Kindergartner();
        int accountId = Integer.parseInt(pid);
        Account a = ad.getAccountByID(accountId);

//        out.println(a);

        k.setFirst_name(fname);
        k.setLast_name(lname);
        k.setKinder_id(Integer.parseInt(kid));

        k.setDob(dob);
        k.setGender(gender);
        k.setImg(image);
        k.setParentAccount(a);

        kd.updateKinder(k);
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
