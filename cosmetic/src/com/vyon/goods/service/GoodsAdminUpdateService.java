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

public class GoodsAdminUpdateService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GoodsAdminUpdateService.process()");

		// 상품 gcode를 가져온다.
		String gcode = request.getParameter("gcode");
//		String noStr = request.getParameter("no");
//		String pageStr = request.getParameter("page");
//		System.out.println("BoardViewService.process().noStr:"+noStr);		

		// DB 처리
		GoodsDao dao 
		= GoodsDaoProvider.getInstance().getGoodsDao();
		Connection con = null;
//		if(noStr==null || noStr.equals("")) return null;
//		if(pageStr==null || pageStr.equals("")) return null;
//		int no = Integer.parseInt(noStr);
//		int page = Integer.parseInt(pageStr);
		
//		화면에 보여질 파일명
//		String viewFile = null;
		try {
			con=JdbcUtil.getConnection();
			con.setAutoCommit(false);
			//상품의 goods와 price 정보 가져오는 메소드
			Goods goods = dao.admin_view(con, gcode);

			//상품의 goods_img 정보를 가져오는 메소드				
			goods.setFileList(dao.fileList(con, gcode));
			
			request.setAttribute("goods", goods);
//				request.setAttribute("page", page);
//				viewFile="/goods/update.jsp";
			
			con.commit();
			con.setAutoCommit(true);
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
			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/update.jsp
		forwardInfo.setForwardStr("/goods/admin_update.jsp");
		return forwardInfo;
	}		
		
}
