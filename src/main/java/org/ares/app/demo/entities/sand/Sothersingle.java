package org.ares.app.demo.entities.sand;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="Sothersingle.findAll", query="SELECT s FROM Sothersingle s")
public class Sothersingle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	private String descr;
	private String value;

	public Sothersingle() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}