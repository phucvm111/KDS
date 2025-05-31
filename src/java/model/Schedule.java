/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Schedule {
    private int     schedule_id;
    private Class classs;
    private Activity activity;
    private Slot slot;
    private String  schedule_date;

    public Schedule() {
    }

    public Schedule(int schedule_id, Class classs, Activity activity, Slot slot, String schedule_date) {
        this.schedule_id = schedule_id;
        this.classs = classs;
        this.activity = activity;
        this.slot = slot;
        this.schedule_date = schedule_date;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Class getClasss() {
        return classs;
    }

    public void setClasss(Class classs) {
        this.classs = classs;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    
}
