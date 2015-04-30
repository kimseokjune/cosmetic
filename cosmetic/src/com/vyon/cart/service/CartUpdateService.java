package com.vyon.cart.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.cart.dao.CartDao;
import com.vyon.cart.dao.CartDaoProvider;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;

public class CartUpdateService implements Service {

	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartListService.process()");
		// 세션을 사용할 객체를 만든다.
		HttpSession sesseion = request.getSession();
		// 세션에서 id 값을 가져온다.
		String id = (String) sesseion.getAttribute("id");

		String url = "";

		// 로그인 시에만 실행
		if (id != null) {

			int cartNo = Integer.parseInt(request.getParameter("cartNo"));
			System.out.println("cartNo: " + cartNo);
			int gcount = Integer.parseInt(request.getParameter("count"));
			System.out.println("count: " + gcount);
			Connection con = null;
			try {
				con = JdbcUtil.getConnection();
				CartDao dao = CartDaoProvider.getInstance().getCartDao();
				dao.cartUpdate(con, id, cartNo, gcount);
				url = "../cart/list.do";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				JdbcUtil.close(con);
			}
		}else{
			url = "../error/error.do";
		}
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : /cart/list.jsp
		forwardInfo.setForwardStr(url);
		return forwardInfo;
	}
}
