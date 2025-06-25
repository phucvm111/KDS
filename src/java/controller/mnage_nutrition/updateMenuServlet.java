/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mnage_nutrition;

import dal.ClassDAO;
import dal.MenuDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Menu;

/**
 *
 * @author ACE
 */
public class updateMenuServlet extends HttpServlet {

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
            out.println("<title>Servlet updateMenuServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateMenuServlet at " + request.getContextPath() + "</h1>");
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

        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            String menudate = request.getParameter("menudate");
            String mealType = request.getParameter("mealType"); // phải trùng với name trong <select>
            String dish = request.getParameter("dish");
            float calories = Float.parseFloat(request.getParameter("calories"));
            String notes = request.getParameter("notes") != null ? request.getParameter("notes") : "";
            String classIdRaw = request.getParameter("classId");

            if (classIdRaw == null || classIdRaw.isEmpty()) {
                request.setAttribute("error", "Thiếu classId.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            int classId = Integer.parseInt(classIdRaw);
            ClassDAO cls=new ClassDAO();
            model.Class clazz = cls.getClassByID(classId);
            if (clazz == null) {
                request.setAttribute("error", "classId không tồn tại: " + classId);
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng menu và set dữ liệu
            Menu m = new Menu();
            m.setMenu_id(menuId);
            m.setMenudate(menudate);
            m.setMenutype(mealType);
            m.setDish(dish);
            m.setCalories(calories);
            m.setNotes(notes);
            m.setCalssid(clazz);

            // Cập nhật trong DB
            MenuDao.updateMenu(m);

            // Điều hướng về lại trang chính
           
            request.getRequestDispatcher("day_class").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi xử lý: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
