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
// Nhận tham số từ request
        String status = request.getParameter("status");  // "graduated" hoặc "studying"
        String yearRaw = request.getParameter("year");
        String classIdRaw = request.getParameter("classId");
        String name = request.getParameter("name");
        if (name == null) name = "";

        // Xử lý mặc định cho năm và lớp
        int year = (yearRaw != null && !yearRaw.isEmpty()) ? Integer.parseInt(yearRaw) : LocalDate.now().getYear();
        int classId = (classIdRaw != null && !classIdRaw.isEmpty()) ? Integer.parseInt(classIdRaw) : -1;

        StudyRecordDAO dao = new StudyRecordDAO();
        ClassDAO classDAO = new ClassDAO();
        List<Class> allClasses = classDAO.getAllClass();

        // Truyền danh sách lớp và thông tin filter về lại JSP
        request.setAttribute("year", year);
        request.setAttribute("classId", classId);
        request.setAttribute("name", name);
        request.setAttribute("classList", allClasses);

        // Lấy danh sách học sinh tùy theo trạng thái
        if ("graduated".equalsIgnoreCase(status)) {
            List<StudyRecord> graduatedList;

            if (classId != -1 && !name.isEmpty()) {
                graduatedList = dao.getGraduatedStudyRecordsByClassYearAndName(classId, year, name);
            } else if (classId != -1) {
                graduatedList = dao.getGraduatedStudyRecordsByClassAndYear(classId, year);
            } else if (!name.isEmpty()) {
                graduatedList = dao.getGraduatedStudyRecordsByClassYearAndName(-1, year, name);
            } else {
                graduatedList = dao.getGraduatedStudyRecordsByYear(year);
            }

            request.setAttribute("studentList", graduatedList);
            request.setAttribute("pageTitle", "🎓 Danh sách học sinh đã tốt nghiệp");
            request.setAttribute("isGraduated", true);
        } else {
            List<StudyRecord> studyingList;

            if (classId != -1 && !name.isEmpty()) {
                studyingList = dao.getStudentsStudyingByClassYearAndName(classId, year, name);
            } else if (classId != -1) {
                studyingList = dao.getStudentsStudyingByClassAndYear(classId, year);
            } else if (!name.isEmpty()) {
                studyingList = dao.getStudentsStudyingByClassYearAndName(-1, year, name);
            } else {
                studyingList = dao.getStudentsStudying(year);
            }

            request.setAttribute("studentList", studyingList);
            request.setAttribute("pageTitle", "📚 Danh sách học sinh đang học");
            request.setAttribute("isGraduated", false);
        }

        // Chuyển tiếp tới file JSP hiển thị
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
     * @return a String containing servlet description
     */
    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
