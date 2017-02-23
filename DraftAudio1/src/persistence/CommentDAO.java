package persistence;

import java.util.List;

import model.Comment;

public interface CommentDAO {

	public void save(Comment comment);
	public Comment fidByPrimaryKey(int id);
	public List<Comment> findAll();
	public void update(Comment comment);
	public void delete (Comment comment);
}
