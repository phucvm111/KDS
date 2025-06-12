package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private final String user = "sa";
    private final String password = "123";
    private final String url = "jdbc:sqlserver://localhost;databaseName=KD;TrustServerCertificate=true;";

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password); // ✅ mở kết nối mới mỗi lần
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
