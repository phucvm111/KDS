/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author Vu Tuan Hai <HE176383>
 */
public class UpdateTeacherServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateTeacherServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTeacherServlet at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        request.setAttribute("account", acc);
        request.getRequestDispatcher("/teacher/teacherupdateprofile.jsp").forward(request, response);
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

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy và trim dữ liệu
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String dob = request.getParameter("dob").trim();
        String genderStr = request.getParameter("gender");
        String phone = request.getParameter("phone").trim();
        String address = request.getParameter("address").trim();
        String email = request.getParameter("email").trim();
        boolean gender = Boolean.parseBoolean(genderStr);

        // Regex kiểm tra khoảng trắng liên tiếp
        Pattern multiSpace = Pattern.compile(" {2,}");
        StringBuilder errorMsg = new StringBuilder();

        // Kiểm tra khoảng trắng
        if (multiSpace.matcher(firstName).find()
                || multiSpace.matcher(lastName).find()
                || multiSpace.matcher(address).find()) {
            errorMsg.append("Không được nhập hai khoảng trắng liên tiếp. ");
        }

        // Kiểm tra số điện thoại
        if (!phone.matches("\\d{9,12}")) {
            errorMsg.append("Phone phải từ 9–12 chữ số. ");
        }

        // Kiểm tra email
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            errorMsg.append("Email không hợp lệ. ");
        }

        // Kiểm tra ngày sinh không lớn hơn ngày hôm nay
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date dobDate = sdf.parse(dob);
            Date today = sdf.parse(sdf.format(new Date())); // reset giờ về 00:00:00

            if (dobDate.after(today)) {
                errorMsg.append("Ngày sinh không được lớn hơn ngày hôm nay. ");
            }
        } catch (Exception e) {
            errorMsg.append("Định dạng ngày không hợp lệ. ");
        }

        // Nếu có lỗi, quay lại form và hiển thị lỗi
        if (errorMsg.length() > 0) {
            request.setAttribute("error", errorMsg.toString());
            request.setAttribute("account", acc);
            request.getRequestDispatcher("/teacher/teacherupdateprofile.jsp").forward(request, response);
            return;
        }

        // Cập nhật dữ liệu
        acc.setFirstName(firstName);
        acc.setLastName(lastName);
        acc.setDob(dob);
        acc.setGender(gender);
        acc.setPhoneNumber(phone);
        acc.setAddress(address);
        acc.setEmail(email);

        boolean success = new AccountDAO().updateAccount(acc);
        if (success) {
            session.setAttribute("account", acc);
            response.sendRedirect("teacher/teacherprofile.jsp");
        } else {
            request.setAttribute("error", "Cập nhật thất bại.");
            request.setAttribute("account", acc);
            request.getRequestDispatcher("/teacher/teacherupdateprofile.jsp").forward(request, response);
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
