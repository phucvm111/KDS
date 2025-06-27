/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.parent;

import dal.AttendanceDAO;
import dal.ClassDAO;
import dal.KindergartnerDAO;
import dal.StudyRecordDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import model.Account;
import model.Attendance;
import model.Class;
import model.Kindergartner;
import model.StudyRecord;

/**
 *
 * @author NQ
 */
public class ChildDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet ChildDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChildDetailServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            request.setAttribute("error", "Vui lòng đăng nhập trước");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        KindergartnerDAO kinderDAO = new KindergartnerDAO();
        StudyRecordDAO srDAO = new StudyRecordDAO();
        ClassDAO classDAO = new ClassDAO();
        AttendanceDAO attDAO = new AttendanceDAO();

        List<Kindergartner> kids = kinderDAO.getKidbyParent(acc);
        List<StudyRecord> allRecords = srDAO.getAllStudyRecord();
        List<Class> allClasses = classDAO.getAllClass();

        // Map: mỗi trẻ kèm class hiện tại (nếu có)
        Map<Integer, Class> classMap = new HashMap<>();
        for (Kindergartner kid : kids) {
            Class c = srDAO.getKidClass(kid.getKinder_id());
            if (c != null) {
                classMap.put(kid.getKinder_id(), c);
            }
        }

        // Danh sách lớp còn chỗ trống (<30 trẻ)
        List<Class> availableClasses = new ArrayList<>();
        for (Class c : allClasses) {
            long count = allRecords.stream()
                    .filter(sr -> sr.getClassID().getClass_id() == c.getClass_id())
                    .count();
            if (count < 30) {
                availableClasses.add(c);
            }
        }

        // Map: mỗi trẻ kèm danh sách điểm danh
        Map<Integer, List<Attendance>> attendanceMap = new HashMap<>();
        for (Kindergartner kid : kids) {
            List<Attendance> alist = attDAO.getKidAttendance(kid.getKinder_id());
            Collections.reverse(alist);
            attendanceMap.put(kid.getKinder_id(), alist);
        }

        session.setAttribute("kidlist", kids);
        session.setAttribute("classMap", classMap);
        session.setAttribute("attendanceMap", attendanceMap);
        session.setAttribute("classlist", availableClasses);

        request.getRequestDispatcher("parent/childdetails.jsp").forward(request, response);
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
