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
    private int id;
    private String teacherName;
    private LocalDate startDate; 
    private LocalDate endDate;   
    private String leaveReason;
    private String leaveType;
    private String status; 
    private LocalDate requestDate; // Ngày gửi đơn
    private int numberOfDays; // Số ngày nghỉ

    public LeaveRequest(int id, String teacherName, LocalDate startDate, LocalDate endDate, String leaveReason, String leaveType, String status, LocalDate requestDate, int numberOfDays) {
        this.id = id;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveReason = leaveReason;
        this.leaveType = leaveType;
        this.status = status;
        this.requestDate = requestDate;
        this.numberOfDays = numberOfDays;
    }

    public LeaveRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" + "id=" + id + ", teacherName=" + teacherName + ", startDate=" + startDate + ", endDate=" + endDate + ", leaveReason=" + leaveReason + ", leaveType=" + leaveType + ", status=" + status + ", requestDate=" + requestDate + ", numberOfDays=" + numberOfDays + '}';
    }
    
}
