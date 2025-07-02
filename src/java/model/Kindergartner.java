package model;

public class Kindergartner {

    private int kinder_id;
    private int parent_id;  // Sửa thành int parent_id thay vì Account parentAccount
    private String first_name;
    private String last_name;
    private String dob;
    private boolean gender;
    private String img;
    private int class_id;
    private String address;
    private String parentPhone;
    private String className; // Trường này không có trong database, chỉ để hiển thị

// Getter và setter cho className
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    // Constructor không tham số
    public Kindergartner() {
    }

    // Constructor đầy đủ tham số
    public Kindergartner(int kinder_id, int parent_id, String first_name, String last_name,
            String dob, boolean gender, String img, int class_id,
            String address, String parentPhone) {
        this.kinder_id = kinder_id;
        this.parent_id = parent_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.img = img;
        this.class_id = class_id;
        this.address = address;
        this.parentPhone = parentPhone;
    }

    // Getters và Setters
    public int getKinder_id() {
        return kinder_id;
    }

    public void setKinder_id(int kinder_id) {
        this.kinder_id = kinder_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    @Override
    public String toString() {
        return "Kindergartner{"
                + "kinder_id=" + kinder_id
                + ", parent_id=" + parent_id
                + ", first_name='" + first_name + '\''
                + ", last_name='" + last_name + '\''
                + ", dob='" + dob + '\''
                + ", gender=" + gender
                + ", img='" + img + '\''
                + ", class_id=" + class_id
                + ", address='" + address + '\''
                + ", parentPhone='" + parentPhone + '\''
                + '}';
    }

    

}
