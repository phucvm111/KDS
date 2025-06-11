/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import dal.AttendanceDAO;
import dal.ClassDAO;
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
import java.util.stream.Collectors;
import model.Account;
import model.Attendance;
import model.Class;
import model.KinderRecordStudy;
import model.Kindergartner;

/**
 *
 * @author Windows 10 TIMT
 */
public class CheckAttendancePastServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckAttendacePastServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckAttendacePastServlet at " + request.getContextPath() + "</h1>");
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
        KindergartnerDAO studao = new KindergartnerDAO();
        AttendanceDAO attdao = new AttendanceDAO();
        String action = request.getParameter("action");
        String checkdate = request.getParameter("checkindate");
        String today = LocalDate.now().toString();
        String filteritem = request.getParameter("filteritem");
        List<Kindergartner> listKinder = new ArrayList<>();
        List<Attendance> listAtt = attdao.getAllAttendanceOfInputDay(checkdate);
        if (filteritem == null || filteritem.equals("getall")) {
            if (action.equals("checkout")) {
                listKinder = listAtt.stream()
                        .filter(x -> x.getStatus() != 0)
                        .map(x -> studao.getKidInfoById(x.getKinder().getKinder_id()))
                        .collect(Collectors.toList());
            } else if (action.equals("checkin")) {
                listKinder = listAtt.stream()
                        .map(x -> studao.getKidInfoById(x.getKinder().getKinder_id()))
                        .collect(Collectors.toList());
            }
        } else if (filteritem.equals("absent")) {
            List<Attendance> listAbsent = new ArrayList<>();
            if (action.equals("checkin")) {
                listAbsent = listAtt.stream()
                        .filter(s -> s.getStatus() == 0)
                        .collect(Collectors.toList());
            } else if (action.equals("checkout")) {
                listAbsent = listAtt.stream()
                        .filter(s -> s.getStatus() == 1)
                        .collect(Collectors.toList());
            }
            listKinder = listAbsent.stream()
                    .map(x -> studao.getKidInfoById(x.getKinder().getKinder_id()))
                    .collect(Collectors.toList());
            request.setAttribute("filter", "absent");

        }
        if (action.equals("checkout")) {
            request.setAttribute("listKinder", listKinder);
            request.setAttribute("checkindate", checkdate);
            if (checkdate.equals(today)) {
                request.setAttribute("attendances", listAtt);
            } else {
                request.setAttribute("listAtt", listAtt);
            }
            request.getRequestDispatcher("teacher/checkout.jsp").forward(request, response);
        } else if (action.equals("checkin")) {
            request.setAttribute("listKinder", listKinder);
            request.setAttribute("checkindate", checkdate);
            if (checkdate.equals(today)) {
                request.setAttribute("attendances", listAtt);
            } else {
                request.setAttribute("listAtt", listAtt);
            }
            request.getRequestDispatcher("teacher/checkin.jsp").forward(request, response);
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
        Class kinderClass = (Class) session.getAttribute("kinder_class");
        Account acc = (Account) session.getAttribute("account");
        Class kc = (Class) session.getAttribute("kinder_class");
        AttendanceDAO attdao = new AttendanceDAO();
        KindergartnerDAO kinderDao = new KindergartnerDAO();
        String action = request.getParameter("action");
        try ( PrintWriter out = response.getWriter()) {
            String date1 = request.getParameter("checkindate");
            String today = java.time.LocalDate.now().toString();
            if (action.equals("checkout")) {
                if (date1.equals(today)) {
                    response.sendRedirect("checkout");
                } else {
                    List<KinderRecordStudy> listKinder = attdao.getAllCheckOutKidsOfADay(kinderClass.getClass_id(), date1);
                    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
                    for (KinderRecordStudy kinderRecordStudy : listKinder) {
                        map.put(kinderRecordStudy.getKinder().getKinder_id(), 1);
                    }
                    List<Attendance> attendances = attdao.getAllAttendanceOfInputDay(date1);
                    if (!attendances.isEmpty()) {
                        for (Attendance a : attendances) {
                            if (a.getStatus() == 2) {
                                map.replace(a.getKinder().getKinder_id(), 2);
                            }
                        }
                    }
                    request.setAttribute("studentMap", map);
                    request.setAttribute("checkindate", date1);
                    request.setAttribute("isPast", true);
                    request.setAttribute("listKinder", listKinder);
                    request.setAttribute("present_kids", attendances);
                    request.getRequestDispatcher("teacher/checkout.jsp").forward(request, response);
                }
            } else if (action.equals("checkin")) {
                if (date1.equals(today)) {
                    response.sendRedirect("attendance");
                } else {

                    List<KinderRecordStudy> listKinder = kinderDao.getKidsByClass(kinderClass.getClass_id());
                    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
                    for (KinderRecordStudy kinderRecordStudy : listKinder) {
                        map.put(kinderRecordStudy.getKinder().getKinder_id(), 0);
                    }
                    List<Attendance> attendances = attdao.getAllAttendanceOfInputDay(date1);
                    if (!attendances.isEmpty()) {
                        for (Attendance a : attendances) {
                            if (a.getStatus() != 0) {
                                map.replace(a.getKinder().getKinder_id(), 1);
                            }
                        }
                    }
                    request.setAttribute("studentMap", map);
                    request.setAttribute("checkindate", date1);
                    request.setAttribute("isPast", true);
                    request.setAttribute("listKinder", listKinder);
                    request.setAttribute("present_kids", attendances);
                    request.getRequestDispatcher("teacher/checkin.jsp").forward(request, response);
                }
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
