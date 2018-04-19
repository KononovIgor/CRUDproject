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

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<User, Integer> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();

        User user = new User();
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));

        userDao.update(user, Integer.parseInt(req.getParameter("userId")));

        req.setAttribute("userName", user.getName());
        doGet(req, resp);
    }
}
