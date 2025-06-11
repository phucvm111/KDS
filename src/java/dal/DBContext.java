package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private final String user = "sa";
    private final String password = "123";
    private final String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=KD;TrustServerCertificate=true;";

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }

  
    public static void main(String[] args) {
        try (Connection conn = new DBContext().getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối thành công với cơ sở dữ liệu!");
            }
        } catch (Exception e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
    }
}
