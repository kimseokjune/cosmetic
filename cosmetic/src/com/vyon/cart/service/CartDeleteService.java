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

public class CartDeleteService implements Service {

	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartDeleteService.process()");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		
		String url = "";

		// 로그인이 되어 있을 때만 실행
		if (id != null) {
			// get방식으로 cartNoList문자 가져오기
			String list[] = request.getParameterValues("check");

			System.out.println(list);

			for (String no : list) {
				System.out.println(no);
			}

			Connection con = null;
			try {
				con = JdbcUtil.getConnection();
				CartDao dao = CartDaoProvider.getInstance().getCartDao();
				dao.delete(con, id, list);
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