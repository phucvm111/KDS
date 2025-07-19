/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.parent;

import dal.KindergartnerDAO;
import dal.PaymentHistoryDAO;
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
import model.PaymentHistory;
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
        PaymentHistoryDAO phd = new PaymentHistoryDAO(); // ✅ thêm DAO mới
        List<TutitionFree> tuitionfrees = new ArrayList<>();

        Account account = (Account) session.getAttribute("account");

        // ✅ Lấy thông tin từ VNPay callback
        String orderInfo = request.getParameter("vnp_OrderInfo");  // ví dụ: "Thanh toan don hang:2"
        String responseCode = request.getParameter("vnp_ResponseCode"); // "00" nếu thành công
        String txnRef = request.getParameter("vnp_TxnRef");              // Mã học phí
        String amountStr = request.getParameter("vnp_Amount");           // Nhân 100
        String transactionNo = request.getParameter("vnp_TransactionNo");// Mã giao dịch VNPay

        if (orderInfo != null && responseCode != null && txnRef != null && responseCode.equals("00")) {
            try {
                String[] txnParts = txnRef.split("_");
                int tuitionId = Integer.parseInt(txnParts[0]);

                double amount = Double.parseDouble(amountStr) / 100.0;

                // ✅ Cập nhật trạng thái học phí
                td.updateStatusById(tuitionId); // ví dụ: set status = 'Đã nộp'

                // ✅ Lưu vào bảng PaymentHistory
                PaymentHistory ph = new PaymentHistory(
                        tuitionId,
                        account.getAccountID(),
                        amount,
                        transactionNo,
                        "Success"
                );
                phd.insert(ph);

            } catch (NumberFormatException e) {
                e.printStackTrace(); // Nếu lỗi parse số
            }
        }

        // ✅ Lấy danh sách học phí còn "Chưa nộp"
        List<Kindergartner> kindergartners = kd.getKindergartnersByParentId(account.getAccountID());
        if (kindergartners != null) {
            for (Kindergartner kinder : kindergartners) {
                TutitionFree tf = td.getTuitionFreeByKinderIdChuanop(kinder.getKinder_id());
                if (tf != null) {
                    tuitionfrees.add(tf);
                }
            }
            request.setAttribute("tuitionFrees", tuitionfrees);
            request.getRequestDispatcher("/parent/paymentparent/paymoney.jsp").forward(request, response);
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
