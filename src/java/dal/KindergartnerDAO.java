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
import javax.swing.UIManager;
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
                kinderRc.setKinder(new Kindergartner(
                        rs.getInt(1),
                        accDao.getAccountByID(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                ));
                kinderRc.setStudyRecord(new StudyRecord(
                        rs.getInt(10),
                        classDao.getClassByID(rs.getInt(11)),
                        kinderdao.getKinderById(rs.getInt(1)),
                        rs.getInt(12)
                ));
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
            String sql = "INSERT INTO [dbo].[Kindergartner]\n"
                    + "           ([parent_id]\n"
                    + "           ,[first_name]\n"
                    + "           ,[last_name]\n"
                    + "           ,[dob]\n"
                    + "           ,[gender]\n"
                    + "           ,[img]\n"
                    + "           ,[parentPhone]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?)";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kindergartner.getParentAccount().getAccountID());
            ps.setString(2, kindergartner.getFirst_name());
            ps.setString(3, kindergartner.getLast_name());
            ps.setString(4, kindergartner.getDob());
            ps.setBoolean(5, kindergartner.isGender());
            ps.setString(6, kindergartner.getImg());
            ps.setString(7, kindergartner.getParentPhone());
            ps.setString(8, kindergartner.getAddress());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateKinder(Kindergartner kindergartner) {
        try {
            String sql = "UPDATE [dbo].[Kindergartner]\n"
                    + "   SET [parent_id] = ?\n"
                    + "      ,[first_name] = ?\n"
                    + "      ,[last_name] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[gender] =?\n"
                    + "      ,[img] =?\n"
                    + "      ,[parentPhone] = ?\n"
                    + "      ,[address] = ?\n"
                    + " WHERE where kinder_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kindergartner.getParentAccount().getAccountID());
            ps.setString(2, kindergartner.getFirst_name());
            ps.setString(3, kindergartner.getLast_name());
            ps.setString(4, kindergartner.getDob());
            ps.setBoolean(5, kindergartner.isGender());
            ps.setString(6, kindergartner.getImg());
            ps.setString(7, kindergartner.getParentPhone());
            ps.setString(8, kindergartner.getAddress());
            ps.setInt(9, kindergartner.getKinder_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String sql = "SELECT * FROM Kindergartner";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Lấy parent từ AccountDAO
                Account parent = accdao.getAccountByID(rs.getInt("parent_id"));

                Kindergartner k = new Kindergartner(
                        rs.getInt("kinder_id"),
                        parent,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getBoolean("gender"),
                        rs.getString("img"),
                        parent.getAddress(),
                        parent.getPhoneNumber()
                );
                list.add(k);
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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

    public Kindergartner getKinderById(int id) {
        try {
            String sql = "SELECT * FROM Kindergartner WHERE kinder_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account parent = accdao.getAccountByID(rs.getInt("parent_id"));
                return new Kindergartner(
                        rs.getInt("kinder_id"),
                        parent,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getBoolean("gender"),
                        rs.getString("img"),
                        parent.getAddress(), // từ Account
                        parent.getPhoneNumber() // từ Account
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Kindergartner> getKindergartnersByParentId(int parentId) {
        List<Kindergartner> list = new ArrayList<>();
        String sql = "SELECT * FROM Kindergartner WHERE parent_id = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parentId);
            ResultSet rs = ps.executeQuery();
             AccountDAO acc=new AccountDAO();
            while (rs.next()) {
                Kindergartner k = new Kindergartner();
                k.setKinder_id(rs.getInt("kinder_id"));
                Account ac=acc.getAccountByID(rs.getInt("parent_id"));
                k.setParentAccount(ac);
                k.setFirst_name(rs.getString("first_name"));
                k.setLast_name(rs.getString("last_name"));
                k.setDob(rs.getDate("dob").toString()); // nếu dob là java.sql.Date
                k.setGender(rs.getBoolean("gender"));
                k.setImg(rs.getString("img"));
                list.add(k);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateKinderBasicInfo(Kindergartner kindergartner) {
        String sql = "UPDATE Kindergartner SET first_name = ?, last_name = ?, dob = ?, gender = ? WHERE kinder_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, kindergartner.getFirst_name());
            ps.setString(2, kindergartner.getLast_name());
            ps.setString(3, kindergartner.getDob());
            ps.setBoolean(4, kindergartner.isGender());
            ps.setInt(5, kindergartner.getKinder_id());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        KindergartnerDAO dao = new KindergartnerDAO();
       List<Kindergartner> kiders=dao.getKindergartnersByParentId(3);
       for(Kindergartner k:kiders){
           System.out.println(k);
       }
    }

}
