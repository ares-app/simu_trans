package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name="Scarblacklist.findAll", query="SELECT c FROM Scarblacklist c")
public class Scarblacklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cbid;
	private int carid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	private String username;

	public Scarblacklist() {
	}

	public int getCbid() {
		return this.cbid;
	}

	public void setCbid(int cbid) {
		this.cbid = cbid;
	}

	public int getCarid() {
		return this.carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}