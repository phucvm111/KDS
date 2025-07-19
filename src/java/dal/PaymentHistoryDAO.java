/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.PaymentHistory;

/**
 *
 * @author ACE
 */
public class PaymentHistoryDAO {

    public void insert(PaymentHistory ph) {
        String sql = "INSERT INTO PaymentHistory (tuition_id, parent_id, amount, transaction_code, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ph.getTuitionId());
            ps.setInt(2, ph.getParentId());
            ps.setDouble(3, ph.getAmount());
            ps.setString(4, ph.getTransactionCode());
            ps.setString(5, ph.getStatus());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public List<PaymentHistory> getPaymentByAccountId(int accountId) {
    List<PaymentHistory> list = new ArrayList<>();
    String sql = "SELECT * FROM PaymentHistory WHERE parent_id = ?";

    try (Connection con = DBContext.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PaymentHistory ph = new PaymentHistory();
            ph.setPaymentId(rs.getInt("payment_id"));
            ph.setTuitionId(rs.getInt("tuition_id"));
            ph.setParentId(rs.getInt("parent_id"));
            ph.setAmount(rs.getDouble("amount"));
            ph.setPaymentTime(rs.getTimestamp("payment_time")); 
            ph.setTransactionCode(rs.getString("transaction_code"));
            ph.setStatus(rs.getString("status"));

            list.add(ph);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
   
   
   
   
    public static void main(String[] args) {
        PaymentHistoryDAO pd=new PaymentHistoryDAO();
        List<PaymentHistory> historys=pd.getPaymentByAccountId(3);
        for(PaymentHistory ph:historys){
            System.out.println(ph);
        }
    }
   

}

    
    


