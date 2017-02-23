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

@WebServlet("/ShowPublicRepo")

public class ShowPublicRepository extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		List<Draft> result = new LinkedList<>();
		List<Draft> l = new LinkedList<>();
		l = db.getDraftDAO().findAll();

		for(Draft i: l){
			if(i.getAuthorDraft().equals(req.getSession().getAttribute("Email")) && i.isPublic()){
				result.add(i);//TODO visualizzare la lista su html
			}
		}
		for(Draft f : result)
			System.out.println(f.getName());
		req.getSession().setAttribute("result", result);
		for(Draft s : result)
			System.out.println(s.getName());
		String nextJSP = "/Results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
	}
}
