package servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Draft;


@WebServlet("/ShowRepository")

public class ShowRepository extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		List<Draft> result = new LinkedList<>();
		List<Draft> l = new LinkedList<>();
		l = db.getDraftDAO().findAll();
		for(Draft i: l){
			if(i.getAuthorDraft() == req.getSession().getAttribute("Email")){
				result.add(i);//TODO visualizzare la lista su html
			}
		}	
		req.setAttribute("list", result);
		String nextJSP = "/profile.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
	}
}
