package dal;

import java.sql.*;
import java.util.*;
import model.ParentMeeting;

public class ParentMeetingDAO extends DBContext {

    public List<ParentMeeting> getAllMeetings() {
        List<ParentMeeting> list = new ArrayList<>();
        String sql = "SELECT * FROM ParentMeeting ORDER BY meeting_date DESC";

        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ParentMeeting pm = new ParentMeeting(
                    rs.getInt("meeting_id"),
                    rs.getInt("class_id"),
                    rs.getInt("teacher_id"),
                    rs.getTimestamp("meeting_date"),
                    rs.getString("topic"),
                    rs.getString("notes"),
                    rs.getTimestamp("created_at")
                );
                list.add(pm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Main test để xem có load dữ liệu được không
    public static void main(String[] args) {
        ParentMeetingDAO dao = new ParentMeetingDAO();
        List<ParentMeeting> meetings = dao.getAllMeetings();

        if (meetings.isEmpty()) {
            System.out.println("Không có lịch họp nào được tải.");
        } else {
            for (ParentMeeting pm : meetings) {
                System.out.println("Meeting ID: " + pm.getMeetingId());
                System.out.println("Class ID: " + pm.getClassId());
                System.out.println("Teacher ID: " + pm.getTeacherId());
                System.out.println("Ngày họp: " + pm.getMeetingDate());
                System.out.println("Chủ đề: " + pm.getTopic());
                System.out.println("Ghi chú: " + pm.getNotes());
                System.out.println("Tạo lúc: " + pm.getCreatedAt());
                System.out.println("-----------------------------");
            }
        }
    }
}
