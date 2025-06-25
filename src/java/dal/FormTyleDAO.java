package dal;

import dal.DBContext;
import java.sql.*;
import java.util.*;
import model.*;

public class FormTyleDAO {

    // 1. Lấy tất cả loại đơn
    public List<FormTyle> getAllFormTypes() {
        List<FormTyle> list = new ArrayList<>();
        String sql = "SELECT * FROM FormType";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FormTyle ft = new FormTyle();
                ft.setForm_type_id(rs.getInt("form_type_id"));
                ft.setType_name(rs.getString("type_name"));
                list.add(ft);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public FormTyle getFormTypeById(int id) {
        String sql = "SELECT * FROM FormType WHERE form_type_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new FormTyle(rs.getInt("form_type_id"), rs.getString("type_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void insertFormType(FormTyle ft) {
        String sql = "INSERT INTO FormType (type_name) VALUES (?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, ft.getType_name());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public void updateFormType(FormTyle ft) {
        String sql = "UPDATE FormType SET type_name = ? WHERE form_type_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ft.getType_name());
            ps.setInt(2, ft.getForm_type_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public void deleteFormType(int id) {
        String sql = "DELETE FROM FormType WHERE form_type_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
