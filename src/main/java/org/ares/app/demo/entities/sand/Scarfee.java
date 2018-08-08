package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="Scarfee.findAll", query="SELECT s FROM Scarfee s")
public class Scarfee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cfid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date feetime;

	private int money;

	@ManyToOne
	@JoinColumn(name="carid")
	private Scarinfo scarinfo;

	public Scarfee() {
	}

	public int getCfid() {
		return cfid;
	}
	public void setCfid(int cfid) {
		this.cfid = cfid;
	}

	public Date getFeetime() {
		return feetime;
	}

	public void setFeetime(Date feetime) {
		this.feetime = feetime;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Scarinfo getScarinfo() {
		return this.scarinfo;
	}

	public void setScarinfo(Scarinfo scarinfo) {
		this.scarinfo = scarinfo;
	}

}