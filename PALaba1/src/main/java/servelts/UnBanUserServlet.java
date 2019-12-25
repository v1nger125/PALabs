package servelts;

import Beans.UserBean;
import entities.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/unBan")
public class UnBanUserServlet extends HttpServlet {
    @EJB
    private UserBean userBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && id != "") {
            User user = userBean.get(Long.parseLong(id));
            userBean.unBanUser(user);
        }
        resp.sendRedirect("list");
    }
}
