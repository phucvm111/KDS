/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.schedule;

import dal.ActivityDAO;
import dal.ClassDAO;
import dal.ScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import model.Activity;
import model.Class;
import model.ScheduleDetails;

/**
 *
 * @author Admin
 */
public class ListScheduleServlet extends HttpServlet {

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
            out.println("<title>Servlet ListAttendanceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAttendanceServlet at " + request.getContextPath() + "</h1>");
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
        ScheduleDAO sd = new ScheduleDAO();
        // get ClassID
        String classid_raw = request.getParameter("cid");
        int classid = 1;
        if (classid_raw == null) {
            classid = 1;
            Integer cid = (Integer) session.getAttribute("cid");
            if (cid != null) {
                classid = cid;
            }
        } else {
            try {
                classid = Integer.parseInt(classid_raw);
            } catch (Exception e) {

            }
        }
        request.setAttribute("cid_raw", classid);
        session.setAttribute("cid", classid);

        LinkedHashMap<LocalDate, String> allWeeks = sd.getAllWeeksInYear(2022);
        request.setAttribute("weeks", allWeeks);

        ClassDAO cd = new ClassDAO();
        List<Class> list = cd.getAllClass();
        request.setAttribute("classes", list);

        ActivityDAO ad = new ActivityDAO();
        List<Activity> listActivity = ad.getAllActivity();
        request.setAttribute("activity", listActivity);

        String date = request.getParameter("recentMonday");
        String date2 = date;

        if (date != null) {
            try {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf1.parse(date);

                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf2.format(d);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            date = sd.firstDayOfWeek(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date3 = "";
            try {
                Date d = sdf.parse(date);
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                date3 = sdf.format(d);
            } catch (Exception e) {
                System.out.println(e);
            }
            session.setAttribute("recentMonday", date3);
        }

        ScheduleDetails sde = sd.getScheduleDetailsByClassDate(classid, date);
        request.setAttribute("scheduleDetails", sde);

        //return true date
        String action = request.getParameter("action");
        if (action == null) {
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

            request.setAttribute("firstMonday", firstDayOfWeek);
        } else {

            request.setAttribute("recentMonday", date2);
//            session.setAttribute("recentMonday", date);

        }

        request.getRequestDispatcher("admin/schedule/admin_schedule.jsp").forward(request, response);
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
//        processRequest(request, response);

        HttpSession session = request.getSession(true);

        String datetest = request.getParameter("ddlViewBy");
        String date = request.getParameter("datee");
        String classID_raw = request.getParameter("cid");
        int classID = 1;

        request.setAttribute("firstMonday", date);

        session.setAttribute("recentMonday", date);     //yyyy-MM-dd

        try {
            ScheduleDAO sd = new ScheduleDAO();
//            PrintWriter out = response.getWriter();
            classID = Integer.parseInt(classID_raw);

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf1.parse(date);

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf2.format(d);

            session.setAttribute("cid", classID);
            session.setAttribute("datechosen", date);
            session.setAttribute("datetest", datetest);

            ActivityDAO ad = new ActivityDAO();
            List<Activity> listActivity = ad.getAllActivity();
            request.setAttribute("activity", listActivity);

            ScheduleDetails sde = sd.getScheduleDetailsByClassDate(classID, date);
            request.setAttribute("scheduleDetails", sde);

            LinkedHashMap<LocalDate, String> allWeeks = sd.getAllWeeksInYear(2022);
            request.setAttribute("weeks", allWeeks);

            ClassDAO cd = new ClassDAO();
            List<Class> list = cd.getAllClass();
            request.setAttribute("classes", list);

            request.setAttribute("cid_raw", classID);
            request.getRequestDispatcher("admin/schedule/admin_schedule.jsp").forward(request, response);
        } catch (Exception e) {

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
