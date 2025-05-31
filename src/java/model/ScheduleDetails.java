/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ScheduleDetails {

    private int classID;
    //hashmap date, list(Schedule)
    private LinkedHashMap<Date, List<Schedule>> scheduleMap;

    public ScheduleDetails() {
    }

    public ScheduleDetails(int classID, LinkedHashMap<Date, List<Schedule>> scheduleMap) {
        this.classID = classID;
        this.scheduleMap = scheduleMap;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public LinkedHashMap<Date, List<Schedule>> getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap(LinkedHashMap<Date, List<Schedule>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

}
