package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Collaboration;

public class AcceptCollaboration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		Collaboration collaboration = new Collaboration();
		collaboration.setIdDraft(Integer.parseInt(req.getParameter("idDraft")));
		collaboration.setAssociate(req.getParameter("associate"));
		collaboration.setAccepted(true);
		db.getCollaboratioDAO().update(collaboration);
	}
}
