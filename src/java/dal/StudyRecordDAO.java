package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kindergartner;
import model.StudyRecord;

public class StudyRecordDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    private AccountDAO ad = new AccountDAO();
    private ClassDAO cd = new ClassDAO();
    private KindergartnerDAO kd = new KindergartnerDAO();

    public List<StudyRecord> getAllStudyRecord() {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        rs.getBoolean("is_graduated"),
                        rs.getBoolean("is_dropped_out")
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public StudyRecord getStudyRecordByKinderId(int kinderId) {
    String sql = "SELECT * FROM Study_Record WHERE kinder_id = ?";
    try {
        connection = new DBContext().getConnection();
        ps = connection.prepareStatement(sql);
        ps.setInt(1, kinderId);
        rs = ps.executeQuery();
        if (rs.next()) {
            StudyRecord sr = new StudyRecord(
                rs.getInt("study_record_id"),
                cd.getClassByID(rs.getInt("class_id")),
                kd.getKinderById(rs.getInt("kinder_id")),
                rs.getInt("study_year"),
                rs.getBoolean("is_graduated"),
                rs.getBoolean("is_dropped_out")
            );
            return sr;
        }
    } catch (Exception ex) {
        Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}


    public void addStudyRecord(StudyRecord sr) {
        String sql = "INSERT INTO Study_Record(class_id, kinder_id, study_year, is_graduated, is_dropped_out) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, sr.getClassID().getClass_id());
            ps.setInt(2, sr.getKinder().getKinder_id());
            ps.setInt(3, sr.getStudyYear());
            ps.setBoolean(4, sr.isGraduated());
            ps.setBoolean(5, sr.isDroppedOut()); // thêm nếu có cột này
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public model.Class getKidClass(int id) {
        String sql = "SELECT * FROM Class "
                + "INNER JOIN Study_Record ON Class.class_id = Study_Record.class_id "
                + "WHERE kinder_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new model.Class(
                        rs.getInt(1),
                        cd.getClassByID(rs.getInt(1)).getClass_name(),
                        rs.getInt(3),
                        rs.getString(4),
                        ad.getAccountByID(rs.getInt(5))
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // ✅ Đánh dấu tốt nghiệp thay vì xóa
    public void markGraduated(int kinderId, int year) {
        String sql = "UPDATE Study_Record SET is_graduated = 1 WHERE kinder_id = ? AND study_year = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kinderId);
            ps.setInt(2, year);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StudyRecord> getStudentsStudying(int year) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE study_year = ? AND is_graduated = 0 AND is_dropped_out = 0";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        rs.getBoolean("is_graduated"),
                        rs.getBoolean("is_dropped_out")
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getGraduatedStudyRecords() {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE is_graduated = 1";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        rs.getBoolean("is_graduated"),
                        rs.getBoolean("is_dropped_out")
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getGraduatedStudyRecordsByClassAndYear(int classId, int year) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE is_graduated = 1 AND class_id = ? AND study_year = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        true,
                        false
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    // Học sinh đang học theo năm, lớp và tên

    public List<StudyRecord> getStudentsStudyingByClassYearAndName(int classId, int year, String name) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record sr "
                + "JOIN Kindergartner k ON sr.kinder_id = k.kinder_id "
                + "WHERE sr.study_year = ? AND sr.is_graduated = 0 AND is_dropped_out = 0 AND sr.class_id = ? AND (k.first_name + ' ' + k.last_name) LIKE ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, classId);
            ps.setString(3, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        false,
                        false
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getGraduatedStudyRecordsByClassYearAndName(int classId, int year, String name) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record sr "
                + "JOIN Kindergartner k ON sr.kinder_id = k.kinder_id "
                + "WHERE sr.is_graduated = 1 AND sr.study_year = ? AND sr.class_id = ? AND (k.first_name + ' ' + k.last_name) LIKE ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, classId);
            ps.setString(3, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        true,
                        false
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getStudentsStudyingByClassAndYear(int classId, int year) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE study_year = ? AND is_graduated = 0 AND is_dropped_out = 0 AND class_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, classId);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        false,
                        false
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getGraduatedStudyRecordsByYear(int year) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE is_graduated = 1 AND study_year = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        true,
                        false
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void markDroppedOut(int kinderId, int studyYear) {
        String sql = "UPDATE Study_Record SET is_dropped_out = 1, is_graduated = 0 WHERE kinder_id = ? AND study_year = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, kinderId);
            ps.setInt(2, studyYear);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<StudyRecord> getDroppedOutStudentsByYear(int year) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE is_dropped_out = 1 AND study_year = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        rs.getBoolean("is_graduated"),
                        rs.getBoolean("is_dropped_out")
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getDroppedOutStudentsByClassAndYear(int classId, int year) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM Study_Record WHERE is_dropped_out = 1 AND class_id = ? AND study_year = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        rs.getBoolean("is_graduated"),
                        rs.getBoolean("is_dropped_out")
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudyRecord> getDroppedOutStudentsByClassYearAndName(int classId, int year, String name) {
        List<StudyRecord> list = new ArrayList<>();
        String sql = "SELECT sr.* FROM Study_Record sr "
                + "JOIN Kindergartner k ON sr.kinder_id = k.kinder_id "
                + "WHERE sr.is_dropped_out = 1 AND sr.study_year = ? "
                + (classId != -1 ? "AND sr.class_id = ? " : "")
                + "AND (k.first_name + ' ' + k.last_name) LIKE ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            int index = 2;
            if (classId != -1) {
                ps.setInt(index++, classId);
            }
            ps.setString(index, "%" + name + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(
                        rs.getInt("study_record_id"),
                        cd.getClassByID(rs.getInt("class_id")),
                        kd.getKinderById(rs.getInt("kinder_id")),
                        rs.getInt("study_year"),
                        rs.getBoolean("is_graduated"),
                        rs.getBoolean("is_dropped_out")
                );
                list.add(sr);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudyRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        StudyRecordDAO srdao = new StudyRecordDAO();
        int year = 2025;

        List<StudyRecord> studyingList = srdao.getStudentsStudying(year);
        System.out.println("🔍 Tổng số học sinh đang học năm " + year + ": " + studyingList.size());

        for (StudyRecord sr : studyingList) {
            System.out.println("👦 " + sr.getKinder().getFirst_name()
                    + " | Lớp: " + sr.getClassID().getClass_name()
                    + " | Tốt nghiệp: " + sr.isGraduated()
                    + " | Thôi học: " + sr.isDroppedOut());
        }
    }

}
