package controller.admin.classs;

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

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account admin = (Account) session.getAttribute("account");

            String reason = request.getParameter("reason");
            String content = request.getParameter("content");

            // Gửi thông báo đến tất cả giáo viên
            List<Account> teachers = dao.getAllTeachers();
            for (Account teacher : teachers) {
                dao.sendFromAdminToTeacher(admin, teacher, reason, content);
            }

            request.setAttribute("message", "Notification sent to all teachers.");

            // 🔥 Quan trọng: Lấy lại danh sách thông báo sau khi gửi
            List<Notification> sentNotifications = dao.getNotificationsBySender(admin.getAccountID());
            request.setAttribute("sentNotifications", sentNotifications);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Failed to send notifications.");
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
