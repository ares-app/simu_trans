package org.ares.app.demo.common.api.verify.impl;

import static org.ares.app.demo.common.cfg.Params.KEY_OF_AUTH_USER;

import java.util.Map;

import org.ares.app.demo.common.api.verify.ApiVerify;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("namepassVerify")
public class UnamePassVerify implements ApiVerify {

	@Override
	public boolean verify(Map<String, ?> param) {
		if(param==null)
			return false;
		String k_username=KEY_OF_AUTH_USER;
		if(!param.containsKey(k_username)||StringUtils.isEmpty(param.get(k_username)))
			return false;
		return true;
	}

}
