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
import jakarta.servlet.http.HttpSession; 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException; 
import java.util.logging.Level;
import java.util.logging.Logger; 

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteEventServlet", urlPatterns = {"/deleteevent"}) 
public class DeleteEventServlet extends HttpServlet {

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
        String idParam = request.getParameter("id"); 
        int eventId = -1;
        HttpSession session = request.getSession(); 

        if (idParam != null && !idParam.isEmpty()) {
            try {
                eventId = Integer.parseInt(idParam);
                EventDAO eventDAO = new EventDAO();
                eventDAO.deleteEvent(eventId); 

                session.setAttribute("successMessage", "Event with ID " + eventId + " deleted successfully!");
            } catch (NumberFormatException e) {
                Logger.getLogger(DeleteEventServlet.class.getName()).log(Level.WARNING, "Invalid event ID format: " + idParam, e);
                session.setAttribute("message", "Invalid event ID provided.");
            } catch (SQLException e) {
                Logger.getLogger(DeleteEventServlet.class.getName()).log(Level.SEVERE, "Database error when deleting event ID: " + eventId, e);
                session.setAttribute("message", "Database error: Could not delete event. Please try again.");
            } catch (Exception e) {
                Logger.getLogger(DeleteEventServlet.class.getName()).log(Level.SEVERE, "Unexpected error when deleting event ID: " + eventId, e);
                session.setAttribute("message", "An unexpected error occurred. Could not delete event.");
            }
        } else {
            session.setAttribute("message", "No event ID specified for deletion.");
        }

        response.sendRedirect("event"); 
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
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for deleting events.";
    }// </editor-fold>

}