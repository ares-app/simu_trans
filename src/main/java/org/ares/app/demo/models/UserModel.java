package org.ares.app.demo.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserModel implements Serializable {

	public UserModel() {
	}
	public UserModel(String userid, String username, String password, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserModel [username=" + username + ", password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	}

	private String username;
	private String password;
	private String email;
	private String role;;
}
