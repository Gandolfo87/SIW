package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import manager.DBManager;
import model.Draft;

public class UpdateDraft extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	DBManager db = DBManager.getInstance();
	Draft draft = new Draft();
	String jsonString = req.getParameter("draft");
	ObjectMapper mapper = new ObjectMapper();
	draft = (Draft)mapper.readValue(jsonString, Draft.class);
	draft.setAuthorDraft("AAA");//TODO da cancella solo per test
	db.getDraftDAO().update(draft);
	}
}
