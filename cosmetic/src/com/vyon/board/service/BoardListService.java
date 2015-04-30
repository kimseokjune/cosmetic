package com.vyon.board.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vyon.board.dao.BoardDao;
import com.vyon.board.dao.BoardDaoProvider;
import com.vyon.board.model.Board;
import com.vyon.board.model.BoardListView;
import com.vyon.board.model.BoardSetting;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;

public class BoardListService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		// 1.게시판id를 가져온다.
		String board_id = request.getParameter("id");
		
		
		//검색 처리
		String searchKey
		= request.getParameter("searchKey");
		String searchWord
		= request.getParameter("searchWord");
		if(searchWord != null) searchWord = searchWord.trim();
		String searchKeyArr[] = null;
		if(searchWord!=null){
			searchWord = searchWord.trim();
			searchKeyArr = searchKey.split("/");
		}
		
		
		
		// 2.DB접속
		BoardDao dao = BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;


		// 전달 받는 현재페이지
		String currPageStr = request.getParameter("page");

		// 처리할 현재 페이지
		int page = 1;

		// 한페이지에 보여줄 글의 갯수
		int rowPerPage = 10;

		try {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		} catch (NumberFormatException e) {
			// System.out.println("오류");
		}

		// 페이지 그룹
		int pagePerGroup = 4;

		// 현재페이지가 0 이면 현제페이지를 1로 세팅.
		if (currPageStr != null && currPageStr != "")
			page = Integer.parseInt(currPageStr);
		if (page < 1)
			page = 1;

		// 전체 페이지 : (전체 글수-1)*페이당글수 +1
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;
		// 화면에 페이지 그룹
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage = 0, endPage = 0;

		try {
			con = JdbcUtil.getConnection();

			// 게시판 정보 가져오기
			BoardSetting boardsetting = dao.selectBoardSetting(con, board_id);
			request.setAttribute("boardsetting", boardsetting);

			totalRow = dao.selectCount(con, board_id , searchKeyArr, searchWord);
			// 전체 페이지 구하기
			if (totalRow > 0)
				totalPage = (totalRow - 1) / rowPerPage + 1;
			// 현재 페이지의 시작과 끝 줄수 구하기
			startRow = (page - 1) * rowPerPage + 1;
			endRow = startRow + rowPerPage - 1;
			// 페이지 그룹에 시작페이지,끝페이지
			startPage = (page - 1) / pagePerGroup * pagePerGroup + 1;
			endPage = startPage + pagePerGroup - 1;
			// 만약에 endPage가 totalPage보다 큰경우
			// endPage를 totalPage로 해준다.
			if (endPage > totalPage)
				endPage = totalPage;
			// 전체 글의 목록 구하기
			List<Board> list = dao.list(con, board_id, startRow, endRow , searchKeyArr, searchWord);

			BoardListView data = new BoardListView(totalRow, totalPage,
					startRow, endRow, startPage, endPage, page, rowPerPage,
					pagePerGroup, list, searchKey, searchWord);

			request.setAttribute("data", data);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JdbcUtil.close(con);
		}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("/board/list.jsp");
		return forwardInfo;
	}

}
