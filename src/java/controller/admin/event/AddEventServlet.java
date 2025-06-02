package controller.admin.event;

import dal.EventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger; 
import model.Event;

/**
 *
 * @author Admin
 */
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
        // Chỉ cần forward đến trang JSP để hiển thị form.
        // Các tham số event_name, event_description... sẽ là null ở lần tải đầu tiên.
        // Nếu có lỗi từ POST request trước đó và forward lại, thì các param vẫn còn.
        request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
    }

  
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); 

        try {
            String eventName = request.getParameter("event_name");
            String eventDescription = request.getParameter("event_description");
            String eventDateStr = request.getParameter("event_date");
            String location = request.getParameter("location");

            logger.info("Adding Event - Name: " + eventName + ", Desc: " + eventDescription + ", Date: " + eventDateStr + ", Location: " + location);

           
            if (eventName == null || eventName.trim().isEmpty() ||
                eventDescription == null || eventDescription.trim().isEmpty() ||
                eventDateStr == null || eventDateStr.trim().isEmpty() ||
                location == null || location.trim().isEmpty()) {

                request.setAttribute("errorMessage", "All fields are required. Please fill in all information.");
                request.setAttribute("oldEventName", eventName);
                request.setAttribute("oldEventDescription", eventDescription);
                request.setAttribute("oldEventDate", eventDateStr);
                request.setAttribute("oldLocation", location);
               
                logger.warning("Validation failed: Missing required fields.");
                request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
                return; 
            }

            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
            java.time.LocalDate eventDate = java.time.LocalDate.parse(eventDateStr, formatter);
            java.sql.Date sqlEventDate = java.sql.Date.valueOf(eventDate);

            Event event = new Event();
            event.setEventName(eventName);
            event.setEventDescription(eventDescription);
            event.setEventDate(sqlEventDate);
            event.setLocation(location);

            EventDAO dao = new EventDAO();
            dao.addEvent(event); 

            
            logger.info("Event '" + eventName + "' added successfully. Redirecting to /event");
            session.setAttribute("successMessage", "Event '" + eventName + "' added successfully!");

            response.sendRedirect("event"); 

        } catch (java.time.format.DateTimeParseException e) {
            logger.log(Level.SEVERE, "Invalid date format submitted for event: " + request.getParameter("event_date"), e);
            request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD.");
            
            request.setAttribute("oldEventName", request.getParameter("event_name"));
            request.setAttribute("oldEventDescription", request.getParameter("event_description"));
            request.setAttribute("oldLocation", request.getParameter("location"));
            request.getRequestDispatcher("admin/event/EventAdminAdd.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add event. Error: " + e.getMessage(), e);
           
            request.setAttribute("errorMessage", "Failed to add event: " + e.getMessage());
           
            request.setAttribute("oldEventName", request.getParameter("event_name"));
            request.setAttribute("oldEventDescription", request.getParameter("event_description"));
            request.setAttribute("oldEventDate", request.getParameter("event_date"));
            request.setAttribute("oldLocation", request.getParameter("location"));
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