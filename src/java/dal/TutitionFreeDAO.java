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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Kindergartner;
import model.Role;
import model.TutitionFree;

/**
 *
 * @author ACE
 */
public class TutitionFreeDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<TutitionFree> getAlltutition() {
        ArrayList<TutitionFree> tutitionFrees = new ArrayList<>();
        KindergartnerDAO kd = new KindergartnerDAO();
        try {
            String sql = """
                       select * from TuitionFee
                       """;
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                TutitionFree tutitionFree = new TutitionFree();
                tutitionFree.setTutition_id(rs.getInt("tuition_id"));
                Kindergartner kinder = kd.getKinderById(rs.getInt("kinder_id"));
                tutitionFree.setKinder(kinder);
                tutitionFree.setAmount(rs.getDouble("amount"));
                tutitionFree.setDue_date(rs.getString("due_date"));
                tutitionFree.setStatus(rs.getString("status"));
                tutitionFrees.add(tutitionFree);

            }
        } catch (Exception e) {
        }
        return tutitionFrees;
    }
    
    public void addTuition(TutitionFree tuitionFree) {
    try {
        String sql = """
            INSERT INTO TuitionFee (kinder_id, amount, due_date, status)
            VALUES (?, ?, ?, ?)
        """;
        connection = new DBContext().getConnection();
        ps = connection.prepareStatement(sql);

        ps.setInt(1, tuitionFree.getKinder().getKinder_id()); 
        ps.setDouble(2, tuitionFree.getAmount());
        ps.setString(3, tuitionFree.getDue_date()); 
        ps.setString(4, tuitionFree.getStatus());

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); // để dễ debug khi có lỗi
    } finally {
        // đóng tài nguyên nếu cần
        try {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


    public List<TutitionFree> getAllChuaNop() {
        ArrayList<TutitionFree> tutitionFrees = new ArrayList<>();
        KindergartnerDAO kd = new KindergartnerDAO();
        try {
            String sql = """
                         SELECT * FROM TuitionFee
                         WHERE status = N'Chưa nộp'
                         """;
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TutitionFree tutitionFree = new TutitionFree();
                tutitionFree.setTutition_id(rs.getInt("tuition_id"));
                Kindergartner kinder = kd.getKinderById(rs.getInt("kinder_id"));
                tutitionFree.setKinder(kinder);
                tutitionFree.setAmount(rs.getDouble("amount"));
                tutitionFree.setDue_date(rs.getString("due_date"));
                tutitionFree.setStatus(rs.getString("status"));
                tutitionFrees.add(tutitionFree);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Nên log lỗi để debug dễ hơn
        }
        return tutitionFrees;
    }

    public TutitionFree getTutitionById(int tuitionId) {
        TutitionFree tutitionFree = null;
        KindergartnerDAO kd = new KindergartnerDAO();

        try {
            String sql = "SELECT * FROM TuitionFee WHERE tuition_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, tuitionId);
            rs = ps.executeQuery();

            if (rs.next()) {
                tutitionFree = new TutitionFree();
                tutitionFree.setTutition_id(rs.getInt("tuition_id"));

                Kindergartner kinder = kd.getKinderById(rs.getInt("kinder_id"));
                tutitionFree.setKinder(kinder);

                tutitionFree.setAmount(rs.getDouble("amount"));
                tutitionFree.setDue_date(rs.getString("due_date"));
                tutitionFree.setStatus(rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace(); // Debug nếu có lỗi
        }

        return tutitionFree;
    }

    public TutitionFree getTuitionFreeByKinderId(int kinderId) {
        KindergartnerDAO kd = new KindergartnerDAO();

        try {
            String sql = """
            SELECT * FROM TuitionFee WHERE kinder_id = ?
        """;

            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kinderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                TutitionFree tf = new TutitionFree();
                tf.setTutition_id(rs.getInt("tuition_id"));
                tf.setAmount(rs.getDouble("amount"));
                tf.setDue_date(rs.getString("due_date"));
                tf.setStatus(rs.getString("status"));

                // Lấy thông tin trẻ
                Kindergartner kinder = kd.getKinderById(kinderId);
                tf.setKinder(kinder);

                return tf; // ✔ Trả về 1 đối tượng
            }

        } catch (Exception e) {
            e.printStackTrace(); // debug nếu có lỗi
        }

        return null; // ✔ Không có thì trả null
    }
public void updateStatusById(int tuitionId) {
    try {
        String sql = """
            UPDATE TuitionFee 
            SET status = N'Đã nộp'
            WHERE tuition_id = ?
        """;

        connection = new DBContext().getConnection();
        ps = connection.prepareStatement(sql);
        ps.setInt(1, tuitionId);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Đóng kết nối nếu bạn có phương thức đóng riêng
    }
}

    public TutitionFree getTuitionFreeByKinderIdChuanop(int kinderId) {
        KindergartnerDAO kd = new KindergartnerDAO();

        try {
            String sql = """
            SELECT * FROM TuitionFee 
            WHERE kinder_id = ? AND status = N'Chưa nộp'
        """;

            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kinderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                TutitionFree tf = new TutitionFree();
                tf.setTutition_id(rs.getInt("tuition_id"));
                tf.setAmount(rs.getDouble("amount"));
                tf.setDue_date(rs.getString("due_date"));
                tf.setStatus(rs.getString("status"));

                // Lấy thông tin trẻ
                Kindergartner kinder = kd.getKinderById(kinderId);
                tf.setKinder(kinder);

                return tf;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

     public static void main(String[] args) {
        TutitionFreeDAO dao = new TutitionFreeDAO();

        // Tạo đối tượng Kindergartner và set ID (giả sử ID 1 tồn tại trong DB)
        Kindergartner kinder = new Kindergartner();
        kinder.setKinder_id(3); // đổi sang setId() nếu bạn đặt tên là id

        // Tạo học phí mới
        TutitionFree tf = new TutitionFree();
        tf.setKinder(kinder);
        tf.setAmount(1500000); // số tiền học phí
        tf.setDue_date("2025-08-10"); // định dạng yyyy-MM-dd (tuỳ theo DB)
        tf.setStatus("Chưa đóng"); // hoặc "Đã đóng"

        // Gọi hàm thêm
        dao.addTuition(tf);

        System.out.println("Đã thêm học phí mới cho bé có ID = " + kinder.getKinder_id());
    }
}
