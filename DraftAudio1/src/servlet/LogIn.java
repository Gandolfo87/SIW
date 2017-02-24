package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import manager.DBManager;
import model.User;

@WebServlet("/login")

public class LogIn extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DBManager bd = DBManager.getInstance();
		User tmp = new User();
		String jsonString = req.getParameter("logUser");
		if(jsonString != null){
			ObjectMapper mapper = new ObjectMapper();
			tmp = (User)mapper.readValue(jsonString, User.class);
			User user = null;
			user = bd.getUserDAO().findByPrimaryKey(tmp.getEmail());
			if(user.getPassword().equals(tmp.getPassword())){
					HttpSession session=req.getSession();
					session.setAttribute("NickName",user.getNickName());
					session.setAttribute("Email", user.getEmail());
			}else{
				resp.getWriter().print("1");
				resp.setStatus(HttpServletResponse.SC_OK); 
			}
		}else{
			resp.getWriter().print("1");
			resp.setStatus(HttpServletResponse.SC_OK); 
		}
	}
}
