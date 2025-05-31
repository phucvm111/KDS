/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import model.Teacher_Record;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NQ
 */
public class TeacherRecordDAO {
    private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;
    
    public ArrayList<Teacher_Record> getAllTeacher_Record() {
        ArrayList<Teacher_Record> trecords = new ArrayList<>();
        try {
            String sql = "SELECT [record_id]\n" +
                        "      ,[teacher_id]\n" +
                        "      ,[description]\n" +
                        "  FROM [teacher_record]";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Teacher_Record tr = new Teacher_Record();

                tr.setRecord_id(rs.getInt("record_id"));
                tr.setTeacher_id(rs.getInt("teacher_id"));
                tr.setDescription(rs.getString("description"));
                trecords.add(tr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trecords;
    }
    
    public void insertTeacher_Record(Teacher_Record trecord) {
        try {
            String sql = "INSERT INTO [teacher_record]\n" +
                        "           ([teacher_id]\n" +
                        "           ,[description])\n" +
                        "     VALUES(?,?)";

            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, trecord.getTeacher_id());
            st.setString(2, trecord.getDescription());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTeacher_Record(Teacher_Record trecord) {
        try {
            String sql = "UPDATE [teacher_record]\n" +
                        "   SET [teacher_id] = ?\n" +
                        "      ,[description] = ?\n" +
                        " WHERE [record_id] = ? ";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, trecord.getTeacher_id());
            st.setString(2, trecord.getDescription());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteTeacher_Record(int id) {
        try {
            String sql = "DELETE FROM [teacher_record]\n"
                    + "      WHERE [record_id] = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TeacherRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}