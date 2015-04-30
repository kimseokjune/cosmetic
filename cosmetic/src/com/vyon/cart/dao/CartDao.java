package com.vyon.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.cart.model.CartBean;
import com.vyon.jdbc.JdbcUtil;

public class CartDao {
	public List<CartBean> cartList(Connection con, String id)
			throws SQLException {
		List<CartBean> list = new ArrayList<CartBean>();

		// 필요한 변수
		// Goods: 상품 이름, 이미지 이름
		// Price: 상품가격, 할인률
		// CartList: 상품개수
		String sql = "select cartlist.no, cartlist.gcode, cartlist.goodsname, cartlist.price, cartlist.gcount, cartlist.gimg, goods.count "
				+ " from cartlist cartlist, goods goods"
				+ " where cartlist.id = ? and goods.gcode = cartlist.gcode "
				+ " order by no desc";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			CartBean bean = new CartBean();
			bean.setCartNo(rs.getInt("no"));
			bean.setGcode(rs.getString("gcode"));
			bean.setGoodsName(rs.getString("goodsname"));
			bean.setPrice(rs.getInt("price"));
			bean.setGcount(rs.getInt("gcount"));
			bean.setImgName(rs.getString("gimg"));
			bean.setCount(rs.getInt("count"));
			list.add(bean);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;
	}

