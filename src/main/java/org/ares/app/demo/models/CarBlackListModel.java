package org.ares.app.demo.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class CarBlackListModel implements Serializable {
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Map<String, String>> getRowsdetail() {
		return rowsdetail;
	}
	public void setRowsdetail(List<Map<String, String>> rowsdetail) {
		this.rowsdetail = rowsdetail;
	}

	@JsonProperty("UserName")
	private String username;
	@JsonProperty("ROWS_DETAIL")
	private List<Map<String,String>> rowsdetail;
	
}
