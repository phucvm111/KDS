package controller.parent;

;

import dal.KindergartnerDAO;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Account;
import model.Kindergartner;
import dal.KindergartnerDAO;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Account;
import model.Kindergartner;



@WebServlet(name = "ChildrenDetailServlet", urlPatterns = {"/childrendetail"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class ChildrenDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/childrenlist");
            return;
        }

        try {
            int kindergartnerId = Integer.parseInt(idParam);
            KindergartnerDAO kindergartnerDAO = new KindergartnerDAO();
            Kindergartner kindergartner = kindergartnerDAO.getKinderById(kindergartnerId);

            // Kiểm tra xem trẻ có thuộc về phụ huynh đang đăng nhập không
            if (kindergartner == null || kindergartner.getParent_id() != account.getAccountID()) {
                response.sendRedirect(request.getContextPath() + "/childrenlist");
                return;
            }

            // Lấy tên lớp học
            String className = kindergartnerDAO.getClassNameById(kindergartner.getClass_id());

            request.setAttribute("kindergartner", kindergartner);
            request.setAttribute("className", className);
            request.getRequestDispatcher("/parent/childrendetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/childrenlist");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy thông tin từ form
        String kindergartnerIdStr = request.getParameter("kinder_id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String dob = request.getParameter("dob");
        // Xử lý gender là boolean
        String genderParam = request.getParameter("gender");
        boolean gender = "male".equalsIgnoreCase(genderParam);
        String address = request.getParameter("address");
        String parentPhone = request.getParameter("parentPhone");

        try {
            int kindergartnerId = Integer.parseInt(kindergartnerIdStr);
            KindergartnerDAO kindergartnerDAO = new KindergartnerDAO();
            Kindergartner kindergartner = kindergartnerDAO.getKinderById(kindergartnerId);

            // Kiểm tra xem trẻ có thuộc về phụ huynh đang đăng nhập không
            if (kindergartner == null || kindergartner.getParent_id() != account.getAccountID()) {
                response.sendRedirect(request.getContextPath() + "/childrenlist");
                return;
            }

            // Cập nhật thông tin trẻ
            kindergartner.setFirst_name(firstName);
            kindergartner.setLast_name(lastName);
            kindergartner.setDob(dob);
            kindergartner.setGender(gender);
            kindergartner.setAddress(address);
            kindergartner.setParentPhone(parentPhone);

            // Xử lý tải lên hình ảnh nếu có
            Part filePart = request.getPart("img");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    // Tạo tên file duy nhất để tránh trùng lặp
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

                    // Đường dẫn lưu file
                    String uploadPath = getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    // Lưu file
                    filePart.write(uploadPath + File.separator + uniqueFileName);

                    // Cập nhật đường dẫn hình ảnh mới
                    kindergartner.setImg(uniqueFileName);

                    // Tùy chọn: Xóa file ảnh cũ nếu tồn tại
                    String oldImage = kindergartner.getImg();
                    if (oldImage != null && !oldImage.isEmpty()) {
                        File oldFile = new File(uploadPath + File.separator + oldImage);
                        if (oldFile.exists()) {
                            oldFile.delete();
                        }
                    }
                }
            } else {
                // Người dùng không tải lên hình ảnh mới
                // Giữ nguyên giá trị img hiện tại từ đối tượng kindergartner đã load từ database
                // Không cần làm gì vì đối tượng kindergartner đã có giá trị img từ khi được load

                // Tuy nhiên, để đảm bảo rõ ràng, bạn có thể viết:
                // kindergartner.setImg(kindergartner.getImg());
                // Hoặc nếu bạn muốn đảm bảo không ghi đè NULL vào cơ sở dữ liệu:
                Kindergartner existingKinder = kindergartnerDAO.getKinderById(kindergartnerId);
                if (existingKinder != null && existingKinder.getImg() != null) {
                    kindergartner.setImg(existingKinder.getImg());
                }
            }

            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            boolean updated = kindergartnerDAO.updateKindergartner(kindergartner);

            if (updated) {
                request.setAttribute("successMessage", "Child information updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update child information. Please try again.");
            }

            // Lấy tên lớp học
            String className = kindergartnerDAO.getClassNameById(kindergartner.getClass_id());

            request.setAttribute("kindergartner", kindergartner);
            request.setAttribute("className", className);
            request.getRequestDispatcher("/parent/childrendetail.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/childrenlist");
        }
    }

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
}
