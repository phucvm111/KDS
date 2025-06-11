/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.parent;

import dal.NotificationDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MarkNotificationReadServlet extends HttpServlet {

    NotificationDAO dao = new NotificationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int notificationId = Integer.parseInt(request.getParameter("id"));
            dao.markAsRead(notificationId);
            response.sendRedirect("parentNotifications"); // chuyển hướng về trang hiển thị
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
