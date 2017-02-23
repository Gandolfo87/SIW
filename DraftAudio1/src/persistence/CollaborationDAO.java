package persistence;

import java.util.List;

import model.Collaboration;

public interface CollaborationDAO {
	public void save(Collaboration collaboration);
	public List<Collaboration> fidByPrimaryKey(int id);
	public List<Collaboration> findAll();
	public void update(Collaboration collaboration);
	public void delete (Collaboration collaboration);
}
