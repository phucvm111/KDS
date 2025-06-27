/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACE
 */
public class FormTyle {
    private int form_type_id;
    private String type_name;

    public FormTyle() {
    }

    public FormTyle(int form_type_id, String type_name) {
        this.form_type_id = form_type_id;
        this.type_name = type_name;
    }

    public int getForm_type_id() {
        return form_type_id;
    }

    public void setForm_type_id(int form_type_id) {
        this.form_type_id = form_type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "FormStyle{" + "form_type_id=" + form_type_id + ", type_name=" + type_name + '}';
    }
    
}
