/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author Admin
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Teacher_Achivement;
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
public class Teacher_AchivementDAO extends DBContext {
     private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;
    
    public ArrayList<Teacher_Achivement> getAllTeacherAchivement() {
        ArrayList<Teacher_Achivement> Tachivements = new ArrayList<>();
        try {
            String sql = "SELECT [achivement_id]\n" +
                        "      ,[content]\n" +
                        "      ,[teacher_id]\n" +
                        "      FROM [teacher_achivement]";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Teacher_Achivement ta = new Teacher_Achivement();

                ta.setAchivement_id(rs.getInt("achivement_id"));
                ta.setContent(rs.getString("content"));
                ta.setTeacher_id(rs.getInt("teacher_id"));
                 Tachivements.add(ta);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Tachivements;
    }
    
    public void insertTachivements(Teacher_Achivement tachivements) {
        try {
            String sql = "INSERT INTO [teacher_achivement]\n" +
                        "           ([content]\n" +
                        "           ,[teacher_id])\n" +
                        "           VALUES(?,?)";

                  
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, tachivements.getContent());
            st.setInt(2, tachivements.getTeacher_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTachivements(Teacher_Achivement tachivements) {
        try {
            String sql = "UPDATE [teacher_achivement]\n" +
                        "   SET [content] = ?\n" +
                        "      ,[teacher_id] = ?\n" +
                        " WHERE [achivement_id]";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, tachivements.getContent());
            st.setInt(2, tachivements.getTeacher_id());
            st.setInt(3, tachivements.getAchivement_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteTeacher_Tachivements(int id) {
        try {
            String sql = "DELETE FROM [teacher_achivement]\n"
                    + "      WHERE [achivement_id] = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Teacher_AchivementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}