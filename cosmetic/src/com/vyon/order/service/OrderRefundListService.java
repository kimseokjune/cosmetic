package com.vyon.order.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

public class OrderRefundListService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderRefundListService.process()");

		// 필요한 변수들 받기
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");
		
		System.out.println("id: " + id);
		
		String query = request.getQueryString();
		System.out.println("query: " + query);
		
		StringTokenizer st = new StringTokenizer(query, "&");
		
		List<String> list = new ArrayList<String>();
		
		while(st.hasMoreTokens()){
			String str = st.nextToken();
			System.out.println("str: " +str );
			list.add(str);
		}	
				
		int odno = Integer.parseInt(list.get(0).substring(5));
		int olno = Integer.parseInt(list.get(1).substring(5));
		
		System.out.println("odno: " + odno);
		System.out.println("olno: " + olno);
		
		String url = "";
		
		// 로그인시만 실행
		if (id != null) {
			Connection con = null;			
			
			OrderDao orderDao = OrderDaoProvider.getInstance().getOrderDao();
			
			try {
				con = JdbcUtil.getConnection();				
				
//				orderDao.updateDeliveryComplte(con, odno);				
				
				url = "/cosmetic/order/customerView.do?orderNo=" + olno;
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