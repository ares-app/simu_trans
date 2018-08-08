package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the setctralog database table.
 * 
 */
@Entity
@NamedQuery(name="Setctralog.findAll", query="SELECT s FROM Setctralog s")
public class Setctralog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int etlid;

	private int carid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date intime;

	private int money;

	@Temporal(TemporalType.TIMESTAMP)
	private Date outtime;

	public Setctralog() {
	}

	public int getEtlid() {
		return this.etlid;
	}

	public void setEtlid(int etlid) {
		this.etlid = etlid;
	}

	public int getCarid() {
		return this.carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getOuttime() {
		return this.outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

}