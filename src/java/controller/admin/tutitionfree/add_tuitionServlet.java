/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin.tutitionfree;

import dal.KindergartnerDAO;
import dal.TutitionFreeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Kindergartner;
import model.TutitionFree;

/**
 *
 * @author ACE
 */
public class add_tuitionServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet add_tuitionServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet add_tuitionServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
         
        
      
        request.getRequestDispatcher("/Management_TuitionFee/TuitionFee/tuition_list.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Lấy tham số từ form gửi lên
    String kinderIdRaw = request.getParameter("kinderId");
    String amountRaw = request.getParameter("amount");
    String dueDate = request.getParameter("dueDate");
    String status = request.getParameter("status");

    try {
        int kinderId = Integer.parseInt(kinderIdRaw);
        double amount = Double.parseDouble(amountRaw);

        // Tạo đối tượng Kindergartner và gán ID
        Kindergartner kinder = new Kindergartner();
        kinder.setKinder_id(kinderId); // Hoặc setId nếu bạn dùng tên khác

        // Tạo đối tượng học phí mới
        TutitionFree tf = new TutitionFree();
        tf.setKinder(kinder);
        tf.setAmount(amount);
        tf.setDue_date(dueDate); 
        tf.setStatus(status);

        // Gọi DAO để thêm
        TutitionFreeDAO dao = new TutitionFreeDAO();
        dao.addTuition(tf);

        // Redirect về trang danh sách sau khi thêm thành công
        response.sendRedirect("tutitionfree"); // hoặc URL mapping tương ứng của bạn

    } catch (NumberFormatException e) {
        request.setAttribute("error", "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        request.getRequestDispatcher("Management_TuitionFee/TuitionFee/add_tuition.jsp").forward(request, response);
    } catch (Exception ex) {
        request.setAttribute("error", "Có lỗi xảy ra: " + ex.getMessage());
        request.getRequestDispatcher("Management_TuitionFee/TuitionFee/add_tuition.jsp").forward(request, response);
    }
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
