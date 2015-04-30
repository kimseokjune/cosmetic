package com.vyon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	public ForwardInfo process
	(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException ;
}
