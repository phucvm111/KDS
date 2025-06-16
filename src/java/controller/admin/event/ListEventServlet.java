package controller.admin.event;

import dal.EventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level; 
import java.util.logging.Logger; 
import model.Event;

@WebServlet(name = "ListEventServlet", urlPatterns = {"/event"})
public class ListEventServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ListEventServlet.class.getName());

    private static final int RECORDS_PER_PAGE = 5; // so ban ghi moi trang

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListEventServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListEventServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventDAO dao = new EventDAO(); 

        String searchName = request.getParameter("searchName");

        if (searchName == null) {
            searchName = "";
            logger.fine("No searchName parameter found, defaulting to empty string."); 
        } else {
            logger.fine("SearchName parameter: " + searchName); 
        }

        int currentPage = 1; 
        String pageParam = request.getParameter("page"); 

        if (pageParam != null) { 
            try {
                currentPage = Integer.parseInt(pageParam); 
                logger.fine("Page parameter received: " + currentPage); 
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Invalid page number format: {0}. Defaulting to page 1.", pageParam);
                currentPage = 1; 
            }
        } else {
            logger.fine("No page parameter found, defaulting to page 1."); 
        }

        int offset = (currentPage - 1) * RECORDS_PER_PAGE; // tinh so ban ghi bo khi tim 
        logger.fine("Calculated offset: " + offset + " for currentPage: " + currentPage);
        
        List<Event> events = null;
        int noOfRecords = 0;
        int noOfPages = 1;

        try {
            events = dao.getEventsPaged(searchName, currentPage, RECORDS_PER_PAGE);
            logger.info("Retrieved " + events.size() + " events for searchName '" + searchName + "' on page " + currentPage);

            noOfRecords = dao.getNoOfRecords(searchName); // lay tổng so bản ghi cho phân trang
            logger.info("Total records for searchName '" + searchName + "': " + noOfRecords);

            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);//tinh tong so trang
            logger.info("Calculated total pages: " + noOfPages);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching events or record count from DAO for searchName: " + searchName, e);
            request.setAttribute("errorMessage", "An error occurred while loading events. Please try again later.");
            events = new java.util.ArrayList<>(); // Trả về danh sách rỗng để tránh NullPointerException trên JSP
            noOfRecords = 0;
            noOfPages = 1; // Đảm bảo luôn có ít nhất 1 trang
        }

        request.setAttribute("eventList", events);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", noOfPages);
        request.setAttribute("searchName", searchName);
        request.setAttribute("offset", offset); 

        request.getRequestDispatcher("admin/event/EventAdminPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for listing and searching events in admin panel";
    }
}