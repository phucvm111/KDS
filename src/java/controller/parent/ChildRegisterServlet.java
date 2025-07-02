package controller.parent;

import dal.ClassDAO;
import dal.KindergartnerDAO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Account;
import model.Class;
import model.Kindergartner;

/**
 * Servlet xử lý đăng ký trẻ mới vào hệ thống
 */
@WebServlet(name = "ChildRegisterServlet", urlPatterns = {"/childregister"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class ChildRegisterServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. Hiển thị form đăng ký trẻ với
     * danh sách lớp học hiện có
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra đăng nhập
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Lấy danh sách lớp học hiện có
        ClassDAO classDAO = new ClassDAO();
        List<Class> classList = classDAO.getAllClass();
        request.setAttribute("classlist", classList);

        // Chuyển hướng đến trang đăng ký
        request.getRequestDispatcher("/parent/childregister.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Xử lý thông tin đăng ký trẻ từ
     * form
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra đăng nhập
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            // Lấy thông tin từ form
            String childFirstName = request.getParameter("ChildFirstName");
            String childLastName = request.getParameter("ChildLastName");
            String dob = request.getParameter("DOB");
            String genderStr = request.getParameter("flexRadioDefault");
            boolean gender = genderStr.equalsIgnoreCase("male");
            int classId = Integer.parseInt(request.getParameter("register_classid"));
            String address = request.getParameter("address");
            String parentPhone = request.getParameter("parentPhone");

            // Debug: In thông tin
            System.out.println("Thông tin đăng ký:");
            System.out.println("Tên: " + childFirstName + " " + childLastName);
            System.out.println("Ngày sinh: " + dob);
            System.out.println("Giới tính: " + gender);
            System.out.println("Lớp ID: " + classId);

            // Xử lý file ảnh
            String imgFileName = "default.jpg"; // Ảnh mặc định
            Part filePart = request.getPart("profileImage");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    // Tạo tên file duy nhất để tránh trùng lặp
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                    // Đường dẫn lưu file
                    String uploadPath = getServletContext().getRealPath("/parent/img/userImg");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // Lưu file
                    filePart.write(uploadPath + File.separator + uniqueFileName);
                    imgFileName = uniqueFileName;
                }
            }

            System.out.println("Ảnh: " + imgFileName);

            // Tạo đối tượng Kindergartner
            Kindergartner kindergartner = new Kindergartner();
            kindergartner.setParent_id(account.getAccountID());
            kindergartner.setFirst_name(childFirstName);
            kindergartner.setLast_name(childLastName);
            kindergartner.setDob(dob);
            kindergartner.setGender(gender);
            kindergartner.setImg(imgFileName);
            kindergartner.setClass_id(classId);
            kindergartner.setParentPhone(account.getPhoneNumber());
            kindergartner.setAddress(account.getAddress());

            // Lưu thông tin trẻ vào database
            KindergartnerDAO kindergartnerDAO = new KindergartnerDAO();
            kindergartnerDAO.insertKinder(kindergartner);

            // Lấy id của trẻ vừa thêm vào
            List<Kindergartner> kidList = kindergartnerDAO.getKidbyParent(account.getAccountID());
            Kindergartner lastAddedKid = null;
            if (!kidList.isEmpty()) {
                lastAddedKid = kidList.get(kidList.size() - 1);
            }

            if (lastAddedKid != null) {
                System.out.println("Đã thêm trẻ thành công với ID: " + lastAddedKid.getKinder_id());

                // Thông báo thành công
                request.setAttribute("success", "Đăng ký thành công!");
                request.setAttribute("registeredChild", lastAddedKid);

                // Lấy lại danh sách lớp học hiện có
                ClassDAO classDAO = new ClassDAO();
                List<Class> classList = classDAO.getAllClass();
                request.setAttribute("classlist", classList);

                // Hiển thị trang đăng ký với thông báo thành công
                request.getRequestDispatcher("/parent/childregister.jsp").forward(request, response);
                return;
            } else {
                System.out.println("Không thể lấy thông tin trẻ vừa thêm");
                request.setAttribute("error", "Không thể lấy thông tin trẻ vừa thêm");
                doGet(request, response);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Dữ liệu không hợp lệ: " + e.getMessage());
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Có lỗi xảy ra: " + e.getMessage() + " (Loại lỗi: " + e.getClass().getName() + ")";
            request.setAttribute("error", errorMessage);
            doGet(request, response);
        }
    }

    /**
     * Trích xuất tên file từ Part
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Servlet xử lý đăng ký trẻ mới vào hệ thống";
    }
}
