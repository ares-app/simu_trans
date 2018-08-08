package org.ares.app.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Peccancytype {

	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPremarks() {
		return premarks;
	}
	public void setPremarks(String premarks) {
		this.premarks = premarks;
	}
	public Integer getPmoney() {
		return pmoney;
	}
	public void setPmoney(Integer pmoney) {
		this.pmoney = pmoney;
	}
	public Integer getPscore() {
		return pscore;
	}
	public void setPscore(Integer pscore) {
		this.pscore = pscore;
	}
	@Id
	private String pcode;
	private String premarks;
	private Integer pmoney;
	private Integer pscore;
}
