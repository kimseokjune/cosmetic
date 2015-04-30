package com.vyon.common;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.vyon.admin.dao.AdminDaoProvider;
import com.vyon.board.dao.BoardDaoProvider;
import com.vyon.cart.dao.CartDaoProvider;
import com.vyon.goods.dao.GoodsDaoProvider;
import com.vyon.member.dao.MemberDaoProvider;
import com.vyon.order.dao.OrderDaoProvider;
import com.vyon.search.dao.SearchDaoProvider;

//import com.webjjang.board.dao.BoardDaoProvider;
//import com.webjjang.qna.dao.QnaDaoProvider;

/**
 * Servlet implementation class DaoProviderInit1
 */
// @WebServlet("/DaoProviderInit")
public class DaoProviderInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig) 서버가 실행되면서 자동으로 실행시켜주는 메소드 : init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("com.vyon.common.DaoProviderInit.init() 시작");
		
		String dbms = getInitParameter("dbms");
		System.out.println("DBMS의 종류:" + dbms);
		// 값이 셋팅되어 있지 않으면 oracle을 기본 값으로
		if (dbms == null)
			dbms = "oracle";
		// 각각의 개발단위의 DaoProvider에 값을 넣어 준다.
		 BoardDaoProvider.getInstance().setDbms(dbms);
		 MemberDaoProvider.getInstance().setDbms(dbms);
		 CartDaoProvider.getInstance().setDbms(dbms);	 
		 GoodsDaoProvider.getInstance().setDbms(dbms);
		 OrderDaoProvider.getInstance().setDbms(dbms);
		 SearchDaoProvider.getInstance().setDbms(dbms);
		 AdminDaoProvider.getInstance().setDbms(dbms);
	}

}
