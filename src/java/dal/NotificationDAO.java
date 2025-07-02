package dal;

import static dal.DBContext.getConnection;
import java.sql.*;
import java.util.*;
import model.Notification;
import model.Account;
import model.Role;

public class NotificationDAO extends DBContext {

    public List<Notification> getAllNotifications() {
        List<Notification> list = new ArrayList<>();
        String sql = """
            SELECT n.*, a.first_name AS sender_first, a.last_name AS sender_last, r.role_name
            FROM Notification n
            JOIN Account a ON n.sender_id = a.account_id
            JOIN Role r ON n.target_role_id = r.role_id
            ORDER BY n.created_at DESC
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapNotification(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Notification> searchByContent(String keyword) {
        List<Notification> list = new ArrayList<>();
        String sql = """
            SELECT n.*, a.first_name AS sender_first, a.last_name AS sender_last, r.role_name
            FROM Notification n
            JOIN Account a ON n.sender_id = a.account_id
            JOIN Role r ON n.target_role_id = r.role_id
            WHERE n.message LIKE ?
            ORDER BY n.created_at DESC
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapNotification(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Notification getById(int id) {
        String sql = """
            SELECT n.*, a.first_name AS sender_first, a.last_name AS sender_last, r.role_name
            FROM Notification n
            JOIN Account a ON n.sender_id = a.account_id
            JOIN Role r ON n.target_role_id = r.role_id
            WHERE n.notification_id = ?
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapNotification(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNotification(Notification n) {
        String sql = """
            INSERT INTO Notification(title, message, created_at, sender_id, target_role_id, is_read, is_emailed)
            VALUES (?, ?, GETDATE(), ?, ?, 0, 0)
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n.getTitle());
            ps.setString(2, n.getMessage());
            ps.setInt(3, n.getSender().getAccountID());
            ps.setInt(4, n.getTargetRole().getRoleID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNotification(Notification n) {
        String sql = """
            UPDATE Notification SET title = ?, message = ?, target_role_id = ?
            WHERE notification_id = ?
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n.getTitle());
            ps.setString(2, n.getMessage());
            ps.setInt(3, n.getTargetRole().getRoleID());
            ps.setInt(4, n.getNotificationId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNotification(int id) {
        String sql = "DELETE FROM Notification WHERE notification_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void markAsEmailed(int notificationId) {
        String sql = "UPDATE Notification SET is_emailed = 1 WHERE notification_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, notificationId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAccountsByRole(int roleId) {
        List<Account> list = new ArrayList<>();
        String sql = """
            SELECT * FROM Account WHERE role_id = ?
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account acc = new Account();
                    acc.setAccountID(rs.getInt("account_id"));
                    acc.setFirstName(rs.getString("first_name"));
                    acc.setLastName(rs.getString("last_name"));
                    acc.setEmail(rs.getString("email"));
                    list.add(acc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Notification mapNotification(ResultSet rs) throws SQLException {
        Notification n = new Notification();
        n.setNotificationId(rs.getInt("notification_id"));
        n.setTitle(rs.getString("title"));
        n.setMessage(rs.getString("message"));
        n.setCreatedAt(rs.getTimestamp("created_at"));
        n.setRead(rs.getBoolean("is_read"));
        n.setEmailed(rs.getBoolean("is_emailed"));

        Account sender = new Account();
        sender.setAccountID(rs.getInt("sender_id"));
        sender.setFirstName(rs.getString("sender_first"));
        sender.setLastName(rs.getString("sender_last"));
        n.setSender(sender);

        Role role = new Role();
        role.setRoleID(rs.getInt("target_role_id"));
        role.setRoleName(rs.getString("role_name"));
        n.setTargetRole(role);

        return n;
    }
    // THÊM VÀO CUỐI FILE NotificationDAO.java

    public static void main(String[] args) {
        NotificationDAO dao = new NotificationDAO();

        // Test getAllNotifications
        System.out.println("===== All Notifications =====");
        List<Notification> list = dao.getAllNotifications();
        for (Notification n : list) {
            System.out.println(
                    "ID=" + n.getNotificationId()
                    + ", Title=" + n.getTitle()
                    + ", is_emailed=" + n.isEmailed()
                    + ", Target Role=" + n.getTargetRole().getRoleName()
                    + ", Sender=" + n.getSender().getFirstName()
            );
        }

        // Test getAccountsByRole
        System.out.println("\n===== Accounts by Role =====");
        List<Account> accounts = dao.getAccountsByRole(2); // ví dụ roleID 2
        for (Account a : accounts) {
            System.out.println(
                    "ID=" + a.getAccountID()
                    + ", Name=" + a.getFirstName() + " " + a.getLastName()
                    + ", Email=" + a.getEmail()
            );
        }

        // Test markAsEmailed
        System.out.println("\n===== Mark as emailed =====");
        int testNotiId = 6; // chỉnh id notification muốn test
        dao.markAsEmailed(testNotiId);
        System.out.println("✅ Marked notification id=" + testNotiId + " as emailed.");

        // Xác minh lại
        Notification nCheck = dao.getById(testNotiId);
        if (nCheck != null) {
            System.out.println("is_emailed after update = " + nCheck.isEmailed());
        }
    }

}
