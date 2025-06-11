/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import dal.AttendanceDAO;
import dal.KindergartnerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import model.Account;
import model.Attendance;
import model.Class;
import model.KinderRecordStudy;

/**
 *
 * @author Windows 10 TIMT
 */
public class CheckOutServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkOutServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        Class kinderClass = (Class) session.getAttribute("kinder_class");
        Account acc = (Account) session.getAttribute("account");
//        KindergartnerDAO kinderDao = new KindergartnerDAO();
        AttendanceDAO attdao = new AttendanceDAO();
        if (acc != null) {
            String checkindate = LocalDate.now().toString();
            List<KinderRecordStudy> listKinder = attdao.getAllCheckInKidsOfADay(kinderClass.getClass_id(), checkindate);
            LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
            for (KinderRecordStudy kinderRecordStudy : listKinder) {
                map.put(kinderRecordStudy.getKinder().getKinder_id(), 1);
            }
            List<Attendance> attendances = attdao.getAllAttendanceOfInputDay(checkindate);
            if (!attendances.isEmpty()) {
                for (Attendance a : attendances) {
                    if (a.getStatus() == 2) {
                        map.replace(a.getKinder().getKinder_id(), 2);
                    }
                }
            }
            request.setAttribute("studentMap", map);
            request.setAttribute("checkindate", checkindate);
            request.setAttribute("listKinder", listKinder);
            request.setAttribute("isPast", false);
            request.setAttribute("present_kids", attendances);
            request.getRequestDispatcher("teacher/checkout.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Do you want to create an account?");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
        HttpSession session = request.getSession(true);
        session.removeAttribute("announcement");
        Account teacher = (Account) session.getAttribute("account");
        String attendanceStrRaw = request.getParameter("attendanceStatus");
        AttendanceDAO attdao = new AttendanceDAO();
        if (attendanceStrRaw.length() != 0) {
            String[] attendanceStr = attendanceStrRaw.split(",");
            String today = LocalDate.now().toString();
            for (String string : attendanceStr) {
                int kinderId = Integer.parseInt(string.substring(0, string.indexOf(":")));
                int status = Integer.parseInt(string.substring(string.indexOf(":") + 1, string.length()));
                attdao.updateAttendanceInfor(status, kinderId, today);
            }
        }else{
            session.setAttribute("announcement", "You have not checked out yet!");
        }

        response.sendRedirect("checkout");
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