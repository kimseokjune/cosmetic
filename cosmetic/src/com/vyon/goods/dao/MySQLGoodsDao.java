package com.vyon.goods.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.vyon.goods.model.AttachedFile;
import com.vyon.goods.model.Goods;

public class MySQLGoodsDao extends GoodsDao {

	@Override
	public int admin_write(Connection con, Goods goods) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Goods> user_list(Connection con, int startRow, int endRow, String cate, String sort, String method)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> admin_list(Connection con, int startRow, int endRow,
			String cate, String sort, String method) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int admin_writeFile(Connection con, int no, String gcode,
			List<AttachedFile> fileList) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
