package org.ares.app.demo.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TransModel implements Serializable {

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	private String carnumber;
}
