package com.vyon.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.vyon.admin.model.Admin;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.model.Member;

public abstract class AdminDao {
	public abstract List<Admin> list(Connection con, int startRow, int endRow) throws SQLException;

	protected Admin makeAdminFromListResultSet(ResultSet rs)
			throws SQLException {
		Admin admin = new Admin();
		admin.setId(rs.getString("id"));
		admin.setPw(rs.getString("pw"));
		admin.setName(rs.getString("name"));
		admin.setEmail(rs.getString("Email"));
		admin.setZipcode(rs.getInt("zipcode"));
		admin.setAddress(rs.getString("address"));
		admin.setdAddress(rs.getString("dAddress"));
		admin.setHp(rs.getString("hp"));
		admin.setTel(rs.getString("tel"));
		admin.setGrade(rs.getInt("grade"));
		admin.setStateNo(rs.getInt("stateNo"));

		return admin;
	}

	public int selectCount(Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리

		try {

			String sql = " select * from member ";
			sql = " select count(*) from (" + sql + ") ";

			pstmt = con.prepareStatement(sql);

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

	

	public int selectCount(Connection con, Admin admin, String name2,
			String select) throws SQLException {
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		System.out.println(admin.getName());
		try {
			if (admin.getName() != null) {
				if(select != null){
				String sql = " select * from member ";
				 sql=" select rownum rnum,id,pw,name,email,tel, "
					   		+ " hp,address,daddress,grade,stateno,zipcode from "
					   		+ " ("+sql+") where 1=2 or "+ select +" like ? ";
				sql = " select count(*) from (" + sql + ") ";
				
				// 상태
				pstmt = con.prepareStatement(sql);
				// 실행 select: executeQuery()
				pstmt.setString(1, "%" + admin.getName() + "%");
				rs = pstmt.executeQuery();
				}else{
					String sql = " select * from member ";
					 sql=" select rownum rnum,id,pw,name,email,tel, "
						   		+ " hp,address,daddress,grade,stateno,zipcode from "
						   		+ " ("+sql+") where 1=2 or name like ? or "
							+ " email like ? or id like ? ";

					sql = "select count(*) from (" + sql + ")";
					// 상태
					pstmt = con.prepareStatement(sql);
					// 실행 select: executeQuery()
					pstmt.setString(1, "%" + admin.getName() + "%");
					pstmt.setString(2, "%" + admin.getName() + "%");
					pstmt.setString(3, "%" + admin.getName() + "%");
					rs = pstmt.executeQuery();
				}
				} else {
				if (name2 != null) {
					if(select != null){
						String sql = " select * from member ";
						 sql=" select rownum rnum,id,pw,name,email,tel, "
							   		+ " hp,address,daddress,grade,stateno,zipcode from "
							   		+ " ("+sql+") where 1=2 or "+select+" like ? ";

					sql = "select count(*) from (" + sql + ")";
					pstmt = con.prepareStatement(sql);
					// 실행 select: executeQuery()
					pstmt.setString(1, "%" + name2 + "%");
					rs = pstmt.executeQuery();
					}else{
						String sql = " select * from member ";
						 sql=" select rownum rnum,id,pw,name,email,tel, "
							   		+ " hp,address,daddress,grade,stateno,zipcode from "
							   		+ " ("+sql+") where 1=2 or name like ? or "
								+ " email like ? or id like ? ";

						sql = "select count(*) from (" + sql + ")";
						pstmt = con.prepareStatement(sql);
						// 실행 select: executeQuery()
						pstmt.setString(1, "%" + name2 + "%");
						pstmt.setString(2, "%" + name2 + "%");
						pstmt.setString(3, "%" + name2 + "%");
						rs = pstmt.executeQuery();
					}
				}else{
					String sql=" select * from member " ;
					sql = "select count(*) from (" + sql + ")";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				}

			}
			
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

	public abstract List<Admin> search(Connection con, Admin admin, int startRow,
			int endRow, String name2, String select)throws SQLException;

	public abstract void memberModify2(Connection con, Admin admin)
			throws SQLException;
}
