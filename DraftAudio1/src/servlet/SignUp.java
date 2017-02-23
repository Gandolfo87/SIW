package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import manager.DBManager;
import model.User;

@WebServlet("/SignUp")

public class SignUp extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsonString = req.getParameter("addUser");
		if(jsonString != null){
			ObjectMapper mapper = new ObjectMapper();
			User user = (User) mapper.readValue(jsonString, User.class);
		Boolean present = false;
		if(DBManager.getInstance().getUserDAO().findByPrimaryKey(user.getEmail()) != null){
			resp.getWriter().print("1");
			resp.setStatus(HttpServletResponse.SC_OK);
			
		}
		else{
		List<User> l = DBManager.getInstance().getUserDAO().findAll();
			for(User i: l){
				if(i.getNickName().equalsIgnoreCase(user.getNickName())){
					present = true;
					break;
				}
			}
			if(!present){
				DBManager.getInstance().getUserDAO().save(user);
				HttpSession session = req.getSession();
				session.setAttribute("NickName", user.getNickName());
				session.setAttribute("Email", user.getEmail());
			}
			else {
				resp.getWriter().print("1");
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		}
	}
}
}
