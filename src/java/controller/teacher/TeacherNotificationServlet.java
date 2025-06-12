/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.teacher;

import dal.NotificationDAO;
import model.Account;
import model.Kindergartner;
import model.Notification;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class TeacherNotificationServlet extends HttpServlet {

    NotificationDAO dao = new NotificationDAO();

    @Override
  
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        List<Account> parents = dao.getAllParents();
        List<Kindergartner> children = dao.getAllChildren();

        request.setAttribute("parents", parents);
        request.setAttribute("children", children);

        Account teacher = (Account) request.getSession().getAttribute("account");
        List<Notification> sentNotifications = dao.getNotificationsBySender(teacher.getAccountID());
        List<Notification> fromAdmin = dao.getNotificationsFromAdminToTeacher(teacher.getAccountID());

        request.setAttribute("sentNotifications", sentNotifications);
        request.setAttribute("adminNotifications", fromAdmin);

        request.getRequestDispatcher("notifications.jsp").forward(request, response);
    } catch (Exception e) {
        throw new ServletException(e);
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account teacher = (Account) session.getAttribute("account");

            // Lấy dữ liệu từ form
            String receiverIdStr = request.getParameter("receiverId");
            String kinderIdStr = request.getParameter("kinderId");
            String reason = request.getParameter("reason");
            String content = request.getParameter("content");

            int receiverId = Integer.parseInt(receiverIdStr);
            int kinderId = Integer.parseInt(kinderIdStr);

            Account parent = new Account();
            parent.setAccountID(receiverId);

            Kindergartner kid = new Kindergartner();
            kid.setKinder_id(kinderId);

            dao.sendFromTeacherToParent(teacher, parent, kid, reason, content);

            request.setAttribute("message", "Notification sent!");
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error sending notification.");
            doGet(request, response);
        }
    }
}

