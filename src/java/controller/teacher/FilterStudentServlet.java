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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
//import java.util.stream.Collectors;
import model.Account;
import model.Attendance;
import model.Class;
import model.KinderRecordStudy;
//import model.Kindergartner;

/**
 *
 * @author Windows 10 TIMT
 */
public class FilterStudentServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterStudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterStudentServlet at " + request.getContextPath() + "</h1>");
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
        String checkbar = request.getParameter("searchbar");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        Class kinderClass = (Class) session.getAttribute("kinder_class");
        Account acc = (Account) session.getAttribute("account");
        KindergartnerDAO kinderDao = new KindergartnerDAO();
        AttendanceDAO attdao = new AttendanceDAO();
        if (acc != null) {
            String checkindate = LocalDate.now().toString();
            List<KinderRecordStudy> listKinder = new ArrayList<>();
            if (action.equalsIgnoreCase("checkin")) {
                listKinder = kinderDao.getKidsByClass(kinderClass.getClass_id());
            } else if (action.equalsIgnoreCase("checkout")) {
                listKinder = attdao.getAllCheckInKidsOfADay(kinderClass.getClass_id(), checkindate);
            }
            List<KinderRecordStudy> listKinderOuput = new ArrayList<>();
            for (KinderRecordStudy kinderRecordStudy : listKinder) {
                String fulname = kinderRecordStudy.getKinder().getFirst_name()+"+"+kinderRecordStudy.getKinder().getLast_name();
                if (fulname.equalsIgnoreCase(checkbar) || fulname.contains(checkbar)) {
                    listKinderOuput.add(kinderRecordStudy);
                }
            }
            LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
            for (KinderRecordStudy kinderRecordStudy : listKinderOuput) {
                if (action.equalsIgnoreCase("checkin")) {
                    map.put(kinderRecordStudy.getKinder().getKinder_id(), 0);
                } else if (action.equalsIgnoreCase("checkout")) {
                    map.put(kinderRecordStudy.getKinder().getKinder_id(), 1);
                }
            }
            List<Attendance> attendances = attdao.getAllAttendanceOfInputDay(checkindate);
            if (!attendances.isEmpty()) {
                for (Attendance a : attendances) {
                    if (a.getStatus() != 0 && action.equalsIgnoreCase("checkin")) {
                        map.replace(a.getKinder().getKinder_id(), 1);
                    } else if (a.getStatus() == 2 && action.equalsIgnoreCase("checkout")) {
                        map.replace(a.getKinder().getKinder_id(), 2);
                    }
                }
            }
            request.setAttribute("studentMap", map);
            request.setAttribute("checkindate", checkindate);
            request.setAttribute("listKinder", listKinderOuput);
            request.setAttribute("isPast", false);
            request.setAttribute("present_kids", attendances);
            if (action.equalsIgnoreCase("checkin")) {
                request.getRequestDispatcher("teacher/checkin.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("checkout")) {
                request.getRequestDispatcher("teacher/checkout.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login");
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