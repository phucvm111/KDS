package controller.admin.notifications;

import dal.NotificationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Notification;
import java.io.IOException;
import java.util.List;

public class AdminNotificationServlet extends HttpServlet {

    NotificationDAO dao = new NotificationDAO();

    // Phương thức kiểm tra ký tự đặc biệt
    private boolean isValidInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        // Allow English letters, numbers, Vietnamese characters, whitespace, and specific punctuation
        String regex = "^[\\p{L}0-9\\s.,:]+$";
        return input.matches(regex);
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account admin = (Account) session.getAttribute("account");

            String reason = request.getParameter("reason");
            String content = request.getParameter("content");

            request.setAttribute("reason", reason);
            request.setAttribute("content", content);
            if (reason == null || reason.trim().isEmpty() || content == null || content.trim().isEmpty()) {
                List<Notification> sentNotifications = dao.getNotificationsBySender(admin.getAccountID());
                request.setAttribute("sentNotifications", sentNotifications);
                request.setAttribute("messageError", "Vui lòng điền đầy đủ lý do và nội dung.");
                request.getRequestDispatcher("admin/admin_send_notification.jsp").forward(request, response);
                return; // Dừng lại nếu không hợp lệ
            } else if (!isValidInput(reason) || !isValidInput(content)) {
                List<Notification> sentNotifications = dao.getNotificationsBySender(admin.getAccountID());
                request.setAttribute("sentNotifications", sentNotifications);
                request.setAttribute("messageError", "Lý do và nội dung không được chứa ký tự đặc biệt như ! @ #$%^&*.");
                request.getRequestDispatcher("admin/admin_send_notification.jsp").forward(request, response);
                return; // Dừng lại nếu có ký tự đặc biệt
            }

            // Gửi thông báo đến tất cả giáo viên
            List<Account> teachers = dao.getAllTeachers();
            for (Account teacher : teachers) {
                dao.sendFromAdminToTeacher(admin, teacher, reason, content);
            }

            request.setAttribute("message", "Gửi thông báo tới tất cả giáo viên.");

            // 🔥 Quan trọng: Lấy lại danh sách thông báo sau khi gửi
            List<Notification> sentNotifications = dao.getNotificationsBySender(admin.getAccountID());
            request.setAttribute("sentNotifications", sentNotifications);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messageError", "Gửi thông báo thất bại.");

        }

        request.getRequestDispatcher("admin/admin_send_notification.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account admin = (Account) session.getAttribute("account");

            List<Notification> sentNotifications = dao.getNotificationsBySender(admin.getAccountID());
            request.setAttribute("sentNotifications", sentNotifications);

            request.getRequestDispatcher("admin/admin_send_notification.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Không thể tải danh sách thông báo.");
            request.getRequestDispatcher("admin/admin_send_notification.jsp").forward(request, response);
        }
    }
}
