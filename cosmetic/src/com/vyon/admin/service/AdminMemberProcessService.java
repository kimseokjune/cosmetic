package com.vyon.admin.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.admin.dao.AdminDao;
import com.vyon.admin.dao.AdminDaoProvider;
import com.vyon.admin.model.Admin;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;

public class AdminMemberProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// 데이터 받기
		ForwardInfo forward = null;
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		System.out.println(id);
		System.out.println(email);
		Admin admin = new Admin();
		admin.setId(id);
		admin.setEmail(email);
		AdminDao dao = AdminDaoProvider.getInstance().getAdminDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			dao.memberModify2(con, admin);
			request.setAttribute("id", admin.getId());
//			session.setAttribute("id", admin.getId());
			request.setAttribute("pw",admin.getPw());
			request.setAttribute("name", admin.getName());
			request.setAttribute("pw", admin.getPw());
			request.setAttribute("email", admin.getEmail());
			System.out.println(admin.getName());
			String zipcode1 = Integer.toString(admin.getZipcode());
			System.out.println(zipcode1);
			String zip1 = zipcode1.substring(0,3); 
			String zip2 = zipcode1.substring(3,6);
		
			request.setAttribute("zip1", zip1);
			request.setAttribute("zip2", zip2);
			request.setAttribute("address", admin.getAddress());
			request.setAttribute("daddress", admin.getdAddress());
			String hp = admin.getHp();
			System.out.println(hp+"여기야여기");
			
			if(hp!=null){
			String hp1 = hp.substring(0,3); 
			String hp2 = hp.substring(4,8);
			String hp3 = hp.substring(9,13);
			
			request.setAttribute("hp1", hp1);
			request.setAttribute("hp2", hp2);
			request.setAttribute("hp3", hp3);
			}else{
				request.setAttribute("hp1", "");
				request.setAttribute("hp2", "");
				request.setAttribute("hp3", "");
			}
			String tel = admin.getTel();
			if(tel!=null){
			String tel1 = tel.substring(0,2); 
			String tel2 = tel.substring(3,7);
			String tel3 = tel.substring(8,12);
			request.setAttribute("tel1", tel1);
			request.setAttribute("tel2", tel2);
			request.setAttribute("tel3", tel3);
			}else{
				request.setAttribute("tel1", "");
				request.setAttribute("tel2", "");
				request.setAttribute("tel3", "");
			}
			
			
			
			/* 관리자 모드 방식이 약간 변경되었습니다.*/
			/*admin_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
			request.setAttribute("admin_right", "../admin/memberModify2.jsp");
			
			
			
			/* 아래부분은 수정하지마세요*/
			ForwardInfo forwardInfo = new ForwardInfo();
			// 진행방식 : forward : true
			forwardInfo.setForward(true);
			// 진행하는 곳 : /board/list.jsp
			forwardInfo.setForwardStr("../admin/admin.jsp");
			return forwardInfo;
			
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("오류입니다.");
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("오류입니다.");
			}
		} finally {
			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("오류입니다.");
			}
			JdbcUtil.close(con);
		}
		return null;

	}
}