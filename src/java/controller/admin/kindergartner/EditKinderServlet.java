/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.kindergartner;

import dal.AccountDAO;
import dal.KindergartnerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import model.Account;
import model.Kindergartner;

/**
 *
 * @author admin
 */
@WebServlet(name = "EditKinderServlet", urlPatterns = {"/editKinder"})
public class EditKinderServlet extends HttpServlet {

    KindergartnerDAO dao = new KindergartnerDAO();
    AccountDAO accDao = new AccountDAO();

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
            out.println("<title>Servlet EditKinderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditKinderServlet at " + request.getContextPath() + "</h1>");
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
        int kinderId = Integer.parseInt(request.getParameter("kinderId"));
        Kindergartner k = dao.getKinderById(kinderId);
        List<Account> parents = accDao.getAllParentInfor();
        request.setAttribute("kinder", k);
        request.setAttribute("parentList", parents);
        request.getRequestDispatcher("/admin/kinder/kinder-edit.jsp").forward(request, response);
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
        try {
            int id = Integer.parseInt(request.getParameter("kinderId"));

            // Lấy và xử lý dữ liệu đầu vào
            String first = request.getParameter("first_name").trim();
            String last = request.getParameter("last_name").trim();
            String dobRaw = request.getParameter("dob").trim();
            boolean gender = "male".equals(request.getParameter("gender"));

            // Validate độ dài tên
            if (first.length() > 20 || last.length() > 20) {
                String msg = "❌ Họ và tên không được vượt quá 20 ký tự.";
                System.out.println(msg);
                handleError(request, response, id, msg);
                return;
            }

            // Parse ngày sinh
            LocalDate dob = LocalDate.parse(dobRaw); // yêu cầu định dạng yyyy-MM-dd
            LocalDate today = LocalDate.now();
            int age = Period.between(dob, today).getYears();

            if (age > 5) {
                String msg = "❌ Ngày sinh không hợp lệ. Học sinh phải dưới hoặc bằng 5 tuổi.";
                System.out.println(msg + " DOB: " + dobRaw);
                handleError(request, response, id, msg);
                return;
            }

            // Nếu dữ liệu hợp lệ
            Kindergartner k = dao.getKinderById(id);
            k.setFirst_name(first);
            k.setLast_name(last);
            k.setDob(dobRaw);
            k.setGender(gender);

            dao.updateKinderBasicInfo(k);
            response.sendRedirect("viewKinderList");

        } catch (Exception e) {
            String msg = "❌ Đã xảy ra lỗi trong quá trình xử lý: " + e.getMessage();
            System.out.println(msg);
            request.setAttribute("error", msg);
            request.getRequestDispatcher("/admin/kinder/kinder-edit.jsp").forward(request, response);
        }
    }

// ✅ Hàm xử lý khi có lỗi validation
    private void handleError(HttpServletRequest request, HttpServletResponse response, int id, String errorMessage)
            throws ServletException, IOException {
        Kindergartner k = dao.getKinderById(id);
        List<Account> parents = accDao.getAllParentInfor();
        request.setAttribute("kinder", k);
        request.setAttribute("parentList", parents);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/admin/kinder/kinder-edit.jsp").forward(request, response);
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
