package org.ares.app.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 违章
 */
@Entity
@Table(name="peccancyx")
public class Peccancyx {

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPdatetime() {
		return pdatetime;
	}
	public void setPdatetime(String pdatetime) {
		this.pdatetime = pdatetime;
	}
	public String getPaddr() {
		return paddr;
	}
	public void setPaddr(String paddr) {
		this.paddr = paddr;
	}
	
	@Override
	public String toString() {
		return "Peccancy [" + (id != null ? "id=" + id + ", " : "")
				+ (carnumber != null ? "carnumber=" + carnumber + ", " : "")
				+ (pcode != null ? "pcode=" + pcode + ", " : "")
				+ (pdatetime != null ? "pdatetime=" + pdatetime + ", " : "") + (paddr != null ? "paddr=" + paddr : "")
				+ "]";
	}

	@Id
	private Integer id;
	private String carnumber;
	private String pcode;
	private String pdatetime;
	private String paddr;
}
