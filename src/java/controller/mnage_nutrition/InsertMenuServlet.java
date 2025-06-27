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
public class InsertMenuServlet extends HttpServlet {

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
            out.println("<title>Servlet InsertMenuServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertMenuServlet at " + request.getContextPath() + "</h1>");
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

        request.getRequestDispatcher("/management_nutrition/menu.jsp").forward(request, response);
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
            String classIdStr = request.getParameter("classId");
            String menudate = request.getParameter("menudate");
            String mealType = request.getParameter("mealType");
            String dish = request.getParameter("dish");
            String caloriesStr = request.getParameter("calories");
            String notes = request.getParameter("notes");

            if (classIdStr == null || classIdStr.trim().isEmpty()
                    || menudate == null || menudate.trim().isEmpty()
                    || mealType == null || mealType.trim().isEmpty()
                    || dish == null || dish.trim().isEmpty()
                    || caloriesStr == null || caloriesStr.trim().isEmpty()) {

                request.setAttribute("error", "Please fill in all required fields.");
                request.setAttribute("selectedDate", menudate);
                request.setAttribute("selectedClassId", classIdStr);
                request.getRequestDispatcher("day_class").forward(request, response);
                return;
            }
            if (dish.length() > 40 || caloriesStr.length() > 8 || notes.length() > 100) {
                request.setAttribute("error", "Information fail");
                request.getRequestDispatcher("day_class").forward(request, response);
                return;
            }
            if (Float.parseFloat(caloriesStr) < 0) {
                request.setAttribute("error", "Calories not nagative !");
                request.getRequestDispatcher("day_class").forward(request, response);
                return;
            }

            int classId = Integer.parseInt(classIdStr);
            float calories = Float.parseFloat(caloriesStr.trim());

            // Tạo đối tượng Menu
            Menu m = new Menu();
            m.setMenudate(menudate);
            m.setMenutype(mealType);
            m.setDish(dish);
            m.setCalories(calories);
            m.setNotes(notes != null ? notes : "");
            m.setCalssid(new ClassDAO().getClassByID(classId));

            // Gọi DAO để insert
            MenuDao.insertMenu(m);

            request.getRequestDispatcher("day_class").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Dữ liệu nhập không hợp lệ (có thể sai định dạng calo).");
            request.getRequestDispatcher("day_class").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi thêm món ăn.");
            request.getRequestDispatcher("day_class").forward(request, response);
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
