package controller.parent;
import dal.KindergartnerDAO;
import dal.ClassDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Kindergartner;

@WebServlet(name = "ChildrenListServlet", urlPatterns = {"/childrenlist"})
public class ChildrenListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        int parentId = account.getAccountID();
        KindergartnerDAO kindergartnerDAO = new KindergartnerDAO();
        List<Kindergartner> kindergartners = kindergartnerDAO.getKindergartnersByParentId(parentId);
        System.out.println(parentId);
        
        
        // Lấy tên lớp học cho mỗi trẻ
        for (Kindergartner k : kindergartners) {
            String className = kindergartnerDAO.getClassNameById(k.getClass_id());
            k.setClassName(className); // Giả sử bạn đã thêm trường className vào lớp Kindergartner
        }
        
        request.setAttribute("kindergartners", kindergartners);
        request.getRequestDispatcher("/parent/childreninformation.jsp").forward(request, response);
    }
}