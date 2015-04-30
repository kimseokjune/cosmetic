package com.vyon.mypage.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;


public class MypageService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		/* 마이페이지 링크는 관리자 처럼 거시면됩니다
		/*mypage_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("mypage_right", "mypage_right.jsp");
		
		
		
		/* 아래부분은 수정하지마세요*/
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../mypage/mypage_menu.jsp");
		return forwardInfo;
		
		
	}

}
