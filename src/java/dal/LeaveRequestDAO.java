/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.getConnection;
import model.LeaveRequest;
import model.LeaveType; // Đảm bảo import LeaveType
import model.Account;  // Đảm bảo import Account

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Kế thừa DBContext
public class LeaveRequestDAO extends DBContext {

    public LeaveRequestDAO() {
        // Constructor rỗng
    }

    // --- Phương thức thêm mới đơn xin nghỉ phép ---
    public boolean addLeaveRequest(LeaveRequest request) {
        String sql = "INSERT INTO LeaveRequest (requester_id, leave_type_id, start_date, end_date, total_days, reason, status, request_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, request.getRequesterId());
            ps.setInt(2, request.getLeaveTypeId());
            ps.setDate(3, java.sql.Date.valueOf(request.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(request.getEndDate()));
            ps.setDouble(5, request.getTotalDays());
            ps.setNString(6, request.getReason());
            ps.setString(7, request.getStatus());
            // Đảm bảo request.getRequestDate() không null và đã được set trước khi gọi
            ps.setDate(8, java.sql.Date.valueOf(request.getRequestDate()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi thêm đơn xin nghỉ phép: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    // --- Phương thức lấy tất cả đơn xin nghỉ phép (có JOIN để lấy teacherName và leaveTypeName) ---
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT lr.request_id, lr.requester_id, a.first_name, a.last_name, lr.leave_type_id, lt.type_name, "
                + "lr.start_date, lr.end_date, lr.total_days, lr.reason, lr.status, lr.request_date "
                + "FROM LeaveRequest lr "
                + "JOIN Account a ON lr.requester_id = a.account_id "
                + "JOIN LeaveType lt ON lr.leave_type_id = lt.leave_type_id "
                + "ORDER BY lr.request_date DESC";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LeaveRequest request = new LeaveRequest();
                request.setRequestId(rs.getInt("request_id"));
                request.setRequesterId(rs.getInt("requester_id"));
                // Lấy first_name và last_name để tạo teacherName
                String teacherFirstName = rs.getString("first_name");
                String teacherLastName = rs.getString("last_name");
                request.setTeacherName(teacherFirstName + " " + teacherLastName);

                LeaveType leaveType = new LeaveType();
                leaveType.setLeaveTypeId(rs.getInt("leave_type_id"));
                leaveType.setTypeName(rs.getString("type_name"));
                request.setLeaveType(leaveType); // Đặt đối tượng LeaveType đã tạo
                request.setLeaveTypeId(leaveType.getLeaveTypeId()); // Cập nhật LeaveTypeId trong LeaveRequest

                // Xử lý request_date (nếu cột là DATETIME)
                if (rs.getTimestamp("request_date") != null) {
                    request.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime().toLocalDate());
                } else {
                    request.setRequestDate(null); // Hoặc giá trị mặc định nếu null
                }
                request.setStartDate(rs.getDate("start_date").toLocalDate());
                request.setEndDate(rs.getDate("end_date").toLocalDate());
                request.setTotalDays(rs.getDouble("total_days"));
                request.setReason(rs.getNString("reason"));
                request.setStatus(rs.getString("status"));

                leaveRequests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi lấy tất cả đơn xin nghỉ phép: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return leaveRequests;
    }

    // --- Phương thức lấy lịch sử yêu cầu nghỉ phép của một giáo viên cụ thể ---
    public List<LeaveRequest> getLeaveRequestsByRequesterId(int requesterId) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT lr.request_id, lr.requester_id, a.first_name, a.last_name, lr.leave_type_id, lt.type_name, "
                + "lr.start_date, lr.end_date, lr.total_days, lr.reason, lr.status, lr.request_date "
                + "FROM LeaveRequest lr "
                + "JOIN Account a ON lr.requester_id = a.account_id "
                + "JOIN LeaveType lt ON lr.leave_type_id = lt.leave_type_id "
                + "WHERE lr.requester_id = ? "
                + "ORDER BY lr.request_date DESC";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, requesterId);
            rs = ps.executeQuery();

            while (rs.next()) {
                LeaveRequest request = new LeaveRequest();
                request.setRequestId(rs.getInt("request_id"));
                request.setRequesterId(rs.getInt("requester_id"));
                String teacherFirstName = rs.getString("first_name");
                String teacherLastName = rs.getString("last_name");
                request.setTeacherName(teacherFirstName + " " + teacherLastName);

                LeaveType leaveType = new LeaveType();
                leaveType.setLeaveTypeId(rs.getInt("leave_type_id"));
                leaveType.setTypeName(rs.getString("type_name"));
                request.setLeaveType(leaveType);
                request.setLeaveTypeId(leaveType.getLeaveTypeId());

                if (rs.getTimestamp("request_date") != null) {
                    request.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime().toLocalDate());
                } else {
                    request.setRequestDate(null);
                }
                request.setStartDate(rs.getDate("start_date").toLocalDate());
                request.setEndDate(rs.getDate("end_date").toLocalDate());
                request.setTotalDays(rs.getDouble("total_days"));
                request.setReason(rs.getNString("reason"));
                request.setStatus(rs.getString("status"));

                leaveRequests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi lấy đơn xin nghỉ phép theo ID người yêu cầu: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return leaveRequests;
    }

