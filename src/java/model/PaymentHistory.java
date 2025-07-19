/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACE
 */
public class PaymentHistory {
    private int paymentId;
    private int tuitionId;
    private int parentId;  // hoặc accountId nếu bạn đặt tên theo bảng Account
    private double amount;
    private java.sql.Timestamp paymentTime;
    private String transactionCode;
    private String status;

    public PaymentHistory() {
    }

    public PaymentHistory(int tuitionId, int parentId, double amount, String transactionCode, String status) {
        this.tuitionId = tuitionId;
        this.parentId = parentId;
        this.amount = amount;
        this.transactionCode = transactionCode;
        this.status = status;
        // paymentId và paymentTime có thể được sinh tự động từ DB
    }

    // --- Getter và Setter ---
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getTuitionId() {
        return tuitionId;
    }

    public void setTuitionId(int tuitionId) {
        this.tuitionId = tuitionId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public java.sql.Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(java.sql.Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentHistory{" + "paymentId=" + paymentId + ", tuitionId=" + tuitionId + ", parentId=" + parentId + ", amount=" + amount + ", paymentTime=" + paymentTime + ", transactionCode=" + transactionCode + ", status=" + status + '}';
    }
    
}
