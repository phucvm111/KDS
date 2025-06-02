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
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class UpdateEventServlet extends HttpServlet {

    private EventDAO eventDAO = new EventDAO(); 

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
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing.");
                return;
            }
            int eventId = Integer.parseInt(idStr);
            Event event = eventDAO.getEventById(eventId);

            if (event != null) {
                request.setAttribute("event", event); 
                request.getRequestDispatcher("admin/event/EventAdminUpdate.jsp").forward(request, response);
            } else {
                
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found with ID: " + eventId);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(UpdateEventServlet.class.getName()).log(Level.SEVERE, "Invalid event ID format.", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid event ID format. Please provide a valid number.");
        } catch (Exception e) {
            Logger.getLogger(UpdateEventServlet.class.getName()).log(Level.SEVERE, "An error occurred while fetching the event for update.", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error occurred.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id"); 
            if (idStr == null || idStr.isEmpty()) {
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing for update.");
                 return;
            }
            int eventId = Integer.parseInt(idStr);

            String eventName = request.getParameter("event_name");
            String eventDescription = request.getParameter("event_description");
            String eventDateStr = request.getParameter("event_date");
            String location = request.getParameter("location");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localEventDate = LocalDate.parse(eventDateStr, formatter);
            Date sqlEventDate = Date.valueOf(localEventDate);
            Event event = new Event();
            event.setEventId(eventId); 
            event.setEventName(eventName);
            event.setEventDescription(eventDescription);
            event.setEventDate(sqlEventDate);
            event.setLocation(location);
            boolean isUpdated = eventDAO.updateEvent(event);

            if (isUpdated) {
                response.sendRedirect("event"); 
            } else {
                request.setAttribute("message", "Failed to update event. Please try again.");
                request.setAttribute("event", eventDAO.getEventById(eventId)); // Lấy lại để đảm bảo form được điền đúng
                request.getRequestDispatcher("admin/event/adminEventUpdate.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(UpdateEventServlet.class.getName()).log(Level.SEVERE, "Invalid event ID format in POST request.", e);
            request.setAttribute("message", "Error: Invalid Event ID format.");
            request.getRequestDispatcher("admin/event/adminEventUpdate.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(UpdateEventServlet.class.getName()).log(Level.SEVERE, "An error occurred during event update.", e);
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    request.setAttribute("event", eventDAO.getEventById(Integer.parseInt(idParam)));
                } catch (NumberFormatException ex) {
                }
            }
            request.getRequestDispatcher("admin/event/adminEventUpdate.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles event update operations.";
    }
}