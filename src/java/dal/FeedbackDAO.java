package dal;

import model.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FeedbackDAO is responsible for performing CRUD operations on the Feedback
 * table.
 */
public class FeedbackDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    // Thêm một feedback mới vào cơ sở dữ liệu
    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (kid_id, teacher_id, fb_content, rating, takenDate) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedback.getKid_id());
            ps.setInt(2, feedback.getTeacher_id());
            ps.setString(3, feedback.getFb_content());
            ps.setDouble(4, feedback.getRating());
            ps.setString(5, feedback.getTakenDate());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    // Lấy tất cả các feedback từ cơ sở dữ liệu
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("kid_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("fb_content"),
                        rs.getDouble("rating"),
                        rs.getString("takenDate")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return feedbackList;
    }

    // Lấy feedback theo feedback_id
    public Feedback getFeedbackById(int feedbackId) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE feedback_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            rs = ps.executeQuery();

            if (rs.next()) {
                feedback = new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("kid_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("fb_content"),
                        rs.getDouble("rating"),
                        rs.getString("takenDate")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return feedback;
    }

    // Cập nhật feedback
    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE Feedback SET fb_content = ?, rating = ?, takenDate = ? WHERE feedback_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, feedback.getFb_content());
            ps.setDouble(2, feedback.getRating());
            ps.setString(3, feedback.getTakenDate());
            ps.setInt(4, feedback.getFeedback_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    // Xóa feedback theo feedback_id
    public boolean deleteFeedback(int feedbackId) {
        String sql = "DELETE FROM Feedback WHERE feedback_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    // Đóng tài nguyên
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
