package org.ares.app.demo.common.danger;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DemoDanger {

	public boolean danger(){
		boolean r=false;
		String ed="2018-03-30";
		if(LocalDate.parse(ed).compareTo(LocalDate.now())<0)
			r=true;
		return r;
	}
}
