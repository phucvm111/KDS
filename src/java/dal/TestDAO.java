/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.TestObject;

/**
 *
 * @author Admin
 */
public class TestDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public TestObject getTestObject() {
        String sql = "select * from Test";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TestObject to = new TestObject(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3), rs.getString(4));
                return to;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public TestObject getLastTestObject() {
        String sql = "SELECT TOP 1 * FROM Test ORDER BY id DESC";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TestObject to = new TestObject(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3), rs.getString(4));
                return to;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void addTestObject(TestObject to) {
        String sql = "insert into Test values\n"
                + "(?, ?, ?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, to.getA());
            ps.setInt(2, to.getB());
            ps.setString(3, null);
            rs = ps.executeQuery();

        } catch (Exception e) {

        }
      
    }

    public void updateTestObject(TestObject to) {
        String sql = "update Test set c = ? where id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, to.getC());
            ps.setInt(2, to.getId());

            ps.executeUpdate();
        } catch (Exception e) {

        }
    }
    
    public void updateTestObject2(TestObject to) {
        String sql = "update Test set c = ? where id = (select max(id) from Test)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, to.getC());

            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        TestDAO td = new TestDAO();
//        TestObject to = td.getTestObject();
//        System.out.println(to.getC());
        TestObject to = new TestObject(1, 1, 5, "6");
        td.updateTestObject(to);
        to = td.getTestObject();
        System.out.println(to.getC());
    }
}
