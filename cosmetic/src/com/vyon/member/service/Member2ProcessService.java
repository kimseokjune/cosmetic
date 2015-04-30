package com.vyon.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.dao.MemberDao;
import com.vyon.member.dao.MemberDaoProvider;
import com.vyon.member.model.Member;

public class Member2ProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("utf-8");
		// 데이터 받기
		ForwardInfo forward = null;
		PrintWriter out = response.getWriter();
		Member member = new Member();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String adno1 = request.getParameter("adno1");
		String adno2 = request.getParameter("adno2");
		int zipcode = Integer.parseInt(adno1+adno2);
		String address = request.getParameter("ad1");
		String daddress = request.getParameter("ad2");
		String hp1 = request.getParameter("hp1");
		String hp2 = request.getParameter("hp2");
		String hp3 = request.getParameter("hp3");
		System.out.println("핸드폰번호는"+hp1);
		
		String hp = hp1+"-"+hp2+"-"+hp3;
		member.setHp(hp);
		
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		
			String tel = tel1+"-"+tel2+"-"+tel3;
			member.setTel(tel);
			
		
		System.out.println("id는"+id);
		System.out.println("pw는"+pw);
		System.out.println("name는"+name);
		System.out.println("email는"+email);
		System.out.println("zipcode는"+zipcode);
		System.out.println("address는"+address);
		System.out.println("daddress는"+daddress);
		System.out.println(member.getHp());
		System.out.println(member.getTel());
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		member.setZipcode(zipcode);
		member.setAddress(address);
		member.setdAddress(daddress);
		MemberDao dao = MemberDaoProvider.getInstance().getMemberDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			dao.memberJoin(con, member);
			forward = new ForwardInfo();
			forward.setForward(false);
			
			con.commit();
			out.println("<script>");
			out.println("alert('회원가입이 완료되었습니다.');");
			out.println("location.href= '../member/login.do';");
			out.println("</script>");
			out.close();
			
			
	
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