package com.vyon.search.service;

import java.io.IOException;
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
import com.vyon.search.dao.SearchDao;
import com.vyon.search.dao.SearchDaoProvider;
import com.vyon.search.model.Search;
import com.vyon.search.model.SearchListView;

public class Search6ListService implements Service {
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
//		HttpSession session = request.getSession();
//		Member member = new Member();
		// 데이터 받기
		ForwardInfo forward = null;
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
//		파라미터로 불러온 값들은 변수에 담아준다
		String currPageStr = request.getParameter("page");
		String sort = request.getParameter("sort");
		String method = request.getParameter("method");
		session.setAttribute("sort", sort);
		session.setAttribute("method", method);
		System.out.println(sort);
		System.out.println(method);
//		String name = request.getParameter("search");
//		if(name != null){
//		session.setAttribute("name", name);
//		}
		String name2 = (String)session.getAttribute("name");
		System.out.println("search6ListService");
		System.out.println(name2);
//		System.out.println(select);
//		if(select !=null){
//		if(select.equals("search2")){
//			select = null;
//		}
//		}
		System.out.println("여기까지");
		
//		Goods goods = new Goods();
//		goods.setName(name);
		
		int page = 1;
		if(currPageStr!=null && currPageStr!=""){
			page = Integer.parseInt(currPageStr);
			if(page<1) page=1;
//			System.out.println("현재 페이지:"+page);
		}
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		int rowPerPage = 8;
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
			totalRow=dao.selectCount(con,name2);
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
			System.out.println("여기까지");
			List<Search> list=dao.search(con,startRow,endRow,name2,sort,method);
//			List<Goods> list
//			= dao.admin_list(con, startRow, endRow, cate, sort, method);
//			// dao.admin_list에서 처리된 데이터와 페이지 처리 데이터를 data로 저장변수 선언
			SearchListView data = new SearchListView(
					totalRow, totalPage, startRow, endRow, startPage,
					endPage, page, rowPerPage, pagePerGroup, list);
////			System.out.println("data.getStartPage():"+data.getStartPage());
////			System.out.println("data.getEndPage():"+data.getEndPage());
			request.setAttribute("data", data);
//			헤드부분 검색창에서 검색하는 페이지로 이동
//			check2와 check3이 같은것은 헤드부분의 검색창에서 검색을 했을때 나오는 결과로
//			정렬을 할때 나오는 페이지다
			request.setAttribute("check2", "check");
			request.setAttribute("check3", "check");
//			Search7ListService로 이동 통합검색에서 오더바이로 정렬은한뒤에 의 페이징처리
			request.setAttribute("check7", "check7");
			request.setAttribute("check5", "check7");
//			헤드부분 검색창에서 검색한후 search9ListService로 이동 통합검색에서 오더바이로 정렬한뒤
//			row개로 화면에 출력해주는 페이지
//			헤드부분의 검색창에서 검색을 한상태에서 row2와 row3이 다르면 여러가지순으로 정렬을 한뒤의
//			row개의 순으로 정렬을 하는 것이다.
			request.setAttribute("row2", "row2");
			request.setAttribute("row3", "row3");
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