/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin.tutitionfree;

import dal.AccountDAO;
import dal.KindergartnerDAO;
import dal.TutitionFreeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Kindergartner;
import model.TutitionFree;

/**
 *
 * @author ACE
 */
public class tutitionfreeServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet tutitionfreeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tutitionfreeServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // DAO khởi tạo
    TutitionFreeDAO td = new TutitionFreeDAO();
    KindergartnerDAO kd = new KindergartnerDAO();
    AccountDAO ad = new AccountDAO();

    // Lấy danh sách học phí
    List<TutitionFree> tfList = td.getAlltutition();
    request.setAttribute("tutitionfrees", tfList);

    // Lấy danh sách trẻ
    List<Kindergartner> kindergartners = kd.getAllStudent();
    request.setAttribute("kinders", kindergartners);

    // Lấy danh sách phụ huynh tương ứng với các bé có học phí
    List<Account> parentAccounts = new ArrayList<>();
    if (tfList != null) {
        for (TutitionFree tf : tfList) {
            int parentId = tf.getKinder().getParent_id();
            Account parent = ad.getAccountByID(parentId);
            parentAccounts.add(parent);
        }
    }
    request.setAttribute("accounts", parentAccounts);

    // Forward đến JSP
    request.getRequestDispatcher("Management_TuitionFee/TuitionFee/tuition_list.jsp")
           .forward(request, response);
}


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
