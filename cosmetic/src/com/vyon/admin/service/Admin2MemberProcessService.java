package com.vyon.admin.service;

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

public class Admin2MemberProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Member member = new Member();
		// 데이터 받기
		ForwardInfo forward = null;
		PrintWriter out = response.getWriter();
//		파라미터로 불러온 값들은 변수에 담아준다
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
		System.out.println(hp1);
		System.out.println(hp2);
		System.out.println(hp3);
		if(!hp1.equals("")|!hp2.equals("")|!hp3.equals("")){
		String hp = hp1+"-"+hp2+"-"+hp3;
		member.setHp(hp);
		}else{
			String hp = "";
			member.setHp(hp);
		}
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		if(!tel1.equals("")|!tel2.equals("")|!tel3.equals("")){
			String tel = tel1+"-"+tel2+"-"+tel3;
			member.setTel(tel);
			}else{
				String tel = "";
				member.setTel(tel);
			}
		
//		String pw1 = (String) session.getAttribute("pw");
		
////		현재 세션에 저장되어있는 비밀번호와 현재 비밀번호에 입력되어있는 값이 같은지 확인한다
//		if(!pw.equals(pw1)){
//			out.println("<script>");
//			out.println("alert('현재비밀번호가 올바르지 않습니다.');");
//			out.println("history.go(-1);");
//			out.println("</script>");
//			out.close();
//		}
//		if(pw.equals(npw)){
//			out.println("<script>");
//			out.println("alert('현재비밀번호와 새비밀번호가 같습니다.');");
//			out.println("history.go(-1);");
//			out.println("</script>");
//			out.close();
//		}
//		if(id==null){
//			out.println("<script>");
//			out.println("alert('재로그인 해주십시요.');");
//			out.println("history.go(-1);");
//			out.println("</script>");
//			out.close();
//		}
//		
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
			dao.memberUpdate(con, member);
			session.setAttribute("id", "admin");
			session.setAttribute("name","관리자");
			session.setAttribute("grade",9);
			System.out.println("여까지확인");
			con.commit();
			out.println("<script>");
			out.println("alert('회원수정이 완료되었습니다.');");
			out.println("location.href= '../admin/admin.do';");
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