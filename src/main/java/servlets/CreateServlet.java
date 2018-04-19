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

public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));

        GenericDao<User, Integer> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();
        userDao.create(user);

        req.setAttribute("userName", user.getName());
        doGet(req, resp);
    }
}
