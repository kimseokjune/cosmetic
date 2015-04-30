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

public class BoardWriteService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 1.게시판id를 가져온다.
		String board_id = request.getParameter("id");

		// 2.DB접속
		BoardDao dao = BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;

		// 게시판 정보 가져오기

		try {

			con = JdbcUtil.getConnection();
			BoardSetting boardsetting = dao.selectBoardSetting(con, board_id);
			request.setAttribute("boardsetting", boardsetting);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("/board/write.jsp");
		return forwardInfo;
	}

}
