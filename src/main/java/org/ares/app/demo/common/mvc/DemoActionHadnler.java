package org.ares.app.demo.common.mvc;

import static org.ares.app.demo.common.cfg.Params.CODE_FAILED_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.KEY_ERRMSG_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.KEY_RESULT_OF_SRV;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ares.app.demo.common.exception.SignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DemoActionHadnler {

	@ExceptionHandler(SignException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleSignException(HttpServletRequest request, Exception e) {
		Map<String, Object> m = new HashMap<>();
		m.put(KEY_RESULT_OF_SRV, CODE_FAILED_OF_SRV);
		m.put(KEY_ERRMSG_OF_SRV, e.getMessage());
		return m;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleException(HttpServletRequest request, Exception e) {
		log.debug(e.getMessage());
		Map<String, Object> m = new HashMap<>();
		m.put(KEY_RESULT_OF_SRV, CODE_FAILED_OF_SRV);
		m.put(KEY_ERRMSG_OF_SRV, e.getMessage());
		return m;
	}
	
	final static Logger log = LoggerFactory.getLogger(DemoActionHadnler.class);
	
}
