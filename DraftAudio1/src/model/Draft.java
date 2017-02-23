package model;

import java.util.List;

public class Draft {
	private int id;
	private String name;
	private String authorDraft;
	private String composer;
	private int tempo;
	private String timeSignature;
	private String noteUnit;
	private String keySignature;
	private String musicalFigure [];
	private boolean isPublic;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public String getTimeSignature() {
		return timeSignature;
	}
	public void setTimeSignature(String timeSignature) {
		this.timeSignature = timeSignature;
	}
	public String getNoteUnit() {
		return noteUnit;
	}
	public void setNoteUnit(String noteUnit) {
		this.noteUnit = noteUnit;
	}
	public String getKeySignature() {
		return keySignature;
	}
	public void setKeySignature(String keySignature) {
		this.keySignature = keySignature;
	}
	public String[] getMusicalFigure() {
		return musicalFigure;
	}
	public void setMusicalFigure(String[] musicalFigure) {
		this.musicalFigure = musicalFigure;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String getAuthorDraft() {
		return authorDraft;
	}
	public void setAuthorDraft(String authorDraft) {
		this.authorDraft = authorDraft;
	}
//	public String[] getCollaboratori() {
//		return collaborators;
//	}
//	public void setCollaboratori(String[] collaboratori) {
//		this.collaborators = collaboratori;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
