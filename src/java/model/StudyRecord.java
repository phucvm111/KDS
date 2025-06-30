package model;

public class StudyRecord {

    private int record_id;
    private Class classID;
    private Kindergartner kinder;
    private int studyYear;
    private boolean isGraduated;
    private boolean isDroppedOut;

    public StudyRecord() {
    }

    public StudyRecord(int record_id, Class classID, Kindergartner kinder, int studyYear, boolean isGraduated, boolean isDroppedOut) {
        this.record_id = record_id;
        this.classID = classID;
        this.kinder = kinder;
        this.studyYear = studyYear;
        this.isGraduated = isGraduated;
        this.isDroppedOut = isDroppedOut;
    }

    public StudyRecord(int record_id, Class classID, Kindergartner kinder, int studyYear) {
        this.record_id = record_id;
        this.classID = classID;
        this.kinder = kinder;
        this.studyYear = studyYear;
        this.isGraduated = false;
        this.isDroppedOut = false;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
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

    public boolean isGraduated() {
        return isGraduated;
    }

    public boolean getIsGraduated() {   // thêm getter chuẩn cho JSP EL
        return isGraduated;
    }

    public void setGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public boolean isDroppedOut() {
        return isDroppedOut;
    }

    public boolean getIsDroppedOut() {  // thêm getter chuẩn cho JSP EL
        return isDroppedOut;
    }

    public void setDroppedOut(boolean isDroppedOut) {
        this.isDroppedOut = isDroppedOut;
    }
}
