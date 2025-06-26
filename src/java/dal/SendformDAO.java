/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Form;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import model.FormTyle;
import model.Kindergartner;

/**
 *
 * @author ACE
 */
public class SendformDAO {

    public List<Form> getAllForm() {
        List<Form> formList = new ArrayList<>();

        try {
            Connection connection = DBContext.getConnection();
            String sql = "SELECT * FROM Form";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            FormTyleDAO fd = new FormTyleDAO();
            AccountDAO acc = new AccountDAO();
            KindergartnerDAO kdd = new KindergartnerDAO();
            while (rs.next()) {
                Form form = new Form();
                form.setForm_id(rs.getInt("form_id"));
                FormTyle formtyple = fd.getFormTypeById(rs.getInt("form_type_id"));
                form.setFormstyle(formtyple);
                Account ac = acc.getAccountByID(rs.getInt("sender_id"));
                form.setAccount(ac);
                Kindergartner kd = kdd.getKinderById(rs.getInt("kinder_id"));
                form.setKindergartner(kd);
                form.setTitle(rs.getString("title"));
                form.setContent(rs.getString("content"));
                form.setDate_submitted(rs.getString("date_submitted"));
                form.setStatus(rs.getString("status"));
                form.setReply(rs.getString("reply"));
                formList.add(form);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formList;
    }

    public List<Form> getFormByParentId(int parentId) {
        List<Form> formList = new ArrayList<>();

        try {
            Connection connection = DBContext.getConnection();
            String sql = "SELECT * FROM Form WHERE sender_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, parentId);
            ResultSet rs = stmt.executeQuery();

            FormTyleDAO fd = new FormTyleDAO();
            AccountDAO acc = new AccountDAO();
            KindergartnerDAO kdd = new KindergartnerDAO();

            while (rs.next()) {
                Form form = new Form();
                form.setForm_id(rs.getInt("form_id"));

                FormTyle formtyple = fd.getFormTypeById(rs.getInt("form_type_id"));
                form.setFormstyle(formtyple);

                Account ac = acc.getAccountByID(rs.getInt("sender_id"));
                form.setAccount(ac);

                // Kiểm tra NULL ở cột kinder_id (nếu được phép null)
                int kinderId = rs.getInt("kinder_id");
                if (!rs.wasNull()) {
                    Kindergartner kd = kdd.getKinderById(kinderId);
                    form.setKindergartner(kd);
                } else {
                    form.setKindergartner(null);
                }

                form.setTitle(rs.getString("title"));
                form.setContent(rs.getString("content"));
                form.setDate_submitted(rs.getString("date_submitted"));
                form.setStatus(rs.getString("status"));
                form.setReply(rs.getString("reply"));

                formList.add(form);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formList;
    }

    public Form getFormById(int id) {
        Form form = null;
        try {
            Connection connection = DBContext.getConnection();
            String sql = "SELECT * FROM Form WHERE form_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            FormTyleDAO fd = new FormTyleDAO();
            AccountDAO acc = new AccountDAO();
            KindergartnerDAO kdd = new KindergartnerDAO();

            if (rs.next()) {
                form = new Form();
                form.setForm_id(rs.getInt("form_id"));
                form.setFormstyle(fd.getFormTypeById(rs.getInt("form_type_id")));
                form.setAccount(acc.getAccountByID(rs.getInt("sender_id")));
                form.setKindergartner(kdd.getKinderById(rs.getInt("kinder_id")));
                form.setTitle(rs.getString("title"));
                form.setContent(rs.getString("content"));
                form.setDate_submitted(rs.getString("date_submitted"));
                form.setStatus(rs.getString("status"));
                form.setReply(rs.getString("reply"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form;
    }

    public void insertForm(Form form) {
        String sql = "INSERT INTO Form (form_type_id, sender_id, kinder_id, title, content, date_submitted, status, reply) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, form.getFormstyle().getForm_type_id());
            ps.setInt(2, form.getAccount().getAccountID());
            ps.setInt(3, form.getKindergartner().getKinder_id());
            ps.setString(4, form.getTitle());
            ps.setString(5, form.getContent());
            ps.setString(6, form.getDate_submitted());
            ps.setString(7, form.getStatus());
            ps.setString(8, form.getReply());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateForm(Form form) {
        String sql = "UPDATE Form SET form_type_id=?, sender_id=?, kinder_id=?, title=?, content=?, date_submitted=?, status=?, reply=? WHERE form_id=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, form.getFormstyle().getForm_type_id());
            ps.setInt(2, form.getAccount().getAccountID());
            ps.setInt(3, form.getKindergartner().getKinder_id());
            ps.setString(4, form.getTitle());
            ps.setString(5, form.getContent());
            ps.setString(6, form.getDate_submitted());
            ps.setString(7, form.getStatus());
            ps.setString(8, form.getReply());
            ps.setInt(9, form.getForm_id());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateReplyAndStatus(Form form) {
        String sql = "UPDATE Form SET status = ?, reply = ? WHERE form_id = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, form.getStatus());
            ps.setString(2, form.getReply());
            ps.setInt(3, form.getForm_id());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Form> getUnrepliedForms() {
        List<Form> forms = new ArrayList<>();
        String sql = "SELECT * FROM Form WHERE reply IS NULL OR reply = ''";
       AccountDAO ac=new AccountDAO();
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Form form = new Form();
                form.setForm_id(rs.getInt("form_id"));
                form.setTitle(rs.getString("title"));
                form.setContent(rs.getString("content"));
                form.setDate_submitted(rs.getString("date_submitted"));
                form.setStatus(rs.getString("status"));
                form.setReply(rs.getString("reply"));
                Account sender = ac.getAccountByID(rs.getInt("sender_id"));
                form.setAccount(sender);
                form.setDate_submitted(rs.getString("date_submitted"));
                      
                forms.add(form);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return forms;
    }

    public void deleteForm(int id) {
        String sql = "DELETE FROM Form WHERE form_id = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendformDAO sd = new SendformDAO();
        List<Form> forms = sd.getFormByParentId(3);
        for (Form f : forms) {
            System.out.println(f);
        }
    }

}
