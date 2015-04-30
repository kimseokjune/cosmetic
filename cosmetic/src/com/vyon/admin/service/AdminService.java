package com.vyon.admin.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;


public class AdminService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		/* 관리자 모드 방식이 약간 변경되었습니다.*/
		/*admin_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("admin_right", "admin_right.jsp");
		
		
		
		/* 아래부분은 수정하지마세요*/
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../admin/admin.jsp");
		return forwardInfo;
		
		
	}

}
