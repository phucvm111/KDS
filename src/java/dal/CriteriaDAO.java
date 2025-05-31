/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Criteria;

/**
 *
 * @author win
 */
public class CriteriaDAO extends DBContext {

    private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;

    public ArrayList<Criteria> getAllCriteria() {
        ArrayList<Criteria> criterias = new ArrayList<>();
        try {
            String sql = "SELECT [criteria_id]\n"
                    + "      ,[content]\n"
                    + "  FROM [criteria]";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Criteria cr = new Criteria();

                cr.setCriteria_id(rs.getInt("criteria_id"));
                cr.setContent(rs.getString("content"));
                criterias.add(cr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CriteriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CriteriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return criterias;
    }

    public void insertCriteria(Criteria criteria) {
        try {
            String sql = "INSERT INTO [criteria]\n"
                    + "           ([content]         \n"
                    + "     VALUES\n"
                    + "           (?)";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, criteria.getContent());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CriteriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CriteriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCriteria(Criteria criteria) {
        try {
            String sql = "UPDATE [criteria]\n"
                    + "   SET[content] = ?\n"
                    + " WHERE [criteria_id] = ?";
            connection = new DBContext().getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, criteria.getContent());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CriteriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CriteriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
