/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACE
 */
public class Form {
    private int form_id;
    private FormTyle formstyle;
    private Account account;
    private Kindergartner kindergartner;
    private String title;
    private String content;
    private String date_submitted;
    private String status;
    private String reply;

    public Form() {
    }

    public Form(int form_id, FormTyle formstyle, Account account, Kindergartner kindergartner, String title, String content, String date_submitted, String status, String reply) {
        this.form_id = form_id;
        this.formstyle = formstyle;
        this.account = account;
        this.kindergartner = kindergartner;
        this.title = title;
        this.content = content;
        this.date_submitted = date_submitted;
        this.status = status;
        this.reply = reply;
    }

    public Form(FormTyle formstyle, Account account, Kindergartner kindergartner, String title, String content, String date_submitted, String status, String reply) {
        this.formstyle = formstyle;
        this.account = account;
        this.kindergartner = kindergartner;
        this.title = title;
        this.content = content;
        this.date_submitted = date_submitted;
        this.status = status;
        this.reply = reply;
    }
    

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public FormTyle getFormstyle() {
        return formstyle;
    }

    public void setFormstyle(FormTyle formstyle) {
        this.formstyle = formstyle;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Kindergartner getKindergartner() {
        return kindergartner;
    }

    public void setKindergartner(Kindergartner kindergartner) {
        this.kindergartner = kindergartner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_submitted() {
        return date_submitted;
    }

    public void setDate_submitted(String date_submitted) {
        this.date_submitted = date_submitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Form{" + "form_id=" + form_id + ", formstyle=" + formstyle + ", account=" + account + ", kindergartner=" + kindergartner + ", title=" + title + ", content=" + content + ", date_submitted=" + date_submitted + ", status=" + status + ", reply=" + reply + '}';
    }

   
    }
