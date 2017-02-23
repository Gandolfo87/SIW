package manager;

import persistence.CollaborationDAO;
import persistence.CommentDAO;
import persistence.DraftDAO;
import persistence.UserDAO;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();
	public abstract DraftDAO getDraftDAO();
	public abstract CommentDAO getCommentDAO();
	public abstract CollaborationDAO getCollaborationDAO();
}
