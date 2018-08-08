package org.ares.app.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SParam {

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Id
	private String name;
	private String val;
	private String descr;
	
}
