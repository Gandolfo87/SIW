package manager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
	final private String dbURI;
	final private String userName;
	final private String password;
	
	public DataSource(String dbURI, String userName, String password) {
		this.dbURI = dbURI;
		this.userName = userName;
		this.password = password;
	}
	
	public Connection getConnection(){
		Connection connection;
		try {
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch(java.sql.SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return connection;
	}
}
