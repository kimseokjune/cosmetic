package com.vyon.cart.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vyon.cart.dao.CartDao;
import com.vyon.cart.dao.CartDaoProvider;
import com.vyon.cart.model.CartBean;
import com.vyon.cart.model.CartDisplayBean;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;

public class CartListService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CartListService.process()");
		// 세션을 사용할 객체를 만든다.
		HttpSession session = request.getSession();
		// 세션에서 id 값을 가져온다.
		String id = (String) session.getAttribute("id");

		String url = "";
		boolean forward = true;

		if (id != null) {
			Connection con = null;
			try {
				con = JdbcUtil.getConnection();
				CartDao dao = CartDaoProvider.getInstance().getCartDao();
				List<CartBean> list = dao.cartList(con, id);
				// 장바구니화면에 보여줄 정보 객체 생성
				CartDisplayBean cartDisplayBean = new CartDisplayBean(list);
				session.setAttribute("cartDisplayBean", cartDisplayBean);
				
				url = "../cart/list.jsp";
				forward = true;
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
		} else {
			url = "../error/error.do";
			forward = false;
		}
		
		
		/* 마이페이지 링크는 관리자 처럼 거시면됩니다
		/*mypage_right.jsp에 본인이 보여주고싶은 페이지를 적으시면됩니다.*/
		request.setAttribute("mypage_right", url);
		
		

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(forward);
		// 진행하는 곳 : /cart/list.jsp
		forwardInfo.setForwardStr("../mypage/mypage_menu.jsp");
		return forwardInfo;
	}
}
