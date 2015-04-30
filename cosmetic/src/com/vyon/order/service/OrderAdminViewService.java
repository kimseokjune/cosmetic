package com.vyon.order.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.order.dao.OrderDao;
import com.vyon.order.dao.OrderDaoProvider;
import com.vyon.order.model.OrderDetailBean;
import com.vyon.order.model.OrderListBean;

public class OrderAdminViewService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderAdminViewService.process()");

		HttpSession session = request.getSession();
		String grade = String.valueOf(session.getAttribute("grade"));	
		
		String url = "";
		
		boolean forward = true;
		
		Connection con = null;
		if (grade.equals("9")) {
			
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			
			try {
				con = JdbcUtil.getConnection();
				OrderDao orderDao = OrderDaoProvider.getInstance()
						.getOrderDao();

				List<OrderDetailBean> orderDetailList = orderDao.getDetailList(
						con, orderNo);
				System.out.println(orderDetailList.size());

				OrderListBean orderListBean = orderDao
						.getListBean(con, orderNo);

				System.out.println(orderListBean.getOrderName());

				request.setAttribute("orderListBean", orderListBean);
				request.setAttribute("orderDetailList", orderDetailList);
				
				url = "/admin/order_view.jsp";
				forward = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}
		} else {
			url = "../error/error.do";
			forward = false;
		}


		/* 관리자 모드 방식이 약간 변경되었습니다.*/
		/*admin_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("admin_right", url);
		
		
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(forward);
		// 진행하는 곳 : /order/payment.html
		forwardInfo.setForwardStr("../admin/admin.jsp");
		return forwardInfo;
	}
}
