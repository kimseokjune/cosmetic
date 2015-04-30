package com.vyon.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;

public class MemberProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		ForwardInfo forwardInfo = new ForwardInfo();
		PrintWriter out = response.getWriter();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		request.setCharacterEncoding("utf-8");
		//회원가입 동의에 값을 불러온다
		String[] values = request.getParameterValues("spk");
		String val=null;
		try{
			for(int i=0; i< values.length; i++){
				val=values[i];
			}
			}catch(NullPointerException e){
				out.println("<script>");
				out.println("alert('약관에 모두 동의를 하셔야합니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
		String[] values1 = request.getParameterValues("spk1");
		String val1=null;
		try{
		for(int i=0; i< values.length; i++){
			val1=values1[i];
		}
		}catch(NullPointerException e){
			out.println("<script>");
			out.println("alert('약관에 모두 동의를 하셔야합니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		if(val.equals("agree")){
			if(val1.equals("agree1")){
				forwardInfo.setForwardStr("/member/member2.jsp");
				return forwardInfo;
			}else{
				out.println("<script>");
				out.println("alert('약관에 모두 동의를 하셔야합니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
		}else{
			out.println("<script>");
			out.println("alert('약관에 모두 동의를 하셔야합니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return forwardInfo;
		
		
	}
}
