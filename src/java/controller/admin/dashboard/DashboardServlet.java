/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.dashboard;

import dal.AccountDAO;
import dal.ClassDAO;
import dal.EventDAO;
import dal.KindergartnerDAO;
import model.Account;
import model.Event;
import model.Class;
import model.Kindergartner;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Vu Tuan Hai <HE176383>
 */
public class DashboardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
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
        AccountDAO account = new AccountDAO();
        ClassDAO cls = new ClassDAO();
        KindergartnerDAO kinder = new KindergartnerDAO();
        EventDAO event = new EventDAO();

        List<Account> acc = account.getAllAccounts();
        List<Account> parent = account.getAllParentInfor();
        List<Account> teacher = account.getAllTeacherInfor();
        List<Class> cl = cls.getAllClass();
        List<Kindergartner> kindergartners = kinder.getAllStudent();
        List<Event> events = event.getAllEvent();

        int accountNum = acc.size();
        int parentNum = parent.size();
        int teacherNum = teacher.size();
        int classNum = cl.size();
        int kindergartnerNum = kindergartners.size();
        int eventNum = events.size();

        request.setAttribute("accountNum", accountNum);
        request.setAttribute("parentNum", parentNum);
        request.setAttribute("teacherNum", teacherNum);
        request.setAttribute("classNum", classNum);
        request.setAttribute("kindergartnerNum", kindergartnerNum);
        request.setAttribute("eventNum", eventNum);

        request.getRequestDispatcher("admin/dashboard/adminDashboard.jsp").forward(request, response);
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
        processRequest(request, response);
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
