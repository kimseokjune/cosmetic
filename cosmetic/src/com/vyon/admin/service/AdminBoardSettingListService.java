package com.vyon.admin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.vyon.board.dao.BoardDao;
import com.vyon.board.dao.BoardDaoProvider;
import com.vyon.board.model.BoardSetting;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;


public class AdminBoardSettingListService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		
		BoardDao dao = BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;


		
		try {
			con = JdbcUtil.getConnection();
			

			// 게시판 정보 가져오기
			List<BoardSetting> boardsetting = dao.selectBoardSetting(con);
			request.setAttribute("boardsetting", boardsetting);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JdbcUtil.close(con);
		}
		
		
		
		
		
		
		
		
		
		
		
		/* 관리자 모드 방식이 약간 변경되었습니다.*/
		/*admin_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("admin_right", "admin_board_setting.jsp");
		
		
		
		/* 아래부분은 수정하지마세요*/
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../admin/admin.jsp");
		return forwardInfo;
		
		
	}

}
