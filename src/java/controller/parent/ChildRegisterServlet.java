package controller.parent;

import dal.ClassDAO;
import dal.KindergartnerDAO;
import dal.StudyRecordDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Class;
import model.Kindergartner;
import model.StudyRecord;

@WebServlet(name = "ChildRegisterServlet", urlPatterns = {"/childregister"})
public class ChildRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAO cdao = new ClassDAO();
        List<Class> classes = cdao.getAllClass();
        request.setAttribute("classlist", classes);
        request.getRequestDispatcher("parent/childregister.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy account từ session
        HttpSession session = request.getSession();
        Account parent = (Account) session.getAttribute("account");

        if (parent == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu từ form
        String firstName = request.getParameter("ChildFirstName");
        String lastName = request.getParameter("ChildLastName");
        String dob = request.getParameter("DOB");
        boolean gender = "male".equals(request.getParameter("flexRadioDefault"));
        String img = request.getParameter("img");
        int classId = Integer.parseInt(request.getParameter("register_classid"));

        // Tạo đối tượng Kindergartner
        Kindergartner kinder = new Kindergartner();
        kinder.setParentAccount(parent);
        kinder.setFirst_name(firstName);
        kinder.setLast_name(lastName);
        kinder.setDob(dob);
        kinder.setGender(gender);
        kinder.setImg(img);
        kinder.setAddress(parent.getAddress());
        kinder.setParentPhone(parent.getPhoneNumber());

        // Lưu vào DB
        KindergartnerDAO kinderDAO = new KindergartnerDAO();
        kinderDAO.insertKinder(kinder);

        // Lấy ID mới thêm
        int newKinderId = kinderDAO.getAllStudent().stream()
                .filter(k -> k.getFirst_name().equals(firstName)
                && k.getLast_name().equals(lastName)
                && k.getDob().equals(dob)
                && k.getParentAccount().getAccountID() == parent.getAccountID())
                .map(Kindergartner::getKinder_id)
                .reduce((first, second) -> second) // lấy ID lớn nhất
                .orElse(-1);

        // Gán vào StudyRecord
        StudyRecordDAO studyDAO = new StudyRecordDAO();
        studyDAO.addStudyRecord(new StudyRecord(0, new ClassDAO().getClassByID(classId), kinderDAO.getKinderById(newKinderId), 2025, false, false));

        response.sendRedirect("childdetailservlet"); // chuyển hướng về danh sách
    }

    @Override
    public String getServletInfo() {
        return "Handles child registration for parents.";
    }
}
