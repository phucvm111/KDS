/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class LeaveRequest {
    private int requestId;
    private int requesterId;
    private int leaveTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalDays;
    private String reason;
    private String status;
    private LocalDate requestDate;
    private String teacherName;
    private String leaveTypeName;
    private LeaveType leaveType; 

    public LeaveRequest() {
    }

    // 2. Constructor dùng để THÊM MỚI (chưa có request_id, status mặc định 'Pending', request_date là LocalDate.now())
    public LeaveRequest(int requesterId, int leaveTypeId, LocalDate startDate, LocalDate endDate,
                        double totalDays, String reason) {
        this.requesterId = requesterId;
        this.leaveTypeId = leaveTypeId; // Lưu leaveTypeId trực tiếp
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.reason = reason;
        this.status = "Pending";
        this.requestDate = LocalDate.now();
        // Không khởi tạo leaveType ở đây vì khi thêm mới, thường chỉ có ID, tên sẽ được lấy sau từ DB
        this.leaveType = null; // Đảm bảo là null ban đầu
    }

    // 3. Constructor dùng để ĐỌC DỮ LIỆU TỪ DATABASE (có tất cả các cột của LeaveRequest)
    // Constructor này thường không được dùng trực tiếp khi có JOIN, nhưng vẫn hữu ích
    // nếu bạn chỉ muốn load dữ liệu cơ bản của LeaveRequest.
    public LeaveRequest(int requestId, int requesterId, int leaveTypeId, LocalDate startDate, LocalDate endDate,
                        double totalDays, String reason, String status, LocalDate requestDate) {
        this.requestId = requestId;
        this.requesterId = requesterId;
        this.leaveTypeId = leaveTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.reason = reason;
        this.status = status;
        this.requestDate = requestDate;
        this.leaveType = null; // Khởi tạo là null, sẽ được set sau bởi DAO nếu có JOIN
    }

    // 4. Constructor dùng để ĐỌC DỮ LIỆU TỪ DATABASE CÓ JOIN (bao gồm teacherName và leaveTypeName)
    // Constructor này sẽ KHÔNG còn được sử dụng trực tiếp trong DAO sau khi bạn dùng constructor rỗng và setter cho LeaveType.
    // Tôi vẫn giữ nó ở đây nhưng không khuyến khích dùng trực tiếp trong DAO để tránh lỗi thứ tự.
    public LeaveRequest(int requestId, int requesterId, String teacherName, int leaveTypeId, String leaveTypeName,
                        LocalDate startDate, LocalDate endDate, double totalDays, String reason, String status, LocalDate requestDate) {
        this(requestId, requesterId, leaveTypeId, startDate, endDate, totalDays, reason, status, requestDate); // Gọi constructor 3
        this.teacherName = teacherName;
        this.leaveTypeName = leaveTypeName;

        // Khởi tạo LeaveType object ở đây để nó có thể được sử dụng
        this.leaveType = new LeaveType();
        this.leaveType.setLeaveTypeId(leaveTypeId);
        this.leaveType.setTypeName(leaveTypeName);
        // Nếu LeaveType có thêm các thuộc tính khác như description, hasEntitlement,
        // bạn cần truyền chúng vào constructor này và set ở đây.
    }


    // --- Getters and Setters ---
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(double totalDays) {
        this.totalDays = totalDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    // GETTER VÀ SETTER MỚI CHO LeaveType
    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
        // Tùy chọn: Cập nhật leaveTypeId và leaveTypeName khi set LeaveType object
        // Điều này giúp giữ tính nhất quán giữa LeaveType object và các thuộc tính riêng lẻ
        if (leaveType != null) {
            this.leaveTypeId = leaveType.getLeaveTypeId();
            this.leaveTypeName = leaveType.getTypeName();
        } else {
            this.leaveTypeId = 0; // Hoặc một giá trị mặc định khác cho ID
            this.leaveTypeName = null;
        }
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
               "requestId=" + requestId +
               ", requesterId=" + requesterId +
               ", leaveTypeId=" + leaveTypeId +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", totalDays=" + totalDays +
               ", reason='" + reason + '\'' +
               ", status='" + status + '\'' +
               ", requestDate=" + requestDate +
               ", teacherName='" + teacherName + '\'' +
               ", leaveTypeName='" + leaveTypeName + '\'' +
               ", leaveType=" + (leaveType != null ? leaveType.toString() : "null") + // In chi tiết LeaveType
               '}';
    }
}