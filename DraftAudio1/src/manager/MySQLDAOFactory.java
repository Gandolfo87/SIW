package manager;




import persistence.CollaborationDAO;
import persistence.CollaborationDAOjdbc;
import persistence.CommentDAO;
import persistence.CommentDAOjdbc;
import persistence.DraftDAO;
import persistence.DraftDAOjdbc;
import persistence.UserDAO;
import persistence.UserDAOjdbc;

public class MySQLDAOFactory extends DAOFactory {
	
	DataSource dataSource = null;
	
	public MySQLDAOFactory(String host, String databaseName, String port, String user, String password) {
		try{
		      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		      dataSource = new DataSource("jdbc:mysql://" + host + ":" + port + "/" + databaseName +"?serverTimezone=UTC", user, password);
		    
		} catch (Exception e) {
			System.err.println("MYSQLDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	@Override
	public UserDAO getUserDAO() {
		
		return new UserDAOjdbc(dataSource);
	}

	@Override
	public DraftDAO getDraftDAO() {
		return new DraftDAOjdbc(dataSource);
	}

	@Override
	public CommentDAO getCommentDAO() {
		return new CommentDAOjdbc(dataSource);
	}

	@Override
	public CollaborationDAO getCollaborationDAO() {
		return new CollaborationDAOjdbc(dataSource);
	}

}
