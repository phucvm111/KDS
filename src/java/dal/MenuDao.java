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
    String sql = "SELECT * FROM Menu WHERE MenuID = ?";
    try (
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {
        ps.setInt(1, menuId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Menu menu = new Menu();
            menu.setMenu_id(rs.getInt("MenuID"));
            menu.setMenudate(rs.getString("MenuDate"));
            menu.setMenutype(rs.getString("MealType")); // tên chính xác từ DB
            menu.setDish(rs.getString("Dish"));
            menu.setCalories(rs.getFloat("Calories"));
            menu.setNotes(rs.getString("Notes"));

            int classId = rs.getInt("ClassID");
            if (rs.wasNull()) {
                menu.setCalssid(null);
            } else {
                menu.setCalssid(new ClassDAO().getClassByID(classId));
            }

            return menu;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}



public static void updateMenu(Menu m) {
    String sql = "UPDATE Menu SET MenuDate=?, MealType=?, Dish=?, Calories=?, Notes=?, ClassID=? WHERE MenuID=?";
    try (Connection connection =  DBContext.getConnection(); 
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, m.getMenudate());
        ps.setString(2, m.getMenutype());
        ps.setString(3, m.getDish());
        ps.setFloat(4, m.getCalories());
        ps.setString(5, m.getNotes());
        ps.setInt(6, m.getCalssid().getClass_id());
        ps.setInt(7, m.getMenu_id());

        int rows = ps.executeUpdate();
        System.out.println("Updated rows: " + rows);
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
    // Tạo một đối tượng Menu mẫu
    Menu m = new Menu();
    m.setMenu_id(35); // ← ID cần tồn tại trong DB
    m.setMenutype("Breakfast");
    m.setDish("toanoppatesting");
    m.setCalories(9.9f);
    m.setNotes("updated from main");

    // Gọi update
    MenuDao.updateMenu(m);

    System.out.println("Update test done.");
}



  
          

  

}
