package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import manager.DataSource;
import model.Collaboration;
import model.Comment;

public class CollaborationDAOjdbc implements CollaborationDAO{
	private DataSource dataSource;
	public CollaborationDAOjdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public void save(Collaboration collaboration) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into collaboration(IDDraft, Associate, Accepted) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, collaboration.getIdDraft());
			statement.setString(2, collaboration.getAssociate());
			statement.setBoolean(3, collaboration.getAccepted());
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				connection.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}	
		
	}
	@Override
	public List<Collaboration> fidByPrimaryKey(int id) {
		Connection connection = dataSource.getConnection();
		List<Collaboration> l = new LinkedList<>();
		try{
			String findByKey = "SELECT * FROM collaboration WHERE IDDraft = ?";
			PreparedStatement statement = connection.prepareStatement(findByKey);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			int i = 0;
			while(result.next()){
				Collaboration c = new Collaboration();
				c.setIdDraft(result.getInt("IDDraft"));
				c.setAccepted(result.getBoolean("Accepted"));
				c.setAssociate(result.getString("Associate"));
				l.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return l;
	}
	@Override
	public List<Collaboration> findAll() {
		Connection connection = dataSource.getConnection();
		List<Collaboration> l = new LinkedList<>();
		try{
			String findByKey = "SELECT * FROM collaboration";
			PreparedStatement statement = connection.prepareStatement(findByKey);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Collaboration c = new Collaboration();
				c.setIdDraft(result.getInt("IDDraft"));
				c.setAccepted(result.getBoolean("Accepted"));
				c.setAssociate(result.getString("Associate"));
				l.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return l;	}
	
	@Override
	public void update(Collaboration collaboration) {
		Connection connection = dataSource.getConnection();
		try {
			String update = "update collaboration SET Accepted = ? WHERE IDDraft = ?, Associate = ? ";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, collaboration.getAssociate());
			statement.setBoolean(2, collaboration.getAccepted());
			statement.setInt(3, collaboration.getIdDraft());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				connection.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	@Override
	public void delete(Collaboration collaboration) {
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM collaboration WHERE IDDraft = ?, Associate = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, collaboration.getIdDraft());
			statement.setString(1, collaboration.getAssociate());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				connection.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}		
	}
}	