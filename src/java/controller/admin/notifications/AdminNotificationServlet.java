package controller.admin.notifications;

import dal.NotificationDAO;
import dal.RoleDAO;
import model.Account;
import model.Notification;
import model.Role;
import com.yourapp.service.EmailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/notification")
public class AdminNotificationServlet extends HttpServlet {

    private NotificationDAO dao;
    private RoleDAO roleDAO;

    @Override
    public void init() throws ServletException {
        dao = new NotificationDAO();
        roleDAO = new RoleDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "add":
                    List<Role> roles = roleDAO.getAllRoles();
                    request.setAttribute("roleList", roles);
                    request.getRequestDispatcher("/admin/notification/addnotification.jsp").forward(request, response);
                    break;

                case "edit":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Notification noti = dao.getById(idEdit);
                    if (noti == null) {
                        request.getSession().setAttribute("message", "Notification not found");
                        response.sendRedirect("notification");
                        return;
                    }
                    request.setAttribute("notification", noti);
                    request.setAttribute("roleList", roleDAO.getAllRoles());
                    request.getRequestDispatcher("/admin/notification/editnotification.jsp").forward(request, response);
                    break;

                case "delete":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    dao.deleteNotification(idDelete);
                    request.getSession().setAttribute("successMessage", "Notification deleted successfully.");
                    response.sendRedirect("notification");
                    break;

                case "sendMail":
                    int idSend = Integer.parseInt(request.getParameter("id"));
                    Notification toSend = dao.getById(idSend);
                    if (toSend == null) {
                        request.getSession().setAttribute("message", "Notification not found");
                        response.sendRedirect("notification");
                        return;
                    }
                    if (toSend.isEmailed()) {
                        request.getSession().setAttribute("message", "Notification already emailed.");
                        response.sendRedirect("notification");
                        return;
                    }

                    List<Account> accounts = dao.getAccountsByRole(toSend.getTargetRole().getRoleID());

                    boolean success = EmailService.sendNotificationToAccounts(accounts, toSend.getTitle(), toSend.getMessage());

                    if (success) {
                        dao.markAsEmailed(idSend);
                        request.getSession().setAttribute("successMessage", "Notification has been sent successfully.");
                    } else {
                        request.getSession().setAttribute("message", "Some emails could not be sent. Please check SMTP/logs.");
                    }
                    response.sendRedirect("notification");

                    break;

                default:
                    String searchContent = request.getParameter("searchContent");
                    String emailStatus = request.getParameter("emailStatus");

                    List<Notification> list;
                    if (searchContent != null && !searchContent.trim().isEmpty()) {
                        list = dao.searchByContent(searchContent);
                    } else {
                        list = dao.getAllNotifications();
                    }

                    // lọc thêm theo trạng thái emailStatus
                    if (emailStatus != null && !emailStatus.isEmpty()) {
                        boolean emailedFilter = "sent".equals(emailStatus);
                        list.removeIf(n -> n.isEmailed() != emailedFilter);
                    }

                    request.setAttribute("notificationList", list);
                    request.setAttribute("emailStatus", emailStatus);
                    request.getRequestDispatcher("/admin/notification/listnotification.jsp").forward(request, response);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("/admin/notification/listnotification.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            Account admin = (Account) request.getSession().getAttribute("account");
            if (admin == null || admin.getRole().getRoleID() != 1) {
                response.sendRedirect("login");
                return;
            }

            if ("add".equals(action)) {
                String title = request.getParameter("title");
                String message = request.getParameter("message");
                int targetRoleId = Integer.parseInt(request.getParameter("targetRole"));

                Notification n = new Notification();
                n.setTitle(title);
                n.setMessage(message);
                n.setSender(admin);
                Role role = new Role();
                role.setRoleID(targetRoleId);
                n.setTargetRole(role);

                dao.insertNotification(n);
                request.getSession().setAttribute("successMessage", "Notification added successfully.");
                response.sendRedirect("notification");

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String message = request.getParameter("message");
                int targetRoleId = Integer.parseInt(request.getParameter("targetRole"));

                Notification n = dao.getById(id);
                if (n == null) {
                    request.getSession().setAttribute("message", "Notification not found");
                    response.sendRedirect("notification");
                    return;
                }
                n.setTitle(title);
                n.setMessage(message);
                Role role = new Role();
                role.setRoleID(targetRoleId);
                n.setTargetRole(role);

                dao.updateNotification(n);
                request.getSession().setAttribute("successMessage", "Notification updated successfully.");
                response.sendRedirect("notification");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("/admin/notification/listnotification.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Admin Notification Management";
    }
}
