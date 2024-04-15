package dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bean.School;

public class ClassNumDao implements Filter {

	public List<String> filter(School s) {
		return (List<String>)s;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		  filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}