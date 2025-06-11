package controller.admin.event;

import dal.EventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException; 
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Event;

@WebServlet(name = "AddEventServlet", urlPatterns = {"/addevent"}) 
public class AddEventServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddEventServlet.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddEventServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEventServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String eventName = request.getParameter("event_name");
        String eventDescription = request.getParameter("event_description");
        String eventDateStr = request.getParameter("event_date");
        String location = request.getParameter("location");

        logger.info("Attempting to add Event - Name: " + eventName + ", Desc: " + eventDescription + ", Date: " + eventDateStr + ", Location: " + location);

        request.setAttribute("oldEventName", eventName);
        request.setAttribute("oldEventDescription", eventDescription);
        request.setAttribute("oldEventDate", eventDateStr);
        request.setAttribute("oldLocation", location);

        try {
            if (eventName == null || eventName.trim().isEmpty() ||
                eventDescription == null || eventDescription.trim().isEmpty() ||
                eventDateStr == null || eventDateStr.trim().isEmpty() ||
                location == null || location.trim().isEmpty()) {

                request.setAttribute("errorMessage", "All fields are required. Please fill in all information.");
                logger.warning("Validation failed: Missing required fields.");
                request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
                return;
            }

            // Chuyển đổi chuỗi ngày sang đối tượng LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate eventDate = null;
            try {
                eventDate = LocalDate.parse(eventDateStr, formatter);
            } catch (DateTimeParseException e) {
                logger.log(Level.SEVERE, "Invalid date format submitted: " + eventDateStr, e);
                request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD.");
                request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
                return; // Dừng xử lý nếu định dạng ngày không hợp lệ
            }

            // validation cho ngày trong tương lai/hiện tại
            LocalDate currentDate = LocalDate.now();
            if (eventDate.isBefore(currentDate)) {
                request.setAttribute("errorMessage", "Event date must be today or in the future.");
                logger.warning("Validation failed: Event date " + eventDate + " is in the past.");
                request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
                return; // Dừng xử lý nếu ngày là quá khứ
            }

            // Chuyển đổi LocalDate sang java.sql.Date lưu vào DB
            Date sqlEventDate = Date.valueOf(eventDate);

            // them Event và thêm vào DB
            Event event = new Event();
            event.setEventName(eventName);
            event.setEventDescription(eventDescription);
            event.setEventDate(sqlEventDate);
            event.setLocation(location);

            EventDAO dao = new EventDAO();
            dao.addEvent(event);

            // thong bao thanh cong
            session.setAttribute("successMessage", "Event '" + eventName + "' added successfully!");
            logger.info("Event '" + eventName + "' added successfully. Redirecting to /event");
            response.sendRedirect("event");

        } catch (Exception e) {
            // Xử lý các ngoại lệ chung LỖi DB.....
            logger.log(Level.SEVERE, "Failed to add event. Error: " + e.getMessage(), e);
            request.setAttribute("errorMessage", "Failed to add event: " + e.getMessage());
            request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles adding new events.";
    }
}