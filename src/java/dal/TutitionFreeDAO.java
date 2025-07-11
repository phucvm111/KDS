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
        TutitionFreeDAO td = new TutitionFreeDAO();
        List<TutitionFree> tutitionFrees = td.getAlltutition();
        for (TutitionFree tt : tutitionFrees) {
            System.out.println(tt);
        }
    }
}
