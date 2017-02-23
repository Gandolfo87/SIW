package persistence;

import java.util.List;

import model.Draft;

public interface DraftDAO {
	public void save(Draft draft);
	public Draft findByPrimaryKey(int id);
	public List<Draft> findAll();
	public void update (Draft draft);
	public void delete (Draft draft);
}
