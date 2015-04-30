package com.vyon.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	private static DataSource ds = getDataSourceDBCP();
	// 단 한번만 실행되는 DataSource를 가져오는 메소드
	private static DataSource getDataSourceDBCP(){
		DataSource ds = null;
		try {
			Context init = new InitialContext();
			ds=(DataSource)init.
					lookup("java:comp/env/jdbc/OracleDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	// Connection을 가져오는 메소드
	public static Connection getConnection()
	throws SQLException{
		return ds.getConnection();
	}
	
	public static void close(ResultSet rs){
		try {
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt){
		try {
			if(pstmt!=null) pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void close(Connection con){
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void rollback(Connection con){
		if(con!=null){
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
