package model;

import java.util.Date;

public class ParentMeeting {
    private int meetingId;
    private int classId;
    private int teacherId;
    private Date meetingDate;
    private String topic;
    private String notes;
    private Date createdAt;
    private String status;

    // Thêm để hiển thị
    private String className;
    private String teacherName;

    public ParentMeeting() {
    }

    public ParentMeeting(int meetingId, int classId, int teacherId, Date meetingDate, String topic, String notes, Date createdAt, String status, String className, String teacherName) {
        this.meetingId = meetingId;
        this.classId = classId;
        this.teacherId = teacherId;
        this.meetingDate = meetingDate;
        this.topic = topic;
        this.notes = notes;
        this.createdAt = createdAt;
        this.status = status;
        this.className = className;
        this.teacherName = teacherName;
    }

    

    // Getters và Setters
    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
