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
import model.Slot;

/**
 *
 * @author Admin
 */
public class SlotDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Slot> getAllSlots() {
        List<Slot> list = new ArrayList<>();

        try {
            String sql = "select * from Slot";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Slot s = new Slot(rs.getInt(1), rs.getFloat(2), rs.getFloat(3));
                list.add(s);
            }
        } catch (Exception e) {

        }
        return list;
    }
    
    public Slot getSlotByID(int id) {
        try {
            String sql = "select * from Slot where slot_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Slot s = new Slot(rs.getInt(1), rs.getFloat(2), rs.getFloat(3));
                return s;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    public static void main(String[] args) {
        SlotDAO sd = new SlotDAO();
        List<Slot> list = sd.getAllSlots();
        System.out.println(list.get(0));
        System.out.println(sd.getSlotByID(1).getSlot_id());
    }
}
