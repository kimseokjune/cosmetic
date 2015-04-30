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
import com.vyon.file.FileCopy;
import com.vyon.jdbc.JdbcUtil;

public class CartInsertService implements Service {

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
			String gcode = request.getParameter("gcode");
			System.out.println("gcode: " + gcode);
			int gcount = Integer.parseInt(request.getParameter("gcount"));
			System.out.println("gcount: " + gcount);
			Connection con = null;
			try {
				con = JdbcUtil.getConnection();
				CartDao dao = CartDaoProvider.getInstance().getCartDao();
				// 이미 카드리스트에 똑같은 상품이 등록 되었는지 확인한다.
				if (dao.checkCartGcode(con, id, gcode)) {
					// 있을 경우에는 값을 가져와 gcount와 더한다.
					int count = dao.getCartGcount(con, id, gcode) + gcount;
					// 둘 값을 합친 값이 99이상이면 99로 변환
					if (count > 99) {
						count = 99;
					}
					// 그리고 더한 값을 업데이트한다.
					dao.cartUpdate(con, id, gcode, count);
				} else {
					String gimg = dao.cartInsert(con, id, gcode, gcount);
					
					FileCopy.imgCopy(gimg, request);
				}
				
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
		} else {
			url = "../error/error.do";
		}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : /cart/list.jsp
		System.out.println(url);
		forwardInfo.setForwardStr(url);
		return forwardInfo;
	}
}
