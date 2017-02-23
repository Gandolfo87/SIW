package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import manager.DataSource;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;;

public class UserDAOjdbc implements UserDAO {
	private DataSource dataSource;
	public UserDAOjdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(User user) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into user(NickName, Email, Name, Surname, Password) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, user.getNickName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getName());
			statement.setString(4, user.getSurname());
			statement.setString(5, user.getPassword());
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
	public User findByPrimaryKey(String email) {
		Connection connection = dataSource.getConnection();
		User user = null;
		try {
			String findByKeY = "SELECT * FROM user WHERE Email = ?";
			PreparedStatement statement = connection.prepareStatement(findByKeY);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				user = new User();
				user.setNickName(result.getString("NickName"));
				user.setEmail(result.getString("Email"));
				user.setName(result.getString("Name"));
				user.setSurname(result.getString("Surname"));
				user.setPassword(result.getString("Password"));
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
		return user;
	}

	@Override
	public List<User> findAll() {
		Connection connection = dataSource.getConnection();
		List<User> user = new LinkedList<>();
		try {
			String findAll = "SELECT * FROM user";
			PreparedStatement statement = connection.prepareStatement(findAll);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				User userTmp = new User();
				userTmp.setNickName(result.getString("NickName"));
				userTmp.setEmail(result.getString("Email"));
				userTmp.setName(result.getString("Name"));
				userTmp.setSurname(result.getString("Surname"));
				userTmp.setPassword(result.getString("Password"));
				user.add(userTmp);
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
		return user;
	}

	@Override
	public void update(User user) {
		Connection connection = dataSource.getConnection();
		try {
			String update = "update user SET NickName = ?, Name = ?, Surname = ?, Password = ? WHERE Email = ? ";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user.getNickName());
			statement.setString(2, user.getName());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
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
	public void delete(User user) {
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM user WHERE Email = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, user.getEmail());
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
