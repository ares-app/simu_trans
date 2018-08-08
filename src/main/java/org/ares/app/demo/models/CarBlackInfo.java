package org.ares.app.demo.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CarBlackInfo implements Serializable {

	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	private String carid;
	private String datetime;
}