    // --- Phương thức lấy một đơn xin nghỉ phép theo ID ---
    public LeaveRequest getLeaveRequestById(int requestId) {
    LeaveRequest request = null;
    // THAY ĐỔI CÂU SQL: Lấy first_name và last_name từ bảng Account
    String sql = "SELECT lr.request_id, lr.requester_id, a.first_name, a.last_name, lr.leave_type_id, lt.type_name, "
            + "lr.start_date, lr.end_date, lr.total_days, lr.reason, lr.status, lr.request_date "
            + "FROM LeaveRequest lr "
            + "JOIN Account a ON lr.requester_id = a.account_id "
            + "JOIN LeaveType lt ON lr.leave_type_id = lt.leave_type_id "
            + "WHERE lr.request_id = ?";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, requestId);
        rs = ps.executeQuery();

        if (rs.next()) {
            request = new LeaveRequest();
            request.setRequestId(rs.getInt("request_id"));
            request.setRequesterId(rs.getInt("requester_id"));

            // Lấy first_name và last_name, sau đó ghép để setTeacherName
            String teacherFirstName = rs.getString("first_name");
            String teacherLastName = rs.getString("last_name");
            request.setTeacherName(teacherFirstName + " " + teacherLastName);

            LeaveType leaveType = new LeaveType();
            leaveType.setLeaveTypeId(rs.getInt("leave_type_id"));
            leaveType.setTypeName(rs.getString("type_name"));
            request.setLeaveType(leaveType);
            // Bạn có thể giữ hoặc xóa dòng này nếu LeaveRequest chỉ cần đối tượng LeaveType
            request.setLeaveTypeId(leaveType.getLeaveTypeId()); 

            // SỬA ĐOẠN NÀY: Xử lý request_date có thể null
            if (rs.getTimestamp("request_date") != null) {
                request.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime().toLocalDate());
            } else {
                request.setRequestDate(null); // Hoặc giá trị mặc định phù hợp
            }

            request.setStartDate(rs.getDate("start_date").toLocalDate());
            request.setEndDate(rs.getDate("end_date").toLocalDate());
            request.setTotalDays(rs.getDouble("total_days"));
            request.setReason(rs.getNString("reason"));
            request.setStatus(rs.getString("status"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Lỗi khi lấy đơn xin nghỉ phép theo Request ID: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                closeConnection(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return request;
}

    // --- Phương thức cập nhật trạng thái đơn xin nghỉ phép ---
    public boolean updateLeaveRequestStatus(int requestId, String newStatus) {
        String sql = "UPDATE LeaveRequest SET status = ? WHERE request_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi cập nhật trạng thái đơn xin nghỉ phép: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    // --- Phương thức cập nhật toàn bộ đơn xin nghỉ phép (Cẩn thận khi cho phép user chỉnh sửa đơn đã gửi) ---
    public boolean updateLeaveRequest(LeaveRequest request) {
        String sql = "UPDATE LeaveRequest SET requester_id = ?, leave_type_id = ?, start_date = ?, end_date = ?, "
                + "total_days = ?, reason = ?, status = ?, request_date = ? "
                + "WHERE request_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, request.getRequesterId());
            ps.setInt(2, request.getLeaveTypeId());
            ps.setDate(3, java.sql.Date.valueOf(request.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(request.getEndDate()));
            ps.setDouble(5, request.getTotalDays());
            ps.setNString(6, request.getReason());
            ps.setString(7, request.getStatus());
            ps.setDate(8, java.sql.Date.valueOf(request.getRequestDate()));
            ps.setInt(9, request.getRequestId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi cập nhật đơn xin nghỉ phép: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    // --- Phương thức xóa đơn xin nghỉ phép (Cẩn thận khi dùng trong thực tế) ---
    public boolean deleteLeaveRequest(int requestId) {
        String sql = "DELETE FROM LeaveRequest WHERE request_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, requestId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi xóa đơn xin nghỉ phép: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}
