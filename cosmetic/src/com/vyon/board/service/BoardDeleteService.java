package com.vyon.board.service;

import java.io.File;
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
import com.vyon.board.model.ServerFileName;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;




public class BoardDeleteService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//1.게시판id를 가져온다.
		String board_id = request.getParameter("id");
		//int no = Integer.parseInt(request.getParameter("no")); 
		String no =request.getParameter("no");
		
		
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
			
			// 파일 삭제 
			ServerFileName filename = dao.getFileNameByNo(con,board_id, no);
			
	    	String file1 = filename.getServerfilename();
	    	String deleteFile = null;
	    	String path = null;
	    	if(file1!=null){
	    		path=request.getSession().getServletContext().getRealPath("/board/" + board_id + "_upload");
	    		deleteFile= path+"/"+file1;
	    		System.out.println(path);
	    	}
	    	File file = new File(deleteFile);
	    	file.delete();
			
			// 글삭제 메소드
			dao.delete(con, board_id, no);
			con.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../board/list.do?id="+board_id);
		return forwardInfo;
	}

}
