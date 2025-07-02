///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.parent;
//
//import dal.NotificationDAO;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import model.Account;
//import model.Notification;
//
//import java.io.IOException;
//import java.util.List;
//
//public class ParentNotificationServlet extends HttpServlet {
//
//    NotificationDAO dao = new NotificationDAO();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        try {
//            HttpSession session = request.getSession(false);
//            Account parent = (Account) session.getAttribute("account");
//
//            List<Notification> notifications = dao.getNotificationsForParent(parent.getAccountID());
//            request.setAttribute("notifications", notifications);
//
//            request.getRequestDispatcher("parent/parent_notifications.jsp").forward(request, response);
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }
//}
