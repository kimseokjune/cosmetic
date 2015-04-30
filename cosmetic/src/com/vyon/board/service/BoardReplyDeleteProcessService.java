package com.vyon.board.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vyon.board.dao.BoardDao;
import com.vyon.board.dao.BoardDaoProvider;
import com.vyon.board.dao.BoardReply;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;

public class BoardReplyDeleteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 게시판 아이디를 가져온다.
		String board_id = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		String rcontent = request.getParameter("replyUpdate");

		// 파일을 제외한 데이터 셋팅
		BoardReply br = new BoardReply();
		br.setRcontent(rcontent);

		// 2.DB접속
		BoardDao dao = BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		// 3.테이블의 정보를 가져온다
		// 4.테이블의 글 내용을 가져온다
		// 5.테이블의 정보와 글내용을 다보낸다

		try {
			con = JdbcUtil.getConnection();
			// 전체 글의 갯수 구하기
			// con.setAutoCommit(false);
			// 글내용 수정
			dao.replyDelete(con, br, board_id, no, pno);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtil.close(con);
		}
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../board/view.do?id=" + board_id + "&no="
				+ pno);
		return forwardInfo;
	}

}
