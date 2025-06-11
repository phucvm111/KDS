package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Enter_Otp_RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Enter_otp_register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String otpInput = request.getParameter("otp");
        Object otpInSession = session.getAttribute("otpregister");

        if (otpInSession == null || otpInput == null || otpInput.trim().isEmpty()) {
            request.setAttribute("otpfalse", "Please enter OTP code ");
            request.getRequestDispatcher("Enter_otp_register.jsp").forward(request, response);
            return;
        }

        try {
            int otpUser = Integer.parseInt(otpInput.trim());
            int otpSession = (int) otpInSession;

            if (otpUser == otpSession) {
                // ✅ Lấy email đã nhập ban đầu
                String email = (String) session.getAttribute("emailregister");

                // ✅ Lưu email xác thực vào session để dùng ở bước đăng ký
                session.setAttribute("email_otp_verified", email);

                // ✅ Xoá session tạm
                session.removeAttribute("otpregister");
                session.removeAttribute("emailregister");

                // ✅ Forward để giữ session
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                request.setAttribute("otpfalse", "OTP code is incorrect");
                request.getRequestDispatcher("Enter_otp_register.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("otpfalse", "OTP is not a number");
            request.getRequestDispatcher("Enter_otp_register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles OTP verification for registration flow";
    }
}
