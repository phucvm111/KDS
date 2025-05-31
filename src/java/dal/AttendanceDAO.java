/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.Attendance;
import model.KinderRecordStudy;
import model.Kindergartner;

/**
 *
 * @author Windows 10 TIMT
 */
public class AttendanceDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    private KindergartnerDAO kinderdao = new KindergartnerDAO();
    private AccountDAO accdao = new AccountDAO();

//    public List<Kindergartner> getAllCheckInKids(int classID) {
//        KindergartnerDAO dao = new KindergartnerDAO();
//        AttendanceDAO dao1 = new AttendanceDAO();
//        List<Attendance> list = dao1.getAllAttendanceOfDay();
//        List<Kindergartner> liststu = dao.getKidsByClass(classID);
//        List<Kindergartner> output = new ArrayList<>();
//        for (Attendance attendence : list) {
//            for (Kindergartner kindergartner : liststu) {
//                if (attendence.getKinder().getKinder_id() == kindergartner.getKinder_id() && attendence.getStatus() == 1) {
//                    output.add(kindergartner);
//                }
//            }
//        }
//        return output;
//    }

    public List<Attendance> getAttByKidId(int kid_id) {
        List<Attendance> output = new ArrayList<>();
        AttendanceDAO dao = new AttendanceDAO();
        List<Attendance> list = dao.getAllAttendanceOfDay();
        for (Attendance attendence : list) {
            if (attendence.getKinder().getKinder_id() == kid_id) {
                output.add(attendence);
            }
        }
        return output;
    }

    public void insertAttendanceInfor(int KinderId, String checkDate, int status, int accId) {
        String sql = "insert into Attendance values (?,?,?,?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, KinderId);
            ps.setString(2, checkDate);
            ps.setInt(3, status);
            ps.setInt(4, accId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAttendanceInfor(int status, int kinderId, String checkDate) {
        String sql = "update attendance set status = ? where student_id = ? and check_date = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, kinderId);
            ps.setString(3, checkDate);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Attendance checkAttendance(int studentId, String checkDate) {
        String sql = "select * from Attendance where student_id = ? and check_date = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setString(2, checkDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Attendance(kinderdao.getKidInfoById(rs.getInt(1)),
                        rs.getString(2),
                        rs.getInt(3),
                        accdao.getAccountByID(rs.getInt(4)));
            }
        } catch (Exception ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Attendance> getAllAttendanceOfDay() {
        String sql = "select * from Attendance";
        List<Attendance> list = new ArrayList<>();
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(kinderdao.getKidInfoById(rs.getInt(1)),
                        rs.getString(2),
                        rs.getInt(3),
                        accdao.getAccountByID(rs.getInt(4)))
                );
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<KinderRecordStudy> getAllCheckInKidsOfADay(int classID, String date) {
        KindergartnerDAO dao = new KindergartnerDAO();
        AttendanceDAO dao1 = new AttendanceDAO();
        List<Attendance> list = dao1.getAllAttendanceOfInputDay(date);
        List<KinderRecordStudy> liststu = dao.getKidsByClass(classID);
        List<KinderRecordStudy> output = new ArrayList<>();
        for (Attendance attendence : list) {
            for (KinderRecordStudy kindergartner : liststu) {
                if (attendence.getKinder().getKinder_id() == kindergartner.getKinder().getKinder_id() && attendence.getStatus() != 0) {
                    output.add(kindergartner);
                }
            }
        }
        return output;
    }
    
    public List<KinderRecordStudy> getAllCheckOutKidsOfADay(int classID, String date) {
        KindergartnerDAO dao = new KindergartnerDAO();
        AttendanceDAO dao1 = new AttendanceDAO();
        List<Attendance> list = dao1.getAllAttendanceOfInputDay(date);
        List<KinderRecordStudy> liststu = dao.getKidsByClass(classID);
        List<KinderRecordStudy> output = new ArrayList<>();
        for (Attendance attendence : list) {
            for (KinderRecordStudy kindergartner : liststu) {
                if (attendence.getKinder().getKinder_id() == kindergartner.getKinder().getKinder_id() && attendence.getStatus() == 1) {
                    output.add(kindergartner);
                }
            }
        }
        return output;
    }

    public List<Attendance> getAllStatusStudent(int status, String checkday) {
        List<Attendance> listAtt = new AttendanceDAO().getAllAttendanceOfInputDay(checkday);
        List<Attendance> outputs = listAtt.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
        return outputs;
    }

    public List<Attendance> getAllAttendanceOfInputDay(String checkDay) {
        String sql = "select * from attendance where check_date = ?";
        List<Attendance> list = new ArrayList<>();
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, checkDay);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(kinderdao.getKidInfoById(rs.getInt(1)),
                        rs.getString(2),
                        rs.getInt(3),
                        accdao.getAccountByID(rs.getInt(4)))
                );
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        AttendanceDAO ad = new AttendanceDAO();
        List<Attendance> list = ad.getAllAttendanceOfInputDay("2022-07-07");
        for (Attendance a : list ) {
            System.out.println(list);
        }
    }
    
    public List<Attendance> getKidAttendance(int id){
        String sql = "select * from attendance where student_id = ?";
        List<Attendance> list = new ArrayList<>();
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(kinderdao.getKidInfoById(id),
                        new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(2)),
                        rs.getInt(3),
                        accdao.getAccountByID(rs.getInt(4)))
                );
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
