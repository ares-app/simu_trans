package org.ares.app.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Loginuser {

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	@Id
	private String username;
	private String userpwd;
	private String userrole;
	
}
