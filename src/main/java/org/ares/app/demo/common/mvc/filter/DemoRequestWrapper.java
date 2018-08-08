package org.ares.app.demo.common.mvc.filter;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

public class DemoRequestWrapper extends HttpServletRequestWrapper {

	public DemoRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		body=StreamUtils.copyToByteArray(request.getInputStream());
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new BufferedServletInputStream( body );
	}
	
	private final byte[] body;

}
