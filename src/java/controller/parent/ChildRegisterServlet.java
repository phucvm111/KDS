/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.parent;

import dal.ClassDAO;
import dal.KindergartnerDAO;
import dal.StudyRecordDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Class;
import model.Kindergartner;
import model.StudyRecord;

/**
 *
 * @author NQ
 */
public class ChildRegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ChildRegister</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChildRegister at " + request.getContextPath() + "</h1>");
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
        ClassDAO cdao=new ClassDAO();
        List<Class> calsss=cdao.getAllClass();
        request.setAttribute("classlist", calsss);
        request.getRequestDispatcher("parent/childregister.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        Account acc = (Account) session.getAttribute("account");
        String childfirstname = request.getParameter("ChildFirstName");
        String childlastname = request.getParameter("ChildLastName");
        String DOB = request.getParameter("DOB");
        String gender = request.getParameter("flexRadioDefault");
        String childimg = request.getParameter("ChildImg");
        String childclass = request.getParameter("ChildClass");
        boolean childgd = true;
        int kinder_classid = Integer.parseInt(request.getParameter("register_classid"));
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        if (gender.equals("male")) {
            childgd = true;
        } else if (gender.equals("female")) {
            childgd = false;
        }
        KindergartnerDAO d = new KindergartnerDAO();
        StudyRecordDAO srdao = new StudyRecordDAO();
        ClassDAO cdao = new ClassDAO();
        Kindergartner newchild = new Kindergartner(0,acc, childfirstname, childlastname, DOB, childgd, childimg);
        d.insertKinder(newchild);
        List<Kindergartner> kidlist = d.getAllStudent();
        Kindergartner newaddedchild = kidlist.get(kidlist.size()-1); 
        srdao.addStudyRecord(new StudyRecord(0,cdao.getClassByID(kinder_classid), newaddedchild,year));
        
        request.getRequestDispatcher("parent/childregister.jsp").forward(request, response);
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
