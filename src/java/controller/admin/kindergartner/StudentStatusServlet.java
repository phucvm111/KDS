/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.kindergartner;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import dal.ClassDAO;
import dal.StudyRecordDAO;
import java.util.ArrayList;
import java.util.List;
import model.Class;
import model.StudyRecord;

/**
 *
 * @author admin
 */
@WebServlet(name = "StudentStatusServlet", urlPatterns = {"/students"})
public class StudentStatusServlet extends HttpServlet {

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
            out.println("<title>Servlet StudentStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentStatusServlet at " + request.getContextPath() + "</h1>");
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

        // ==== Phân trang ====
        int page = 1;
        int pageSize = 10;
        String pageRaw = request.getParameter("page");
        if (pageRaw != null && !pageRaw.isEmpty()) {
            try {
                page = Integer.parseInt(pageRaw);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // ==== Lấy tham số lọc ====
        String status = request.getParameter("status");
        request.setAttribute("status", status); // truyền status về jsp để xử lý hiển thị

        String yearRaw = request.getParameter("year");
        String classIdRaw = request.getParameter("classId");
        String name = request.getParameter("name");
        if (name == null) {
            name = "";
        }

        int year = (yearRaw != null && !yearRaw.isEmpty()) ? Integer.parseInt(yearRaw) : LocalDate.now().getYear();
        int classId = (classIdRaw != null && !classIdRaw.isEmpty()) ? Integer.parseInt(classIdRaw) : -1;

        StudyRecordDAO dao = new StudyRecordDAO();
        ClassDAO classDAO = new ClassDAO();
        List<Class> allClasses = classDAO.getAllClass();
        request.setAttribute("classList", allClasses);
        request.setAttribute("year", year);
        request.setAttribute("classId", classId);
        request.setAttribute("name", name);

        // ==== Lấy danh sách theo trạng thái ====
        List<StudyRecord> fullList = new ArrayList<>();

        switch (status != null ? status : "") {
            case "graduated":
                if (classId != -1 && !name.isEmpty()) {
                    fullList = dao.getGraduatedStudyRecordsByClassYearAndName(classId, year, name);
                } else if (classId != -1) {
                    fullList = dao.getGraduatedStudyRecordsByClassAndYear(classId, year);
                } else if (!name.isEmpty()) {
                    fullList = dao.getGraduatedStudyRecordsByClassYearAndName(-1, year, name);
                } else {
                    fullList = dao.getGraduatedStudyRecordsByYear(year);
                }
                request.setAttribute("pageTitle", "🎓 Danh sách học sinh đã tốt nghiệp");
                request.setAttribute("isGraduated", true);
                break;

            case "dropped":
                if (classId != -1 && !name.isEmpty()) {
                    fullList = dao.getDroppedOutStudentsByClassYearAndName(classId, year, name);
                } else if (classId != -1) {
                    fullList = dao.getDroppedOutStudentsByClassAndYear(classId, year);
                } else if (!name.isEmpty()) {
                    fullList = dao.getDroppedOutStudentsByClassYearAndName(-1, year, name);
                } else {
                    fullList = dao.getDroppedOutStudentsByYear(year);
                }
                request.setAttribute("pageTitle", "⛔ Danh sách học sinh thôi học");
                request.setAttribute("isGraduated", false);
                break;

            default: // studying
                if (classId != -1 && !name.isEmpty()) {
                    fullList = dao.getStudentsStudyingByClassYearAndName(classId, year, name);
                } else if (classId != -1) {
                    fullList = dao.getStudentsStudyingByClassAndYear(classId, year);
                } else if (!name.isEmpty()) {
                    fullList = dao.getStudentsStudyingByClassYearAndName(-1, year, name);
                } else {
                    fullList = dao.getStudentsStudying(year);
                }
                request.setAttribute("pageTitle", "📚 Danh sách học sinh đang học");
                request.setAttribute("isGraduated", false);
                break;
        }

        // ==== Phân trang thủ công ====
        int totalStudents = fullList.size();
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalStudents);
        List<StudyRecord> pagedList = fullList.subList(start, end);

        // ==== Truyền cho JSP ====
        request.setAttribute("studentList", pagedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalStudents", totalStudents);

        request.getRequestDispatcher("/admin/kinder/students.jsp").forward(request, response);
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
