/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin.tutitionfree;

import static com.yourapp.service.EmailService.sendReminderEmail;
import dal.AccountDAO;
import dal.TutitionFreeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.TutitionFree;
import javax.mail.*;


/**
 *
 * @author ACE
 */
public class remindServlet extends HttpServlet {
   
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
            out.println("<title>Servlet remindServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet remindServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
       // processRequest(request, response);
        HttpSession session= request.getSession();
        AccountDAO ac=new AccountDAO();
        TutitionFreeDAO tf=new TutitionFreeDAO();
       String tuitionIdstring= request.getParameter("tuitionId");
       String accountIdstring= request.getParameter("accountId");
       int accountid=Integer.parseInt(accountIdstring);
       int tuitionId=Integer.parseInt(tuitionIdstring);
       Account account=ac.getAccountByID(accountid);
       TutitionFree tuit=tf.getTutitionById(tuitionId);
    String namekinder=   tuit.getKinder().getFirst_name()+" "+ tuit.getKinder().getLast_name();
    String nameparent=account.getFirstName()+" "+account.getLastName();
    String emailoarent=account.getEmail();
    double amount=tuit.getAmount();
    sendReminderEmail(emailoarent, nameparent, namekinder,amount+"" );
    session.setAttribute("message", "Đã gửi nhắc nhở đến " + emailoarent);
    response.sendRedirect("tutitionfree");
       
       
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
