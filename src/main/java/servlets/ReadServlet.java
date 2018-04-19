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
import java.util.LinkedList;
import java.util.List;

public class ReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsersList = new LinkedList<User>();
        GenericDao<User, Integer> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();
        allUsersList = userDao.read();
        req.setAttribute("listUsers", allUsersList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/read.jsp");
        requestDispatcher.forward(req, resp);
    }
}
