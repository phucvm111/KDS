/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vu Tuan Hai <HE176383>
 */
import java.util.Date;

public class FeedbackTeacher {

    private int feedbackId;   // feedback_id
    private int parentId;     // parent_id
    private int teacherId;    // teacher_id
    private String fbContent; // fb_content
    private float rating;     // rating
    private Date fbDate;      // fb_date

    // Constructor
    public FeedbackTeacher(int feedbackId, int parentId, int teacherId, String fbContent, float rating, Date fbDate) {
        this.feedbackId = feedbackId;
        this.parentId = parentId;
        this.teacherId = teacherId;
        this.fbContent = fbContent;
        this.rating = rating;
        this.fbDate = fbDate;
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getFbDate() {
        return fbDate;
    }

    public void setFbDate(Date fbDate) {
        this.fbDate = fbDate;
    }

    @Override
    public String toString() {
        return "FeedbackTeacher{"
                + "feedbackId=" + feedbackId
                + ", parentId=" + parentId
                + ", teacherId=" + teacherId
                + ", fbContent='" + fbContent + '\''
                + ", rating=" + rating
                + ", fbDate=" + fbDate
                + '}';
    }
}
