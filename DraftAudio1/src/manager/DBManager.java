package manager;

import persistence.*;

public class DBManager {
private static DBManager instance = null;
private UserDAO userDAO;
private DraftDAO draftDAO;
private CommentDAO commentDAO;
private CollaborationDAO collaboratioDAO;
	
	public UserDAO getUserDAO() {
	return userDAO;
}

public CollaborationDAO getCollaboratioDAO() {
		return collaboratioDAO;
	}

public DraftDAO getDraftDAO() {
	return draftDAO;
}

public CommentDAO getCommentDAO() {
	return commentDAO;
}

	public static DBManager getInstance(){
		if (instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public DBManager() {
		DAOFactory daoFactory = new MySQLDAOFactory("localhost", "draftaudio", "3306", "root", "alblaalcho");
		userDAO = daoFactory.getUserDAO();
		draftDAO = daoFactory.getDraftDAO();
		commentDAO = daoFactory.getCommentDAO();
		collaboratioDAO = daoFactory.getCollaborationDAO();
	}
	
}
