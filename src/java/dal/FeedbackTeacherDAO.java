/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FeedbackTeacher;

/**
 *
 * @author Vu Tuan Hai <HE176383>
 */
public class FeedbackTeacherDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    // Lấy tất cả các feedback teacher
    public List<FeedbackTeacher> getAllFeedback() {
        List<FeedbackTeacher> feedbackList = new ArrayList<>();
        try {
            String sql = "SELECT feedback_id, parent_id, teacher_id, fb_content, rating, fb_date FROM FeedbackTeacher";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                FeedbackTeacher feedback = new FeedbackTeacher(
                        rs.getInt("feedback_id"),
                        rs.getInt("parent_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("fb_content"),
                        rs.getFloat("rating"),
                        rs.getDate("fb_date")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return feedbackList;
    }

    // Thêm feedback teacher
    public boolean addFeedback(FeedbackTeacher feedback) {
        String sql = "INSERT INTO FeedbackTeacher (parent_id, teacher_id, fb_content, rating, fb_date) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedback.getParentId());
            ps.setInt(2, feedback.getTeacherId());
            ps.setString(3, feedback.getFbContent());
            ps.setFloat(4, feedback.getRating());
            ps.setDate(5, new java.sql.Date(feedback.getFbDate().getTime()));

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    // Cập nhật feedback teacher
    public boolean updateFeedback(FeedbackTeacher feedback) {
        String sql = "UPDATE FeedbackTeacher SET parent_id = ?, teacher_id = ?, fb_content = ?, rating = ?, fb_date = ? WHERE feedback_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedback.getParentId());
            ps.setInt(2, feedback.getTeacherId());
            ps.setString(3, feedback.getFbContent());
            ps.setFloat(4, feedback.getRating());
            ps.setDate(5, new java.sql.Date(feedback.getFbDate().getTime()));
            ps.setInt(6, feedback.getFeedbackId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    // Xóa feedback teacher
    public boolean deleteFeedback(int feedbackId) {
        String sql = "DELETE FROM FeedbackTeacher WHERE feedback_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    // Lấy tất cả feedback theo teacherId
    public List<FeedbackTeacher> getFeedbackByTeacherId(int teacherId) {
        List<FeedbackTeacher> feedbackList = new ArrayList<>();
        try {
            String sql = "SELECT feedback_id, parent_id, teacher_id, fb_content, rating, fb_date "
                    + "FROM FeedbackTeacher WHERE teacher_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, teacherId);  // Đặt giá trị của teacherId vào câu lệnh SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                FeedbackTeacher feedback = new FeedbackTeacher(
                        rs.getInt("feedback_id"),
                        rs.getInt("parent_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("fb_content"),
                        rs.getFloat("rating"),
                        rs.getDate("fb_date")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return feedbackList;
    }
// Retrieve all feedbacks for a specific teacher

    public List<FeedbackTeacher> getFeedbackByParent( int parentId) {
        List<FeedbackTeacher> feedbackList = new ArrayList<>();
        String sql = "SELECT feedback_id, parent_id, teacher_id, fb_content, rating, fb_date "
                + "FROM FeedbackTeacher WHERE parent_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, parentId);
            rs = ps.executeQuery();

            while (rs.next()) {
                FeedbackTeacher feedback = new FeedbackTeacher(
                        rs.getInt("feedback_id"),
                        rs.getInt("parent_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("fb_content"),
                        rs.getFloat("rating"),
                        rs.getDate("fb_date")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbackList;
    }
// Get feedback by feedback ID

    public FeedbackTeacher getFeedbackById(int feedbackId) {
        String sql = "SELECT feedback_id, parent_id, teacher_id, fb_content, rating, fb_date FROM FeedbackTeacher WHERE feedback_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new FeedbackTeacher(
                        rs.getInt("feedback_id"),
                        rs.getInt("parent_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("fb_content"),
                        rs.getFloat("rating"),
                        rs.getDate("fb_date")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    // Close resources
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

    public static void main(String[] args) {
        FeedbackTeacherDAO feedbackDAO = new FeedbackTeacherDAO();

        // Giả sử bạn muốn lấy feedback của giáo viên có teacherId là 2
        int teacherId = 2;

        // Lấy tất cả các feedback của giáo viên có teacherId = 2
        List<FeedbackTeacher> feedbackList = feedbackDAO.getFeedbackByTeacherId(teacherId);

        // In ra kết quả
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback found for teacher with ID: " + teacherId);
        } else {
            System.out.println("Feedback for teacher ID " + teacherId + ":");
            for (FeedbackTeacher feedback : feedbackList) {
                System.out.println(feedback);  // In ra thông tin feedback
            }
        }
    }

}
