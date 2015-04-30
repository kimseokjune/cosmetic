package com.vyon.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.admin.model.Admin;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.model.Member;
import com.vyon.search.model.Search;


public class OracleAdinDao extends AdminDao{

	@Override
	public List<Admin> list(Connection con,int startRow,int endRow) throws SQLException{
		// 사용할 객체 선언
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<Admin> list = null;
				// 처리
				try{
					//sql 작성
//					멤버의 모든수에 1번째부터 10번째까지 보여지게 한다
					String sql=" select * from member "
							+ " order by name asc ";
						   sql=" select rownum rnum,id,pw,name,email,tel, "
						   		+ " hp,address,daddress,grade,stateno,zipcode from "
						   		+ " ("+sql+") ";
						   sql=" select * from ("+sql+") where rnum between ? and ? ";
					//상태
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					
					
					//실행 select: executeQuery()
					rs=pstmt.executeQuery();
					if(rs!=null){
						list=new ArrayList<Admin>();
						while(rs.next()){
//							Board board = new Board();
//							board.setRnum(rs.getInt("rnum"));
//							board.setNo(rs.getInt("no"));
//							board.setTitle(rs.getString("title"));
//							board.setWriter(rs.getString("writer"));
//							board.setWdate(rs.getString("wdate"));
//							board.setTarget(rs.getInt("target"));
//							list.add(board);
							list.add(makeAdminFromListResultSet(rs));
						}
					}
					return list;
				} finally {
					// 처리가 된 후 객체 닫기
					JdbcUtil.close(pstmt);JdbcUtil.close(rs);
				}
	}

	@Override
	public List<Admin> search(Connection con, Admin admin, int startRow,
			int endRow, String name2, String select) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Admin> list = null;
		// 처리
		try{
			System.out.println("goods.Name()은"+admin.getName());
			System.out.println("name2는"+name2);
			System.out.println("startRow는"+startRow);
			System.out.println("endRow는"+endRow);
			System.out.println("select는"+select);
			//sql 작성
			if(admin.getName()!=null){
				if(select != null){
					String sql = " select * from member ";
					 sql=" select rownum rnum,id,pw,name,email,tel, "
						   		+ " hp,address,daddress,grade,stateno,zipcode from "
						   		+ " ("+sql+") where 1=2 or " + select 
				     + " like ? ";
					// rnum중에서 startRow,endRow를 적용시킨다.:where
					sql=" select * from ("+sql+") "
					+ " where rnum between ? and ? ";
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, select);
			pstmt.setString(1, "%" + admin.getName() + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			}else{
				String sql = " select * from member ";
				 sql=" select rownum rnum,id,pw,name,email,tel, "
					   		+ " hp,address,daddress,grade,stateno,zipcode from "
					   		+ " ("+sql+") where 1=2 or name like ? or "
						+" email like ? or id like ? ";
						sql= " select * from ("+sql+") "
							+" where rnum between ? and ? ";
					pstmt = con.prepareStatement(sql);
					// ?에 대한 데이터 셋팅
					pstmt.setString(1, "%" + admin.getName() + "%");
					pstmt.setString(2, "%" + admin.getName() + "%");
					pstmt.setString(3, "%" + admin.getName() + "%");
					pstmt.setInt(4, startRow);
					pstmt.setInt(5, endRow);
					//실행 select: executeQuery()
					rs=pstmt.executeQuery();
				
			}
			}else{
				String sql=" select * from member where rnum between ? and ? " ;
					
					//실행 select: executeQuery()
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
					rs=pstmt.executeQuery();
			}
			if(rs!=null){
				list=new ArrayList<Admin>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));

				list.add(makeAdminFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
	}
	@Override
	public void memberModify2(Connection con, Admin admin)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//sql 작성
			 String sql = "select * "
					 +" from member "
					 +" where id=? and email=? ";
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			pstmt.setString(1, admin.getId());
			pstmt.setString(2, admin.getEmail());
			rs=pstmt.executeQuery();
			if (rs.next()) {
				admin.setId(rs.getString("id"));
				admin.setPw(rs.getString("pw"));
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setZipcode(rs.getInt("zipcode"));
				admin.setAddress(rs.getString("address"));
				admin.setdAddress(rs.getString("dAddress"));
				admin.setHp(rs.getString("hp"));
				admin.setTel(rs.getString("tel"));
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		
		
	}
	
		
	}



