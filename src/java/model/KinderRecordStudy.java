/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author win
 */
public class KinderRecordStudy {
    private Kindergartner kinder;
    private StudyRecord studyRecord;

    public KinderRecordStudy() {
    }

    public KinderRecordStudy(Kindergartner kinder, StudyRecord studyRecord) {
        this.kinder = kinder;
        this.studyRecord = studyRecord;
    }

    public Kindergartner getKinder() {
        return kinder;
    }

    public void setKinder(Kindergartner kinder) {
        this.kinder = kinder;
    }

    public StudyRecord getStudyRecord() {
        return studyRecord;
    }

    public void setStudyRecord(StudyRecord studyRecord) {
        this.studyRecord = studyRecord;
    }

    @Override
    public String toString() {
        return "KinderRecordStudy{" + "kinder=" + kinder + ", studyRecord=" + studyRecord + '}';
    }
    
    
}
