package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Role;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(true);

        String email = (String) session.getAttribute("email_otp_verified");

        if (email == null || email.isEmpty()) {
            response.sendRedirect("Get_otp_register.jsp");
            return;
        }

        String fname = request.getParameter("fname").trim();
        String lname = request.getParameter("lname").trim();
        String gender_raw = request.getParameter("gender");
        boolean gender = gender_raw != null && gender_raw.equals("male");
        String address = request.getParameter("address").trim();
        String phone = request.getParameter("phone").trim();
        String dob = request.getParameter("dob");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        // Set form values back to request attributes for reuse
        request.setAttribute("fname", fname);
        request.setAttribute("lname", lname);
        request.setAttribute("gender", gender_raw);
        request.setAttribute("address", address);
        request.setAttribute("phone", phone);
        request.setAttribute("dob", dob);

        AccountDAO dao = new AccountDAO();

        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(password2)) {
            request.setAttribute("error4", "Xác nhận mật khẩu không đúng.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra trùng email
        List<String> emailList = dao.getAllEmail();
        if (emailList.contains(email)) {
            request.setAttribute("error1", "Email này đã được đăng ký.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo account
        Account acc = new Account();
        acc.setFirstName(fname);
        acc.setLastName(lname);
        acc.setGender(gender);
        acc.setEmail(email);
        acc.setPassword(password); // Nên mã hóa mật khẩu ở đây
        acc.setDob(dob);
        acc.setPhoneNumber(phone);
        acc.setAddress(address);
        acc.setImg(null); // Avatar mặc định nếu cần

        Role role = new Role(3, "parent");
        acc.setRole(role);

        // Thêm account vào DB
        dao.addAccount(acc);

        // Lưu account vào session
        Account newAcc = dao.getAccountByEmail(email);
        session.setAttribute("account", newAcc);

        // Xóa email OTP sau khi đăng ký xong
        session.removeAttribute("email_otp_verified");

        response.sendRedirect("childregister");
    }

    @Override
    public String getServletInfo() {
        return "Handles user registration after OTP email verification";
    }
}
