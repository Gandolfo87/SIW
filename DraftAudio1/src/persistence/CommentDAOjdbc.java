package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import manager.DataSource;
import model.Comment;

public class CommentDAOjdbc implements CommentDAO {
	private DataSource dataSource;
	public CommentDAOjdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Comment comment) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into comment(Text, Author, Draft, Date) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, comment.getText());
			statement.setString(2, comment.getAuthor());
			statement.setInt(3, comment.getIdDraft());
			statement.setDate(4, comment.getData());
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
	public Comment fidByPrimaryKey(int id) {
		Connection connection = dataSource.getConnection();
		Comment comment = null;
		try {
			String findByKeY = "SELECT * FROM comment WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(findByKeY);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				comment.setAuthor(result.getString("Author"));
				comment.setData(result.getDate("Date"));
				comment.setIdDraft(result.getInt("Draft"));
				comment.setText(result.getString("Text"));
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
		return comment;
	}

	@Override
	public List<Comment> findAll() {
		Connection connection = dataSource.getConnection();
		List<Comment> comment = new LinkedList<>();
		try {
			String findAll = "SELECT * FROM comment";
			PreparedStatement statement = connection.prepareStatement(findAll);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Comment commentTmp = new Comment();
				commentTmp.setAuthor(result.getString("author"));
				commentTmp.setData(result.getDate("date"));
				commentTmp.setIdDraft(result.getInt("Draft"));
				commentTmp.setText(result.getString("Text"));
				commentTmp.setId(result.getInt("ID"));
				comment.add(commentTmp);
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
		return comment;
	}

	@Override
	public void update(Comment comment) {
		Connection connection = dataSource.getConnection();
		try {
			String update = "update comment SET Text = ?, Author = ?, Draft = ?, Date = ? WHERE ID = ? ";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, comment.getText());
			statement.setString(2, comment.getAuthor());
			statement.setInt(3, comment.getIdDraft());
			statement.setDate(4, comment.getData());
			statement.setInt(5, comment.getId());
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
	public void delete(Comment comment) {
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM comment WHERE ID = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, comment.getId());
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
