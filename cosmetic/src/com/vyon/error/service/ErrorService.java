package com.vyon.error.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;

public class ErrorService  implements Service{

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("ErrorService.process()");
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : false
		forwardInfo.setForward(true);
		// 진행하는 곳 : /error/error.do
		forwardInfo.setForwardStr("../error/error.jsp");
		return forwardInfo;
	}
}
