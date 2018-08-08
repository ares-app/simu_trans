package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Smonthtemperature.findAll", query="SELECT s FROM Smonthtemperature s")
public class Smonthtemperature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int month;
	private int maxt;
	private int mint;

	public Smonthtemperature() {
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMaxt() {
		return this.maxt;
	}

	public void setMaxt(int maxt) {
		this.maxt = maxt;
	}

	public int getMint() {
		return this.mint;
	}

	public void setMint(int mint) {
		this.mint = mint;
	}

}