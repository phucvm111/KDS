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
import java.util.List;
import model.Menu;

/**
 *
 * @author ACE
 */
public class day_class_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet day_class_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet day_class_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAO c = new ClassDAO();
        List<model.Class> cl = c.getAllClass();

        List<Menu> getallmenu = MenuDao.getAllmenu();
        if (cl != null&& getallmenu!=null) {
            request.setAttribute("classList", cl);
            request.setAttribute("menuList", getallmenu);
        }

        request.getRequestDispatcher("/management_nutrition/menu.jsp").forward(request, response);

    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy classId từ parameter hoặc attribute
        String classIdStr = request.getParameter("classId");
        if (classIdStr == null) {
            Object attr = request.getAttribute("classId");
            if (attr != null) {
                classIdStr = attr.toString();
            }
        }

        // Lấy menudate từ parameter hoặc attribute
        String menuDate = request.getParameter("menudate");
        if (menuDate == null) {
            Object attr = request.getAttribute("menudate");
            if (attr != null) {
                menuDate = attr.toString();
            }
        }

        try {
            int classId = Integer.parseInt(classIdStr);

            List<Menu> getallmenu = MenuDao.getAllmenu();
            List<model.Class> classList = new ClassDAO().getAllClass();
            List<Menu> filteredMenu = MenuDao.getMenusByClassAndDate(classId, menuDate);

            model.Class selectedClass = new ClassDAO().getClassByID(classId);

            request.setAttribute("menuList", getallmenu);
            request.setAttribute("menuListFiltered", filteredMenu);
            request.setAttribute("classList", classList);
            request.setAttribute("selectedClassId", classId);
            request.setAttribute("selectedDate", menuDate);
            request.setAttribute("selectedClass", selectedClass);

            request.getRequestDispatcher("/management_nutrition/menu.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(400, "classId không hợp lệ: " + classIdStr);
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
