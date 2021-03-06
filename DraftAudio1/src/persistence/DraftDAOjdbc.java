
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import manager.DataSource;
import model.Draft;

public class DraftDAOjdbc implements DraftDAO {
	private DataSource dataSource;
	public DraftDAOjdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	@Override
	public void save(Draft draft) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into draft(Name, DraftAuthor, Composer, Tempo, TimeSignature, NoteUnit, KeySignature, MusicalFigure, Public, Visit) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, draft.getName());
			statement.setString(2, draft.getAuthorDraft());
			statement.setString(3, draft.getComposer());
			statement.setInt(4, draft.getTempo());
			statement.setString(5, draft.getTimeSignature());
			statement.setString(6, draft.getNoteUnit());
			statement.setString(7, draft.getKeySignature());
			String musicalFigure="";
			for(String s : draft.getMusicalFigure()){
				musicalFigure+=s;
				musicalFigure+="+";
			}
			statement.setString(8, musicalFigure);
			statement.setBoolean(9, draft.isPublic());
			statement.setInt(10, draft.getVisit());
			statement.executeUpdate();
//			insert = "insert into collaborazioni(Associate, DraftName, AuthorDraft) values(?,?,?)";
//			for(String i : draft.getCollaboratori()){
//				statement = connection.prepareStatement(insert);
//				statement.setString(1, i);
//				statement.setString(2, draft.getName());
//				statement.setString(3, draft.getAuthorDraft());
//				statement.executeUpdate();
//			}
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
	public Draft findByPrimaryKey(int id) {
		Connection connection = dataSource.getConnection();
		Draft draft = null;
		try {
			String findByKeY = "SELECT * FROM draft WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(findByKeY);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				draft = new Draft();
				draft.setId(result.getInt("ID"));
				draft.setName(result.getString("Name"));
				draft.setAuthorDraft(result.getString("DraftAuthor"));
				draft.setComposer(result.getString("Composer"));
				draft.setTempo(result.getShort("Tempo"));
				draft.setKeySignature(result.getString("KeySignature"));
				draft.setNoteUnit(result.getString("NoteUnit"));
				draft.setTimeSignature(result.getString("TimeSignature"));
				draft.setPublic(result.getBoolean("Public"));
				String[] parti = result.getString("MusicalFigure").split("\\+");
				draft.setMusicalFigure(parti);
				draft.setVisit(result.getInt("Visit"));
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
		return draft;
	}

	@Override
	public List<Draft> findAll() {
		Connection connection = dataSource.getConnection();
		List<Draft> draft = new LinkedList<>();
		try {
			String findAll = "SELECT * FROM draft";
			PreparedStatement statement = connection.prepareStatement(findAll);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Draft draftTmp = new Draft();
				draftTmp.setId(result.getInt("ID"));
				draftTmp.setName(result.getString("Name"));
				draftTmp.setAuthorDraft(result.getString("DraftAuthor"));
				draftTmp.setComposer(result.getString("Composer"));
				draftTmp.setTempo(result.getInt("Tempo"));
				draftTmp.setTimeSignature(result.getString("TimeSignature"));
				draftTmp.setNoteUnit(result.getString("NoteUnit"));
				draftTmp.setKeySignature(result.getString("KeySignature"));
				String [] part =(result.getString("MusicalFigure").split("\\+"));
				draftTmp.setMusicalFigure(part);
				draftTmp.setPublic(result.getBoolean("Public"));
				draftTmp.setVisit(result.getInt("Visit"));
				draft.add(draftTmp); 
				System.out.println("ID "+draftTmp.getId()+ " Name "+ draftTmp.getName());
			}
//			for(Draft i : draft){
//				findAll = "SELECT Associate FROM Collaboration WHERE IDDraft = ?";
//				statement.setInt(1, i.getId());
//				result = statement.executeQuery();
//				String[] tmp = null;
//				int j = 0;
//				while(result.next()){
//					tmp[j] = (result.getString("Associate"));
//					j++;
//				}
//				if(tmp.length != 0)
//					i.setCollaboratori(tmp);
//			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return draft;
	}

	@Override
	public void update(Draft draft) {
		Connection connection = dataSource.getConnection();
		try {
			String update = "UPDATE draft SET Name = ?, Composer = ?, Tempo = ?, TimeSignature = ?, NoteUnit = ?, KeySignature = ?, MusicalFigure = ?, Public = ?, DraftAuthor = ?, Visit = ? WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, draft.getName());
			statement.setString(2, draft.getComposer());
			statement.setInt(3, draft.getTempo());
			statement.setString(4, draft.getTimeSignature());
			statement.setString(5, draft.getNoteUnit());
			statement.setString(6, draft.getKeySignature());
			String musicalFigure="";
			for(String i : draft.getMusicalFigure()){
				musicalFigure+=i;
				musicalFigure+="+";
			}
			statement.setString(7, musicalFigure);
			statement.setBoolean(8, draft.isPublic());
			statement.setString(9, draft.getAuthorDraft());
			statement.setInt(10, draft.getVisit());
			statement.setInt(11, draft.getId());
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
	public void delete(Draft draft) {
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM draft WHERE ID = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, draft.getId());
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
