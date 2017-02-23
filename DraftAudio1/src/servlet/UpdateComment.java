package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Comment;

public class UpdateComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		Comment comment = new Comment();
		comment.setId(Integer.parseInt(req.getParameter("id")));
		comment.setText(req.getParameter("text"));
		comment.setIdDraft(Integer.parseInt(req.getParameter("idDraft")));
		comment.setAuthor(req.getParameter("author"));
		comment.setData(Date.valueOf(req.getParameter("date")));
		db.getCommentDAO().update(comment);
	}
}
