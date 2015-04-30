package com.vyon.admin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.admin.dao.AdminDao;
import com.vyon.admin.dao.AdminDaoProvider;
import com.vyon.admin.model.Admin;
import com.vyon.admin.model.AdminListView;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;

public class AdminSearchProcessService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		// HttpSession session = request.getSession();
		// 데이터 받기
		ForwardInfo forward = null;
		HttpSession session = request.getSession();
		// PrintWriter out = response.getWriter();
		// 파라미터로 불러온 값들은 변수에 담아준다
		String currPageStr = request.getParameter("page");
		String name = request.getParameter("search");
		String select = request.getParameter("select");
		session.setAttribute("select", select);
//		어드민에서 검색창에 회원정보를 검색하면 나오는 페이지에 search 값은 넘겨주기
//		위해서 세션에 search 값을 저장한다
		session.setAttribute("search",name);
		System.out.println(name);
		System.out.println(select);
		if (select.equals("search2")) {
			select = null;
		}
		System.out.println(select);
		if (name != null) {
			session.setAttribute("name", name);
		}
		String name2 = (String) session.getAttribute("name");
		System.out.println(name);

		Admin admin = new Admin();
		admin.setName(name);

		int page = 1;
		if (currPageStr != null && currPageStr != "") {
			page = Integer.parseInt(currPageStr);
			if (page < 1)
				page = 1;
			// System.out.println("현재 페이지:"+page);
		}
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		int rowPerPage = 10;
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;
		// 화면에 페이지 그룹
		int pagePerGroup = 10;
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage = 0, endPage = 0;
		AdminDao dao = AdminDaoProvider.getInstance().getAdminDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			totalRow = dao.selectCount(con, admin, name2, select);
			// 전체 페이지 구하기
			if (totalRow > 0)
				totalPage = (totalRow - 1) / rowPerPage + 1;
			// 현재 페이지의 시작과 끝 줄수 구하기
			startRow = (page - 1) * rowPerPage + 1;
			endRow = startRow + rowPerPage - 1;
			// System.out.println("startRow:"+startRow);
			// System.out.println("endRow:"+endRow);
			// 페이지 그룹에 시작페이지,끝페이지
			startPage = (page - 1) / pagePerGroup * pagePerGroup + 1;
			endPage = startPage + pagePerGroup - 1;
			// 만약에 endPage가 totalPage보다 큰경우
			// endPage를 totalPage로 해준다.
			if (endPage > totalPage)
				endPage = totalPage;
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			List<Admin> list = dao.search(con, admin, startRow, endRow, name2,
					select);
			// List<Goods> list
			// = dao.admin_list(con, startRow, endRow, cate, sort, method);
			// // dao.admin_list에서 처리된 데이터와 페이지 처리 데이터를 data로 저장변수 선언
			AdminListView data = new AdminListView(totalRow, totalPage,
					startRow, endRow, startPage, endPage, page, rowPerPage,
					pagePerGroup, list);
			// //
			// System.out.println("data.getStartPage():"+data.getStartPage());
			// // System.out.println("data.getEndPage():"+data.getEndPage());
			request.setAttribute("data", data);
			request.setAttribute("check", "check");
			request.setAttribute("check2", "check2");
			request.setAttribute("admin_right", "memberList2.jsp");

			/* 아래부분은 수정하지마세요 */
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