/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.parent;

import dal.KindergartnerDAO;
import dal.TutitionFreeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Kindergartner;
import model.TutitionFree;

/**
 *
 * @author ACE
 */
public class paymoneyServlet extends HttpServlet {

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
            out.println("<title>Servlet paymoney</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet paymoney at " + request.getContextPath() + "</h1>");
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
        KindergartnerDAO kd = new KindergartnerDAO();
        TutitionFreeDAO td = new TutitionFreeDAO();
        List<TutitionFree> tuitionfrees = new ArrayList<>();

        Account account = (Account) session.getAttribute("account");

        // ✅ Bước 1: Lấy thông tin đơn hàng từ VNPay trả về
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String responseCode = request.getParameter("vnp_ResponseCode");

        if (orderInfo != null && responseCode != null && responseCode.equals("00")) {
            // ✅ Bước 2: Parse mã đơn hàng
            String orderId = orderInfo.substring(orderInfo.lastIndexOf(":") + 1);
            int tuitionId = Integer.parseInt(orderId);

            // ✅ Bước 3: Cập nhật trạng thái đơn hàng thành "Đã nộp"
            td.updateStatusById(tuitionId);  // <-- bạn đã viết hàm này rồi
        }

        // ✅ Bước 4: Lấy lại danh sách học phí còn "Chưa nộp"
        List<Kindergartner> kindergartners = kd.getKindergartnersByParentId(account.getAccountID());
        if (kindergartners != null) {
            for (Kindergartner kinder : kindergartners) {
                TutitionFree tf = td.getTuitionFreeByKinderIdChuanop(kinder.getKinder_id());
                if (tf != null) {
                    tuitionfrees.add(tf);
                }
            }
            request.setAttribute("tuitionFrees", tuitionfrees);
            request.getRequestDispatcher("/parent/paymoney.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
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
