package model;

import java.sql.Date;

public class Comment {
	private int id;
	private String text;
	private Date data;
	private String author;
	private int idDraft;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdDraft() {
		return idDraft;
	}
	public void setIdDraft(int idDraft) {
		this.idDraft = idDraft;
	}
}
