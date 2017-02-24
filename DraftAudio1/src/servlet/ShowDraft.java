package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Draft;

@WebServlet("/ShowDraft")

public class ShowDraft extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		Draft draft = null;
		draft = db.getDraftDAO().findByPrimaryKey((Integer.parseInt(req.getParameter("id"))));
		draft.setVisit(draft.getVisit()+1);
		db.getDraftDAO().update(draft);
		String s = "T:" + draft.getName()+ "\n"+"C:" + draft.getComposer() + "\n"+"K:" + draft.getKeySignature() + "\n"+
		"M:" + draft.getTimeSignature() + "\n"+"L:" + draft.getNoteUnit() + "\n"+"Q:" + draft.getTempo() + "\n";
		for(String i : draft.getMusicalFigure()){
			s+=i;
		}
		req.getSession().setAttribute("draft", s);
		String nextJSP = "/user1.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
	}
}
