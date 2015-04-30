package com.vyon.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.board.model.AttachedFile;
import com.vyon.board.model.Board;
import com.vyon.jdbc.JdbcUtil;

public class OracleBoardDao extends BoardDao {

	@Override
	public int write(Connection con, Board board, String board_id)
			throws SQLException {
		// TODO Auto-generated method stub
		// 필요한 객체 선언
		// System.out.println("OracleBoardDao.write()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " insert into " + board_id
					+ " (no,title,id,content,grp,grporder,lv,parentsno) " + " values("
					+ board_id + "_seq.nextval,?,?,?," + board_id + "_seq.nextval,1,0," + board_id + "_seq.nextval)";
			// 상태
			pstmt = con.prepareStatement(sql);

			// ?에 대한 데이터 셋팅
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			
			pstmt.executeUpdate();
			System.out.println(sql);
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	// 파일을 첨부시키는 메소드
	public int writeFile(Connection con, int no, List<AttachedFile> fileList,
			String table_id) throws SQLException {
		// System.out.println("OracleBoardDao.writeFile()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " update " + table_id
					+ " set originalFile=? ,serverFile=?  where no= ?";
			// 상태
			pstmt = con.prepareStatement(sql);
			for (AttachedFile af : fileList) {
				// ?에 대한 데이터 셋팅
				pstmt.setString(1, af.getOriginalFile());
				pstmt.setString(2, af.getServerFile());
				pstmt.setInt(3, no);
				// 실행
				pstmt.executeUpdate();
			}
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	// 게시판 글 리스트 가져오기

	public List<Board> list(Connection con, String board_id, int startRow,
			int endRow, String [] searchKeyArr, String searchWord) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("OracleBoardDao.list()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = null;
		String searchStr = "";
		if(searchWord != null && !searchWord.equals("")){
			searchStr = " where 1=2 ";
			for(String searchKey: searchKeyArr){
				searchStr += " or " + searchKey + " like ? ";
			}
		}
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc

			String sql = " select no,title,id,to_char(wdate,'yyyy-mm-dd') wdate,"
					+ " grp, grporder, lv, target "
					+ " from " + board_id 
					+ searchStr
					+ " order by no desc ";
			
			sql = " select * from (" +sql+ " ) order by grp desc , grporder asc"; 
			
			// rownum을 붙인다.
			sql = " select rownum rnum, no,title,id, wdate,"
					+ " grp,grporder,lv,target "
					+ " from ( " + sql + " ) ";


			// rnum중에서 startRow,endRow를 적용시킨다.:where
			sql = " select * from ( " + sql + " ) "
					+ " where rnum between ? and ? ";
		
            //System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int idx=1;
			if(searchWord != null && !searchWord.equals(""))
				for(int i=0;i<searchKeyArr.length;i++){
					pstmt.setString(idx++, "%"+searchWord+"%");
				}
			pstmt.setInt(idx++, startRow);
			pstmt.setInt(idx++, endRow);

			// ?에 대한 데이터 셋팅

			// System.out.println("sql:" + sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null) {
				list = new ArrayList<Board>();
				while (rs.next()) {
					list.add(makeBoardFromListResultSet(rs));
				}
			}
			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}

	// 답변 가져오기
	public int reply(Connection con, Board board, String board_id, int no)
			throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int ReplyGrpOrder= 0;
		int Rlv= 0;
		int Rgrp = 0;
		// 처리
		try {
			
			String sql = " select grp,grporder,lv from " + board_id + " where no = ?   ";
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			rs.next();
			Rgrp = rs.getInt ("grp");
			ReplyGrpOrder = rs.getInt("grporder");
			Rlv= rs.getInt("lv");
			
						
			String sql1 = " insert into " + board_id
					+ " (no,title,id,content,grp,grporder,lv,parentsno) " + " values("
					+ board_id + "_seq.nextval,?,?,?,?,?,?,?) ";
			// 상태
			pstmt = con.prepareStatement(sql1);

			// ?에 대한 데이터 셋팅
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, Rgrp);
			pstmt.setInt(5, ReplyGrpOrder+1);
			pstmt.setInt(6, Rlv+1);
			pstmt.setInt(7, board.getParentsno());
			pstmt.executeUpdate();
			System.out.println(sql1);
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}


}
