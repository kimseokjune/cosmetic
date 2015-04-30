package com.vyon.order.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.order.dao.OrderDao;
import com.vyon.order.dao.OrderDaoProvider;

public class OrderExchangeRequestService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderExchangeRequestService.process()");

		// 필요한 변수들 받기
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");
		
		System.out.println("id: " + id);
		
		String query = request.getQueryString();
		System.out.println("query: " + query);
		
		StringTokenizer st = new StringTokenizer(query, "=");
		
		String str = "";
		
		while(st.hasMoreTokens()){
			str = st.nextToken();
		}
		
		int orderNo = Integer.parseInt(str);
		
		System.out.println("orderNo: " + orderNo);
		
		String url = "";
		
		// 로그인시만 실행
		if (id != null) {
			Connection con = null;
			
			// 선택한 리스트 가져오기 
			String selectedOrderList[] = request.getParameterValues("check");			

			
			OrderDao orderDao = OrderDaoProvider.getInstance().getOrderDao();
			
			try {
				con = JdbcUtil.getConnection();				
				
				orderDao.updateExchangeReqeust(con, id, selectedOrderList);				
				
				url = "/cosmetic/order/customerView.do?orderNo=" + orderNo;
				System.out.println("url : " + url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			url = "../error/error.do";
		}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /order/OrderCustomerList.do
		forwardInfo.setForwardStr(url);
		return forwardInfo;
	}

}