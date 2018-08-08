package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Sbusinfo.findAll", query="SELECT s FROM Sbusinfo s")
public class Sbusinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int busid;

	private int buscapacity;

	public Sbusinfo() {
	}

	public int getBusid() {
		return this.busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public int getBuscapacity() {
		return this.buscapacity;
	}

	public void setBuscapacity(int buscapacity) {
		this.buscapacity = buscapacity;
	}

}