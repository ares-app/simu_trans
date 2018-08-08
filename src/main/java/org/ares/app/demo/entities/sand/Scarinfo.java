package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Scarinfo.findAll", query="SELECT s FROM Scarinfo s")
public class Scarinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id private int carid;
	private int balance;
	private String caraction;

	@OneToMany(mappedBy="scarinfo")
	private List<Scarcharge> scarcharges;

	@OneToMany(mappedBy="scarinfo")
	private List<Scarfee> scarfees;
	
	public Scarinfo() {
	}

	public int getCarid() {
		return this.carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCaraction() {
		return this.caraction;
	}

	public void setCaraction(String caraction) {
		this.caraction = caraction;
	}

	public List<Scarcharge> getScarcharges() {
		return this.scarcharges;
	}

	public void setScarcharges(List<Scarcharge> scarcharges) {
		this.scarcharges = scarcharges;
	}
	
	public List<Scarfee> getScarfees() {
		return scarfees;
	}

	public void setScarfees(List<Scarfee> scarfees) {
		this.scarfees = scarfees;
	}

	public Scarcharge addScarcharge(Scarcharge scarcharge) {
		getScarcharges().add(scarcharge);
		scarcharge.setScarinfo(this);
		return scarcharge;
	}

	public Scarcharge removeScarcharge(Scarcharge scarcharge) {
		getScarcharges().remove(scarcharge);
		scarcharge.setScarinfo(null);
		return scarcharge;
	}

}