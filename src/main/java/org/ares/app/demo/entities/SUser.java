package org.ares.app.demo.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SUser {

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
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcardid() {
		return pcardid;
	}
	public void setPcardid(String pcardid) {
		this.pcardid = pcardid;
	}
	public String getPsex() {
		return psex;
	}
	public void setPsex(String psex) {
		this.psex = psex;
	}
	public String getPtel() {
		return ptel;
	}
	public void setPtel(String ptel) {
		this.ptel = ptel;
	}
	public Date getPregisterdate() {
		return pregisterdate;
	}
	public void setPregisterdate(Date pregisterdate) {
		this.pregisterdate = pregisterdate;
	}

	@Override
	public String toString() {
		return "SUser [" + (username != null ? "username=" + username + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (role != null ? "role=" + role + ", " : "")
				+ (pname != null ? "pname=" + pname + ", " : "") + (pcardid != null ? "pcardid=" + pcardid + ", " : "")
				+ (psex != null ? "psex=" + psex + ", " : "") + (ptel != null ? "ptel=" + ptel + ", " : "")
				+ (pregisterdate != null ? "pregisterdate=" + pregisterdate : "") + "]";
	}

	@Id
	private String username;
	private String password;
	private String email;
	private String role;
	
	private String pname;
	private String pcardid;
	private String psex;
	private String ptel;
	private Date pregisterdate;
	
}
