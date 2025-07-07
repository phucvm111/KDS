///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dal;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//import model.Attendance;
//import model.KinderRecordStudy;
//import model.Kindergartner;
//
///**
// *
// * @author Windows 10 TIMT
// */
//public class AttendanceDAO {
//
//    private Connection connection;
//    private PreparedStatement ps;
//    private ResultSet rs;
//
//    private KindergartnerDAO kinderdao = new KindergartnerDAO();
//    private AccountDAO accdao = new AccountDAO();
//
////    public List<Kindergartner> getAllCheckInKids(int classID) {
////        KindergartnerDAO dao = new KindergartnerDAO();
////        AttendanceDAO dao1 = new AttendanceDAO();
////        List<Attendance> list = dao1.getAllAttendanceOfDay();
////        List<Kindergartner> liststu = dao.getKidsByClass(classID);
////        List<Kindergartner> output = new ArrayList<>();
////        for (Attendance attendence : list) {
////            for (Kindergartner kindergartner : liststu) {
////                if (attendence.getKinder().getKinder_id() == kindergartner.getKinder_id() && attendence.getStatus() == 1) {
////                    output.add(kindergartner);
////                }
////            }
////        }
////        return output;
////    }
//
//    public List<Attendance> getAttByKidId(int kid_id) {
//        List<Attendance> output = new ArrayList<>();
//        AttendanceDAO dao = new AttendanceDAO();
//        List<Attendance> list = dao.getAllAttendanceOfDay();
//        for (Attendance attendence : list) {
//            if (attendence.getKinder().getKinder_id() == kid_id) {
//                output.add(attendence);
//            }
//        }
//        return output;
//    }
//
//    public void insertAttendanceInfor(int KinderId, String checkDate, int status, int accId) {
//        String sql = "insert into Attendance values (?,?,?,?)";
//        try {
//            connection = new DBContext().getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, KinderId);
//            ps.setString(2, checkDate);
//            ps.setInt(3, status);
//            ps.setInt(4, accId);
//            ps.executeUpdate();
//        } catch (Exception ex) {
//            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void updateAttendanceInfor(int status, int kinderId, String checkDate) {
//        String sql = "update attendance set status = ? where student_id = ? and check_date = ?";
//        try {
//            connection = new DBContext().getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, status);
//            ps.setInt(2, kinderId);
//            ps.setString(3, checkDate);
//            ps.executeUpdate();
//        } catch (Exception ex) {
//            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public Attendance checkAttendance(int studentId, String checkDate) {
//        String sql = "select * from Attendance where student_id = ? and check_date = ?";
//        try {
//            connection = new DBContext().getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, studentId);
//            ps.setString(2, checkDate);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return new Attendance(kinderdao.getKidInfoById(rs.getInt(1)),
//                        rs.getString(2),
//                        rs.getInt(3),
//                        accdao.getAccountByID(rs.getInt(4)));
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public List<Attendance> getAllAttendanceOfDay() {
//        String sql = "select * from Attendance";
//        List<Attendance> list = new ArrayList<>();
//        try {
//            connection = new DBContext().getConnection();
//            ps = connection.prepareStatement(sql);
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Attendance(kinderdao.getKidInfoById(rs.getInt(1)),
//                        rs.getString(2),
//                        rs.getInt(3),
//                        accdao.getAccountByID(rs.getInt(4)))
//                );
//            }
//            return list;
//        } catch (Exception ex) {
//            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public List<KinderRecordStudy> getAllCheckInKidsOfADay(int classID, String date) {
//        KindergartnerDAO dao = new KindergartnerDAO();
//        AttendanceDAO dao1 = new AttendanceDAO();
//        List<Attendance> list = dao1.getAllAttendanceOfInputDay(date);
//        List<KinderRecordStudy> liststu = dao.getKidsByClass(classID);
//        List<KinderRecordStudy> output = new ArrayList<>();
//        for (Attendance attendence : list) {
//            for (KinderRecordStudy kindergartner : liststu) {
//                if (attendence.getKinder().getKinder_id() == kindergartner.getKinder().getKinder_id() && attendence.getStatus() != 0) {
//                    output.add(kindergartner);
//                }
//            }
//        }
//        return output;
//    }
//    
//    public List<KinderRecordStudy> getAllCheckOutKidsOfADay(int classID, String date) {
//        KindergartnerDAO dao = new KindergartnerDAO();
//        AttendanceDAO dao1 = new AttendanceDAO();
//        List<Attendance> list = dao1.getAllAttendanceOfInputDay(date);
//        List<KinderRecordStudy> liststu = dao.getKidsByClass(classID);
//        List<KinderRecordStudy> output = new ArrayList<>();
//        for (Attendance attendence : list) {
//            for (KinderRecordStudy kindergartner : liststu) {
//                if (attendence.getKinder().getKinder_id() == kindergartner.getKinder().getKinder_id() && attendence.getStatus() == 1) {
//                    output.add(kindergartner);
//                }
//            }
//        }
//        return output;
//    }
//
//    public List<Attendance> getAllStatusStudent(int status, String checkday) {
//        List<Attendance> listAtt = new AttendanceDAO().getAllAttendanceOfInputDay(checkday);
//        List<Attendance> outputs = listAtt.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
//        return outputs;
//    }
//
//    public List<Attendance> getAllAttendanceOfInputDay(String checkDay) {
//        String sql = "select * from attendance where check_date = ?";
//        List<Attendance> list = new ArrayList<>();
//        try {
//            connection = new DBContext().getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, checkDay);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Attendance(kinderdao.getKidInfoById(rs.getInt(1)),
//                        rs.getString(2),
//                        rs.getInt(3),
//                        accdao.getAccountByID(rs.getInt(4)))
//                );
//            }
//            return list;
//        } catch (Exception ex) {
//            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        AttendanceDAO ad = new AttendanceDAO();
//        List<Attendance> list = ad.getAllAttendanceOfInputDay("2022-07-07");
//        for (Attendance a : list ) {
//            System.out.println(list);
//        }
//    }
//    
//    public List<Attendance> getKidAttendance(int id){
//        String sql = "select * from attendance where student_id = ?";
//        List<Attendance> list = new ArrayList<>();
//        try {
//            connection = new DBContext().getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Attendance(kinderdao.getKidInfoById(id),
//                        new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(2)),
//                        rs.getInt(3),
//                        accdao.getAccountByID(rs.getInt(4)))
//                );
//            }
//            return list;
//        } catch (Exception ex) {
//            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//}
package dal;

import model.Account;
import model.Attendance;
import model.Kindergartner;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

public class AttendanceDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Attendance> getAttendanceByDateAndClass(Date date, int classId) {
        List<Attendance> list = new ArrayList<>();
        String sql = """
            SELECT a.kinder_id, a.check_date, a.status, a.teacher_id
            FROM Attendance a
            JOIN Study_Record s ON a.kinder_id = s.kinder_id
            WHERE s.class_id = ? AND a.check_date = ?
        """;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Kindergartner kid = new Kindergartner();
                kid.setKinder_id(rs.getInt("kinder_id"));
                att.setKinder(kid);
                att.setCheck_date(rs.getDate("check_date").toString());
                att.setStatus(rs.getString("status").equalsIgnoreCase("Present") ? 1 : 0);
                Account teacher = new Account();
                teacher.setAccountID(rs.getInt("teacher_id"));
                att.setTeacherAccount(teacher);
                list.add(att);
            }
        } catch (Exception e) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void insertOrUpdateAttendance(Attendance att) {
        String sql = """
            MERGE INTO Attendance AS target
            USING (SELECT ? AS kinder_id, ? AS check_date) AS source
            ON target.kinder_id = source.kinder_id AND target.check_date = source.check_date
            WHEN MATCHED THEN
                UPDATE SET status = ?, teacher_id = ?
            WHEN NOT MATCHED THEN
                INSERT (kinder_id, check_date, status, teacher_id) VALUES (?, ?, ?, ?);
        """;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, att.getKinder().getKinder_id());
            ps.setDate(2, Date.valueOf(att.getCheck_date()));
            ps.setString(3, att.getStatus() == 1 ? "Present" : "Absent");
            ps.setInt(4, att.getTeacherAccount().getAccountID());
            ps.setInt(5, att.getKinder().getKinder_id());
            ps.setDate(6, Date.valueOf(att.getCheck_date()));
            ps.setString(7, att.getStatus() == 1 ? "Present" : "Absent");
            ps.setInt(8, att.getTeacherAccount().getAccountID());
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Map<String, Double> getAttendanceStatistics(int classId, int month, int year) {
        Map<String, Double> stats = new LinkedHashMap<>();
        String sql = """
            SELECT k.first_name, k.last_name,
                   SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) as present_days,
                   COUNT(*) as total_days
            FROM Attendance a
            JOIN Kindergartner k ON a.kinder_id = k.kinder_id
            JOIN Study_Record s ON s.kinder_id = k.kinder_id
            WHERE s.class_id = ? AND MONTH(a.check_date) = ? AND YEAR(a.check_date) = ?
            GROUP BY k.first_name, k.last_name
        """;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setInt(2, month);
            ps.setInt(3, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                int present = rs.getInt("present_days");
                int total = rs.getInt("total_days");
                double percent = total > 0 ? present * 100.0 / total : 0.0;
                stats.put(name, percent);
            }
        } catch (Exception e) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return stats;
    }

    public double getAttendancePercentageForKid(int kinderId) {
        String sql = """
        SELECT 
          SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) AS present_days,
          COUNT(*) AS total_days
        FROM Attendance
        WHERE kinder_id = ?
    """;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, kinderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int present = rs.getInt("present_days");
                int total = rs.getInt("total_days");
                return total > 0 ? (present * 100.0 / total) : 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
     /**
     * đếm số ngày có mặt của 1 trẻ
     */
    public int countPresentDays(int kinderId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Attendance WHERE kinder_id = ? AND status = 1";
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, kinderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * đếm số ngày vắng mặt của 1 trẻ
     */
    public int countAbsentDays(int kinderId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Attendance WHERE kinder_id = ? AND status = 0";
        try (Connection conn = connection;
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, kinderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Tỷ lệ có mặt của 1 trẻ (ví dụ 70%)
     */
   

}
