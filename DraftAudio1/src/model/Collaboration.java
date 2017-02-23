package model;

public class Collaboration {
	private int idDraft;
	private String associate;
	private Boolean accepted;
	
	public int getIdDraft() {
		return idDraft;
	}
	public void setIdDraft(int idDraft) {
		this.idDraft = idDraft;
	}
	public String getAssociate() {
		return associate;
	}
	public void setAssociate(String associate) {
		this.associate = associate;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
}
