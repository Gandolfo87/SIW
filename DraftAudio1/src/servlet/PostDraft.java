package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Draft;

public class PostDraft extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		Draft draft = db.getDraftDAO().findByPrimaryKey(Integer.parseInt(req.getParameter("ID")));
		draft.setPublic(!draft.isPublic());
		db.getDraftDAO().update(draft);
	}
}
