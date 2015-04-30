package com.vyon.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.board.model.AttachedFile;
import com.vyon.board.model.Board;
import com.vyon.board.model.BoardSetting;
import com.vyon.board.model.ServerFileName;
import com.vyon.jdbc.JdbcUtil;

public abstract class BoardDao {
	// 공통이 아닌 메소드를 추상 메소드로 선언
	public abstract int write(Connection con, Board board, String board_id)
			throws SQLException;

	public abstract int writeFile(Connection con, int no,
			List<AttachedFile> fileList, String board_id) throws SQLException;

	public abstract List<Board> list(Connection con, String board_id, int startRow ,
			int endRow, String[] searchKeyArr, String searchWord)
			throws SQLException;

	public abstract int reply(Connection con, Board board, String board_id ,int no)
			throws SQLException;
	// ******* 공통으로 사용되는(동일한 코드) 메소드를 구현한다.

	// 글보기 - 선택한 번호의 하나의 글 보기
	public Board view(Connection con, String board_id, int no)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try {
			// sql 작성

			String sql = "select rownum rnum,no,title,id,content,to_char(wdate,'yyyy-mm-dd') "
					+ " wdate,serverfile,originalfile,grp,grporder,lv,target "
					+ " from " + board_id + " where no= " + no;
			// 상태
			pstmt = con.prepareStatement(sql);
			// System.out.println(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next())
					return makeBoardFromViewResultSet(rs);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return null;
	}

	// Message(bean)에 rs에 있는 데이터를 담는다.List용 메소드
	protected Board makeBoardFromListResultSet(ResultSet rs)
			throws SQLException {
		Board board = new Board();

		board.setRnum(rs.getInt("rnum"));
		board.setNo(rs.getInt("no"));
		board.setTitle(rs.getString("title"));
		board.setId(rs.getString("id"));
		board.setWdate(rs.getString("wdate"));
		board.setGrp(rs.getInt("grp"));
		board.setGrporder(rs.getInt("grporder"));
		board.setLv(rs.getInt("lv"));
		board.setTarget(rs.getInt("target"));

		return board;
	}

	// Message(bean)에 rs에 있는 데이터를 담는다.view용 메소드
	protected Board makeBoardFromViewResultSet(ResultSet rs)

			throws SQLException {
		Board board = makeBoardFromListResultSet(rs);
		// 추가로 content만 담는다.
		board.setContent(rs.getString("content"));
		board.setOriginalfile(rs.getString("originalfile"));
		board.setServerfile(rs.getString("serverfile"));
		return board;
	}
	
	

