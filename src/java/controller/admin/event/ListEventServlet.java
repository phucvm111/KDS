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
import model.Event;

@WebServlet(name = "ListEventServlet", urlPatterns = {"/event"})
public class ListEventServlet extends HttpServlet {


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
        }
        List<Event> events = dao.getAllEvents(searchName);

        request.setAttribute("eventList", events);
        request.setAttribute("searchName", searchName); 

        request.getRequestDispatcher("admin/event/EventAdminPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for listing and searching events in admin panel (without pagination)";
    }
}