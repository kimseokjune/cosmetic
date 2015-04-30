package com.vyon.order.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.order.dao.OrderDao;

public class OrderCancelService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderCancelService.process()");

		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");
		
		String url = "";

		if (id != null) {
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));

			System.out.println("orderNo: " + orderNo);

			Connection con = null;

			OrderDao orderDao = new OrderDao();

			try {
				con = JdbcUtil.getConnection();

				// 수량을 돌려 놓기
				
				String selectedList[] = request.getParameterValues("check");				

				// orderDetail에 들은 상품의 수량을 돌려 놓기
				orderDao.plusGoodsCount(con, selectedList);

				// 상태 변경
				orderDao.cancelOrderDetail(con, selectedList);
				
				url = "../order/customerView.do?orderNo=" + orderNo;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			url = "../error/error.do";
		}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : /order/payment.html
		forwardInfo.setForwardStr(url);
		return forwardInfo;
	}

}
