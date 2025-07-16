package controller.parent;

import dal.KindergartnerDAO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
    maxFileSize = 1024 * 1024 * 10,  // 10 MB
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

            if (kindergartner == null || kindergartner.getParent_id() != account.getAccountID()) {
                response.sendRedirect(request.getContextPath() + "/childrenlist");
                return;
            }

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

        request.setCharacterEncoding("UTF-8");

        String kindergartnerIdStr = request.getParameter("kinder_id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String dob = request.getParameter("dob");
        String genderParam = request.getParameter("gender");
        String address = request.getParameter("address");
        String parentPhone = request.getParameter("parentPhone");

        try {
            int kindergartnerId = Integer.parseInt(kindergartnerIdStr);
            KindergartnerDAO kindergartnerDAO = new KindergartnerDAO();
            Kindergartner kindergartner = kindergartnerDAO.getKinderById(kindergartnerId);

            if (kindergartner == null || kindergartner.getParent_id() != account.getAccountID()) {
                response.sendRedirect(request.getContextPath() + "/childrenlist");
                return;
            }

            // === Trim dữ liệu ===
            firstName = (firstName != null) ? firstName.trim() : "";
            lastName = (lastName != null) ? lastName.trim() : "";
            address = (address != null) ? address.trim() : "";
            parentPhone = (parentPhone != null) ? parentPhone.trim() : "";

            // === Validate ===
            if (firstName.isEmpty() || lastName.isEmpty()) {
                request.setAttribute("errorMessage", "Họ và tên không được để trống hoặc chỉ chứa khoảng trắng.");
                forwardBack(request, response, kindergartner, kindergartnerDAO);
                return;
            }

            if (firstName.length() > 50 || lastName.length() > 50) {
                request.setAttribute("errorMessage", "Họ và tên không được vượt quá 50 ký tự.");
                forwardBack(request, response, kindergartner, kindergartnerDAO);
                return;
            }

            if (!firstName.matches("^[A-Za-zÀ-ỹà-ỹ\\s]+$") || !lastName.matches("^[A-Za-zÀ-ỹà-ỹ\\s]+$")) {
                request.setAttribute("errorMessage", "Họ và tên chỉ được chứa chữ cái và khoảng trắng.");
                forwardBack(request, response, kindergartner, kindergartnerDAO);
                return;
            }

            if (dob == null || dob.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Ngày sinh không được để trống.");
                forwardBack(request, response, kindergartner, kindergartnerDAO);
                return;
            }

            LocalDate dobDate = LocalDate.parse(dob);
            if (dobDate.isAfter(LocalDate.now())) {
                request.setAttribute("errorMessage", "Ngày sinh không được lớn hơn ngày hiện tại.");
                forwardBack(request, response, kindergartner, kindergartnerDAO);
                return;
            }

            boolean gender = "male".equalsIgnoreCase(genderParam);

            if (!parentPhone.isEmpty() && !parentPhone.matches("^[0-9]{8,15}$")) {
                request.setAttribute("errorMessage", "Số điện thoại phải là số, từ 8 đến 15 chữ số.");
                forwardBack(request, response, kindergartner, kindergartnerDAO);
                return;
            }

            // === Gán lại dữ liệu đã kiểm tra ===
            kindergartner.setFirst_name(firstName);
            kindergartner.setLast_name(lastName);
            kindergartner.setDob(dob);
            kindergartner.setGender(gender);
            kindergartner.setAddress(address);
            kindergartner.setParentPhone(parentPhone);

            // === Xử lý ảnh ===
            Part filePart = request.getPart("img");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

                    String uploadPath = getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    filePart.write(uploadPath + File.separator + uniqueFileName);

                    String oldImage = kindergartner.getImg();
                    if (oldImage != null && !oldImage.isEmpty()) {
                        File oldFile = new File(uploadPath + File.separator + oldImage);
                        if (oldFile.exists()) oldFile.delete();
                    }

                    kindergartner.setImg(uniqueFileName);
                }
            } else {
                Kindergartner existingKinder = kindergartnerDAO.getKinderById(kindergartnerId);
                if (existingKinder != null && existingKinder.getImg() != null) {
                    kindergartner.setImg(existingKinder.getImg());
                }
            }

            boolean updated = kindergartnerDAO.updateKindergartner(kindergartner);

            if (updated) {
                request.setAttribute("successMessage", "Cập nhật thông tin trẻ thành công!");
            } else {
                request.setAttribute("errorMessage", "Cập nhật không thành công. Vui lòng thử lại.");
            }

            forwardBack(request, response, kindergartner, kindergartnerDAO);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/childrenlist");
        }
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response,
                             Kindergartner kindergartner, KindergartnerDAO dao)
            throws ServletException, IOException {
        String className = dao.getClassNameById(kindergartner.getClass_id());
        request.setAttribute("kindergartner", kindergartner);
        request.setAttribute("className", className);
        request.getRequestDispatcher("/parent/childrendetail.jsp").forward(request, response);
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
