package controller.parent;

import dal.FeedbackTeacherDAO;
import model.FeedbackTeacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditFeedbackServlet", urlPatterns = {"/EditFeedbackServlet"})
public class EditFeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FeedbackTeacherDAO feedbackTeacherDAO = new FeedbackTeacherDAO();

    // Display feedback for editing
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        FeedbackTeacher feedback = feedbackTeacherDAO.getFeedbackById(feedbackId);

        // Ensure that the full feedback object is passed to the JSP (including teacherId and parentId)
        request.setAttribute("feedback", feedback);
        request.getRequestDispatcher("/parent/editFeedback.jsp").forward(request, response);
    }

    // Handle feedback update
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        String fbContent = request.getParameter("fbContent");
        float rating = Float.parseFloat(request.getParameter("rating"));

        // Retrieve the existing feedback object to maintain parentId and teacherId
        FeedbackTeacher existingFeedback = feedbackTeacherDAO.getFeedbackById(feedbackId);

        // Create a new FeedbackTeacher object, maintaining the parentId and teacherId
        FeedbackTeacher updatedFeedback = new FeedbackTeacher(
                feedbackId,
                existingFeedback.getParentId(),
                existingFeedback.getTeacherId(),
                fbContent,
                rating,
                new java.util.Date() // Current date
        );

        // Update the feedback in the database
        boolean success = feedbackTeacherDAO.updateFeedback(updatedFeedback);

        // Redirect based on the success of the update
        if (success) {
            response.sendRedirect("FeedbackTeacherServlet?teacherId=" + existingFeedback.getTeacherId());
        } else {
            request.setAttribute("error", "Failed to update feedback.");
            doGet(request, response);
        }
    }
}
