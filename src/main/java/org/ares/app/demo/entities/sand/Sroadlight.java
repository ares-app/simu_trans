package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Sroadlight.findAll", query="SELECT s FROM Sroadlight s")
public class Sroadlight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int roadlightid;

	private String controlmode;

	private String status;

	public Sroadlight() {
	}

	public int getRoadlightid() {
		return this.roadlightid;
	}

	public void setRoadlightid(int roadlightid) {
		this.roadlightid = roadlightid;
	}

	public String getControlmode() {
		return this.controlmode;
	}

	public void setControlmode(String controlmode) {
		this.controlmode = controlmode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}