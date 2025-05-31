/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class StudyRecord {

    private int studyRecordID;
    private Class classID;
    private Kindergartner kinder;
    private int studyYear;

    public StudyRecord() {
    }

    public StudyRecord(int studyRecordID, Class classID, Kindergartner kinder, int studyYear) {
        this.studyRecordID = studyRecordID;
        this.classID = classID;
        this.kinder = kinder;
        this.studyYear = studyYear;
    }

    public int getStudyRecordID() {
        return studyRecordID;
    }

    public void setStudyRecordID(int studyRecordID) {
        this.studyRecordID = studyRecordID;
    }

    public Class getClassID() {
        return classID;
    }

    public void setClassID(Class classID) {
        this.classID = classID;
    }

    public Kindergartner getKinder() {
        return kinder;
    }

    public void setKinder(Kindergartner kinder) {
        this.kinder = kinder;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

}
