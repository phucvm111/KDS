package controller.teacher.attendance;

import dal.AttendanceDAO;
import dal.ClassDAO;
import dal.KindergartnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Attendance;
import model.Class;
import model.Kindergartner;

@WebServlet(name = "AttendanceServlet", urlPatterns = {"/attendance"})
public class AttendanceServlet extends HttpServlet {

    AttendanceDAO attDAO = new AttendanceDAO();
    KindergartnerDAO kinderDAO = new KindergartnerDAO();
    ClassDAO classDAO = new ClassDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account teacher = (Account) request.getSession().getAttribute("account");
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp"); // Chuyển hướng về trang đăng nhập nếu chưa đăng nhập
            return;
        }

        String dateStr = request.getParameter("date");
        String classIdStr = request.getParameter("classId");

        try {
            if (dateStr != null && !dateStr.isEmpty() && classIdStr != null && !classIdStr.isEmpty()) {
                // Hiển thị danh sách điểm danh cho ngày và lớp đã chọn
                Date selectedDate = Date.valueOf(dateStr);
                int classId = Integer.parseInt(classIdStr);

                // Lấy thông tin lớp học để hiển thị tên lớp
                Class selectedClass = classDAO.getClassByID(classId);
                String className = (selectedClass != null) ? selectedClass.getClass_name() : "Không xác định";

                List<Kindergartner> kids = kinderDAO.getKidsByClass(classId);
                List<Attendance> attRecords = attDAO.getAttendanceByDateAndClass(selectedDate, classId);

                // Chuyển đổi danh sách điểm danh thành Map để dễ dàng truy cập trạng thái
                Map<Integer, Integer> attListMap = new HashMap<>();
                for (Attendance att : attRecords) {
                    attListMap.put(att.getKinder().getKinder_id(), att.getStatus());
                }

                // Thống kê tháng hiện tại
                LocalDate today = LocalDate.now();
                Map<String, Double> stats = attDAO.getAttendanceStatistics(
                        classId, today.getMonthValue(), today.getYear()
                );

                request.setAttribute("kids", kids);
                request.setAttribute("attList", attListMap); // Truyền Map thay vì List
                request.setAttribute("date", dateStr);
                request.setAttribute("classId", classIdStr);
                request.setAttribute("className", className); // Truyền tên lớp
                request.setAttribute("stats", stats);

                request.getRequestDispatcher("teacher/Attendance/attendance.jsp").forward(request, response);
            } else {
                // Hiển thị form chọn ngày và lớp
                int teacherId = teacher.getAccountID();
                List<Class> classes = classDAO.getClassesByTeacher(teacherId);

                // Đặt ngày mặc định là ngày hiện tại nếu chưa có chọn ngày
                if (dateStr == null || dateStr.isEmpty()) {
                    dateStr = LocalDate.now().toString();
                }

                request.setAttribute("date", dateStr);
                request.setAttribute("classes", classes);
                request.getRequestDispatcher("teacher/Attendance/attendance_form.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID lớp không hợp lệ.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Lỗi khi tải trang điểm danh: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String[] kinderIds = request.getParameterValues("kinder_id");
            String date = request.getParameter("date");
            String classId = request.getParameter("classId");

            Account teacher = (Account) request.getSession().getAttribute("account");
            if (teacher == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            if (kinderIds != null) {
                for (String kinderIdStr : kinderIds) {
                    int kinderId = Integer.parseInt(kinderIdStr);
                    // Lấy trạng thái điểm danh cho từng học sinh cụ thể
                    String statusParam = request.getParameter("status_" + kinderId);
                    int status = (statusParam != null) ? Integer.parseInt(statusParam) : 0; // Mặc định vắng mặt nếu không chọn

                    Attendance att = new Attendance();
                    Kindergartner kid = new Kindergartner();
                    kid.setKinder_id(kinderId);
                    att.setKinder(kid);
                    att.setCheck_date(date);
                    att.setStatus(status);
                    att.setTeacherAccount(teacher);

                    attDAO.insertOrUpdateAttendance(att);
                }
            }
            // Chuyển hướng về trang điểm danh với ngày và lớp đã chọn sau khi lưu
            response.sendRedirect("attendance?date=" + date + "&classId=" + classId);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Dữ liệu không hợp lệ khi lưu điểm danh.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Lỗi khi lưu điểm danh: " + e.getMessage(), e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Attendance management for teachers";
    }
}
