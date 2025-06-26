/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import dal.SendformDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Form;

/**
 *
 * @author ACE
 */
public class RespondFormServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RespondFormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RespondFormServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        SendformDAO sf = new SendformDAO();
        List<Form> formList = sf.getUnrepliedForms();
        if (formList != null) {
            request.setAttribute("formList", formList);
            request.getRequestDispatcher("/teacher/respond_form.jsp").forward(request, response);
        }
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
        try {
            SendformDAO dao = new SendformDAO();
            String formString = request.getParameter("form_id");
            int formid = Integer.parseInt(formString);
            Form getform = dao.getFormById(formid);
            String fullname = getform.getAccount().getFirstName() + " " + getform.getAccount().getLastName();
            Form f = new Form();
            f.setForm_id(formid);
            f.setStatus(request.getParameter("status"));
            f.setReply(request.getParameter("reply"));

            dao.updateReplyAndStatus(f);
            List<Form> formList = dao.getUnrepliedForms();
            request.setAttribute("formList", formList);
            request.setAttribute("success", "Phản hồi tới " + fullname + " " + "thành công");
            request.getRequestDispatcher("/teacher/respond_form.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erorr", "Phản hồi thất bại");
            response.sendRedirect("respondform");
        }

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
