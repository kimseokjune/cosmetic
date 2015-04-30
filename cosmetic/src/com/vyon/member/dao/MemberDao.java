package com.vyon.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.model.Member;

public abstract class MemberDao {
	// 공통이 아닌 메소드를 추상 메소드로 선언
	public abstract int login(Connection con, Member member)
			throws SQLException;

	// public abstract int write
	// (Connection con, Member member) throws SQLException;
	//
	// public abstract List<Member> list
	// (Connection con, int startRow, int endRow)
	// throws SQLException;
	//
	// ******* 공통으로 사용되는(동일한 코드) 메소드를 구현한다.
	// 글보기 - 선택한 번호의 하나의 글 보기
	// public Member view(Connection con, int messageId)
	// throws SQLException{
	// System.out.println("BoardDao.view()");
	// return null;
	// }
	public int idCheck(String id) {
		int rst = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return rst;
	}

	public int eCheck(String email) {
		int rst = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return rst;
	}

	public abstract void memberJoin(Connection con, Member member)
			throws SQLException;

	public void serid(Connection con, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member where name=? and email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setStateNo(rs.getInt("stateno"));
			}

		} catch (Exception e) {
			System.out.println("오류입니다.");

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void serpw(Connection con, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member where id=? and name=? and email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setPw(rs.getString("pw"));
				member.setStateNo(rs.getInt("stateno"));
			}

		} catch (Exception e) {
			System.out.println("오류입니다.");

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void memberPw(Connection con, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member where id=? and pw=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getInt("zipcode"));
				member.setAddress(rs.getString("address"));
				member.setdAddress(rs.getString("daddress"));
				member.setHp(rs.getString("hp"));
				member.setTel(rs.getString("tel"));

			}

		} catch (Exception e) {
			System.out.println("오류입니다.");

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void memberUpdate(Connection con, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		try {
			String sql = " update member set pw =?,name=?,email=?,zipcode=?,address=?, "
					+ " daddress=?,hp=?,tel=? where id=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setInt(4, member.getZipcode());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getdAddress());
			pstmt.setString(7, member.getHp());
			pstmt.setString(8, member.getTel());
			pstmt.setString(9, member.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("오류입니다.");

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// Message(bean)에 rs에 있는 데이터를 담는다.List용 메소드
	// protected Member makeBoardFromListResultSet(ResultSet rs)
	// throws SQLException {
	// Member board = new Member();
	// board.setRnum(rs.getInt("rnum"));
	// board.setNo(rs.getInt("no"));
	// board.setTitle(rs.getString("title"));
	// board.setWriter(rs.getString("writer"));
	// board.setWdate(rs.getString("wdate"));
	// board.setTarget(rs.getInt("target"));
	// board.setFileCount(rs.getInt("fileCount"));
	// return board;
	// }

	public abstract void memberWithdraw(Connection con, Member member)
			throws SQLException;

	// TODO Auto-generated method stub

	

	

	

	// TODO Auto-generated method stub

	public Member getMemberInfo(Connection con, String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from member where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		Member bean = new Member();
		if (rs.next()) {
			bean.setName(rs.getString("name"));
			bean.setEmail(rs.getString("email"));
			bean.setZipcode(rs.getInt("zipcode"));
			bean.setAddress(rs.getString("address"));
			bean.setdAddress(rs.getString("daddress"));
			bean.setHp(rs.getString("hp"));
			bean.setTel(rs.getString("tel"));
		}
		return bean;
	}

	// // Message(bean)에 rs에 있는 데이터를 담는다.view용 메소드
	// protected Member makeBoardFromViewResultSet(ResultSet rs)
	// throws SQLException {
	// Member board = makeBoardFromListResultSet(rs);
	// // 추가로 content만 담는다.
	// board.setContent(rs.getString("content"));
	// return board;
	// }

	// // 데이터의 갯수(전체 데이터)를 세는 메소드
	// public int selectCount(Connection con)
	// throws SQLException{
	// // 사용할 객체 선언
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// // 처리
	// try{
	// //sql 작성
	// String sql="select count(*) from board";
	// //상태
	// pstmt = con.prepareStatement(sql);
	// //실행 select: executeQuery()
	// rs=pstmt.executeQuery();
	// if(rs!=null)
	// if(rs.next())
	// return rs.getInt(1);
	// } finally {
	// // 처리가 된 후 객체 닫기
	// JdbcUtil.close(pstmt);JdbcUtil.close(rs);
	// }
	// return 0;
	// }

	// 입력된 글번호를 가져오는 메소드
	// public int currentNo(Connection con)
	// throws SQLException{
	// // 사용할 객체 선언
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// // 처리
	// try{
	// //sql 작성
	// String sql="select max(no) from board";
	// //상태
	// pstmt = con.prepareStatement(sql);
	// //실행 select: executeQuery()
	// rs=pstmt.executeQuery();
	// if(rs!=null)
	// if(rs.next())
	// return rs.getInt(1);
	// } finally {
	// // 처리가 된 후 객체 닫기
	// JdbcUtil.close(pstmt);JdbcUtil.close(rs);
	// }
	// return 0;
	// }

	// 수정 처리 메소드
	// public int update(Connection conn, Member board)
	// throws SQLException {
	// System.out.println("BoardDao.update()");
	// return 0;
	// }
	//
	// // 삭제 처리 메소드
	// public int delete(Connection conn, int no)
	// throws SQLException {
	// System.out.println("BoardDao.delete()");
	// return 0;
	// }
}
