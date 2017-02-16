package com.wsc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsc.common.Constants;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("±àÂë¹ýÂËÆ÷Ïú»Ù¡£¡£¡£");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		req.setCharacterEncoding(Constants.DEFAULT_CODING);
		resp.setCharacterEncoding(Constants.DEFAULT_CODING);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("±àÂë¹ýÂËÆ÷³õÊ¼»¯¡£¡£¡£");
	}

}
