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


/**
 * The persistent class for the scarcharge database table.
 * 
 */
@Entity
@NamedQuery(name="Scarcharge.findAll", query="SELECT s FROM Scarcharge s")
public class Scarcharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int ccid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date chargetime;

	private int money;

	//bi-directional many-to-one association to Scarinfo
	@ManyToOne
	@JoinColumn(name="carid")
	private Scarinfo scarinfo;

	public Scarcharge() {
	}

	public int getCcid() {
		return this.ccid;
	}

	public void setCcid(int ccid) {
		this.ccid = ccid;
	}

	public Date getChargetime() {
		return this.chargetime;
	}

	public void setChargetime(Date chargetime) {
		this.chargetime = chargetime;
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