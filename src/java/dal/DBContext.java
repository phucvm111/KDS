package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String user = "sa";
            String password = "123";
            String url = "jdbc:sqlserver://localhost;databaseName=SWP391_Project_Lastest;TrustServerCertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace(); // Đừng để trống, cần log để debug
        }
        return conn;
    }
    
    public static void main(String[] args) {
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Kết nối thành công với CSDL!");
            } else {
                System.out.println("❌ Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}