package servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Draft;

public class ShowPublicDraft extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		List<Draft> result = new LinkedList<>();
		List<Draft> l = new LinkedList<>();
		l = db.getDraftDAO().findAll();
		for(Draft i: l){
			if(i.isPublic() == true){
				result.add(i);//TODO visualizzare la lista su html
			}
		}
	}
}
