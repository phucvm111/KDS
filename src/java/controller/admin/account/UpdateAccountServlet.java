package controller.admin.account;

import dal.AccountDAO;
import dal.RoleDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;

@WebServlet(name = "UpdateAccountServlet_1", urlPatterns = {"/UpdateAccountServlet_1"})
public class UpdateAccountServlet extends HttpServlet {

    AccountDAO ad = new AccountDAO();
    RoleDAO rd = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        List<Role> listR = rd.getAllRoles();
        Account s = ad.getAccountByID(id);
        request.setAttribute("s", s);
        request.setAttribute("listR", listR);
        request.getRequestDispatcher("admin/account/adminAccountUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = trimParam(request.getParameter("txtFirstName"));
        String lastName = trimParam(request.getParameter("txtLastName"));
        String genderStr = request.getParameter("gender");
        boolean gender = "male".equalsIgnoreCase(genderStr);
        String email = trimParam(request.getParameter("txtEmail"));
        String password = trimParam(request.getParameter("txtPassword"));
        String dob = trimParam(request.getParameter("dob"));
        String phone = trimParam(request.getParameter("txtPhone"));
        String address = trimParam(request.getParameter("ttAddress"));
        String img = trimParam(request.getParameter("txtImg"));
        int roleId = Integer.parseInt(request.getParameter("slRole"));
        int accId = Integer.parseInt(request.getParameter("ida"));

        String error = "";

        // ==== VALIDATE TIẾNG VIỆT ====
        if (firstName.isEmpty() || lastName.isEmpty()) {
            error = "Họ và tên không được để trống.";
        } else if (firstName.length() > 50 || lastName.length() > 50) {
            error = "Họ và tên không được vượt quá 50 ký tự.";
        } else if (!Pattern.matches("^[A-Za-zÀ-ỹà-ỹ\\s]+$", firstName) || !Pattern.matches("^[A-Za-zÀ-ỹà-ỹ\\s]+$", lastName)) {
            error = "Họ và tên chỉ được chứa chữ cái và khoảng trắng.";
        } else if (email.isEmpty()) {
            error = "Email không được để trống.";
        } else if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$", email)) {
            error = "Định dạng email không hợp lệ.";
        } else if (password.isEmpty() || password.length() < 6) {
            error = "Mật khẩu phải từ 6 ký tự trở lên.";
        } else if (dob.isEmpty()) {
            error = "Ngày sinh không được để trống.";
        } else {
            try {
                LocalDate.parse(dob);
            } catch (Exception e) {
                error = "Định dạng ngày sinh không hợp lệ (yyyy-MM-dd).";
            }
        }

        if (!phone.isEmpty() && !Pattern.matches("^[0-9]{8,15}$", phone)) {
            error = "Số điện thoại phải là số, từ 8 đến 15 chữ số.";
        }

        if (!error.isEmpty()) {
            request.setAttribute("errorMessage", error);
            request.setAttribute("listR", rd.getAllRoles());
            Account acc = ad.getAccountByID(accId);
            request.setAttribute("s", acc);
            request.getRequestDispatcher("admin/account/adminAccountUpdate.jsp").forward(request, response);
            return;
        }

        Account ac = new Account();
        ac.setAccountID(accId);
        ac.setFirstName(firstName);
        ac.setLastName(lastName);
        ac.setGender(gender);
        ac.setEmail(email);
        ac.setPassword(password);
        ac.setDob(dob);
        ac.setPhoneNumber(phone);
        ac.setAddress(address);
        ac.setImg(img);

        Role r = rd.getRoleByID(roleId);
        ac.setRole(r);

        ad.updateAccount(ac);
        response.sendRedirect("listaccount");
    }

    private String trimParam(String param) {
        return param != null ? param.trim() : "";
    }

    @Override
    public String getServletInfo() {
        return "UpdateAccountServlet - Cập nhật tài khoản với kiểm tra tiếng Việt đầy đủ.";
    }

}
