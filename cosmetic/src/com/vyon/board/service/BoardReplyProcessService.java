package com.vyon.board.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vyon.board.dao.BoardDao;
import com.vyon.board.dao.BoardDaoProvider;
import com.vyon.board.model.AttachedFile;
import com.vyon.board.model.Board;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.jdbc.TimestampFileRenamePolicy;

public class BoardReplyProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 게시판 아이디를 가져온다.
		String board_id = request.getParameter("id");
		int rno = Integer.parseInt(request.getParameter("no")); 
		

		String uploadPath = request.getServletContext().getRealPath(
				"/board/" + board_id + "_upload");
		// System.out.println(uploadPath);
		// 첨부파일 용량 제한 : 10M
		int size = 10 * 1024 * 1024;
		// 파일 업로드와 함께하는 데이터 받기
		// ** Multipart 사용
		MultipartRequest multi = new MultipartRequest(request, uploadPath,
				size, "utf-8", new TimestampFileRenamePolicy());

		// 1.게시판id를 가져온다.

		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer_id = multi.getParameter("writer_id");

		@SuppressWarnings("unchecked")
		Enumeration<String> fileNames = multi.getFileNames();
		List<AttachedFile> fileList = null;
		boolean attachFile = false;

		while (fileNames.hasMoreElements()) {
			String fileName = fileNames.nextElement();
			String originalFile = multi.getOriginalFileName(fileName);
			if (originalFile != null) {
				if (!attachFile) {
					fileList = new ArrayList<AttachedFile>();
					attachFile = true;
				}
				// 업로드한 파일 이름 가져오기
				String serverFile = multi.getFilesystemName(fileName);
				AttachedFile attachedFile = new AttachedFile();
				attachedFile.setOriginalFile(originalFile);
				attachedFile.setServerFile(serverFile);
				fileList.add(attachedFile);
			}
		}



		// 2.DB접속
		BoardDao dao = BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		// 3.테이블의 정보를 가져온다
		// 4.테이블의 글 내용을 가져온다
		// 5.테이블의 정보와 글내용을 다보낸다

		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);

			int pno = dao.selectParent(con,board_id , rno);
			
			
			
			// 파일을 제외한 데이터 셋팅
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			board.setId(writer_id);
			board.setParentsno(pno);
			// 첨부된 파일 데이터 셋팅
			board.setFileList(fileList);

			
			
			dao.reply(con, board, board_id, rno);

			// 파일이 있는 경우 처리
			if (fileList != null && fileList.size() > 0) {
				// 방금 작성된 글번호를 가져온다.
				int no = dao.currentNo(con, board_id);
				// 작성된 글번호로 파일 정보를 입력한다.
				System.out.println("★★★★" + no);
				dao.writeFile(con, no, board.getFileList(), board_id);
			}

			con.commit();

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
		forwardInfo.setForwardStr("../board/list.do?id=" + board_id);
		return forwardInfo;
	}

}
