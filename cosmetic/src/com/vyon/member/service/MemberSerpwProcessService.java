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

public class MemberSerpwProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// 데이터 받기
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		// DB에서 받은 데이터에 맞는 id, name, grade를 가져온다.
		Member member = new Member();
		
		member.setId(id);
		member.setName(name);
		member.setEmail(email);
		
		
		MemberDao dao = MemberDaoProvider.getInstance().getMemberDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			ForwardInfo forwardInfo = new ForwardInfo();
			// 진행방식 : forward : true
			forwardInfo.setForward(true);
			// 진행하는 곳 : /board/list.jsp
			// 게시판에 글을 쓴다.
			dao.serpw(con, member);
			request.setAttribute("member", member);
			
			System.out.println("여기까지옴");
			
			
			if(member.getStateNo()==1){
				out.println("<script>");
				out.println("alert('탈퇴한 회원입니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
			if(member.getPw()!=null){
				String pw = member.getPw();
				System.out.println(pw.substring(3));
				String pw2 = pw.replace(pw.substring(3), "*");
				member.setPw(pw2);
				forwardInfo.setForwardStr("../member/serpw2.jsp");
				return forwardInfo;

			} else{
				
				System.out.println("로그인 실패");
				out.println("<script>");
				out.println("alert('가입되지않은 이름과 이메일 과 아이디입니다.');");
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
