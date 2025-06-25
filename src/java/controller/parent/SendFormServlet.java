/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.parent;

import dal.FormTyleDAO;
import dal.KindergartnerDAO;
import dal.SendformDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import model.Account;
import model.Form;
import model.FormTyle;
import model.Kindergartner;

/**
 *
 * @author ACE
 */
public class SendFormServlet extends HttpServlet {

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
            out.println("<title>Servlet SendFormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendFormServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {

            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            FormTyleDAO ft = new FormTyleDAO();
            KindergartnerDAO kd = new KindergartnerDAO();

            List<FormTyle> forms = ft.getAllFormTypes();
            List<Kindergartner> kinder = kd.getKindergartnersByParentId(acc.getAccountID());

            request.setAttribute("formTypes", forms);
            request.setAttribute("kinderList", kinder);

            request.getRequestDispatcher("/parent/sendform/sendForm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi tải dữ liệu. Vui lòng thử lại.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
        // processRequest(request, response);
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        SendformDAO sf = new SendformDAO();
        KindergartnerDAO kd = new KindergartnerDAO();
        FormTyleDAO ft = new FormTyleDAO();
        int senderid = acc.getAccountID();
        LocalDate timenow = LocalDate.now();
        String dateofsubmit = timenow + "";
        String formTypeString = request.getParameter("formTypeId");
        int formtypeid = Integer.parseInt(formTypeString);
        FormTyle formstyle = ft.getFormTypeById(formtypeid);
        String kinderString = request.getParameter("kinderId");
      
        
          int kinderid = Integer.parseInt(kinderString);
         Kindergartner   kindergartner = kd.getKinderById(kinderid);
       
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Form form = new Form(formstyle, acc, kindergartner, title, content, dateofsubmit, "Pending", "");
        try {
            sf.insertForm(form);
            request.setAttribute("success", "Đã gửi đơn thành công và các thông tin đơn nằm trong lịch.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Gửi đơn thất bại! Vui lòng thử lại.");
        }
        request.getRequestDispatcher("/parent/sendform/sendForm.jsp").forward(request, response);

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
