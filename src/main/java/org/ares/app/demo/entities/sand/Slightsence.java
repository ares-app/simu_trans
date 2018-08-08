package org.ares.app.demo.entities.sand;

import java.io.Serializable;

import javax.persistence.Id;

/*@Entity
@NamedQuery(name="Slightsence.findAll", query="SELECT s FROM Slightsence s")*/
public class Slightsence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String down;
	@Id
	private String up;

	public Slightsence() {
	}

	public String getDown() {
		return this.down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public String getUp() {
		return this.up;
	}

	public void setUp(String up) {
		this.up = up;
	}

}