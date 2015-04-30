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

public class MemberWithdrawProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// 데이터 받기
		ForwardInfo forward = null;
		PrintWriter out = response.getWriter();
		String pw = request.getParameter("pw");
		String pw1 = (String) session.getAttribute("pw");
		String id = (String) session.getAttribute("id");
//		현재 세션에 저장되어있는 비밀번호와 현재 비밀번호에 입력되어있는 값이 같은지 확인한다
		if(!pw.equals(pw1)){
			out.println("<script>");
			out.println("alert('현재비밀번호가 올바르지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		
		if(pw1==null){
			out.println("<script>");
			out.println("alert('재로그인 해주십시요.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		
		MemberDao dao = MemberDaoProvider.getInstance().getMemberDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			dao.memberWithdraw(con, member);
			
			con.commit();
			out.println("<script>");
			out.println("alert('회원탈퇴가 완료되었습니다.');");
			out.println("location.href= '../member/withdraw2.do';");
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