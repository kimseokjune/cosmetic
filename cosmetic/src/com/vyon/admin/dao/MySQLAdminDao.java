package com.vyon.admin.dao;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import java.util.List;

import com.vyon.admin.model.Admin;

public class MySQLAdminDao extends AdminDao{

	@Override
	public List<Admin> list(Connection con, int startRow, int endRow)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> search(Connection con, Admin admin, int startRow,
			int endRow, String name2, String select) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memberModify2(Connection con, Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	


}
