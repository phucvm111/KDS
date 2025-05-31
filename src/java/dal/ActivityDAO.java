/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Activity;

/**
 *
 * @author win
 */
public class ActivityDAO extends DBContext {

    private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;

    public ArrayList<Activity> getAllActivity() {
        ArrayList<Activity> activities = new ArrayList<>();
        try {
            String sql = "SELECT [activity_id]\n"
                    + "      ,[act_description]\n"
                    + "      ,[act_name]\n"
                    + "  FROM [activity]";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Activity t = new Activity();

                t.setActivity_id(rs.getInt("activity_id"));
                t.setAct_description(rs.getString("act_description"));
                t.setAct_name(rs.getString("act_name"));
                activities.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activities;
    }

    public Activity getAllActivityByID(int id) {

        try {
            String sql = "select * from Activity where activity_id = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Activity t = new Activity();
                t.setActivity_id(rs.getInt("activity_id"));
                t.setAct_description(rs.getString("act_description"));
                t.setAct_name(rs.getString("act_name"));
                return t;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertActivity(Activity activity) {
        try {
            String sql = "INSERT INTO [activity]\n"
                    + "           ([act_description]\n"
                    + "           ,[act_name])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);

            st.setString(1, activity.getAct_description());
            st.setString(2, activity.getAct_name());
            st.executeUpdate();
        } catch (SQLException ex) {
            
        } catch (Exception ex) {
            
        }
    }

    public void updateActivity(Activity activity) {
        try {
            String sql = "UPDATE [activity]\n"
                    + "   SET [act_description] = ?\n"
                    + "      ,[act_name] = ?\n"
                    + " WHERE activity_id = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(3, activity.getActivity_id());
            st.setString(1, activity.getAct_description());
            st.setString(2, activity.getAct_name());
            st.executeUpdate();
        } catch (SQLException ex) {
            
        } catch (Exception ex) {
            
        }
    }

//    public void deleteActivity(int id) {
//        try {
//            String sql = "DELETE FROM [activity]\n"
//                    + "      WHERE activity_id = ?";
//            connection = new DBContext().getConnection();
//            st = connection.prepareStatement(sql);
//            st.setInt(1, id);
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
        ActivityDAO aa = new ActivityDAO();
//        aa.insertActivity(new Activity("thanhld", "14"));
//        aa.updateActivity(new Activity(1, "NguyenNgocAnh", "Cho con nghi hoc"));
//
//        ArrayList<Activity> aaa = aa.getAllActivity();
//        System.out.println(aaa);
        Activity a = aa.getAllActivityByID(1);
        System.out.println(a.getAct_description());
    }
}
