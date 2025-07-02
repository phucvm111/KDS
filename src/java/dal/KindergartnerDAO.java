package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feedback;
import model.Kindergartner;
import model.StudyRecord;

public class KindergartnerDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    private AccountDAO accdao = new AccountDAO();
    private ClassDAO classdao = new ClassDAO();

    public List<Kindergartner> getKidsByClass(int class_id) {
        List<Kindergartner> list = new ArrayList<>();
        String sql = "SELECT * FROM Kindergartner WHERE class_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, class_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Kindergartner k = new Kindergartner(
                        rs.getInt("kinder_id"),
                        rs.getInt("parent_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getBoolean("gender"),
                        rs.getString("img"),
                        rs.getInt("class_id"),
                        rs.getString("address"),
                        rs.getString("parentPhone")
                );
                list.add(k);
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return list;
    }

    public void insertKinder(Kindergartner kindergartner) {
        try {
            String sql = "INSERT INTO Kindergartner (parent_id, first_name, last_name, dob, gender, img, class_id, address, parentPhone) VALUES (?,?,?,?,?,?,?,?,?)";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, kindergartner.getParent_id());
            ps.setString(2, kindergartner.getFirst_name());
            ps.setString(3, kindergartner.getLast_name());
            ps.setString(4, kindergartner.getDob());
            ps.setBoolean(5, kindergartner.isGender());
            ps.setString(6, kindergartner.getImg());
            ps.setInt(7, kindergartner.getClass_id());
            ps.setString(8, kindergartner.getAddress());
            ps.setString(9, kindergartner.getParentPhone());

            ps.executeUpdate();

            // lấy id vừa thêm
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int kinderId = generatedKeys.getInt(1);
                // thêm StudyRecord mặc định
                StudyRecordDAO srdao = new StudyRecordDAO();
                srdao.addStudyRecord(new StudyRecord(
                        0,
                        new ClassDAO().getClassByID(kindergartner.getClass_id()),
                        getKinderById(kinderId),
                        java.time.LocalDate.now().getYear(),
                        false, // isGraduated
                        false // isDroppedOut
                ));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updateKinder(Kindergartner kindergartner) {
        try {
            String sql = "UPDATE [dbo].[Kindergartner]\n"
                    + "   SET [parent_id] = ?\n"
                    + "      ,[first_name] = ?\n"
                    + "      ,[last_name] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[img] = ?\n"
                    + "      ,[class_id] = ?\n"
                    + "      ,[address] = ?\n"
                    + "      ,[parentPhone] = ?\n"
                    + " WHERE kinder_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kindergartner.getParent_id());
            ps.setString(2, kindergartner.getFirst_name());
            ps.setString(3, kindergartner.getLast_name());
            ps.setString(4, kindergartner.getDob());
            ps.setBoolean(5, kindergartner.isGender());
            ps.setString(6, kindergartner.getImg());
            ps.setInt(7, kindergartner.getClass_id());
            ps.setString(8, kindergartner.getAddress());
            ps.setString(9, kindergartner.getParentPhone());
            ps.setInt(10, kindergartner.getKinder_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void deleteKinder(int id) {
        try {
            String sql = "DELETE FROM [Kindergartner]\n"
                    + "      WHERE kinder_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public List<Kindergartner> getAllStudent() {
        List<Kindergartner> list = new ArrayList<>();
        String sql = "SELECT * FROM Kindergartner";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Kindergartner k = new Kindergartner(
                        rs.getInt("kinder_id"),
                        rs.getInt("parent_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getBoolean("gender"),
                        rs.getString("img"),
                        rs.getInt("class_id"),
                        rs.getString("address"),
                        rs.getString("parentPhone")
                );
                list.add(k);
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return list;
    }

    public Kindergartner getKidInfoById(int id) {
        return getKinderById(id);
    }

    public Account getParentById(int id) {
        AccountDAO ad = new AccountDAO();
        List<Account> list = ad.getAllParentInfor();
        for (Account parent : list) {
            if (parent.getAccountID() == id) {
                return parent;
            }
        }
        return null;
    }

    public Kindergartner getKinderById(int id) {
        try {
            String sql = "SELECT * FROM Kindergartner WHERE kinder_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Kindergartner(
                        rs.getInt("kinder_id"),
                        rs.getInt("parent_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getBoolean("gender"),
                        rs.getString("img"),
                        rs.getInt("class_id"),
                        rs.getString("address"),
                        rs.getString("parentPhone")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return null;
    }

    public Feedback getFbByKidID(int kid_id) {
        String sql = "select * from feedback where kid_id = ?";

        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, kid_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6));
                return fb;
            }
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return null;
    }

    public void insertFBtoDB(Feedback fb) {
        String sql = "insert into Feedback(kid_id, teacher_id, fb_content, rating, fb_date) "
                + "values(?,?,?,?,?)";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, fb.getKid_id());
            ps.setInt(2, fb.getTeacher_id());
            ps.setString(3, fb.getFb_content());
            ps.setDouble(4, fb.getRating());
            ps.setString(5, fb.getTakenDate());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(KindergartnerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }

    public List<Kindergartner> getKidbyParent(int parentId) {
        List<Kindergartner> kidparentlist = new ArrayList<>();
        String sql = "SELECT * FROM Kindergartner WHERE parent_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, parentId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kindergartner k = new Kindergartner(
                        rs.getInt("kinder_id"),
                        rs.getInt("parent_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getBoolean("gender"),
                        rs.getString("img"),
                        rs.getInt("class_id"),
                        rs.getString("address"),
                        rs.getString("parentPhone")
                );
                kidparentlist.add(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return kidparentlist;
    }

    public List<Kindergartner> getKindergartnersByParentId(int parentId) {
        List<Kindergartner> kindergartners = new ArrayList<>();
        String sql = "SELECT * FROM kindergartner WHERE parent_id = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Kindergartner kindergartner = new Kindergartner();
                    kindergartner.setKinder_id(rs.getInt("kinder_id"));
                    kindergartner.setParent_id(rs.getInt("parent_id"));
                    kindergartner.setClass_id(rs.getInt("class_id"));
                    kindergartner.setFirst_name(rs.getString("first_name"));
                    kindergartner.setLast_name(rs.getString("last_name"));
                    kindergartner.setDob(rs.getString("dob"));

                    kindergartner.setGender(rs.getBoolean("gender"));

                    kindergartner.setImg(rs.getString("img"));
                    kindergartner.setAddress(rs.getString("address"));
                    kindergartner.setParentPhone(rs.getString("parentPhone"));

                    // Lấy tên lớp học cho trẻ em này
                    String className = getClassNameById(kindergartner.getClass_id());
                    kindergartner.setClassName(className);

                    kindergartners.add(kindergartner);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving kindergartners by parent ID: " + e.getMessage());
            e.printStackTrace();
        }

        return kindergartners;
    }

    public void updateKinderBasicInfo(Kindergartner kindergartner) {
        String sql = "UPDATE Kindergartner SET first_name = ?, last_name = ?, dob = ?, gender = ? WHERE kinder_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, kindergartner.getFirst_name());
            ps.setString(2, kindergartner.getLast_name());
            ps.setString(3, kindergartner.getDob());
            ps.setBoolean(4, kindergartner.isGender());
            ps.setInt(5, kindergartner.getKinder_id());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Phương thức cập nhật lớp học cho trẻ
    public void updateKinderClass(int kinderId, int classId) {
        String sql = "UPDATE Kindergartner SET class_id = ? WHERE kinder_id = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setInt(2, kinderId);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Phương thức đóng tài nguyên
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Lấy tên lớp học dựa trên class_id

    public String getClassNameById(int classId) {
        String className = null;
        String sql = "SELECT class_name FROM class WHERE class_id = ?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                className = rs.getString("class_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return className;
    }

    public boolean updateKindergartner(Kindergartner kindergartner) {
        String sql = "UPDATE Kindergartner SET first_name=?, last_name=?, dob=?, gender=?, "
                + "img=?, address=?, parentPhone=? WHERE kinder_id=?";

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập các tham số cho câu lệnh SQL
            ps.setString(1, kindergartner.getFirst_name());
            ps.setString(2, kindergartner.getLast_name());
            ps.setString(3, kindergartner.getDob());

            // Chuyển boolean thành string khi lưu vào database
            // Giả sử trường gender trong DB là kiểu nvarchar/varchar lưu "male" hoặc "female"
            ps.setBoolean(4, kindergartner.isGender());

            // Xử lý trường hợp img có thể null
            if (kindergartner.getImg() != null) {
                ps.setString(5, kindergartner.getImg());
            } else {
                ps.setNull(5, java.sql.Types.VARCHAR);
            }

            ps.setString(6, kindergartner.getAddress());
            ps.setString(7, kindergartner.getParentPhone());
            ps.setInt(8, kindergartner.getKinder_id());

            // Thêm log để debug
            System.out.println("Executing SQL: " + sql);
            System.out.println("Parameters: "
                    + "first_name=" + kindergartner.getFirst_name() + ", "
                    + "last_name=" + kindergartner.getLast_name() + ", "
                    + "dob=" + kindergartner.getDob() + ", "
                    + "gender=" + (kindergartner.isGender()) + ", "
                    + "img=" + kindergartner.getImg() + ", "
                    + "address=" + kindergartner.getAddress() + ", "
                    + "parentPhone=" + kindergartner.getParentPhone() + ", "
                    + "kinder_id=" + kindergartner.getKinder_id());

            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error in updateKindergartner: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        KindergartnerDAO dao = new KindergartnerDAO();
        List<Kindergartner> kiders = dao.getKindergartnersByParentId(3);
        for (Kindergartner k : kiders) {
            System.out.println(k);
        }
    }

}
