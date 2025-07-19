package dal;

import java.sql.*;
import java.util.*;
import model.ParentMeeting;

public class ParentMeetingDAO extends DBContext {

    // Lấy tất cả
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
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapMeeting(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    

    
    // thêm
    public void addMeeting(ParentMeeting pm) {
        String sql = """
            INSERT INTO ParentMeeting (class_id, teacher_id, meeting_date, topic, notes, status, created_at)
            VALUES (?, ?, ?, ?, ?, ?, GETDATE())
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pm.getClassId());
            ps.setInt(2, pm.getTeacherId());
            ps.setTimestamp(3, new Timestamp(pm.getMeetingDate().getTime()));
            ps.setString(4, pm.getTopic());
            ps.setString(5, pm.getNotes());
            ps.setString(6, pm.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // sửa
    // sửa
    public void updateMeeting(ParentMeeting pm) {
        String sql = """
        UPDATE ParentMeeting
        SET topic = ?, notes = ?, status = ?, meeting_date = ?, class_id = ?
        WHERE meeting_id = ?
    """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pm.getTopic().trim());
            ps.setString(2, pm.getNotes() != null ? pm.getNotes().trim() : null);
            ps.setString(3, pm.getStatus());
            ps.setTimestamp(4, new Timestamp(pm.getMeetingDate().getTime()));
            ps.setInt(5, pm.getClassId());
            ps.setInt(6, pm.getMeetingId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lấy theo id
    public ParentMeeting getMeetingById(int id) {
        String sql = """
            SELECT pm.*, c.class_name,
                   CONCAT(a.first_name, ' ', a.last_name) AS teacher_name
            FROM ParentMeeting pm
            JOIN Class c ON pm.class_id = c.class_id
            JOIN Account a ON pm.teacher_id = a.account_id
            WHERE pm.meeting_id = ?
        """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapMeeting(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ParentMeeting mapMeeting(ResultSet rs) throws SQLException {
        ParentMeeting pm = new ParentMeeting();
        pm.setMeetingId(rs.getInt("meeting_id"));
        pm.setClassId(rs.getInt("class_id"));
        pm.setTeacherId(rs.getInt("teacher_id"));
        pm.setMeetingDate(rs.getTimestamp("meeting_date"));
        pm.setTopic(rs.getString("topic"));
        pm.setNotes(rs.getString("notes"));
        pm.setStatus(rs.getString("status"));
        pm.setCreatedAt(rs.getTimestamp("created_at"));
        pm.setClassName(rs.getString("class_name"));
        pm.setTeacherName(rs.getString("teacher_name"));
        return pm;
    }
    // Lấy theo parentId

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
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapMeeting(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

// Lấy theo parentId + status
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
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, parentId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapMeeting(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ParentMeeting> searchMeetingsForTeacher(int teacherId, String search, String status) {
        List<ParentMeeting> list = new ArrayList<>();
        String sql = "SELECT pm.*, c.class_name FROM ParentMeeting pm "
                + "JOIN Class c ON pm.class_id = c.class_id "
                + "WHERE c.teacher_id = ? ";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND pm.topic LIKE ? ";
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND pm.status = ? ";
        }
        sql += " ORDER BY pm.meeting_date DESC";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            int i = 1;
            ps.setInt(i++, teacherId);
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(i++, "%" + search + "%");
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(i++, status);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParentMeeting m = new ParentMeeting();
                m.setMeetingId(rs.getInt("meeting_id"));
                m.setTopic(rs.getString("topic"));
                m.setNotes(rs.getString("notes"));
                m.setMeetingDate(rs.getTimestamp("meeting_date"));
                m.setStatus(rs.getString("status"));
                m.setClassName(rs.getString("class_name"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countMeetingsForTeacher(int teacherId, String search, String status) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ParentMeeting pm "
                + "JOIN Class c ON pm.class_id = c.class_id "
                + "WHERE pm.teacher_id = ? ";
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND pm.topic LIKE ? ";
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND pm.status = ? ";
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            ps.setInt(index++, teacherId);
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(index++, "%" + search.trim() + "%");
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(index++, status.trim());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<ParentMeeting> searchMeetingsForTeacherPaging(int teacherId, String search, String status, int offset, int limit) {
        List<ParentMeeting> list = new ArrayList<>();
        String sql = "SELECT pm.*, c.class_name FROM ParentMeeting pm "
                + "JOIN Class c ON pm.class_id = c.class_id "
                + "WHERE pm.teacher_id = ? ";
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND pm.topic LIKE ? ";
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND pm.status = ? ";
        }
        sql += " ORDER BY pm.meeting_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            ps.setInt(index++, teacherId);
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(index++, "%" + search.trim() + "%");
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(index++, status.trim());
            }
            ps.setInt(index++, offset);
            ps.setInt(index++, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParentMeeting pm = new ParentMeeting();
                pm.setMeetingId(rs.getInt("meeting_id"));
                pm.setTopic(rs.getString("topic"));
                pm.setNotes(rs.getString("notes"));
                pm.setStatus(rs.getString("status"));
                pm.setMeetingDate(rs.getTimestamp("meeting_date"));
                pm.setClassId(rs.getInt("class_id"));
                pm.setClassName(rs.getString("class_name"));
                list.add(pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
