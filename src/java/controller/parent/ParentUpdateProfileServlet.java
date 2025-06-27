package controller.parent;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import model.Account;

public class ParentUpdateProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chỉ cần hiển thị trang JSP khi người dùng truy cập bằng GET
        request.getRequestDispatcher("/parent/parentupdateprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account currentAcc = (Account) session.getAttribute("account");

        // 1. Kiểm tra xem người dùng đã đăng nhập chưa
        if (currentAcc == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 2. Lấy dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dobStr = request.getParameter("dob");
        String genderStr = request.getParameter("gender");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        // 3. Validation dữ liệu (Server-side)
        Map<String, String> errors = new HashMap<>();

        // -- Validate First Name & Last Name
        String nameRegex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỳỵỷỹ\\s]{1,25}$";
        if (firstName == null || !firstName.trim().matches(nameRegex)) {
            errors.put("firstNameError", "First name is invalid or too long (max 25 characters).");
        }
        if (lastName == null || !lastName.trim().matches(nameRegex)) {
            errors.put("lastNameError", "Last name is invalid or too long (max 25 characters).");
        }

        // -- Validate Phone Number
        String phoneRegex = "^(03|05|07|08|09)[0-9]{8}$";
        if (phoneNumber == null || !phoneNumber.trim().matches(phoneRegex)) {
            errors.put("phoneError", "Invalid phone number format (10 digits, starting with 03, 05, 07, 08, 09).");
        }

        // -- Validate Email
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        if (email == null || !email.trim().matches(emailRegex)) {
            errors.put("emailError", "Invalid email address format.");
        }
        
        // -- Validate Address
        if (address == null || address.trim().isEmpty()) {
            errors.put("addressError", "Address cannot be empty.");
        } else if (address.trim().length() > 255) {
            errors.put("addressError", "Address is too long (max 255 characters).");
        }

        // -- Validate Date of Birth
        LocalDate dob = null;
        if (dobStr == null || dobStr.isEmpty()) {
            errors.put("dobError", "Please enter your date of birth.");
        } else {
            try {
                dob = LocalDate.parse(dobStr);
                int year = dob.getYear();
                if (year < 1951 || year >= LocalDate.now().getYear()) {
                    errors.put("dobError", "Year of birth must be from 1951 to present.");
                }
            } catch (DateTimeParseException e) {
                errors.put("dobError", "Invalid date format. Please use yyyy-mm-dd.");
            }
        }

        // 4. Xử lý sau khi validation
        if (!errors.isEmpty()) {
            // Nếu có lỗi, gửi lỗi và dữ liệu người dùng đã nhập trở lại form
            request.setAttribute("errors", errors);
            
            // Tạo một đối tượng Account tạm để chứa dữ liệu người dùng đã nhập
            Account submittedData = new Account();
            submittedData.setFirstName(firstName);
            submittedData.setLastName(lastName);
            submittedData.setDob(dobStr);
            submittedData.setGender("true".equals(genderStr));
            submittedData.setPhoneNumber(phoneNumber);
            submittedData.setAddress(address);
            submittedData.setEmail(email);
            // Giữ lại ID và các thông tin không đổi khác
            submittedData.setAccountID(currentAcc.getAccountID());
            
            request.setAttribute("submittedData", submittedData);

            request.getRequestDispatcher("/parent/parentupdateprofile.jsp").forward(request, response);
        } else {
             // Nếu không có lỗi, tiến hành cập nhật
            AccountDAO adao = new AccountDAO();
            try {
                // Tạo đối tượng Account mới với dữ liệu đã được làm sạch và xác thực
                Account updatedAccount = new Account();
                updatedAccount.setAccountID(currentAcc.getAccountID());
                updatedAccount.setFirstName(firstName.trim());
                updatedAccount.setLastName(lastName.trim());
                updatedAccount.setDob(dobStr);
                updatedAccount.setGender("true".equals(genderStr));
                updatedAccount.setPhoneNumber(phoneNumber.trim());
                updatedAccount.setAddress(address.trim());
                updatedAccount.setEmail(email.trim());

                // GỌI PHƯƠNG THỨC UPDATE. KHÔNG CẦN BIẾN BOOLEAN
                adao.updateParent(updatedAccount);

                // NẾU DÒNG TRÊN CHẠY MÀ KHÔNG GÂY LỖI, CÓ NGHĨA LÀ THÀNH CÔNG
                // CÁC BƯỚC XỬ LÝ THÀNH CÔNG SẼ ĐƯỢC THỰC HIỆN NGAY TẠI ĐÂY:

                // 1. Cập nhật lại session với thông tin mới nhất
                session.setAttribute("account", adao.getAccountByID(currentAcc.getAccountID()));

                // 2. Dùng Redirect để tránh lỗi F5, kèm theo thông báo thành công
                session.setAttribute("successMessage", "Profile updated successfully!");
                response.sendRedirect(request.getContextPath() + "/parent/parentprofile.jsp");

            } catch (Exception e) {
                // NẾU updateParent CÓ LỖI, NÓ SẼ NHẢY VÀO ĐÂY
                e.printStackTrace(); // Ghi log lỗi ra console
                request.setAttribute("errorMessage", "An error occurred while updating the profile. Please try again.");
                request.getRequestDispatcher("/parent/parentupdateprofile.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles parent profile updates with server-side validation.";
    }
}