/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author Admin
 */
public class RoleDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Role> getAllRoles() {
        List<Role> list = new ArrayList<>();
        try {
            String sql = "select * from Role";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(1), rs.getString(2));
                list.add(role);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public Role getRoleByID(int id) {

        try {
            String sql = "select * from Role where role_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(1), rs.getString(2));
                return role;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        RoleDAO rd = new RoleDAO();
        List<Role> list = rd.getAllRoles();
        System.out.println(list.get(0).getRoleName());
    }
}
