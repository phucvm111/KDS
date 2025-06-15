package controller.admin.event;

import model.Event;
import dal.EventDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "UpdateEventServlet", urlPatterns = {"/updateevent"})
public class UpdateEventServlet extends HttpServlet {

    private EventDAO eventDAO = new EventDAO();
    private static final Logger logger = Logger.getLogger(UpdateEventServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateEventServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateEventServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                logger.log(Level.WARNING, "Event ID is missing for GET request to update event.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing.");
                return;
            }
            int eventId = Integer.parseInt(idStr);
            Event event = eventDAO.getEventById(eventId);

            if (event != null) {
                request.setAttribute("event", event);
                request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
            } else {
                logger.log(Level.WARNING, "Event not found with ID: " + eventId);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found with ID: " + eventId);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid event ID format in GET request.", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid event ID format. Please provide a valid number.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while fetching the event for update.", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error occurred.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Event eventToUpdate = new Event();

        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                logger.log(Level.WARNING, "Event ID is missing for POST request to update event.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing for update.");
                return;
            }
            int eventId = Integer.parseInt(idStr);

            // Populate eventToUpdate with current form values to persist them in case of error
            eventToUpdate.setEventId(eventId);
            eventToUpdate.setEventName(request.getParameter("event_name"));
            eventToUpdate.setEventDescription(request.getParameter("event_description"));
            eventToUpdate.setLocation(request.getParameter("location"));
            String eventDateStr = request.getParameter("event_date");

            if (eventDateStr != null && !eventDateStr.isEmpty()) {
                 try {
                    eventToUpdate.setEventDate(Date.valueOf(LocalDate.parse(eventDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                } catch (DateTimeParseException ex) {
                    logger.log(Level.FINE, "Temporary date parsing failed for persistence, actual validation will catch this.", ex);
                }
            }

            request.setAttribute("event", eventToUpdate);

            // Validate các trường bắt buộc
            if (eventToUpdate.getEventName() == null || eventToUpdate.getEventName().trim().isEmpty() ||
                eventToUpdate.getEventDescription() == null || eventToUpdate.getEventDescription().trim().isEmpty() ||
                eventDateStr == null || eventDateStr.trim().isEmpty() ||
                eventToUpdate.getLocation() == null || eventToUpdate.getLocation().trim().isEmpty()) {

                request.setAttribute("message", "All fields are required. Please fill in all information.");
                logger.warning("Validation failed: Missing required fields during event update for ID: " + eventId);
                request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
                return;
            }

            //Validate chuỗi ngày sự kiện thành LocalDate và xử lý lỗi định dạng
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localEventDate;
            try {
                localEventDate = LocalDate.parse(eventDateStr, formatter);
            } catch (DateTimeParseException e) {
                logger.log(Level.SEVERE, "Invalid date format submitted for event update ID: " + eventId + ", date string: " + eventDateStr, e);
                request.setAttribute("message", "Invalid date format. Please useAPAC-MM-DD.");
                request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
                return;
            }

            //Validate ngày sự kiện là hôm nay hay trong tương lai
            LocalDate currentDate = LocalDate.now();
            if (localEventDate.isBefore(currentDate)) {
                request.setAttribute("message", "Event date must be today or in the future.");
                logger.warning("Validation failed: Event date " + localEventDate + " is in the past for event ID: " + eventId);
                request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
                return;
            }

            // Convert LocalDate to java.sql.Date for database
            Date sqlEventDate = Date.valueOf(localEventDate);

            // Update the eventToUpdate object with validated and parsed data
            eventToUpdate.setEventDate(sqlEventDate);

            // Perform the update in the DAO
            boolean isUpdated = eventDAO.updateEvent(eventToUpdate);

            if (isUpdated) {
                request.getSession().setAttribute("successMessage", "Event '" + eventToUpdate.getEventName() + "' updated successfully!");
                response.sendRedirect("event");
            } else {
                request.setAttribute("message", "Failed to update event. Please try again.");
                logger.log(Level.SEVERE, "EventDAO.updateEvent returned false for event ID: " + eventId);
                request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid event ID format in POST request, ID string: " + request.getParameter("id"), e);
            request.setAttribute("message", "Error: Invalid Event ID format.");
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    request.setAttribute("event", eventDAO.getEventById(Integer.parseInt(idParam)));
                } catch (NumberFormatException | NullPointerException ex) {
                    logger.log(Level.SEVERE, "Failed to retrieve event after initial NumberFormatException for ID: " + idParam, ex);
                }
            }
            request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
        } catch (Exception e) {
         
            logger.log(Level.SEVERE, "An unexpected error occurred during event update.", e); 
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("event", eventToUpdate); // Ensure eventToUpdate is still in request scope to repopulate form
            request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles event update operations.";
    }
}