	// 입력된 글번호를 가져오는 메소드
	public int currentNo(Connection con, String board_id) throws SQLException {
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try {
			// sql 작성
			String sql = "select max(no) from " + board_id;
			// 상태
			pstmt = con.prepareStatement(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next())
					return rs.getInt(1);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return 0;
	}

	// 서버에 저장되어있는 파일명 가져오는 메소드
	public ServerFileName getFileNameByNo(Connection con, String board_id,
			String no) throws SQLException {
		// 조회수 1증가 메소드
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try {
			// sql 작성
			String sql = "select originalfile,serverfile from " + board_id
					+ " where no = " + no;
			// 상태
			pstmt = con.prepareStatement(sql);
			// System.out.println(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next()) {
					ServerFileName sfn = new ServerFileName();

					sfn.setOriginalfilename(rs.getString("originalfile"));
					sfn.setServerfilename(rs.getString("serverfile"));
					return sfn;
			} 

		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return null;
	}

	// 조회수 1증가 메소드
	public void targetincrease(Connection con, String board_id, int no)
			throws SQLException {
		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			String sql = " update " + board_id
					+ " set target=target+1  where no = " + no;
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();

		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	// 글 삭제 메소드
	public void delete(Connection con, String board_id, String no)
			throws SQLException {
		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			String sql = " delete from " + board_id + " where no = " + no;
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	// 글 업데이트 메소드
	public void update(Connection con, Board board, String board_id, String no)
			throws SQLException {

		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			String sql = " update " + board_id
					+ " set title=? ,content=?, wdate=sysdate   where no = "
					+ no;
			// 상태
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.executeUpdate();

		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}

	}

	// 파일 업데이트 메소드
	public int updateFile(Connection con, String no,
			List<AttachedFile> fileList, String board_id) throws SQLException {

		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " update " + board_id
					+ " set originalFile= ?,serverFile = ? " + " where no ="
					+ no;
			// 상태
			pstmt = con.prepareStatement(sql);
			for (AttachedFile af : fileList) {
				// ?에 대한 데이터 셋팅
				pstmt.setString(1, af.getOriginalFile());
				pstmt.setString(2, af.getServerFile());
				// 실행
				pstmt.executeUpdate();
			}
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}

	}

	// 게시판 세팅 정보 가져오기
	public BoardSetting selectBoardSetting(Connection con, String id)
			throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 처리
		try {
			// sql 작성

			String sql = "select board_id,board_name,writer_lv,comment_lv "
					+ " ,reply_lv,pagepercount,isfileupload "
					+ " from board_setting "
					+ " where board_id = ? ";
			// 상태
			pstmt = con.prepareStatement(sql);
			// 실행 select: executeQuery()
			pstmt.setString(1, id);
		    rs = pstmt.executeQuery();
		    
			if (rs != null)
				if (rs.next()) {
					BoardSetting boardsetting = new BoardSetting();
					boardsetting.setBoard_id(rs.getString("board_id"));
					boardsetting.setBoard_name(rs.getString("board_name"));
					boardsetting.setWriter_lv(rs.getInt("writer_lv"));
					boardsetting.setComment_lv(rs.getInt("comment_lv"));
					boardsetting.setReply_lv(rs.getInt("reply_lv"));
					boardsetting.setPagepercount(rs.getInt("pagepercount"));
					boardsetting.setIsfileupload(rs.getInt("isfileupload"));
					return boardsetting;
				}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return null;
	}

	// 글의 총개수 가져오기
	public int selectCount(Connection con,String board_id, String[] searchKeyArr, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try{
			//sql 작성
			String sql=" select count(*) from " + board_id ;
			
			String searchStr = "";
			
			if(searchWord != null && !searchWord.equals("")){
				searchStr = " where 1=2 ";
				for(String searchKey: searchKeyArr){
					searchStr += " or " + searchKey + " like ? ";
				}
			}
			sql += searchStr;
			
			//상태
			pstmt = con.prepareStatement(sql);
			int idx=1;
			if(searchWord != null && !searchWord.equals(""))
				for(int i=0;i<searchKeyArr.length;i++){
					pstmt.setString(idx++, "%"+searchWord+"%");
				}
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null)
				if(rs.next())
					return rs.getInt(1);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return 0;
	}

	  //글의 덧글을 가져오는 메소드
	public List<BoardReply> replyView(Connection con, String board_id, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardReply> list = null;
		// 처리
		try {
			// sql 작성

			String sql = "select no,rno,rcontent,id,rdate from " +board_id +"_comment where rno = ? order by rdate desc";
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			// System.out.println(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				list = new ArrayList<BoardReply>();
				while (rs.next()){
					BoardReply br = new BoardReply();
					br.setNo(rs.getInt("no"));
					br.setPno(rs.getInt("rno"));
					br.setRcontent(rs.getString("rcontent"));
					br.setId(rs.getString("id"));
					br.setRdate(rs.getString("rdate"));
					list.add(br);
				}
				return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	
	}

	public int replyWrite(Connection con, BoardReply br, String board_id,
			String rno) throws SQLException {
		
		PreparedStatement pstmt = null;
		// 처리
		try {

			String sql = " insert into " + board_id+"_comment (no,rno,rcontent,id) " + 
			 "values ("+board_id+"_comment_seq.nextval,?,?,?)";
			// 상태
			pstmt = con.prepareStatement(sql);

			// ?에 대한 데이터 셋팅
			pstmt.setInt(1, Integer.parseInt(rno));
			pstmt.setString(2, br.getRcontent());
			pstmt.setString(3, br.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}

		
	}

	public void replyUpdate(Connection con, BoardReply br, String board_id,
			int no, int pno) throws SQLException {
		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " update " + board_id +"_comment "
					+ " set rcontent = ? where no = ? and rno = ? ";
					
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, br.getRcontent());
			pstmt.setInt(2,no);
			pstmt.setInt(3, pno);
			pstmt.executeUpdate();
			
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		
	}

	public void replyDelete(Connection con, BoardReply br, String board_id,
			int no, int pno) throws SQLException {
		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " delete from " + board_id +"_comment "
					+ " where no = ? and rno = ? ";
					
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,no);
			pstmt.setInt(2, pno);
			pstmt.executeUpdate();
			
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		
	}
	
	// 부모글의 parentno 가져오기

	public int selectParent(Connection con, String board_id, int rno) throws SQLException {
		// TODO Auto-generated method stub
		// 사용할 객체 선언
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				// 처리
				try {
					// sql 작성
					String sql = "select parentsno  from " + board_id + " where no = ?";
					// 상태
					pstmt = con.prepareStatement(sql);
					// 실행 select: executeQuery()
					pstmt.setInt(1, rno);
					rs = pstmt.executeQuery();
					if (rs != null)
						if (rs.next())
							//System.out.println("부모글 번호 :"+ rs.getInt("parentsno"));
							return rs.getInt("parentsno");
				} finally {
					// 처리가 된 후 객체 닫기
					JdbcUtil.close(pstmt);
					JdbcUtil.close(rs);
				}
				return 0;
		
		
	}

	public List<BoardSetting> selectBoardSetting(Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardSetting> list = null;
		// 처리
		try {
			// sql 작성

			String sql = "select board_id,board_name,writer_lv,comment_lv "
					+ " ,reply_lv,pagepercount,isfileupload "
					+ " from board_setting ";
					
			// 상태
			pstmt = con.prepareStatement(sql);
			// 실행 select: executeQuery()
		    rs = pstmt.executeQuery();
		    
			if (rs != null)
				list = new ArrayList<BoardSetting>();
				while (rs.next()) {
					
					BoardSetting boardsetting = new BoardSetting();
					boardsetting.setBoard_id(rs.getString("board_id"));
					boardsetting.setBoard_name(rs.getString("board_name"));
					boardsetting.setWriter_lv(rs.getInt("writer_lv"));
					boardsetting.setComment_lv(rs.getInt("comment_lv"));
					boardsetting.setReply_lv(rs.getInt("reply_lv"));
					boardsetting.setPagepercount(rs.getInt("pagepercount"));
					boardsetting.setIsfileupload(rs.getInt("isfileupload"));
					list.add(boardsetting);
				}
				return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

	}

	public int updateBoardSetting(Connection con, BoardSetting bs) throws SQLException {
		PreparedStatement pstmt = null;
		
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " update board_setting "
			+ " set board_name = ? , writer_lv = ? , comment_lv = ?  , "
			+ " reply_lv = ? , isfileupload = ? , pagepercount = ? "
			+ " where board_id = ? ";
					
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bs.getBoard_name());
			pstmt.setInt(2, bs.getWriter_lv());
			pstmt.setInt(3, bs.getComment_lv());
			pstmt.setInt(4, bs.getReply_lv());
			pstmt.setInt(5, bs.getIsfileupload());
			pstmt.setInt(6, bs.getPagepercount());
			pstmt.setString(7, bs.getBoard_id());
			
			System.out.println(sql);
			pstmt.executeUpdate();
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		
	}

	
	
	
}
