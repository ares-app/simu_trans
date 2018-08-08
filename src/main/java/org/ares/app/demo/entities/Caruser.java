package org.ares.app.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Caruser {

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getPregisterdate() {
		return pregisterdate;
	}
	public void setPregisterdate(String pregisterdate) {
		this.pregisterdate = pregisterdate;
	}
	@Id
	private String username;
	private String pname;
	private String pcardid;
	private String psex;
	private String ptel;
	private String pregisterdate;
	
}
