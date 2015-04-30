package com.vyon.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.jdbc.JdbcUtil;
import com.vyon.order.model.OrderDetailBean;
import com.vyon.order.model.OrderListBean;

public class OrderDao {

	public int insertOrderList(Connection con, String id, OrderListBean bean)
			throws SQLException {
		// TODO Auto-generated method stub
		int orderListNo = 0;

		// 주문 리스트 넣기
		String sql = " insert into order_list(olno, id, order_day, order_name, order_address, order_zipcode, order_tel, "
				+ " ADDRESSEE_NAME, ADDRESSEE_ADDRESS, ADDRESSEE_ZIPCODE, ADDRESSEE_TEL, PNO, GOODSNAME, TOTALPRICE) "
				+ " values(cartlist_seq.nextval, ?, to_char(sysdate, 'yy/mm/dd') , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// 1: 아이디
		// 2: 주문자 이름
		// 3: 주문자 주소
		// 4: 주문자 우편번호
		// 5: 주문자 연락처
		// 6: 수령인 이름
		// 7: 수령인 주소
		// 8: 수령인 우편번호
		// 9: 수령인 연락처
		// 10: 결제상태
		// 11: 주문상품명
		// 12: 총결제금액

		PreparedStatement pstmt = con.prepareStatement(sql);

		String orderName = bean.getOrderName();
		System.out.println("orderName : " + orderName);
		String orderAddress = bean.getOrderAddress();
		String orderZipcode = bean.getOrderZipcode();
		String orderTel = bean.getOrderTel();

		String addresseeName = bean.getAddresseeName();
		String addresseeAddress = bean.getAddresseeAddress();
		String addresseeZipcode = bean.getAddresseeZipcode();
		String addresseeTel = bean.getAddresseeTel();

		int paystate = bean.getPno();

		String goodsName = bean.getGoodsName();

		int totalPrice = bean.getTotalPrice();

		pstmt.setString(1, id);

		pstmt.setString(2, orderName);
		pstmt.setString(3, orderAddress);
		pstmt.setString(4, orderZipcode);
		pstmt.setString(5, orderTel);

		pstmt.setString(6, addresseeName);
		pstmt.setString(7, addresseeAddress);
		pstmt.setString(8, addresseeZipcode);
		pstmt.setString(9, addresseeTel);

		pstmt.setInt(10, paystate);
		pstmt.setString(11, goodsName);
		pstmt.setInt(12, totalPrice);

		pstmt.executeUpdate();

		// 방금 넣은 주문 번호 가져오기

		sql = " select * from order_list where id = ? ";
		sql = " select max(olno) from ( " + sql + " )";

		pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			orderListNo = rs.getInt(1);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		System.out.println("orderListNo: " + orderListNo);

		return orderListNo;
	}

