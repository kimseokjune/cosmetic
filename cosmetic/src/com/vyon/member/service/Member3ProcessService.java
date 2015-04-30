package com.vyon.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.dao.MemberDao;
import com.vyon.member.dao.MemberDaoProvider;
import com.vyon.member.model.Member;

public class Member3ProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// 데이터 받기
		ForwardInfo forward = null;
		PrintWriter out = response.getWriter();
		String pw = request.getParameter("pw");
		String id = (String) session.getAttribute("id");
		System.out.println("비밀번호는"+pw);
		if(pw.equals("")){
			out.println("<script>");
			out.println("alert('비밀번호를 입력해주세요.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		if(id==null){
			out.println("<script>");
			out.println("alert('재로그인 해주세요.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		Member member = new Member();
		member.setPw(pw);
		member.setId(id);
		MemberDao dao = MemberDaoProvider.getInstance().getMemberDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			dao.memberPw(con, member);
			if(member.getName()==null){
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다..');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
			session.setAttribute("name", member.getName());
			session.setAttribute("pw", member.getPw());
			session.setAttribute("email", member.getEmail());
			
			String zipcode1 = Integer.toString(member.getZipcode());
			String zip1 = zipcode1.substring(0,3); 
			String zip2 = zipcode1.substring(3,6);
		
			session.setAttribute("zip1", zip1);
			session.setAttribute("zip2", zip2);
			session.setAttribute("address", member.getAddress());
			session.setAttribute("daddress", member.getdAddress());
			String hp = member.getHp();
			String[] result = hp.split("-");
			String hp1 = result[0];
			String hp2 = result[1];
			String hp3 = result[2];
			System.out.println(hp1);
			System.out.println(hp2);
			System.out.println(hp3);
			session.setAttribute("hp1", hp1);
			session.setAttribute("hp2", hp2);
			session.setAttribute("hp3", hp3);
			
			String tel = member.getTel();
			String[] result2 = tel.split("-");
			String tel1 = result2[0];
			String tel2 = result2[1];
			String tel3 = result2[2];		
				session.setAttribute("tel1", tel1);
				session.setAttribute("tel2", tel2);
				session.setAttribute("tel3", tel3);
			
			forward = new ForwardInfo();
			forward.setForward(false);
			if (member.getName() != null) {
				out.println("<script>");
				out.println("alert('비밀번호가 일치합니다.');");
				out.println("location.href= '../member/member4.do';");
				out.println("</script>");
				out.close();
			} 

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