package controller.parent;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

@WebServlet(name = "ParentProfileServlet", urlPatterns = {"/parentprofile"})
public class ParentProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Account sessionAccount = (Account) session.getAttribute("account");
        
        AccountDAO dao = new AccountDAO();
        Account parent = null;
        try {
            
            parent = dao.getParentByID(sessionAccount.getAccountID());
        } catch (Exception ex) {
           
            Logger.getLogger(ParentProfileServlet.class.getName()).log(Level.SEVERE, "Error retrieving parent account from database.", ex);
            
            response.sendRedirect(request.getContextPath() + "/home"); 
            return; 
        }

        if (parent != null) {
           
            if (parent.getRole().getRoleID() == 3) { 
                request.setAttribute("parentAccount", parent);

                String successMessage = (String) session.getAttribute("successMessage");
                if (successMessage != null) {
                    request.setAttribute("successMessage", successMessage);
                    session.removeAttribute("successMessage");
                }

                request.getRequestDispatcher("/parent/parentprofile.jsp").forward(request, response);
            } else {
                
                Logger.getLogger(ParentProfileServlet.class.getName()).log(Level.WARNING, "Account with ID " + sessionAccount.getAccountID() + " is logged in as parent but has role_id " + parent.getRole().getRoleID());
                session.invalidate(); 
                response.sendRedirect(request.getContextPath() + "/login?error=unauthorized_access");
            }
        } else {
            Logger.getLogger(ParentProfileServlet.class.getName()).log(Level.WARNING, "Parent account not found for ID: " + sessionAccount.getAccountID() + ". Redirecting to login.");
            session.invalidate(); 
            response.sendRedirect(request.getContextPath() + "/login?error=account_not_found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Parent Profile Servlet";
    }
}