package dal;

import model.LeaveType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LeaveTypeDAO extends DBContext {

    public List<LeaveType> getAllLeaveTypes() {
        List<LeaveType> leaveTypes = new ArrayList<>();
        String sql = "SELECT leave_type_id, type_name, description, has_entitlement, is_accumulative, allow_half_day " +
                     "FROM LeaveType"; // <<-- Cập nhật câu lệnh SELECT để lấy tất cả các cột

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // Lấy kết nối từ DBContext
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LeaveType type = new LeaveType();
                type.setLeaveTypeId(rs.getInt("leave_type_id"));
                type.setTypeName(rs.getString("type_name"));
                type.setDescription(rs.getNString("description")); // Dùng getNString cho NVARCHAR(MAX)
                type.setHasEntitlement(rs.getBoolean("has_entitlement")); // <<-- Đọc kiểu BIT thành boolean
                type.setIsAccumulative(rs.getBoolean("is_accumulative")); // <<-- Đọc kiểu BIT thành boolean
                type.setAllowHalfDay(rs.getBoolean("allow_half_day"));   // <<-- Đọc kiểu BIT thành boolean
                leaveTypes.add(type);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách loại nghỉ phép: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) closeConnection(conn); // Gọi phương thức closeConnection của DBContext
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return leaveTypes;
    }

    // Bạn có thể thêm các phương thức khác như getLeaveTypeById nếu cần
    public LeaveType getLeaveTypeById(int id) {
        LeaveType type = null;
        String sql = "SELECT leave_type_id, type_name, description, has_entitlement, is_accumulative, allow_half_day " +
                     "FROM LeaveType WHERE leave_type_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                type = new LeaveType();
                type.setLeaveTypeId(rs.getInt("leave_type_id"));
                type.setTypeName(rs.getString("type_name"));
                type.setDescription(rs.getNString("description"));
                type.setHasEntitlement(rs.getBoolean("has_entitlement"));
                type.setIsAccumulative(rs.getBoolean("is_accumulative"));
                type.setAllowHalfDay(rs.getBoolean("allow_half_day"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy loại nghỉ phép theo ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return type;
    }

    // Các phương thức thêm, sửa, xóa LeaveType
    // Ví dụ thêm mới:
    public boolean addLeaveType(LeaveType type) {
        String sql = "INSERT INTO LeaveType (type_name, description, has_entitlement, is_accumulative, allow_half_day) " +
                     "VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setNString(1, type.getTypeName());
            ps.setNString(2, type.getDescription());
            ps.setBoolean(3, type.isHasEntitlement());
            ps.setBoolean(4, type.isIsAccumulative());
            ps.setBoolean(5, type.isAllowHalfDay());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) success = true;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm loại nghỉ phép: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) closeConnection(conn);
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return success;
    }
}
//