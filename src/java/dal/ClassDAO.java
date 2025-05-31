/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Admin
 */
public class ClassDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
public void updateClass(Class cl) {
        try {
            String sql = "UPDATE [Class]   SET [class_name] = ?\n"
                    + "                      ,[grade] = ?\n"
                    + "                     ,[class_description] = ?\n"
                    + "                     ,[teacher_id] = ?\n"
                    + "                    WHERE class_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cl.getClass_name());
            ps.setInt(2, cl.getGrade());
            ps.setString(3, cl.getClass_description());
            ps.setInt(4, cl.getAcc().getAccountID());
            ps.setInt(5, cl.getClass_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void addClass(Class c) {
        String sql = "insert into Class values(?,?,?,?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, c.getClass_name());
            ps.setInt(2, c.getGrade());
            ps.setString(3, c.getClass_description());
            ps.setInt(4, c.getAcc().getAccountID());

            ps.executeUpdate();
        } catch (Exception ex) {

        }
    }
    public void deleteAccount(int id) {
        try {
            String sql = "DELETE FROM [Class]\n"
                    + "      WHERE class_id=?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Class getTeacherClass(int id) {
        ClassDAO cd = new ClassDAO();
        List<Class> list = cd.getAllClass();
        for (Class kinder_Class : list) {
            if (kinder_Class.getAcc().getAccountID() == id) {
                return kinder_Class;
            }
        }
        return null;
    }

    public List<Class> getAllClass() {
        List<Class> list = new ArrayList<>();
        String sql = "select * from class";
        try {
            AccountDAO ad = new AccountDAO();
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_name"));
                c.setGrade(rs.getInt("grade"));
                c.setClass_description(rs.getString("class_description"));
                Account a = ad.getAccountByID(rs.getInt("teacher_id"));
                c.setAcc(a);
                list.add(c);
            }
            return list;

        } catch (Exception e) {

        }
        return null;
    }

    public Class getClassByID(int id) {

        String sql = "select * from class where class_id = ?";
        try {
            AccountDAO ad = new AccountDAO();
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_name"));
                c.setGrade(rs.getInt("grade"));
                c.setClass_description(rs.getString("class_description"));
                Account a = ad.getAccountByID(rs.getInt("teacher_id"));
                c.setAcc(a);
                return c;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        ClassDAO cd = new ClassDAO();
        List<Class> classs=cd.getAllClass();
        
        for(Class c :classs){
            System.out.println(c.getClass_name());
        }
    }
}
