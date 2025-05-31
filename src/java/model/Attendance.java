/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Attendance {

    private Kindergartner kinder;
    private String check_date;
    private int status;
    private Account teacherAccount;

    public Attendance() {
    }

    public Attendance(Kindergartner kinder, String check_date, int status, Account teacherAccount) {
        this.kinder = kinder;
        this.check_date = check_date;
        this.status = status;
        this.teacherAccount = teacherAccount;
    }
    public Attendance(Kindergartner kinder, String check_date, Account teacherAccount) {
        this.kinder = kinder;
        this.check_date = check_date;
        this.teacherAccount = teacherAccount;
    }

    public Kindergartner getKinder() {
        return kinder;
    }

    public void setKinder(Kindergartner kinder) {
        this.kinder = kinder;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(Account teacherAccount) {
        this.teacherAccount = teacherAccount;
    }

    @Override
    public String toString() {
        return "Attendence{" + "kinder_id=" + kinder + ", check_date=" + check_date + ", status=" + status + ", teacherAccount=" + teacherAccount + '}';
    }

}
