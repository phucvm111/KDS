/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author Admin
 */
public class ViewEventDetailsServlet extends HttpServlet {

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
        
        String eventIdStr = request.getParameter("id");
        int eventId = -1; // Default invalid ID

        if (eventIdStr != null && !eventIdStr.isEmpty()) {
            try {
                eventId = Integer.parseInt(eventIdStr);
            } catch (NumberFormatException e) {
                // Handle invalid ID format, e.g., log error or redirect with error message
                System.err.println("Invalid Event ID format: " + eventIdStr);
                request.setAttribute("message", "Invalid event ID.");
                request.getRequestDispatcher("admin/event/EventAdminPage.jsp").forward(request, response);
                return;
            }
        }

        if (eventId != -1) {
            EventDAO eventDAO = new EventDAO(); // Khởi tạo DAO của bạn
            Event event = eventDAO.getEventById(eventId); // Giả sử bạn có phương thức getEventById trong DAO của mình

            if (event != null) {
                request.setAttribute("event", event); // Đặt đối tượng event vào request scope
                request.getRequestDispatcher("admin/event/EventAdminView.jsp").forward(request, response);
            } else {
                // Event not found
                request.setAttribute("message", "Event not found for ID: " + eventId);
                request.getRequestDispatcher("admin/event/EventAdminPage.jsp").forward(request, response);
            }
        } else {
            // No event ID provided or invalid
            request.setAttribute("message", "No event ID provided.");
            request.getRequestDispatcher("admin/event/EventAdminPage.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