	public void minusGoodsCount(Connection con, List<OrderDetailBean> orderList)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (OrderDetailBean bean : orderList) {

			sql = "update goods set " + " goods.count = goods.count - ? " // 굿즈
																			// 카운트에서
																			// OrderDetailBean에
																			// 담긴
																			// gcount만큼
																			// 빼기
					+ " where goods.gcode = ? "; // goods.code와 OrderDetailBean에
													// 담긴 gcode와 같을 때

			pstmt = con.prepareStatement(sql);

			int gcount = bean.getGcount();
			System.out.println(gcount);
			String gcode = bean.getGcode();
			System.out.println(gcode);

			pstmt.setInt(1, gcount);
			pstmt.setString(2, gcode);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void insertOrderDetail(Connection con, String id, int orderNo,
			List<OrderDetailBean> orderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		for (OrderDetailBean bean : orderList) {

			// gcode의 이미지 파일과 상품 이름 가져오기.
			sql = "select goods.name, goods_img.s_file "
					+ " from goods goods, goods_img goods_img  "
					+ " where goods.gcode = ? and goods_img.gcode = goods.gcode ";

			pstmt = con.prepareStatement(sql);

			String gcode = bean.getGcode();
			String goodsname = ""; // 상품의 이름
			String gimg = ""; // 상품의 이미지 이름
			int gcount = bean.getGcount(); // 상품의 개수
			System.out.println("gcount : " + gcount);
			int orderState = bean.getSno(); // 상품의 주문 상태
			System.out.println("orderState : " + orderState);

			pstmt.setString(1, gcode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				goodsname = rs.getString("name");
				System.out.println("goodsname : " + goodsname);				
				gimg = rs.getString("s_file");
				System.out.println("gimg : " + gimg);
			}
			
			// 현재 가격 넣기
			sql = " select round(price.price*(100-price.disrate)/100,-1) price "
				+ " from price "
				+ " where gcode = ? and sysdate between startdate and enddate ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, gcode);
			
			rs = pstmt.executeQuery();
			
			int price = 0;

			if (rs.next()) {
				price = rs.getInt("price");
			}			
			

			sql = "insert into order_detail(odno, gcode, gcount, olno, sno, id, paymentday, GOODSNAME, GMIG, price) "
					+ "values(orderdetail_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gcode);
			pstmt.setInt(2, gcount);
			pstmt.setInt(3, orderNo);
			pstmt.setInt(4, orderState);
			pstmt.setString(5, id);
			pstmt.setString(6, goodsname);
			pstmt.setString(7, gimg);
			pstmt.setInt(8, price);

			pstmt.executeUpdate();	
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}

	public List<OrderListBean> getList(Connection con, String id)
			throws SQLException {
		// TODO Auto-generated method stub
		List<OrderListBean> list = new ArrayList<OrderListBean>();

		String sql = "select order_list.olno, to_char(order_day, 'yy/mm/dd') order_day, order_list.goodsname, order_list.totalprice, order_list.ORDER_NAME, order_list.ADDRESSEE_NAME, payment.payment_name "
				+ " from order_list order_list, payment payment "
				+ " where id = ? and order_list.pno = payment.pno "
				+ " order by order_list.olno desc ";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			OrderListBean bean = new OrderListBean();

			int olno = rs.getInt("olno"); // 주문번호
			String orderDay = rs.getString("order_day"); // 주문날짜
			String goodsName = rs.getString("goodsname"); // 주문번호
			String paymentName = rs.getString("payment_name");// 주문 결제 방법
			int totalPrice = rs.getInt("totalprice");// 토탈 금액
			String orderName = rs.getString("ORDER_NAME");
			String addresseeName = rs.getString("ADDRESSEE_NAME");

			// System.out.println(olno);
			// System.out.println(orderDay);
			// System.out.println(goodsName);
			// System.out.println(paymentName);
			// System.out.println(orderStateName);
			// System.out.println(totalPrice);

			bean.setOrderNo(olno);
			bean.setOrderDay(orderDay);
			bean.setGoodsName(goodsName);
			bean.setPaystate(paymentName);
			bean.setTotalPrice(totalPrice);
			bean.setOrderName(orderName);
			bean.setAddresseeName(addresseeName);

			list.add(bean);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;

	}

	public List<OrderDetailBean> getDetailList(Connection con, int orderNo)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("getDetailList");
		List<OrderDetailBean> list = new ArrayList<OrderDetailBean>();

		String sql = "select order_detail.odno, order_detail.gcode, order_detail.gcount, order_detail.price, order_detail.goodsname, order_detail.gmig, order_state.orderstate_name, order_detail.sno, order_detail.deliveryday, order_detail.receiptday, order_detail.paymentday "
				+ " from order_detail order_detail, order_state order_state"
				+ " where olno = ? and order_state.osno = order_detail.sno";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, orderNo);
		System.out.println("dao orderno: " + orderNo);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			OrderDetailBean bean = new OrderDetailBean();
			bean.setNo(rs.getInt("odno"));
			bean.setGcode(rs.getString("gcode"));
			bean.setGoodsName(rs.getString("goodsname"));
			bean.setPrice(rs.getInt("price"));
			bean.setGcount(rs.getInt("gcount"));
			bean.setImgName(rs.getString("gmig"));
			bean.setOrderState(rs.getString("orderstate_name"));
			bean.setSno(rs.getInt("sno"));
			bean.setDeliveryday(rs.getString("deliveryday"));
			bean.setReceiptday(rs.getString("receiptday"));
			bean.setPaymentday(rs.getString("paymentday"));
			int sumPrice = bean.getGcount() * bean.getPrice();
			bean.setSumPrice(sumPrice);

			list.add(bean);
		}

