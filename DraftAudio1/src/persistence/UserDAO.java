package persistence;

import java.util.List;

import model.User;

public interface UserDAO {
	public void save(User user);
	public User findByPrimaryKey(String email);
	public List<User> findAll();
	public void update(User user);
	public void delete(User user);
	
}
