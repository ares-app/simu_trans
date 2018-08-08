package org.ares.app.demo.entities.sand;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;

@Entity
@IdClass(SbusstationId.class)
@NamedQuery(name="Sbusstation.findAll", query="SELECT s FROM Sbusstation s")
public class Sbusstation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id private int busid;
	@Id private int busstationid;
	private int distance;

	public Sbusstation() {
	}

	public int getBusid() {
		return this.busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public int getBusstationid() {
		return this.busstationid;
	}

	public void setBusstationid(int busstationid) {
		this.busstationid = busstationid;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}