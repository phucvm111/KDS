/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.activation.ActivationDataFlavor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TestObject;

/**
 *
 * @author Admin
 */
public class Cal2Servlet extends HttpServlet {

    TestDAO td = new TestDAO();

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
            out.println("<title>Servlet CalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        int a = 0, b = 0;
        String a_raw = request.getParameter("a");
        String b_raw = request.getParameter("b");
//            try ( PrintWriter out = response.getWriter()) {
//                out.println("ok");
//                out.println(a_raw);
//                out.println(b_raw);

//            }
        try {
            a = Integer.parseInt(a_raw);
            b = Integer.parseInt(b_raw);
            int c = a + b;
            String cc = "" + c;
            TestObject to = td.getLastTestObject();
            to.setC(cc);

            td.updateTestObject2(to);
            request.setAttribute("to", to);
            
        } catch (Exception e) {

        }
        request.getRequestDispatcher("calculate_1.jsp").forward(request, response);

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
        TestDAO td = new TestDAO();

        String a_raw = request.getParameter("a");
        String b_raw = request.getParameter("b");
        int a = 0, b = 0;
        try {
            a = Integer.parseInt(a_raw);
            b = Integer.parseInt(b_raw);
            TestObject to = new TestObject(0, a, b, null);
            td.addTestObject(to);
            request.setAttribute("to", to);
        } catch (Exception e) {

        }

        request.getRequestDispatcher("calculate_1.jsp").forward(request, response);
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
