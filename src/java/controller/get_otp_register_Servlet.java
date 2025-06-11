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

        // Validate email is not empty and has correct format
        if (email == null || email.trim().isEmpty() || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            request.setAttribute("emailexisted", "Please enter a valid Gmail address.");
            request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
            return;
        }

        List<String> existingEmails = dao.getAllEmail();
        boolean emailExists = existingEmails.stream()
                .anyMatch(existing -> existing != null && existing.equalsIgnoreCase(email.trim()));

        if (emailExists) {
            request.setAttribute("emailexisted", "This email is already in use. Please use a different one.");
            request.getRequestDispatcher("Get_otp_register.jsp").forward(request, response);
        } else {
            int otpCode = 100000 + new Random().nextInt(900000); // 6-digit OTP

            // Send OTP to email
            EmailService.sendEmailgetPassword(email, String.valueOf(otpCode));

            // Store in session
            session.setAttribute("otpregister", otpCode);
            session.setAttribute("emailregister", email);

            // Redirect to enter OTP page
            request.getRequestDispatcher("Enter_otp_register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Xử lý gửi OTP đăng ký";
    }
}
