package com.vyon.order.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
import com.vyon.file.FileCopy;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.member.dao.MemberDao;
import com.vyon.member.dao.MemberDaoProvider;
import com.vyon.member.model.Member;
import com.vyon.order.model.PaymentDisplayInfo;

public class OrderPaymentService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderPaymentService.process()");

		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		String url = "";
		boolean forward = true;

		if (id != null) {

			String prevPage = request.getParameter("prevPage");
			Connection con = null;
			MemberDao memberDao = MemberDaoProvider.getInstance()
					.getMemberDao();
			CartDao cartDao = CartDaoProvider.getInstance().getCartDao();	
			
			try {
				con = JdbcUtil.getConnection();
				// 화면에 필요한 정보를 받을 변수 선언
				PaymentDisplayInfo pdi = new PaymentDisplayInfo();

				// 카드에서 가져온 정보를 담을 객체
				List<String> cartNoList = null;
				List<CartBean> selectCartlist = null;
				List<CartBean> insufficientList = null;

				// 장바구니화면과 같은 보여줄 정보 객체 선언
				CartDisplayBean selectCartlistBean = null;
				CartDisplayBean insufficientListBean = null;

				if (prevPage.equals("cart")) {
					// 카트 페이지에서 해당 서비스로 넘어왔을 경우
					String[] tempList = request.getParameterValues("check");

					if (tempList != null) {
						// String 배열 -> List 변환
						cartNoList = new ArrayList<String>(
								Arrays.asList(tempList));
					} else {
						cartNoList = (List<String>) session.getAttribute("cartNoList");
						System.out.println("size : " + cartNoList.size());
						System.out.println("첫번째 값: " + cartNoList.get(0));
					}

					session.setAttribute("cartNoList", cartNoList);

					// 수량이 없는 CartList의 내용을 가져온다.
					insufficientList = cartDao.checkInsufficient(con, id);

					// 수량이 있는 CartList의 내용을 가져온다.
					selectCartlist = cartDao.getSeleteCartList(con, id,
							cartNoList);
				} else if (prevPage.equals("goodsView")) {
					// 상품 페이지에서 넘어왔을 경우

					String gcode = request.getParameter("gcode");
					int gcount = Integer.parseInt(request
							.getParameter("gcount"));
					selectCartlist = new ArrayList<CartBean>();
					// 생성만 함.
					insufficientList = new ArrayList<CartBean>();

					// 해당 상품의 수량이 있는지 확인
					if (cartDao.checkGoodsCount(con, gcode, gcount)) {
						// 상품 정보 가져오기.
						CartBean bean = cartDao.getCartBean(con, gcode);
						bean.setGcode(gcode);
						bean.setGcount(gcount);
						selectCartlist.add(bean);
						System.out.println(insufficientList.size());
					}
				}

				// 장바구니화면에 보여줄 정보 객체 생성
				selectCartlistBean = new CartDisplayBean(selectCartlist);
				insufficientListBean = new CartDisplayBean(insufficientList);
				
				// 이미지 파일 복사
				List<String> imgNameList = cartDao.getImgNameList(con, selectCartlist);
				
				System.out.println("size : " + imgNameList.size());
				
				if(imgNameList.size() != 0){
					FileCopy.imgCopy(imgNameList, request);
				}

				// System.out.println(cartDisplayBean.getTotalPrice());

				pdi.setSelectedCartDisplayBean(selectCartlistBean);
				pdi.setInsufficientListBean(insufficientListBean);

				Member memberBean = memberDao.getMemberInfo(con, id);
				pdi.setMemberBean(memberBean);

				// 전화 번호 나누기
				String tel[] = new String[3];
				StringTokenizer st1 = new StringTokenizer(memberBean.getTel(),
						"-");

				for (int i = 0; st1.hasMoreTokens(); i++) {
					tel[i] = st1.nextToken();
				}

				// 우편번호 나누기
				String str = String.valueOf(memberBean.getZipcode());
				String zip1 = str.substring(0, 3);
				String zip2 = str.substring(3, 6);

				session.setAttribute("pdi", pdi);
				session.setAttribute("tel", tel);
				session.setAttribute("zip1", zip1);
				session.setAttribute("zip2", zip2);

				url = "/order/payment.jsp";
				forward = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
