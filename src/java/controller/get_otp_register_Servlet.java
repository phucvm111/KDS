package controller;

import com.yourapp.service.EmailService;
import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class get_otp_register_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountDAO dao = new AccountDAO();

        String email = request.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailexisted", "Vui lòng nhập email hợp lệ.");
            request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
            return;
        }

        List<String> existingEmails = dao.getAllEmail();
        boolean emailExists = false;

        for (String existing : existingEmails) {
            if (existing != null && existing.equalsIgnoreCase(email.trim())) {
                emailExists = true;
                break;
            }
        }

        if (emailExists) {
            request.setAttribute("emailexisted", "Email đã được sử dụng. Vui lòng chọn email khác.");
            request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
        } else {
            int otpCode = 100000 + new Random().nextInt(900000); // 6 chữ số

            // Gửi OTP qua email
            EmailService.sendEmailgetPassword(email, String.valueOf(otpCode));

            // Lưu vào session để kiểm tra sau
            session.setAttribute("otpregister", otpCode);
            session.setAttribute("emailregister", email);

            // Chuyển sang trang nhập OTP
            request.getRequestDispatcher("Enter_otp_register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Xử lý gửi OTP đăng ký";
    }
}
