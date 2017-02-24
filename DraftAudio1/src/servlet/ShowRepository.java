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
import javax.swing.plaf.synth.SynthSpinnerUI;

import manager.DBManager;
import model.Draft;


@WebServlet("/ShowRepository")

public class ShowRepository extends HttpServlet{
	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		List<Draft> result = new LinkedList<Draft>();
		List<Draft> l = new LinkedList<Draft>();
		l = db.getDraftDAO().findAll();
		for(Draft i: l){
			if(i.getAuthorDraft().equals(req.getSession().getAttribute("Email"))){
				result.add(i);//TODO visualizzare la lista su html
			}
		}
		resp.setStatus(HttpServletResponse.SC_OK);
		req.getSession().setAttribute("list", result);
		
		String nextJSP = "profile.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(nextJSP);
    for (Draft draft : l) {
	}
        dispatcher.forward(req, resp);
	}
}
