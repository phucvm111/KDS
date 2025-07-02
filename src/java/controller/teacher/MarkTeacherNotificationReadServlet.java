///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.teacher;
//
//import dal.NotificationDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//
//
//public class MarkTeacherNotificationReadServlet extends HttpServlet {
//
//    NotificationDAO dao = new NotificationDAO();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            int id = Integer.parseInt(request.getParameter("id"));
//            dao.markAsRead(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        response.sendRedirect(request.getContextPath() + "/notifications");
//    }
//}
