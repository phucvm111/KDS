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
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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
        try (PrintWriter out = response.getWriter()) {
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

        // 1. Lấy danh sách lớp
        request.setAttribute("classs", new ClassDAO().getAllClass());

        // 2. Lấy danh sách các tuần trong năm
        LinkedHashMap<LocalDate, String> weeks = new ScheduleDAO().getAllWeeksInYear(2025);
        request.setAttribute("weeks", weeks);

        // 3. Nếu chưa chọn tuần (lần đầu vào), gán tuần hiện tại cho select
        LocalDate currentMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (request.getParameter("action") == null) {
            LinkedHashMap<LocalDate, String> currentWeek = new LinkedHashMap<>();
            String label = fmt.format(currentMonday) + " to " + fmt.format(currentMonday.plusDays(6));
            currentWeek.put(currentMonday, label);
            request.setAttribute("currentweek", currentWeek);
        }

        // 4. Tạo danh sách 7 ngày của tuần được chọn (hoặc tuần hiện tại)
        String datee = request.getParameter("datee");
        LocalDate mondayOfSelectedWeek = (datee != null)
                ? LocalDate.parse(datee)
                : currentMonday;

        List<LocalDate> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daysOfWeek.add(mondayOfSelectedWeek.plusDays(i));
        }
        request.setAttribute("daysOfWeek", daysOfWeek);

        // 5. Chuyển tiếp đến trang JSP
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

            LinkedHashMap<LocalDate, String> allWeeks = sd.getAllWeeksInYear(2025);
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
