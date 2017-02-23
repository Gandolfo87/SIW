package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import manager.DBManager;
import model.Draft;

@WebServlet("/SaveDraft")

public class SaveDraft extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Draft draft = new Draft();
		String jsonString = req.getParameter("saveDraft");
		if(jsonString != null){
		ObjectMapper mapper = new ObjectMapper();
		draft = (Draft)mapper.readValue(jsonString, Draft.class);
		if(req.getParameter("publicDraft").equals("true")){
			draft.setPublic(false);
		}
		else
			draft.setPublic(true);
		draft.setAuthorDraft((String)req.getSession().getAttribute("Email"));
		for(String s : draft.getMusicalFigure())
			System.out.println(s);
		DBManager.getInstance().getDraftDAO().save(draft);
		}
		else{
			resp.getWriter().print("1");
			System.out.println("File vuoto");
			resp.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
