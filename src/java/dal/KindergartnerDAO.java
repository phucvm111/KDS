/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feedback;
import model.KinderRecordStudy;
import model.Kindergartner;
import model.StudyRecord;

/**
 *
 * @author Windows 10 TIMT
 */
public class KindergartnerDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    private AccountDAO accdao = new AccountDAO();
    private ClassDAO classdao = new ClassDAO();
    
    public List<KinderRecordStudy> getKidsByClass(int class_id) {
        KindergartnerDAO kinderdao = new KindergartnerDAO();
        ClassDAO classDao = new ClassDAO();
        AccountDAO accDao = new AccountDAO();
        List<KinderRecordStudy> list = new ArrayList<>();
        String sql = "select * from Kindergartner inner join Study_Record on Kindergartner.kinder_id = Study_Record.kinder_id where class_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, class_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                KinderRecordStudy kinderRc = new KinderRecordStudy();
                kinderRc.setKinder(new Kindergartner(rs.getInt(1),
                        accDao.getAccountByID(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7)));
                kinderRc.setStudyRecord(new StudyRecord(rs.getInt(8), 
                        classDao.getClassByID(rs.getInt(9)), 
                        kinderdao.getKinderById(rs.getInt(1)), 
                        rs.getInt(10)));
                list.add(kinderRc);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertKinder(Kindergartner kindergartner) {
        try {
            String sql = "INSERT INTO [Kindergartner]\n"
                    + "           ([parent_id]\n"
                    + "           ,[first_name]\n"
                    + "           ,[last_name]\n"
                    + "           ,[dob]\n"
                    + "           ,[gender]\n"
                    + "           ,[img])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kindergartner.getParentAccount().getAccountID());
            ps.setString(2, kindergartner.getFirst_name());
            ps.setString(3, kindergartner.getLast_name());
            ps.setString(4, kindergartner.getDob());
            ps.setBoolean(5, kindergartner.isGender());
            ps.setString(6, kindergartner.getImg());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateKinder(Kindergartner kindergartner) {
        try {
            String sql = "update Kindergartner \n"
                    + "set parent_id = ?, first_name = ?, last_name = ?, dob = ?, gender = ?, img = ?\n"
                    + "where kinder_id = ?;";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kindergartner.getParentAccount().getAccountID());
            ps.setString(2, kindergartner.getFirst_name());
            ps.setString(3, kindergartner.getLast_name());
            ps.setString(4, kindergartner.getDob());
            ps.setBoolean(5, kindergartner.isGender());
            ps.setString(6, kindergartner.getImg());
            ps.setInt(7, kindergartner.getKinder_id());
            ps.executeUpdate();
            System.out.println("Update successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Kindergartner getKinderById(int id) {
        try {
            String sql = "SELECT [kinder_id]\n"
                    + "      ,[parent_id]\n"
                    + "      ,[first_name]\n"
                    + "      ,[last_name]\n"
                    + "      ,[dob]\n"
                    + "      ,[gender]\n"
                    + "      ,[img]\n"
                    + "  FROM [Kindergartner] where kinder_id = " + id + "";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Kindergartner k = new Kindergartner(rs.getInt(1),
                        accdao.getAccountByID(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                return k;
            }

        } catch (SQLException ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteKinder(int id) {
        try {
            String sql = "DELETE FROM [Kindergartner]\n"
                    + "      WHERE kinder_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Kindergartner> getAllStudent() {
        List<Kindergartner> list = new ArrayList<>();
        String sql = "select * from Kindergartner";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Kindergartner k = new Kindergartner(rs.getInt(1),
                        accdao.getAccountByID(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                list.add(k);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Kindergartner getKidInfoById(int id) {
        KindergartnerDAO kinderdao = new KindergartnerDAO();
        List<Kindergartner> list = kinderdao.getAllStudent();
        for (Kindergartner kindergartner : list) {
            if (kindergartner.getKinder_id() == id) {
                return kindergartner;
            }
        }
        return null;
    }

    public Account getParentById(int id) {
        AccountDAO ad = new AccountDAO();
        List<Account> list = ad.getAllParentInfor();
        for (Account parent : list) {
            if (parent.getAccountID() == id) {
                return parent;
            }
        }
        return null;
    }

    public List<StudyRecord> getAllStudyRecord() {
        KindergartnerDAO kinderdao = new KindergartnerDAO();
        String sql = "select * from Study_Record";
        List<StudyRecord> list = new ArrayList<>();
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord k = new StudyRecord(rs.getInt(1),
                        classdao.getClassByID(rs.getInt(2)),
                        kinderdao.getKidInfoById(rs.getInt(3)),
                        rs.getInt(4)
                );
                list.add(k);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Feedback getFbByKidID(int kid_id) {
        String sql = "select * from feedback where kid_id = ?";

        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kid_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6));
                return fb;
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertFBtoDB(Feedback fb) {
        String sql = "insert into Feedback(feedback_id, kid_id, teacher_id, fb_content, rating, fb_date) "
                + "values(?,?,?,?,?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, fb.getKid_id());
            ps.setInt(2, fb.getTeacher_id());
            ps.setString(3, fb.getFb_content());
            ps.setDouble(4, fb.getRating());
            ps.setString(5, fb.getTakenDate());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Kindergartner> getKidbyParent(Account account) {
        KindergartnerDAO dao = new KindergartnerDAO();
        Account a = new Account();
        List<Kindergartner> list = dao.getAllStudent();
        List<Kindergartner> kidparentlist = new ArrayList<>();
        for (Kindergartner kindergartner : list) {
            if (kindergartner.getParentAccount().getAccountID() == account.getAccountID()) {
                kidparentlist.add(kindergartner);
            }
        }
        return kidparentlist;
    }

    public static void main(String[] args) {
        KindergartnerDAO dao = new KindergartnerDAO();
        List<StudyRecord> list = dao.getAllStudyRecord();
        for (StudyRecord s : list) {
            System.out.println(s.toString());
        }

    }

}
