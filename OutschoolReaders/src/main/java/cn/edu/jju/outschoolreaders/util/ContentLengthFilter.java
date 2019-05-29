package cn.edu.jju.outschoolreaders.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class ContentLengthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		long contentLength = 0;
		try {
			contentLength = Long.valueOf(request.getHeader("Content-Length"));
		}catch(NumberFormatException e) {
		}
		if(contentLength > 10485760l) {
			response.sendError(413, "Content length should not exceed 10MB.");
			return;
		}
		chain.doFilter(req, res);
	}

}
