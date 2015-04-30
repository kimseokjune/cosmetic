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
import com.vyon.goods.model.Goods;
import com.vyon.jdbc.JdbcUtil;

public class GoodsUserViewService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GoodsUserViewService.process()");
				
		// DB 처리
		GoodsDao dao 
		= GoodsDaoProvider.getInstance().getGoodsDao();
		Connection con = null;
		
		String gcode = request.getParameter("gcode");
		//System.out.println(gcode);
		if(gcode==null || gcode.equals("")) return null;
//		String gcode = getString("gcode");
		try {
			con=JdbcUtil.getConnection();
			
			// 상품의 goods와 price table의 정보를 저장
			Goods goods = dao.user_view(con, gcode);
			
			//상품의 goods_img 정보를 가져오는 메소드
			String isM = dao.fileListSort(con, gcode, "m");
			String isD = dao.fileListSort(con, gcode, "d");	
			String isB = dao.fileListSort(con, gcode, "b");	
		
			//상품의 goods와 price, goods_img 정보를저정한 goods의 문자열 저장
			request.setAttribute("goods", goods);
			request.setAttribute("isM", isM);
			request.setAttribute("isD", isD);
			request.setAttribute("isB", isB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("/goods/user_view.jsp");
		return forwardInfo;
	}

}
