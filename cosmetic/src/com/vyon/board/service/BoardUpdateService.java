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




public class BoardUpdateService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//1.게시판id를 가져온다.
		String board_id = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no")); 
		//2.DB접속
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		//3.테이블의 정보를 가져온다
		//4.테이블의 글 내용을 가져온다
		//5.테이블의 정보와 글내용을 다보낸다

		try {
			con=JdbcUtil.getConnection();
			con.setAutoCommit(false);
		    //조회수 1증가 메소드
			Board board = dao.view(con, board_id, no);
			con.commit();
			request.setAttribute("data", board);
			
			
			// 게시판 정보 가져오기
			BoardSetting boardsetting = dao.selectBoardSetting(con, board_id);
			request.setAttribute("boardsetting", boardsetting);

			
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
		forwardInfo.setForwardStr("/board/update.jsp");
		return forwardInfo;
	}

}