		return list;
	}

	public OrderListBean getListBean(Connection con, int orderNo)
			throws SQLException {
		// TODO Auto-generated method stub
		OrderListBean orderListBean = new OrderListBean();

		String sql = "select order_list.ORDER_NAME, order_list.ADDRESSEE_TEL, order_list.ADDRESSEE_ZIPCODE, order_list.ADDRESSEE_ADDRESS, order_list.ADDRESSEE_NAME, "
				+ " order_list.ORDER_TEL, order_list.ORDER_ADDRESS, order_list.ORDER_DAY, order_list.OLNO, order_list.ORDER_ZIPCODE, order_list.ID, order_list.TOTALPRICE, order_list.GOODSNAME, payment.payment_name "
				+ " from order_list order_list, payment payment where olno = ? and payment.pno = order_list.pno";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, orderNo);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			orderListBean.setOrderNo(rs.getInt("OLNO"));
			orderListBean.setOrderName(rs.getString("ORDER_NAME"));
			orderListBean.setOrderAddress(rs.getString("ORDER_ADDRESS"));
			orderListBean.setOrderTel(rs.getString("ORDER_TEL"));
			orderListBean.setOrderZipcode(rs.getString("ORDER_ZIPCODE"));
			orderListBean.setOrderDay(rs.getString("ORDER_DAY"));
			orderListBean.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
			orderListBean
					.setAddresseeAddress(rs.getString("ADDRESSEE_ADDRESS"));
			orderListBean.setAddresseeTel(rs.getString("ADDRESSEE_TEL"));
			orderListBean
					.setAddresseeZipcode(rs.getString("ADDRESSEE_ZIPCODE"));
			orderListBean.setTotalPrice(rs.getInt("TOTALPRICE"));
			orderListBean.setPaystate(rs.getString("payment_name"));
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return orderListBean;
	}

	public void plusGoodsCount(Connection con, String[] selectedList)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("plusGoodsCount");
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("orderDetailList");

		for (String odno : selectedList) {

			sql = "select gcount, gcode from order_detail where odno = ? and sno = 2 ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			rs = pstmt.executeQuery();

			int gcount = 0;
			String gcode = "";

			if (rs.next()) {
				gcount = rs.getInt("gcount");
				gcode = rs.getString("gcode");
			}

			sql = "update goods set count = count + ? where gcode = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, gcount);
			System.out.println("gcount: " + gcount);

			pstmt.setString(2, gcode);
			System.out.println("gcode: " + gcode);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}

	public void cancelOrderDetail(Connection con, String[] selectedList)
			throws SQLException {
		// TODO Auto-generated method stub

		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedList) {
			sql = "update order_detail set sno = 15 where odno = ? and sno = 14 or sno =2";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void deleteOrderList(Connection con, int orderNo)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from order_list where olno = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, orderNo);

		pstmt.executeUpdate();

		JdbcUtil.close(pstmt);
	}

	public List<OrderListBean> getList(Connection con) throws SQLException {
		// TODO Auto-generated method stub

		List<OrderListBean> list = new ArrayList<OrderListBean>();

		String sql = "select order_list.olno, to_char(order_day, 'yy/mm/dd') order_day, order_list.order_name, order_list.addressee_name, order_list.goodsname, payment.payment_name, order_list.totalprice"
				+ " from order_list order_list, payment payment"
				+ " where order_list.pno = payment.pno"
				+ " order by order_list.olno desc ";

		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			OrderListBean bean = new OrderListBean();

			bean.setOrderNo(rs.getInt("olno")); // 주문번호
			bean.setOrderDay(rs.getString("order_day")); // 주문날짜
			bean.setGoodsName(rs.getString("goodsname")); // 주문상품명
			bean.setOrderName(rs.getString("order_name")); // 주문자
			bean.setAddresseeName(rs.getString("addressee_name")); // 주문자
			bean.setPaystate(rs.getString("payment_name")); // 주문 결제 방법
			bean.setTotalPrice(rs.getInt("totalprice")); // 토탈 금액

			list.add(bean);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;
	}

	public void updateOrderState(Connection con, String[] selectedOrderList,
			int orderState) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String olno : selectedOrderList) {
			// order_list 주문 상태 변경
			sql = "update order_list set sno = ? where olno = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, orderState);
			pstmt.setString(2, olno);

			pstmt.executeUpdate();

			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = ? where olno = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, orderState);
			pstmt.setString(2, olno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateDeliveryComplete(Connection con, String id,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "";

		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			sql = "update order_detail set sno = 5, receiptday = sysdate where sno = 4 and odno = ? and id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);
			pstmt.setString(2, id);

			pstmt.executeUpdate();

		}

		JdbcUtil.close(pstmt);
	}

	public void updateExchangeReqeust(Connection con, String id,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";

		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			sql = "update order_detail set sno = 6 where sno = 5 and odno = ? and id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateRefundReqeust(Connection con, int odno)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update order_detail set sno = 7 where sno = 5 and odno = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, odno);

		pstmt.executeUpdate();

		JdbcUtil.close(pstmt);
	}

	public void updatePaymentState(Connection con, String[] selectedOrderList)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	public void updateCheckDeposit(Connection con, String[] selectedOrderList)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 2 where sno = 14 and odno = ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateReadyDelivery(Connection con, String[] selectedOrderList)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 3 where odno = ? and sno = 2";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateShipping(Connection con, String[] selectedOrderList)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 4, deliveryday = sysdate where odno = ? and sno = 3";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateExchangeAcception(Connection con,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 10 where odno = ? and sno = 6";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateExchanging(Connection con, String[] selectedOrderList)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 8 where odno = ? and sno = 10";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateExchangeComplete(Connection con, String id,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 12 where odno = ? and sno = 8 and id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateRefundReqeust(Connection con, String id,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 7 where odno = ? and sno = 5 and id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateRefundProcess(Connection con, String id,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 11 where odno = ? and sno = 7 and id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateRefunding(Connection con, String[] selectedOrderList)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 9 where odno = ? and sno = 11";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public void updateRefundComplete(Connection con, String id,
			String[] selectedOrderList) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		PreparedStatement pstmt = null;

		for (String odno : selectedOrderList) {
			// order_detail 주문 상태 변경
			sql = "update order_detail set sno = 13 where odno = ? and sno = 9 and id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, odno);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

		JdbcUtil.close(pstmt);
	}

	public int selectCount(Connection con, String id, String searchKey,
			String searchWord, String startDate, String endDate)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// sql 작성
		String sql = " select count(*) from order_list ";

		String searchStr = "";

		if (searchWord != null && !searchWord.equals("")) {
			searchStr = " where 1=2 or " + searchKey + " like ? and ";
		} else {
			searchStr = " where ";
		}

		sql += searchStr + " order_day between ? and ? and id = ? ";

		System.out.println("sql : " + sql);
		// 상태
		pstmt = con.prepareStatement(sql);
		int idx = 1;
		if (searchWord != null && !searchWord.equals("")) {
			pstmt.setString(idx++, "%" + searchWord + "%");
		}

		pstmt.setString(idx++, startDate);
		pstmt.setString(idx++, endDate);
		pstmt.setString(idx++, id);

		rs = pstmt.executeQuery();
		if (rs != null)
			if (rs.next())
				return rs.getInt(1);

		JdbcUtil.close(pstmt);
		JdbcUtil.close(rs);

		return 0;
	}

	public List<OrderListBean> getList(Connection con, String id, int startRow,
			int endRow, String searchKey, String searchWord, String startDate,
			String endDate) throws SQLException {
		// TODO Auto-generated method stub
		List<OrderListBean> list = new ArrayList<OrderListBean>();

		String sql = "select order_list.olno, to_char(order_day, 'yy/mm/dd') order_day, order_list.goodsname, order_list.totalprice, order_list.ORDER_NAME, order_list.ADDRESSEE_NAME, payment.payment_name "
				+ " from order_list order_list, payment payment ";

		String searchStr = "";

		if (searchWord != null && !searchWord.equals("")) {

			searchStr = " where 1=2 or " + searchKey + " like ? and ";
		} else {
			searchStr = " where ";
		}

		sql += searchStr
				+ " order_day between ? and ? and id = ? and order_list.pno = payment.pno "
				+ " order by order_day desc, order_list.olno desc";

		// rownum을 붙인다.
		sql = " select rownum rnum, olno, order_day, goodsname, totalprice, ORDER_NAME, ADDRESSEE_NAME, payment_name "
				+ " from ( " + sql + " ) ";

		// rnum중에서 startRow,endRow를 적용시킨다.:where
		sql = " select olno, order_day, goodsname, totalprice, ORDER_NAME, ADDRESSEE_NAME, payment_name "
				+ " from ( " + sql + " ) " + " where rnum between ? and ? ";

		System.out.println("sql : " + sql);
		PreparedStatement pstmt = con.prepareStatement(sql);

		int idx = 1;

		if (searchWord != null && !searchWord.equals("")) {
			pstmt.setString(idx++, "%" + searchWord + "%");
		}

		pstmt.setString(idx++, startDate);
		pstmt.setString(idx++, endDate);
		pstmt.setString(idx++, id);
		pstmt.setInt(idx++, startRow);
		pstmt.setInt(idx++, endRow);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			OrderListBean bean = new OrderListBean();

			int olno = rs.getInt("olno"); // 주문번호
			String orderDay = rs.getString("order_day"); // 주문날짜
			String goodsName = rs.getString("goodsname"); // 주문번호
			String paymentName = rs.getString("payment_name");// 주문 결제 방법
			int totalPrice = rs.getInt("totalprice");// 토탈 금액
			String orderName = rs.getString("ORDER_NAME");
			String addresseeName = rs.getString("ADDRESSEE_NAME");

			// System.out.println(olno);
			// System.out.println(orderDay);
			// System.out.println(goodsName);
			// System.out.println(paymentName);
			// System.out.println(orderStateName);
			// System.out.println(totalPrice);

			bean.setOrderNo(olno);
			bean.setOrderDay(orderDay);
			bean.setGoodsName(goodsName);
			bean.setPaystate(paymentName);
			bean.setTotalPrice(totalPrice);
			bean.setOrderName(orderName);
			bean.setAddresseeName(addresseeName);

			list.add(bean);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;
	}

	public String getRecentDay(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String endDate = "";

		String sql = "select to_char(max(order_day), 'yy/mm/dd') from order_list ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			endDate = rs.getString(1);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return endDate;
	}

	public String getLastDay(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String startDate = "";

		String sql = "select to_char(min(order_day), 'yy/mm/dd') from order_list ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			startDate = rs.getString(1);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return startDate;
	}

	public int selectCount(Connection con, String searchKey, String searchWord,
			String startDate, String endDate) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// sql 작성
		String sql = " select count(*) from order_list ";

		String searchStr = "";

		if (searchWord != null && !searchWord.equals("")) {
			searchStr = " where 1=2 or " + searchKey + " like ? and ";
		} else {
			searchStr = " where ";
		}

		sql += searchStr + " order_day between ? and ? ";

		System.out.println("sql : " + sql);
		// 상태
		pstmt = con.prepareStatement(sql);

		int idx = 1;
		if (searchWord != null && !searchWord.equals("")) {
			pstmt.setString(idx++, "%" + searchWord + "%");
		}

		pstmt.setString(idx++, startDate);
		pstmt.setString(idx++, endDate);

		rs = pstmt.executeQuery();
		if (rs != null)
			if (rs.next())
				return rs.getInt(1);

		JdbcUtil.close(pstmt);
		JdbcUtil.close(rs);

		return 0;
	}

	public List<OrderListBean> getList(Connection con, int startRow,
			int endRow, String searchKey, String searchWord, String startDate,
			String endDate) throws SQLException {
		// TODO Auto-generated method stub
		List<OrderListBean> list = new ArrayList<OrderListBean>();

		String sql = "select order_list.olno, to_char(order_day, 'yy/mm/dd') order_day, order_list.goodsname, order_list.totalprice, order_list.ORDER_NAME, order_list.ADDRESSEE_NAME, payment.payment_name "
				+ " from order_list order_list, payment payment ";

		String searchStr = "";

		if (searchWord != null && !searchWord.equals("")) {

			searchStr = " where 1=2 or " + searchKey + " like ? and ";
		} else {
			searchStr = " where ";
		}

		sql += searchStr
				+ " order_day between ? and ? and order_list.pno = payment.pno "
				+ " order by order_day desc, order_list.olno desc";

		// rownum을 붙인다.
		sql = " select rownum rnum, olno, order_day, goodsname, totalprice, ORDER_NAME, ADDRESSEE_NAME, payment_name "
				+ " from ( " + sql + " ) ";

		// rnum중에서 startRow,endRow를 적용시킨다.:where
		sql = " select olno, order_day, goodsname, totalprice, ORDER_NAME, ADDRESSEE_NAME, payment_name "
				+ " from ( " + sql + " ) " + " where rnum between ? and ? ";

		System.out.println("sql : " + sql);
		PreparedStatement pstmt = con.prepareStatement(sql);

		int idx = 1;

		if (searchWord != null && !searchWord.equals("")) {
			pstmt.setString(idx++, "%" + searchWord + "%");
		}

		pstmt.setString(idx++, startDate);
		pstmt.setString(idx++, endDate);
		pstmt.setInt(idx++, startRow);
		pstmt.setInt(idx++, endRow);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			OrderListBean bean = new OrderListBean();

			int olno = rs.getInt("olno"); // 주문번호
			String orderDay = rs.getString("order_day"); // 주문날짜
			String goodsName = rs.getString("goodsname"); // 주문번호
			String paymentName = rs.getString("payment_name");// 주문 결제 방법
			int totalPrice = rs.getInt("totalprice");// 토탈 금액
			String orderName = rs.getString("ORDER_NAME");
			String addresseeName = rs.getString("ADDRESSEE_NAME");

			// System.out.println(olno);
			// System.out.println(orderDay);
			// System.out.println(goodsName);
			// System.out.println(paymentName);
			// System.out.println(orderStateName);
			// System.out.println(totalPrice);

			bean.setOrderNo(olno);
			bean.setOrderDay(orderDay);
			bean.setGoodsName(goodsName);
			bean.setPaystate(paymentName);
			bean.setTotalPrice(totalPrice);
			bean.setOrderName(orderName);
			bean.setAddresseeName(addresseeName);

			list.add(bean);
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return list;
	}

	public String getGoodsImgName(Connection con, String gcode)
			throws SQLException {
		// TODO Auto-generated method stub
		String gimg = "";

		String sql = "select s_file from goods_img where gcode = ? and img_size = 's' ";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, gcode);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			gimg = rs.getString("s_file");
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

		return gimg;
	}
}
