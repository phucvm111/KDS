///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.teacher;
//
//import dal.NotificationDAO;
//import model.Account;
//import model.Kindergartner;
//import model.Notification;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//public class TeacherNotificationServlet extends HttpServlet {
//
//    NotificationDAO dao = new NotificationDAO();
//
//    // Phương thức kiểm tra ký tự đặc biệt
//     private boolean isValidInput(String input) {
//        if (input == null || input.trim().isEmpty()) {
//            return false;
//        }
//        // Allow English letters, numbers, Vietnamese characters, whitespace, and specific punctuation
//        String regex = "^[\\p{L}0-9\\s.,:]+$";
//        return input.matches(regex);
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            List<Account> parents = dao.getAllParents();
//            List<Kindergartner> children = dao.getAllChildren();
//
//            request.setAttribute("parents", parents);
//            request.setAttribute("children", children);
//
//            Account teacher = (Account) request.getSession().getAttribute("account");
//            List<Notification> sentNotifications = dao.getNotificationsBySender(teacher.getAccountID());
//            List<Notification> fromAdmin = dao.getNotificationsFromAdminToTeacher(teacher.getAccountID());
//
//            request.setAttribute("sentNotifications", sentNotifications);
//            request.setAttribute("adminNotifications", fromAdmin);
//
//            request.getRequestDispatcher("notifications.jsp").forward(request, response);
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            HttpSession session = request.getSession();
//            Account teacher = (Account) session.getAttribute("account");
//
//            // Lấy dữ liệu từ form
//            String receiverIdStr = request.getParameter("receiverId");
//            String kinderIdStr = request.getParameter("kinderId");
//            String reason = request.getParameter("reason");
//            String content = request.getParameter("content");
//
//            // Lưu trữ dữ liệu đã nhập để hiển thị lại khi có lỗi
//            request.setAttribute("receiverId", receiverIdStr);
//            request.setAttribute("kinderId", kinderIdStr);
//            request.setAttribute("reason", reason);
//            request.setAttribute("content", content);
//
//            // Lấy danh sách parents và children để hiển thị lại trong form
//            List<Account> parents = dao.getAllParents();
//            List<Kindergartner> children = dao.getAllChildren();
//
//            request.setAttribute("parents", parents);
//            request.setAttribute("children", children);
//
//            List<Notification> sentNotifications = dao.getNotificationsBySender(teacher.getAccountID());
//            List<Notification> fromAdmin = dao.getNotificationsFromAdminToTeacher(teacher.getAccountID());
//
//            request.setAttribute("sentNotifications", sentNotifications);
//            request.setAttribute("adminNotifications", fromAdmin);
//
//            // Kiểm tra dữ liệu hợp lệ
//            if (reason == null || reason.trim().isEmpty() || content == null || content.trim().isEmpty()) {
//                request.setAttribute("messageError", "Vui lòng điền đầy đủ lý do và nội dung.");
//                request.getRequestDispatcher("notifications.jsp").forward(request, response);
//                return;
//            } else if (!isValidInput(reason) || !isValidInput(content)) {
//                request.setAttribute("messageError", "Nội dung không được chứa ký tự đặc biệt như ! @ #$%^&*.");
//                request.getRequestDispatcher("notifications.jsp").forward(request, response);
//                return;
//            }
//
//            // Chuyển đổi và gửi thông báo
//            int receiverId = Integer.parseInt(receiverIdStr);
//            int kinderId = Integer.parseInt(kinderIdStr);
//
//            Account parent = new Account();
//            parent.setAccountID(receiverId);
//
//            Kindergartner kid = new Kindergartner();
//            kid.setKinder_id(kinderId);
//
//            dao.sendFromTeacherToParent(teacher, parent, kid, reason, content);
//
//            request.setAttribute("message", "Thông báo đã được gửi thành công!");
////            request.getRequestDispatcher("notifications.jsp").forward(request, response);
//            doGet(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("messageError", "Không gửi được thông báo.");
//            request.getRequestDispatcher("notifications.jsp").forward(request, response);
//        }
//    }
//}
