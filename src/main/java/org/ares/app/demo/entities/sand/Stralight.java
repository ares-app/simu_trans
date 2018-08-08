package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Stralight.findAll", query="SELECT s FROM Stralight s")
public class Stralight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int trafficlightid;

	private int greentime;

	private int redtime;

	private String status;

	private int time;

	private int yellowtime;

	public Stralight() {
	}

	public int getTrafficlightid() {
		return this.trafficlightid;
	}

	public void setTrafficlightid(int trafficlightid) {
		this.trafficlightid = trafficlightid;
	}

	public int getGreentime() {
		return this.greentime;
	}

	public void setGreentime(int greentime) {
		this.greentime = greentime;
	}

	public int getRedtime() {
		return this.redtime;
	}

	public void setRedtime(int redtime) {
		this.redtime = redtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getYellowtime() {
		return this.yellowtime;
	}

	public void setYellowtime(int yellowtime) {
		this.yellowtime = yellowtime;
	}

}