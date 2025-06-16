/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author ACE
 */
public class MenuDao {

    public static List<Menu> getAllmenu() {
        List<Menu> list = new ArrayList<>();
        String sql = "select * from Menu";
        try {
            ClassDAO cl = new ClassDAO();
            Connection connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu m = new Menu();
                m.setMenu_id(rs.getInt("MenuID"));
                m.setMenudate(rs.getString("MenuDate"));
                m.setMenutype(rs.getString("MealType"));
                m.setDish(rs.getString("Dish"));
                m.setCalories(rs.getFloat("Calories"));
                m.setNotes(rs.getString("Notes"));
                model.Class iclass = cl.getClassByID(rs.getInt("ClassID"));

                m.setCalssid(iclass);

                list.add(m);

            }
            return list;

        } catch (Exception e) {

        }
        return list;
    }
    public static Menu getMenuById(int menuId) {
    String sql = "SELECT * FROM menu WHERE menu_id = ?";
    try (
        Connection connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, menuId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Menu m = new Menu();
            m.setMenu_id(rs.getInt("menu_id"));
            m.setDish(rs.getString("dish"));
            m.setCalories(rs.getFloat("calories"));
            m.setNotes(rs.getString("notes"));
            m.setMenutype(rs.getString("menutype"));
            m.setMenudate(rs.getString("menudate"));
            m.setCalssid(new ClassDAO().getClassByID(rs.getInt("class_id")));
            return m;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

public static void updateMenu(Menu m) {
    String sql = "UPDATE menu SET menutype=?, dish=?, calories=?, notes=?, menudate=?, class_id=? WHERE menu_id=?";
    try (Connection connection = new DBContext().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, m.getMenutype());
        ps.setString(2, m.getDish());
        ps.setFloat(3, m.getCalories());
        ps.setString(4, m.getNotes());
        ps.setString(5, m.getMenudate());
        ps.setInt(6, m.getCalssid().getClass_id());
        ps.setInt(7, m.getMenu_id());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public static List<Menu> getMenusByClassAndDate(int classId, String date) {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM Menu WHERE ClassID = ? AND MenuDate = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, classId);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();

            ClassDAO cl = new ClassDAO();

            while (rs.next()) {
                Menu m = new Menu();
                m.setMenu_id(rs.getInt("MenuID"));
                m.setMenudate(rs.getString("MenuDate"));
                m.setMenutype(rs.getString("MealType"));
                m.setDish(rs.getString("Dish"));
                m.setCalories(rs.getFloat("Calories"));
                m.setNotes(rs.getString("Notes"));
                m.setCalssid(cl.getClassByID(rs.getInt("ClassID")));
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insertMenu(Menu m) {
        String sql = "INSERT INTO Menu (MenuDate, MealType, Dish, Calories, Notes, ClassID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getMenudate());
            ps.setString(2, m.getMenutype());
            ps.setString(3, m.getDish());
            ps.setFloat(4, m.getCalories());
            ps.setString(5, m.getNotes());
            ps.setInt(6, m.getCalssid().getClass_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void deleteMenuById(int menuId) {
    Connection connection = null;
    PreparedStatement ps = null;
    try {
        String sql = "DELETE FROM Menu WHERE MenuID = ?";
        connection = new DBContext().getConnection();
        ps = connection.prepareStatement(sql);
        ps.setInt(1, menuId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    public static void main(String[] args) {
        List<Menu> menu=MenuDao.getAllmenu();
        for(Menu m:menu){
            System.out.println(m.getMenu_id());
        }
    }


  
          

  

}
