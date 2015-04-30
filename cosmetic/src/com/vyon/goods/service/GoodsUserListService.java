package com.vyon.goods.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.goods.dao.GoodsDao;
import com.vyon.goods.dao.GoodsDaoProvider;
import com.vyon.goods.model.Goods;
import com.vyon.goods.model.GoodsListView;
import com.vyon.jdbc.JdbcUtil;

public class GoodsUserListService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GoodsUserListService.process()");

		// DB 처리
		GoodsDao dao 
		= GoodsDaoProvider.getInstance().getGoodsDao();
		Connection con = null;
		// 필요한 변수들을 선언
		// 현재 페이지
		// 전달 받는 현재페이지
		String currPageStr = request.getParameter("page");
		String cate = request.getParameter("cate");
		String sort = request.getParameter("sort");
		String method = request.getParameter("method");
		//오늘 날짜 구하기
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String today = mSimpleDateFormat.format ( currentTime );
		//System.out.println ( today );
		
		// 처리할 현재 페이지
		int page = 1;
		if(currPageStr!=null && currPageStr!=""){
			page = Integer.parseInt(currPageStr);
			if(page<1) page=1;
//			System.out.println("현재 페이지:"+page);
		}
		// 전체 페이지 : (전체 글수-1)*페이당글수 +1
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		int rowPerPage = 8;
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;
		//화면에 페이지 그룹
		int pagePerGroup = 8;
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage=0, endPage = 0;
		try {
			con=JdbcUtil.getConnection();
						
			//전체 상품 갯수 구하기
//			totalRow = dao.selectCount(con, cate);
			totalRow =dao.selectCountSysdate(con, cate); 
			//전체 페이지 구하기
			if(totalRow>0)
				totalPage = (totalRow-1)/rowPerPage +1;
			// 현재 페이지의 시작과 끝 줄수 구하기
			startRow=(page-1)*rowPerPage +1;
			endRow = startRow+rowPerPage -1;
//			System.out.println("startRow:"+startRow);
//			System.out.println("endRow:"+endRow);
			// 페이지 그룹에 시작페이지,끝페이지
			startPage 
			= (page-1)/pagePerGroup*pagePerGroup+1;
			endPage
			= startPage+pagePerGroup -1;
			// 만약에 endPage가 totalPage보다 큰경우
			// endPage를 totalPage로 해준다.
			if(endPage > totalPage) endPage=totalPage;
//			System.out.println("BoardListService.process():startPage-"
//			+startPage+", endPage-"+endPage+", totalPage-"+totalPage);
//			System.out.println
//			("BoardListService.process().totalRow:"+totalRow);

			// dao.user_list에서 처리된 데이터를 list로 저장할 변수선언
			List<Goods> list
			= dao.user_list(con, startRow, endRow, cate, sort, method);

			// dao.user_list에서 처리된 데이터와 페이지 처리 데이터를 data로 저장변수 선언
			GoodsListView data = new GoodsListView(
					totalRow, totalPage,
					startRow, endRow, startPage,
					endPage, page, rowPerPage,
					pagePerGroup, list);
//			System.out.println("data.getStartPage():"+data.getStartPage());
//			System.out.println("data.getEndPage():"+data.getEndPage());
			request.setAttribute("data", data);
			request.setAttribute("cate", cate);
			request.setAttribute("sort", sort);
			request.setAttribute("method", method);
			request.setAttribute("today", today);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}		
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("/goods/user_list.jsp");
		return forwardInfo;
	}

}
