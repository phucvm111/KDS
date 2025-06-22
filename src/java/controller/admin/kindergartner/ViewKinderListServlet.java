/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.kindergartner;

import dal.ClassDAO;
import dal.KindergartnerDAO;
import dal.StudyRecordDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.StudyRecord;
import model.Class;

/**
 *
 * @author admin
 */
@WebServlet(name = "ViewKinderListServlet", urlPatterns = {"/viewKinderList"})
public class ViewKinderListServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewKinderListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewKinderListServlet at " + request.getContextPath() + "</h1>");
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

        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("").trim().toLowerCase();
        int classId = -1;
        try {
            classId = Integer.parseInt(request.getParameter("classId"));
        } catch (Exception e) {
            // Giữ nguyên -1 nếu không truyền classId
        }

        // Phân trang
        int page = 1;
        int size = 5;
        try {
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
                if (page < 1) {
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        ClassDAO classDAO = new ClassDAO();
        StudyRecordDAO studyDAO = new StudyRecordDAO();

        List<Class> classList = classDAO.getAllClass();
        List<StudyRecord> allRecords = studyDAO.getAllStudyRecord();
        List<StudyRecord> filtered = new ArrayList<>();

        for (StudyRecord sr : allRecords) {
            boolean matchClass = classId == -1 || sr.getClassID().getClass_id() == classId;
            boolean matchName = keyword.isEmpty() || sr.getKinder().getFullName().toLowerCase().contains(keyword);

            if (matchClass && matchName) {
                filtered.add(sr);
            }
        }

        // Tính phân trang
        int total = filtered.size();
        int totalPages = (int) Math.ceil((double) total / size);
        int start = (page - 1) * size;
        int end = Math.min(start + size, total);
        List<StudyRecord> pagedList = filtered.subList(start, end);

        // Truyền dữ liệu cho JSP
        request.setAttribute("classList", classList);
        request.setAttribute("studentList", pagedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("classId", classId);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/admin/kinder/kinder_view.jsp").forward(request, response);
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
