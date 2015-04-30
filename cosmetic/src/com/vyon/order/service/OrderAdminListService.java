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
import com.vyon.order.model.OrderListBean;
import com.vyon.order.model.OrederListView;

public class OrderAdminListService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderAdminListService.process()");
		
		HttpSession session = request.getSession();

		String grade = String.valueOf(session.getAttribute("grade"));

		String url = "";

		boolean forward = true;

		String startDate = request.getParameter("startDate");
		System.out.println("startDate :" + startDate);

		String endDate = request.getParameter("endDate");
		System.out.println("endDate :" + endDate);

		// 검색 처리
		String searchKey = request.getParameter("searchKey");

		System.out.println("searchKey: " + searchKey);

		String searchWord = request.getParameter("searchWord");
		
		System.out.println("searchWord: " + searchWord);

		if (searchWord != null)
			searchWord = searchWord.trim();

		// 전달 받는 현재페이지
		String currPageStr = request.getParameter("page");

		// 처리할 현재 페이지
		int page = 1;

		// 한페이지에 보여줄 글의 갯수
		int rowPerPage = 10;

		try {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		} catch (NumberFormatException e) {
			// System.out.println("오류");
		}

		// 페이지 그룹
		int pagePerGroup = 5;

		// 현재페이지가 0 이면 현제페이지를 1로 세팅.
		if (currPageStr != null && currPageStr != "")
			page = Integer.parseInt(currPageStr);

		if (page < 1)
			page = 1;

		System.out.println("page: " + page);

		// 전체 페이지 : (전체 글수-1)*페이당글수 +1
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;

		// 화면에 페이지 그룹
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage = 0, endPage = 0;

		Connection con = null;

		if (grade.equals("9")) {
			try {
				con = JdbcUtil.getConnection();
				OrderDao orderDao = OrderDaoProvider.getInstance()
						.getOrderDao();
								
				if (startDate == null || startDate.equals("")) {
					// 시작 날짜가 null 일 때에는 리스트에 가장 옛날에 주문된 주문의 날짜를 가져온다.
					startDate = orderDao.getLastDay(con);
					System.out.println("startDate: " + startDate);
				}

				if (endDate == null || endDate.equals("")) {
					// 마지막 날짜가 null 일 때에는 리스트에 가장 최신 주문 날짜를 가져온다.
					endDate = orderDao.getRecentDay(con);
					System.out.println("endDate: " + endDate);
				}

				totalRow = orderDao.selectCount(con, searchKey, searchWord,
						startDate, endDate);
				
				System.out.println("totalRow : " + totalRow);

				// 전체 페이지 구하기
				if (totalRow > 0)
					totalPage = (totalRow - 1) / rowPerPage + 1;
				// 현재 페이지의 시작과 끝 줄수 구하기
				startRow = (page - 1) * rowPerPage + 1;
				endRow = startRow + rowPerPage - 1;
				// 페이지 그룹에 시작페이지,끝페이지
				startPage = (page - 1) / pagePerGroup * pagePerGroup + 1;
				endPage = startPage + pagePerGroup - 1;
				// 만약에 endPage가 totalPage보다 큰경우
				// endPage를 totalPage로 해준다.
				if (endPage > totalPage)
					endPage = totalPage;

				// 전체 목록 구하기

				List<OrderListBean> list = orderDao.getList(con, startRow,
						endRow, searchKey, searchWord, startDate, endDate);

				System.out.println("들어가기 전 page : " + page);			
				System.out.println("들어가기 전 searchKey : " + searchKey);
				
				OrederListView pagaView = new OrederListView(totalRow,
						totalPage, startRow, endRow, startPage, endPage, page,
						rowPerPage, pagePerGroup, searchKey, searchWord,
						startDate, endDate);				
				
				request.setAttribute("orderList", list);
				request.setAttribute("pageView", pagaView);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			url = "../admin/order_list.jsp";
			forward = true;
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
