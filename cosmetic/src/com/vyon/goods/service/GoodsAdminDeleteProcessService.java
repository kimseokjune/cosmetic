package com.vyon.goods.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.goods.dao.GoodsDao;
import com.vyon.goods.dao.GoodsDaoProvider;
import com.vyon.jdbc.JdbcUtil;

public class GoodsAdminDeleteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GoodsAdminDeleteProcessService.process()");

		// get 방식으로 gcode list 가져오기
		System.out.println("getParam : " + request.getParameterValues("check"));
		String[] list = request.getParameterValues("check");
		System.out.println("gcodeList :" +list);
		
		for(String no : list){
			System.out.println(no);
		}

		// DB 처리
		GoodsDao dao = GoodsDaoProvider.getInstance().getGoodsDao();
		Connection con = null;
		
		try {
			con=JdbcUtil.getConnection();
			// 상품삭제 메소드
			dao.admin_delete(con, list);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcUtil.close(con);
			}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /goods/list.jsp
		forwardInfo.setForwardStr("../goods/admin_list.do?cate=cle&sort=sale&method=asc");
		return forwardInfo;
	}
}
