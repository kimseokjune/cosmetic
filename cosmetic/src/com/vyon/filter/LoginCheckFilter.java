package com.vyon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {
	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) 
			throws IOException,ServletException{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

	
		String url = httpRequest.getRequestURI();
		System.out.println("★접속된주소"+url);
	
	boolean login = false;
	if(session!=null){
		if(session.getAttribute("id") !=null){
			login = true;
		}
	}
		
		if(login){
			chain.doFilter(request, response);
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("../member/login.do");
			dispatcher.forward(request, response);
		}
		
	}

	@Override
	public void destroy() {

	}

}
