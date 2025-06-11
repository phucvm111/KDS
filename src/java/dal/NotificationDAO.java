package dal;

import model.Account;
import model.Kindergartner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Notification;

public class NotificationDAO extends DBContext {

    // Gửi notification từ giáo viên tới phụ huynh (với con cụ thể)
    public void sendFromTeacherToParent(Account sender, Account receiver, Kindergartner kid, String reason, String content) throws SQLException, Exception {
        String sql = "INSERT INTO Notification (sender_id, receiver_id, kinder_id, reason, content, notification_date, is_read, notification_type) "
                + "VALUES (?, ?, ?, ?, ?, GETDATE(), 0, 'Message')";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sender.getAccountID());
            ps.setInt(2, receiver.getAccountID());
            ps.setInt(3, kid.getKinder_id());
            ps.setString(4, reason);
            ps.setString(5, content);
            ps.executeUpdate();
        }
    }

    // Lấy toàn bộ phụ huynh
    public List<Account> getAllParents() throws SQLException, Exception {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE role_id = 3";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountID(rs.getInt("account_id"));
                acc.setFirstName(rs.getString("first_name"));
                acc.setLastName(rs.getString("last_name"));
                list.add(acc);
            }
        }
        return list;
    }

    // Lấy danh sách trẻ theo parent_id
    // Lấy toàn bộ trẻ (để hiển thị dropdown Child trong form)
    public List<Kindergartner> getAllChildren() throws SQLException, Exception {
        List<Kindergartner> list = new ArrayList<>();
        String sql = "SELECT * FROM Kindergartner";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Kindergartner k = new Kindergartner();
                k.setKinder_id(rs.getInt("kinder_id"));
                k.setFirst_name(rs.getString("first_name"));
                k.setLast_name(rs.getString("last_name"));
                list.add(k);
            }
        }
        return list;
    }

    public List<Notification> getNotificationsBySender(int senderId) throws Exception {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT n.*, a.first_name AS parentFirst, a.last_name AS parentLast, "
                + "k.first_name AS childFirst, k.last_name AS childLast "
                + "FROM Notification n "
                + "LEFT JOIN Account a ON n.receiver_id = a.account_id "
                + "LEFT JOIN Kindergartner k ON n.kinder_id = k.kinder_id "
                + "WHERE n.sender_id = ? "
                + "ORDER BY n.notification_date DESC";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, senderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setNotificationId(rs.getInt("notification_id"));

                n.setNotificationDate(new java.util.Date(rs.getTimestamp("notification_date").getTime()));

                n.setContent(rs.getString("content"));
                n.setReason(rs.getString("reason"));

                n.setRead(rs.getBoolean("is_read"));

                // Tạo thông tin người nhận (phụ huynh)
                Account parent = new Account();
                parent.setFirstName(rs.getString("parentFirst"));
                parent.setLastName(rs.getString("parentLast"));
                n.setReceiver(parent);

                // Tạo thông tin trẻ
                Kindergartner kid = new Kindergartner();
                kid.setFirst_name(rs.getString("childFirst"));
                kid.setLast_name(rs.getString("childLast"));
                n.setKindergartner(kid);

                list.add(n);
            }
        }
        return list;
    }

    public List<Notification> getNotificationsForParent(int parentId) throws Exception {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT n.*, t.first_name AS teacherFirst, t.last_name AS teacherLast, "
                + "k.first_name AS childFirst, k.last_name AS childLast "
                + "FROM Notification n "
                + "LEFT JOIN Account t ON n.sender_id = t.account_id "
                + "LEFT JOIN Kindergartner k ON n.kinder_id = k.kinder_id "
                + "WHERE n.receiver_id = ? "
                + "ORDER BY n.notification_date DESC";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setNotificationId(rs.getInt("notification_id"));
                n.setNotificationDate(new java.util.Date(rs.getTimestamp("notification_date").getTime()));
                n.setReason(rs.getString("reason"));
                n.setContent(rs.getString("content"));
                n.setRead(rs.getBoolean("is_read"));

                // Sender (teacher)
                Account teacher = new Account();
                teacher.setFirstName(rs.getString("teacherFirst"));
                teacher.setLastName(rs.getString("teacherLast"));
                n.setSender(teacher);

                // Child
                Kindergartner kid = new Kindergartner();
                kid.setFirst_name(rs.getString("childFirst"));
                kid.setLast_name(rs.getString("childLast"));
                n.setKindergartner(kid);
                list.add(n);
            }
        }
        return list;
    }

    public void markAsRead(int notificationId) throws Exception {
        String sql = "UPDATE Notification SET is_read = 1 WHERE notification_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, notificationId);
            ps.executeUpdate();
        }
    }

    // Lấy toàn bộ giáo viên
    public List<Account> getAllTeachers() throws Exception {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE role_id = 2"; // assuming role_id 2 = teacher
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountID(rs.getInt("account_id"));
                acc.setFirstName(rs.getString("first_name"));
                acc.setLastName(rs.getString("last_name"));
                list.add(acc);
            }
        }
        return list;
    }

// Gửi từ admin đến teacher
    public void sendFromAdminToTeacher(Account admin, Account teacher, String reason, String content) throws Exception {
        String sql = "INSERT INTO Notification (sender_id, receiver_id, content, reason, notification_date, is_read, notification_type) "
                + "VALUES (?, ?, ?, ?, GETDATE(), 0, 'AdminMessage')";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, admin.getAccountID());
            ps.setInt(2, teacher.getAccountID());
            ps.setString(3, content);
            ps.setString(4, reason);
            ps.executeUpdate();
        }
    }

    // Lấy danh sách thông báo từ admin gửi xuống giáo viên
    public List<Notification> getNotificationsFromAdminToTeacher(int teacherId) throws Exception {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT n.*, a.first_name AS adminFirst, a.last_name AS adminLast "
                + "FROM Notification n "
                + "JOIN Account a ON n.sender_id = a.account_id "
                + "WHERE n.receiver_id = ? AND n.notification_type = 'AdminMessage' "
                + "ORDER BY n.notification_date DESC";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setNotificationId(rs.getInt("notification_id"));
                n.setContent(rs.getString("content"));
                n.setReason(rs.getString("reason"));
                n.setNotificationDate(new java.util.Date(rs.getTimestamp("notification_date").getTime()));
                n.setRead(rs.getBoolean("is_read"));
                n.setNotificationType(rs.getString("notification_type"));

                Account admin = new Account();
                admin.setFirstName(rs.getString("adminFirst"));
                admin.setLastName(rs.getString("adminLast"));
                n.setSender(admin);

                list.add(n);
            }
        }
        return list;
    }

}
