package com.vyon.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

public class MemberLoginProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// 데이터 받기
		ForwardInfo forward = null;
		PrintWriter out = response.getWriter();
//		파라미터로 아이디와 비밀번호를 불러온다
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Member member = new Member();
		HttpSession session = request.getSession();
//		Member클래스에 아이디와 비밀번호를 저장한다.
		member.setId(id);
		member.setPw(pw);

		MemberDao dao = MemberDaoProvider.getInstance().getMemberDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			dao.login(con, member);
			forward = new ForwardInfo();
			forward.setForward(false);
//			만약 상태가 1(탈퇴회원)이면 탈퇴한 회원이라는 alert창을 띄운다
			if(member.getStateNo()==1){
				out.println("<script>");
				out.println("alert('탈퇴한 회원입니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
//			상태가 회원이고 파라미터 아이디와 DB의 아이디가 일치하고 등급이 일반회원일 경우
//			세션으로 아이디 이름 등급 이메일을 저장한다
			if(member.getStateNo()==0){
			if (id.equals(member.getId()) && member.getGrade() == 1) {
				session.setAttribute("id", member.getId());
				session.setAttribute("name", member.getName());
				session.setAttribute("grade", member.getGrade());
				session.setAttribute("email", member.getEmail());
				
				forward.setForwardStr("../main/main.do");
				return forward;
//				상태가 회원이고 파라미터 아이디와 DB의 아이디가 일치하고 등급이 관리자일 경우
//				세션으로 아이디 이름 등급 이메일을 저장한다
			} else if (id.equals(member.getId()) && member.getGrade() == 9) {
				session.setAttribute("id", member.getId());
				session.setAttribute("name", member.getName());
				session.setAttribute("grade", member.getGrade());
				session.setAttribute("email", member.getEmail());
				
				forward.setForwardStr("../main/main.do");
				return forward;
			} else {
//				위의 조건을 둘다 만족하지 않으면 아이디 또는 비밀번호가 잘못 되었다는
//				문구를 alert창으로 띄운다
				System.out.println("로그인 실패");
				out.println("<script>");
				out.println("alert('아이디또는 비밀번호가 잘못되었습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				
			}
//			가입한 회원이아니면 탈퇴회원이라는 alert창을 띄운다
			}else{
				out.println("<script>");
				out.println("alert('탈퇴한회원입니다.');");
				out.println("history.go(-1);");
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
