package com.vyon.admin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vyon.admin.dao.AdminDao;
import com.vyon.admin.dao.AdminDaoProvider;
import com.vyon.admin.model.Admin;
import com.vyon.admin.model.AdminListView;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;


public class Admin3MemberModifyService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = 1;
		
		// 전체 페이지 : (전체 글수-1)*페이당글수 +1
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		int rowPerPage = 10;
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;
		//화면에 페이지 그룹
		int pagePerGroup = 10;
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage=0, endPage = 0;
		AdminDao dao 
		= AdminDaoProvider.getInstance().getAdminDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			totalRow=dao.selectCount(con);
			//전체 페이지 구하기
			if(totalRow>0)
				totalPage = (totalRow-1)/rowPerPage +1;
			// 현재 페이지의 시작과 끝 줄수 구하기
			startRow=(page-1)*rowPerPage +1;
			endRow = startRow+rowPerPage -1;
//			System.out.println("startRow:"+startRow);
//			System.out.println("endRow:"+endRow);
			// 페이지 그룹에 시작페이지,끝페이지
			startPage 
			= (page-1)/pagePerGroup*pagePerGroup+1;
			endPage
			= startPage+pagePerGroup -1;
			// 만약에 endPage가 totalPage보다 큰경우
			// endPage를 totalPage로 해준다.
			if(endPage > totalPage) endPage=totalPage;
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
		List<Admin> list
		= dao.list(con,startRow,endRow);
		
		AdminListView data = new AdminListView(
				totalRow, totalPage,
				startRow, endRow, startPage,
				endPage, page, rowPerPage,
				pagePerGroup, list);
//		System.out.println("data.getStartPage():"+data.getStartPage());
//		System.out.println("data.getEndPage():"+data.getEndPage());
		request.setAttribute("data", data);
		request.setAttribute("check", "check");
		request.setAttribute("check2", "check");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
		/* 관리자 모드 방식이 약간 변경되었습니다.*/
		/*admin_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("admin_right", "memberModify3.jsp");
		
		
		
		/* 아래부분은 수정하지마세요*/
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../admin/admin.jsp");
		return forwardInfo;
		
		
	}

}
