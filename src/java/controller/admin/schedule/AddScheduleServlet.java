/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.schedule;

import dal.ScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AddScheduleServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddScheduleServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
//        processRequest(request, response);
        HttpSession session = request.getSession(true);
        ScheduleDAO sd = new ScheduleDAO();
        String act_id_raw = request.getParameter("select_activity");
        String date = request.getParameter("date_picked_converted");
        String recent_date = (String) session.getAttribute("recentMonday");

        String class_id_raw = request.getParameter("cid_raw");
        String slot_id_raw = request.getParameter("slot_chosen");
        int act_id, class_id, slot_id;
        try {
            act_id = Integer.parseInt(act_id_raw);
            class_id = Integer.parseInt(class_id_raw);
            slot_id = Integer.parseInt(slot_id_raw);
            sd.addSchedule(class_id, act_id, slot_id, date);
        } catch (Exception e) {

        }
//        try ( PrintWriter out = response.getWriter()) {
//            out.print(date);
//
//        }
//        try {
//            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//            Date d = sdf1.parse(date);
//
//            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
//            date = sdf2.format(d);
//        } catch (Exception e) {
//
//        }

//        request.setAttribute("datechosen", date);
//        String url = "listschedule?action=add&recentMonday=" + recent_date;
//        request.getRequestDispatcher(url).forward(request, response);
        response.sendRedirect("listschedule?action=add&recentMonday=" + recent_date);
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
