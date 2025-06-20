package dal;

import java.sql.*;
import java.util.*;
import model.ParentMeeting;

public class ParentMeetingDAO extends DBContext {

    public List<ParentMeeting> getAllMeetings() {
        List<ParentMeeting> list = new ArrayList<>();
        String sql = """
            SELECT pm.*, c.class_name,
                   CONCAT(a.first_name, ' ', a.last_name) AS teacher_name
            FROM ParentMeeting pm
            JOIN Class c ON pm.class_id = c.class_id
            JOIN Account a ON pm.teacher_id = a.account_id
            ORDER BY pm.meeting_date DESC
        """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ParentMeeting pm = new ParentMeeting();
                pm.setMeetingId(rs.getInt("meeting_id"));
                pm.setClassId(rs.getInt("class_id"));
                pm.setTeacherId(rs.getInt("teacher_id"));
                pm.setMeetingDate(rs.getTimestamp("meeting_date"));
                pm.setTopic(rs.getString("topic"));
                pm.setNotes(rs.getString("notes"));
                pm.setStatus(rs.getString("status")); // <-- thêm dòng này
                pm.setCreatedAt(rs.getTimestamp("created_at"));
                pm.setClassName(rs.getString("class_name"));
                pm.setTeacherName(rs.getString("teacher_name"));
                list.add(pm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ParentMeeting> getMeetingsByParentId(int parentId) {
        List<ParentMeeting> list = new ArrayList<>();
        String sql = """
            SELECT DISTINCT pm.*, c.class_name,
                   CONCAT(a.first_name, ' ', a.last_name) AS teacher_name
            FROM ParentMeeting pm
            JOIN Class c ON pm.class_id = c.class_id
            JOIN Account a ON pm.teacher_id = a.account_id
            JOIN Study_Record sr ON sr.class_id = pm.class_id
            JOIN Kindergartner k ON k.kinder_id = sr.kinder_id
            WHERE k.parent_id = ?
            ORDER BY pm.meeting_date DESC
        """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ParentMeeting pm = new ParentMeeting();
                    pm.setMeetingId(rs.getInt("meeting_id"));
                    pm.setClassId(rs.getInt("class_id"));
                    pm.setTeacherId(rs.getInt("teacher_id"));
                    pm.setMeetingDate(rs.getTimestamp("meeting_date"));
                    pm.setTopic(rs.getString("topic"));
                    pm.setNotes(rs.getString("notes"));
                    pm.setStatus(rs.getString("status")); // <-- thêm dòng này
                    pm.setCreatedAt(rs.getTimestamp("created_at"));
                    pm.setClassName(rs.getString("class_name"));
                    pm.setTeacherName(rs.getString("teacher_name"));
                    list.add(pm);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ParentMeeting> getMeetingsByParentIdAndStatus(int parentId, String status) {
        List<ParentMeeting> list = new ArrayList<>();
        String sql = """
            SELECT DISTINCT pm.*, c.class_name,
                   CONCAT(a.first_name, ' ', a.last_name) AS teacher_name
            FROM ParentMeeting pm
            JOIN Class c ON pm.class_id = c.class_id
            JOIN Account a ON pm.teacher_id = a.account_id
            JOIN Study_Record sr ON sr.class_id = pm.class_id
            JOIN Kindergartner k ON k.kinder_id = sr.kinder_id
            WHERE k.parent_id = ? AND pm.status = ?
            ORDER BY pm.meeting_date DESC
        """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parentId);
            ps.setString(2, status);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ParentMeeting pm = new ParentMeeting();
                    pm.setMeetingId(rs.getInt("meeting_id"));
                    pm.setClassId(rs.getInt("class_id"));
                    pm.setTeacherId(rs.getInt("teacher_id"));
                    pm.setMeetingDate(rs.getTimestamp("meeting_date"));
                    pm.setTopic(rs.getString("topic"));
                    pm.setNotes(rs.getString("notes"));
                    pm.setStatus(rs.getString("status")); // <-- thêm dòng này
                    pm.setCreatedAt(rs.getTimestamp("created_at"));
                    pm.setClassName(rs.getString("class_name"));
                    pm.setTeacherName(rs.getString("teacher_name"));
                    list.add(pm);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Test nhanh
    public static void main(String[] args) {
        ParentMeetingDAO dao = new ParentMeetingDAO();

        List<ParentMeeting> meetings = dao.getMeetingsByParentIdAndStatus(3, "Scheduled");
        for (ParentMeeting pm : meetings) {
            System.out.println("Meeting ID: " + pm.getMeetingId());
            System.out.println("Class: " + pm.getClassName());
            System.out.println("Teacher: " + pm.getTeacherName());
            System.out.println("Status: " + pm.getStatus());
            System.out.println("Date: " + pm.getMeetingDate());
            System.out.println("Topic: " + pm.getTopic());
            System.out.println("Notes: " + pm.getNotes());
            System.out.println("----------------------------");
        }
    }
}
