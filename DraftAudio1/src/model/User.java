package model;

public class User {
	private String nickName;
	private String email;
	private String name;
	private String surname;
	private String password;
	
	public User() {
		
	}
	public User(String nickName, String email, String name, String surname, String password) {
		super();
		this.nickName = nickName;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String nome) {
		this.name = nome;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String cognome) {
		this.surname = cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
}
