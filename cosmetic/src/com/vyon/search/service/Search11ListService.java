package com.vyon.search.service;

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
import com.vyon.member.model.Member;
import com.vyon.search.dao.SearchDao;
import com.vyon.search.dao.SearchDaoProvider;
import com.vyon.search.model.Search;
import com.vyon.search.model.SearchListView;

public class Search11ListService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
//		HttpSession session = request.getSession();
		// 데이터 받기
		ForwardInfo forward = null;
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
//		파라미터로 불러온 값들은 변수에 담아준다
		String currPageStr = request.getParameter("page");
//		String name = request.getParameter("search");
		String select = (String)session.getAttribute("select");
		if(select.equals("search2")){
			select = null;
		}
		System.out.println(select);
		int row = Integer.parseInt((String) request.getParameter("row"));
//		Search15ListService로 보내줄 row값을 세션에 저장한다.
		session.setAttribute("row", row);
		String name2 = (String)session.getAttribute("name");
		
		Search search = new Search();
		
		int page = 1;
		if(currPageStr!=null && currPageStr!=""){
			page = Integer.parseInt(currPageStr);
			if(page<1) page=1;
//			System.out.println("현재 페이지:"+page);
		}
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		int rowPerPage = row;
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;
		//화면에 페이지 그룹
		int pagePerGroup = 8;
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage=0, endPage = 0;
		SearchDao dao = SearchDaoProvider.getInstance().getSearchDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			totalRow=dao.selectCount(con, search, name2);
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
			List<Search> list=dao.search(con,startRow,endRow,name2,select);
//			List<Goods> list
//			= dao.admin_list(con, startRow, endRow, cate, sort, method);
//			// dao.admin_list에서 처리된 데이터와 페이지 처리 데이터를 data로 저장변수 선언
			SearchListView data = new SearchListView(
					totalRow, totalPage, startRow, endRow, startPage,
					endPage, page, rowPerPage, pagePerGroup, list);
////			System.out.println("data.getStartPage():"+data.getStartPage());
////			System.out.println("data.getEndPage():"+data.getEndPage());
			request.setAttribute("data", data);
//			검색결과내의 검색창에서 검색을 한뒤 바로 row 개씩 화면에 보여주게 한뒤의 페이징숫자
//			숫자를 클릭하면 Search15ListSevice로 넘어간다
			request.setAttribute("check10","check10");
			request.setAttribute("check5","check10");
//			검색결과 내의 검색창에서 검색했을때의 페이지로 이동
			request.setAttribute("check2","check2");
			request.setAttribute("check3","check3");
//			검색결과 내의 검색창에서 검색을 한뒤 바로 row개씩 화면에 보여지게 하는 페이지
			request.setAttribute("row2","row");
			request.setAttribute("row4","row");
			forward = new ForwardInfo();
			forward.setForward(true);
			forward.setForwardStr("/search/searchList.jsp");
			return forward;

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