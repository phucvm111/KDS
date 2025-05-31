/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        String sql = "select * from Study_Record";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudyRecord sr = new StudyRecord(rs.getInt(1),
                        cd.getClassByID(rs.getInt(2)),
                        kd.getKinderById(rs.getInt(3)),
                        rs.getInt(4));
                        
                list.add(sr);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addStudyRecord(StudyRecord sr){
        String sql = "insert into Study_Record(class_id, kinder_id, study_year) "
                + "values(?,?,?)";
         try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, sr.getClassID().getClass_id());
            ps.setInt(2, sr.getKinder().getKinder_id());
            ps.setInt(3, sr.getStudyYear());
           
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public model.Class getKidClass(int id){
        
        String sql = "select * from Class Inner join Study_Record on Class.class_id = Study_Record.class_id where kinder_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                model.Class cl = new model.Class(rs.getInt(1),cd.getClassByID(rs.getInt(1)).getClass_name(),rs.getInt(3),rs.getString(4),ad.getAccountByID(rs.getInt(5)));
                return cl;
            }
           
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
