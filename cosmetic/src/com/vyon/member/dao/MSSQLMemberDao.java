package com.vyon.member.dao;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import java.util.List;

import com.vyon.member.model.Member;

public class MSSQLMemberDao extends MemberDao{

	@Override
	public int login(Connection con, Member member) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void memberJoin(Connection con, Member member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberWithdraw(Connection con, Member member)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	



//	// 방명록 쓰기 메소드
//	@Override
//	public int write(Connection con, Member board)
//		throws SQLException {
//		// TODO Auto-generated method stub
//		//필요한 객체 선언
//		return 0;
//	}
//
//	@Override
//	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
//	// 구현(재정의)해 준다. 
//	public List<Member> list
//	(Connection con, int startRow, int endRow)
//	throws SQLException {
//		// TODO Auto-generated method stub
////		System.out.println("OracleLMessageDao.selectList()");
//		return null;
//	}
//
//	@Override
//	public int writeFile(Connection con, int no, List<AttachedFile> fileList)
//			throws SQLException {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
