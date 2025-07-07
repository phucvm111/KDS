package controller.teacher.attendance;

import dal.AttendanceDAO;
import dal.ClassDAO;
import dal.KindergartnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Kindergartner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AttendanceReportServlet", urlPatterns = {"/attendanceReport"})
public class AttendanceReportServlet extends HttpServlet {

    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private final KindergartnerDAO kinderDAO = new KindergartnerDAO();
    private final ClassDAO classDAO = new ClassDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String classIdStr = request.getParameter("classId");

        try {
            int classId = Integer.parseInt(classIdStr);

            List<Kindergartner> kids = kinderDAO.getKidsByClass(classId);

            Map<Integer, Double> attendancePercentages = new HashMap<>();
            // Không cần totalPresent và totalAbsent ở đây nếu biểu đồ dùng avgClassAttendance

            for (Kindergartner kid : kids) {
                double percent = attendanceDAO.getAttendancePercentageForKid(kid.getKinder_id());
                attendancePercentages.put(kid.getKinder_id(), percent);
            }

            String className = classDAO.getClassByID(classId) != null
                    ? classDAO.getClassByID(classId).getClass_name()
                    : "Không xác định";

            double totalPercentSum = 0; // Đổi tên để tránh nhầm lẫn với totalPresent/Absent
            for (double p : attendancePercentages.values()) {
                totalPercentSum += p;
            }
            double avgClassAttendance = attendancePercentages.size() > 0
                    ? totalPercentSum / attendancePercentages.size()
                    : 0;

            request.setAttribute("kids", kids);
            request.setAttribute("className", className);
            request.setAttribute("attendancePercentages", attendancePercentages);
            request.setAttribute("avgClassAttendance", avgClassAttendance);

            // Truyền trực tiếp tỷ lệ cho biểu đồ từ avgClassAttendance
            // Chart.js tự tính % nếu bạn truyền raw data. Ở đây bạn muốn 2 slice 
            // có giá trị là % của Có mặt và Vắng mặt.
            // Biểu đồ sẽ tốt hơn nếu hiển thị avgClassAttendance (tỷ lệ có mặt) và (100 - avgClassAttendance) (tỷ lệ vắng mặt)
            request.setAttribute("avgPresentForChart", avgClassAttendance); // Tỷ lệ có mặt
            request.setAttribute("avgAbsentForChart", 100 - avgClassAttendance); // Tỷ lệ vắng mặt

            request.getRequestDispatcher("teacher/Attendance/attendance_report.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Lỗi khi tải báo cáo điểm danh: " + e.getMessage());
            request.getRequestDispatcher("error.jsp") // Chuyển hướng đến trang lỗi nếu có
                    .forward(request, response);
        }
    }
}