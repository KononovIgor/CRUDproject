package servlets;

import dao.AbstractDaoFactory;
import dao.GenericDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<User, Integer> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();
        int userIdForDeleting = Integer.parseInt(req.getParameter("userId"));
        List<Integer> allUserIdList = userDao.getAllId();
        Integer successDeletingId = null;
        if (allUserIdList.contains(userIdForDeleting)) {
            userDao.delete(userIdForDeleting);
            successDeletingId = userIdForDeleting;
        }

        req.setAttribute("successDeletingId", successDeletingId);
        doGet(req, resp);
    }
}
