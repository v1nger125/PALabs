package servelts;

import Beans.UserBean;
import entities.User;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class MainServlet extends HttpServlet {

    @EJB
    private UserBean userBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userBean.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
        req.setAttribute("users", users);
        dispatcher.forward(req, resp);
    }
}
