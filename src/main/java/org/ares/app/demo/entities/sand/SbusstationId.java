package org.ares.app.demo.entities.sand;

import java.io.Serializable;

import javax.persistence.Id;

public class SbusstationId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id private int busid;
	@Id private int busstationid;

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

}