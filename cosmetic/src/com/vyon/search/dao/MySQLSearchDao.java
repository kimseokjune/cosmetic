package com.vyon.search.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.vyon.search.model.Search;

public class MySQLSearchDao extends SearchDao {


	@Override
	public List<Search> search(Connection con, Search search,int startRow, int endRow,String name2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Search> search(Connection con, Search search, int startRow,
			int endRow, String name2, String select) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2, String select) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2, String select, String sort, String method)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2, String sort, String method) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	

	

	

}
