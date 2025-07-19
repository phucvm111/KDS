/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACE
 */
public class TutitionFree {
    private int tuition_id;
    private Kindergartner kinder;
    private double amount;
    private String due_date;
    private String status;

    public TutitionFree() {
    }

    public TutitionFree(int tutition_id, Kindergartner kinder, double amount, String due_date, String status) {
        this.tuition_id = tutition_id;
        this.kinder = kinder;
        this.amount = amount;
        this.due_date = due_date;
        this.status = status;
    }

    public int getTuition_id() {
        return tuition_id;
    }

    public void setTutition_id(int tutition_id) {
        this.tuition_id = tutition_id;
    }

    public Kindergartner getKinder() {
        return kinder;
    }

    public void setKinder(Kindergartner kinder) {
        this.kinder = kinder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TutitionFree{" + "tutition_id=" + tuition_id + ", kinder=" + kinder + ", amount=" + amount + ", due_date=" + due_date + ", status=" + status + '}';
    }

   
}
