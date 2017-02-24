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

@WebServlet("/LoadDraft")

public class LoadDraft extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		Draft draft = null;
		draft = db.getDraftDAO().findByPrimaryKey((Integer.parseInt(req.getParameter("id"))));
		String s="";
		for(String i: draft.getMusicalFigure()){
			s+=i;
			s+="+";
		}
		System.out.println(s);
		req.getSession().setAttribute("loadDraft", draft);
		req.setAttribute("music", s);
		String nextJSP = "/Editor.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
	}
	
}
