package com.vyon.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.admin.model.Admin;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.model.Member;

public class OracleMemberDao extends MemberDao{

	@Override
	public int login(Connection con, Member member) throws SQLException {
		// TODO Auto-generated method stub
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//sql 작성
			 String sql = "select id,pw,grade,stateNo,name "
					 +" from member "
					 +" where id=? and pw=? ";
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			rs=pstmt.executeQuery();
			if (rs.next()) {
				
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setGrade(rs.getInt("grade"));
				member.setStateNo(rs.getInt("stateNo"));
				member.setName(rs.getString("name"));
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		return 0;
		
	}

	@Override
	public void memberJoin(Connection con, Member member) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//sql 작성
	
			 String sql = "insert into member(id,pw, "
				+ " name,email,zipcode,address,daddress,hp,tel,grade,stateno) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getZipcode());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getdAddress());
			pstmt.setString(8, member.getHp());
			pstmt.setString(9, member.getTel());
			pstmt.setInt(10, 1);
			pstmt.setInt(11, 0);
			pstmt.executeUpdate();
			System.out.println("여까지 올라이트");
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		
		
	}

	@Override
	public void memberWithdraw(Connection con, Member member)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		try{
			 String sql = "update member set stateno=1 where id=? and pw=? ";
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, member.getId());
			 pstmt.setString(2, member.getPw());
			 pstmt.executeUpdate();
		}finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	

	
	

		
	}
	// 게시판 쓰기 메소드
//	@Override
//	public int write(Connection con, Member board)
//		throws SQLException {
//		// TODO Auto-generated method stub
//		//필요한 객체 선언
//		System.out.println("OracleBoardDao.write()");
//		// 사용할 객체 선언
//		PreparedStatement pstmt = null;
//		// 처리
//		try{
//			//sql 작성
//			// board의 내용의 리스트해당항목을 글번호 desc
//			String sql=" insert into board(no,title, "
//				+ " content,writer, "
//				+ " fileCount) "
//				+ " values(board_seq.nextval,?,?,?,?) ";
//			//상태
//			pstmt = con.prepareStatement(sql);
//			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, board.getTitle());
//			pstmt.setString(2, board.getContent());
//			pstmt.setString(3, board.getWriter());
//			if(board.getFileList() !=null && board.getFileList().size()>0){
//				pstmt.setInt(4, board.getFileList().size());
//			} else {
//				pstmt.setInt(4,0);
//			}
//			// 실행
//			pstmt.executeUpdate();
//			return 1;
//		} finally {
//			// 처리가 된 후 객체 닫기
//			JdbcUtil.close(pstmt);
//		}
//	}

//
//	@Override
////	파일을 첨부시키는 메소드
//	public int writeFile(Connection con, 
//		int no, List<AttachedFile> fileList)
//		throws SQLException
//	{
//		System.out.println("OracleBoardDao.writeFile()");
//		// 사용할 객체 선언
//		PreparedStatement pstmt = null;
//		// 처리
//		try{
//			//sql 작성
//			// board의 내용의 리스트해당항목을 글번호 desc
//			String sql=" insert into board_file(no,bno, "
//				+" originalFile,serverFile) "
//				+" values(board_file_seq.nextval,?,?,?) ";
//			//상태
//			pstmt = con.prepareStatement(sql);
//			for(AttachedFile af : fileList){
//				// ?에 대한 데이터 셋팅
//				pstmt.setInt(1, no);
//				pstmt.setString(2, af.getOriginalFile());
//				pstmt.setString(3, af.getServerFile());
//				// 실행
//				pstmt.executeUpdate();
//			}
//			return 1;
//		} finally {
//			// 처리가 된 후 객체 닫기
//			JdbcUtil.close(pstmt);
//		}
//	}

//	@Override
	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
	// 구현(재정의)해 준다. 
//	public List<Member> list
//	(Connection con, int startRow, int endRow)
//	throws SQLException {
//		// TODO Auto-generated method stub
//		System.out.println("OracleBoardDao.list()");
//		// 사용할 객체 선언
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<Member> list = null;
//		// 처리
//		try{
//			//sql 작성
//			// board의 내용의 리스트해당항목을 글번호 desc
//			String sql=" select no,title,writer, "
//			+" to_char(wdate,'yyyy-mm-dd') wdate, "
//			+" target, fileCount from board "
//			+" order by no desc ";
//			// rownum을 붙인다.
//			sql=" select rownum rnum,no,title,writer, "
//			+ " wdate, target, fileCount "
//			+ " from ("+sql+") ";
//			// rnum중에서 startRow,endRow를 적용시킨다.:where
//			sql=" select * from ("+sql+") "
//			+ " where rnum between ? and ? ";
//			//상태
//			pstmt = con.prepareStatement(sql);
//			// ?에 대한 데이터 셋팅
//			pstmt.setInt(1, startRow);
//			pstmt.setInt(2, endRow);
//			//실행 select: executeQuery()
//			rs=pstmt.executeQuery();
//			if(rs!=null){
//				list=new ArrayList<Member>();
//				while(rs.next()){
////					Board board = new Board();
////					board.setRnum(rs.getInt("rnum"));
////					board.setNo(rs.getInt("no"));
////					board.setTitle(rs.getString("title"));
////					board.setWriter(rs.getString("writer"));
////					board.setWdate(rs.getString("wdate"));
////					board.setTarget(rs.getInt("target"));
////					list.add(board);
//					list.add(makeBoardFromListResultSet(rs));
//				}
//			}
//			return list;
//		} finally {
//			// 처리가 된 후 객체 닫기
//			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
//		}
//	}


//	@Override
//	public int login(Connection con, Member member) throws SQLException {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	@Override
//	public int write(Connection con, Member member) throws SQLException {
//		// TODO Auto-generated method stub
//		return 0;
//	}


