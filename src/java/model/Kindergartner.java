/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Kindergartner {

    private int kinder_id;
    private Account parentAccount;
    private String first_name;
    private String last_name;
    private String dob;
    private boolean gender;
    private String img;

    public Kindergartner() {
    }

    public Kindergartner(int kinder_id, Account parentAccount, String first_name, String last_name, String dob, boolean gender, String img) {
        this.kinder_id = kinder_id;
        this.parentAccount = parentAccount;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.img = img;
    }
    
    

    public int getKinder_id() {
        return kinder_id;
    }

    public void setKinder_id(int kinder_id) {
        this.kinder_id = kinder_id;
    }

    public Account getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(Account parentAccount) {
        this.parentAccount = parentAccount;
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
    
    public String getFullName() {
        return first_name + " " + last_name;
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

    @Override
    public String toString() {
        return "Kindergartner{" + "kinder_id=" + kinder_id + ", parentAccount=" + parentAccount + ", first_name=" + first_name + ", last_name=" + last_name + ", dob=" + dob + ", gender=" + gender + ", img=" + img + '}';
    }

}
