package com.vyon.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.board.model.AttachedFile;
import com.vyon.board.model.Board;


public class MySQLBoardDao extends BoardDao{

	// 방명록 쓰기 메소드
	public int write(Connection con, Board board)
		throws SQLException {
		// TODO Auto-generated method stub
		//필요한 객체 선언
		return 0;
	}

	
	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
	// 구현(재정의)해 준다. 
	public List<Board> list
	(Connection con, int startRow, int endRow)
	throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("OracleLMessageDao.selectList()");
		return null;
	}

	@Override
	public int write(Connection con, Board board, String board_id)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int writeFile(Connection con, int no, List<AttachedFile> fileList,
			String board_id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Board> list(Connection con, String board_id)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Board> list(Connection con, String board_id, int startRow,
			int endRow, String[] searchKeyArr, String searchWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int reply(Connection con, Board board, String board_id, int no)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
