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
import java.util.regex.Pattern;

public class get_otp_register_Servlet extends HttpServlet {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@gmail\\.com$");

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
        if (email != null) {
            email = email.trim();
        }

        // Gửi lại email người dùng nhập nếu có lỗi
        request.setAttribute("email", email);

        // Kiểm tra email rỗng hoặc sai định dạng
        if (email == null || email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            request.setAttribute("emailexisted", "Please enter a valid Gmail address.");
            request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
            return;
        }

        // Cần biến final hoặc effectively final để dùng trong lambda
        final String emailForCheck = email;

        List<String> existingEmails = dao.getAllEmail();
        boolean emailExists = existingEmails.stream()
                .anyMatch(existing -> existing != null && existing.equalsIgnoreCase(emailForCheck));

        if (emailExists) {
            request.setAttribute("emailexisted", "This email is already in use. Please choose another email.");
            request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
        } else {
            // Sinh mã OTP
            int otpCode = 100000 + new Random().nextInt(900000);

            // Gửi OTP đến email
            EmailService.sendEmailgetPassword(email, String.valueOf(otpCode));

            // Lưu OTP và email vào session
            session.setAttribute("otpregister", otpCode);
            session.setAttribute("emailregister", email);

            // Điều hướng sang trang nhập mã OTP
            request.getRequestDispatcher("Enter_otp_register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Xử lý gửi OTP đăng ký";
    }
}
