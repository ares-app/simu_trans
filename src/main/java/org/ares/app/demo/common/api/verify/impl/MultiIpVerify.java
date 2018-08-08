package org.ares.app.demo.common.api.verify.impl;

import static org.ares.app.demo.common.cfg.Params.CLIENT_REQUEST_IP;

import java.util.Map;

import org.ares.app.demo.common.api.verify.ApiVerify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("multiIpVerify")
public class MultiIpVerify implements ApiVerify {

	@Override
	public boolean verify(Map<String, ?> param) {
		boolean r=false;
		String request_ip=param.get(CLIENT_REQUEST_IP)+"";
		for(String s:client_ip.split(",")) {
			if(s.equals(request_ip)) {
				r=true;
				break;
			}
				
		}
		return r;
	}
	
	@Value("${client.ip}")
	String client_ip;

}
