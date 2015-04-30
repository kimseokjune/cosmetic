package com.vyon.member.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;


public class Member3Service implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		/* 마이페이지 링크는 관리자 처럼 거시면됩니다
		/*mypage_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("mypage_right", "../member/member3.jsp");
		
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../mypage/mypage_menu.jsp");
		return forwardInfo;
	}

}
