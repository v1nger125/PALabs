package servelts;

import Beans.HistoryBean;
import entities.HistoryItem;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    @EJB
    HistoryBean historyBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HistoryItem> history = historyBean.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/history.jsp");
        req.setAttribute("history", history);
        dispatcher.forward(req, resp);
    }
}