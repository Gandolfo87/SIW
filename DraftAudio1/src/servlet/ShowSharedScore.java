package servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DBManager;
import model.Collaboration;
import model.Draft;

public class ShowSharedScore extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		int [] result = null;
		List<Collaboration> l = new LinkedList<>();
		l = db.getCollaboratioDAO().findAll();
		int j = 0;
		for(Collaboration i: l){
			if(i.getAssociate() == req.getSession().getAttribute("Email")){
				result[j] = i.getIdDraft();// .add(i);//TODO visualizzare la lista su html
				j++;
			}
		}
		List<Draft> list = new LinkedList<>();
		for(int i = 0; i < result.length; i++){
			list.add(db.getDraftDAO().findByPrimaryKey(result[i]));
		}
	}
}
