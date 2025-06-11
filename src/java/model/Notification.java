package model;

import java.util.Date;

public class Notification {

    private int notificationId;
    private String content;
    private Date notificationDate;
    private boolean isRead;
    private String notificationType;
    private String reason;

    private Account sender;
    private Account receiver;
    private Kindergartner kindergartner;

    // Constructors (optional)
    public Notification() {
    }

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Kindergartner getKindergartner() {
        return kindergartner;
    }

    public void setKindergartner(Kindergartner kindergartner) {
        this.kindergartner = kindergartner;
    }
}