	public void cartListAllDelete(Connection con, String id)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from cartlist where id = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);

		pstmt.executeUpdate();

		JdbcUtil.close(pstmt);
	}

	public String cartInsert(Connection con, String id, String gcode, int gcount)
			throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select goods_img.s_file, round(price.price*(100-price.disrate)/100,-1) price, goods.name "
				+ " from goods_img goods_img, price price, goods goods "
				+ " where goods_img.gcode = ? and goods_img.gcode = price.gcode and sysdate between price.startdate and price.enddate "
				+ " and goods_img.gcode = goods.gcode and goods_img.img_size = 's'";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
				
		pstmt.setString(1, gcode);		
		
		ResultSet rs = pstmt.executeQuery();
		
		String gimg = "";
		int price = 0;
		String goodsname = "";
		
		if(rs.next()){
			gimg = rs.getString("s_file");
			price = rs.getInt("price");
			goodsname = rs.getString("name");
		}
		
		sql = "insert into cartlist(no,id,gcode,gcount,gimg,price,goodsname) "
				+ "values(cartlist_seq.nextval, ?, ?, ?, ?, ?, ?)";

		pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setString(2, gcode);
		pstmt.setInt(3, gcount);
		pstmt.setString(4, gimg);
		pstmt.setInt(5, price);
		pstmt.setString(6, goodsname);

		pstmt.executeUpdate();

		JdbcUtil.close(rs);		
		JdbcUtil.close(pstmt);
		
		return gimg;
	}

	public boolean checkCartGcode(Connection con, String id, String gcode)
			throws SQLException {
		// TODO Auto-generated method stub
		boolean returnValue = false;

		String sql = "select gcode from cartlist where gcode=? and id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, gcode);
		pstmt.setString(2, id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			returnValue = true;
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return returnValue;
	}

	public void cartUpdate(Connection con, String id, String gcode, int gcount)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update cartlist set gcount = ? where id=? and gcode=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, gcount);
		pstmt.setString(2, id);
		pstmt.setString(3, gcode);

		pstmt.executeUpdate();

		JdbcUtil.close(pstmt);

	}

	public void cartUpdate(Connection con, String id, int cartNo, int gcount)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update cartlist set gcount = ? where id=? and no=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, gcount);
		pstmt.setString(2, id);
		pstmt.setInt(3, cartNo);

		pstmt.executeUpdate();

		JdbcUtil.close(pstmt);

	}

	public int getCartGcount(Connection con, String id, String gcode)
			throws SQLException {
		// TODO Auto-generated method stub

		int returnValue = 0;

		String sql = "select gcount from cartlist where gcode=? and id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, gcode);
		pstmt.setString(2, id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			returnValue = rs.getInt("gcount");
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return returnValue;
	}

	public void delete(Connection con, String id, String[] list)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String no : list) {
			sql = "delete from cartlist where no = ? and id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(no));
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public List<CartBean> getSeleteCartList(Connection con, String id,
			List<String> cartNoList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		List<CartBean> list = new ArrayList<CartBean>();

		ResultSet rs = null;

		for (String no : cartNoList) {
			sql = "select cartlist.gcode, goods.name, round(price.price*(100-price.disrate)/100,-1) price, cartlist.gcount, goods_img.o_file "
					+ " from goods goods, price price, cartlist cartlist, goods_img goods_img "
					+ " where cartlist.id = ? and cartlist.gcode = goods.gcode and goods.gcode = price.gcode "
					+ " and sysdate between price.startdate and price.enddate and goods.gcode=goods_img.gcode and goods_img.img_size = 'm' and cartlist.no = ? and cartlist.gcount <= goods.count ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				CartBean cartbean = new CartBean();

				cartbean.setCartNo(Integer.parseInt(no));
				cartbean.setGcode(rs.getString("gcode"));
				cartbean.setGcount(rs.getInt("gcount"));
				cartbean.setGoodsName(rs.getString("name"));
				cartbean.setImgName(rs.getString("o_file"));
				cartbean.setPrice(rs.getInt("price"));

				list.add(cartbean);
			}
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;
	}

	public List<CartBean> checkInsufficient(Connection con, String id)
			throws SQLException {
		// TODO Auto-generated method stub
		List<CartBean> list = new ArrayList<CartBean>();

		String sql = " select cartlist.no, cartlist.gcode, goods.name, round(price.price*(100-price.disrate)/100,-1) price, cartlist.gcount, goods_img.o_file "
				+ " from goods goods, price price, cartlist cartlist, goods_img goods_img "
				+ " where cartlist.id = ? and cartlist.gcode = goods.gcode and goods.gcode = price.gcode "
				+ " and sysdate between price.startdate and price.enddate and goods.gcode=goods_img.gcode and goods_img.img_size = 'm' and cartlist.gcount > goods.count";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			CartBean bean = new CartBean();

			bean.setCartNo(rs.getInt("no"));
			bean.setGcode(rs.getString("gcode"));
			bean.setGcount(rs.getInt("gcount"));
			bean.setGoodsName(rs.getString("name"));
			bean.setImgName(rs.getString("o_file"));
			bean.setPrice(rs.getInt("price"));

			list.add(bean);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;
	}

	public void deleteSelecetedCartList(Connection con, String id,
			List<CartBean> cartList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (CartBean bean : cartList) {
			sql = "delete from cartlist where id = ? and no = ?";
			pstmt = con.prepareStatement(sql);

			int cartNo = bean.getCartNo();

			pstmt.setString(1, id);
			pstmt.setInt(2, cartNo);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public boolean checkGoodsCount(Connection con, String gcode, int gcount)
			throws SQLException {
		// TODO Auto-generated method stub

		boolean returnVal = false;
		String sql = "select count from goods where gcode = ? and ? <= goods.count";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, gcode);
		pstmt.setInt(2, gcount);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			returnVal = true;
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return returnVal;
	}

	public String getGoodsName(Connection con, String gcode)
			throws SQLException {
		// TODO Auto-generated method stub

		String sql = "select name from goods where gcode = ? and ";

		String goodsName = "";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, gcode);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			goodsName = rs.getString("name");
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return goodsName;
	}

	public CartBean getCartBean(Connection con, String gcode)
			throws SQLException {
		// TODO Auto-generated method stub

		CartBean bean = new CartBean();

		String sql = "select goods.name, round(price.price*(100-price.disrate)/100,-1) price, goods_img.s_file "
				+ " from goods goods, price price, goods_img goods_img "
				+ " where goods.gcode = ? and goods.gcode = price.gcode "
				+ " and sysdate between price.startdate and price.enddate and goods.gcode=goods_img.gcode and goods_img.img_size = 's'";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, gcode);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			bean.setCartNo(-1);
			bean.setGoodsName(rs.getString("name"));
			bean.setImgName(rs.getString("s_file"));
			bean.setPrice(rs.getInt("price"));
		}else{
			System.out.println("없어용");
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return bean;
		
	}

	public List<String> getImgNameList(Connection con,
			List<CartBean> selectCartlist) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String gimg = "";
		
		List<String> imgNameList = new ArrayList<String>();
				
		for(CartBean bean : selectCartlist){
			String gcode = bean.getGcode();
			
			sql = " select s_file from goods_img where gcode = ? and img_size = 's'";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, gcode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				gimg = rs.getString("s_file");
			}
			
			System.out.println(gimg);
			imgNameList.add(gimg);			
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	
		return imgNameList;
	}
}
