package com.vyon.order.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.vyon.file.FileCopy;
import com.vyon.jdbc.JdbcUtil;
import com.vyon.order.dao.OrderDao;
import com.vyon.order.dao.OrderDaoProvider;
import com.vyon.order.model.OrderDetailBean;
import com.vyon.order.model.OrderListBean;
import com.vyon.order.model.PaymentDisplayInfo;

public class OrderPaymentProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderPaymentProcessService.process()");

		// 필요한 변수들 받기
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");
		
		String url = "";

		// 로그인시만 실행
		if (id != null) {
			
			String uploadPath=request.getServletContext().getRealPath("goods/gimage");
			System.out.println("uploadPath : " + uploadPath);

			// 이전화면정보 가져오기
			PaymentDisplayInfo pdi = (PaymentDisplayInfo) session.getAttribute("pdi");
			System.out.println("pdi : " + pdi);

			// 이전화면에서 결제 할 수 있는 카드 리스트 가져오기
			CartDisplayBean cdb = pdi.getSelectedCartDisplayBean();

			// 카트 리스트 Dao에 보내기 위해 받기
			List<CartBean> cartList = cdb.getList();
			System.out.println("cartList: " + cartList.size());

			// 카트 리스트에 담긴 결제 정보를 담을 orderList 생성
			List<OrderDetailBean> orderList = new ArrayList<OrderDetailBean>();

			// cartlist의 값 orderlist에 넣기
			for (int i = 0; i < cartList.size(); i++) {
				OrderDetailBean bean = new OrderDetailBean();

				bean.setGcode(cartList.get(i).getGcode());
				bean.setGcount(cartList.get(i).getGcount());
				bean.setGoodsName(cartList.get(i).getGoodsName());
				bean.setImgName(cartList.get(i).getImgName());
				bean.setPrice(cartList.get(i).getSumPrice());
				int orderState = 14; // 입금 확인중
				bean.setSno(orderState);
				System.out.println("orderstate: " + bean.getOrderState());

				orderList.add(bean);
			}

			// 상품이름 만들기
			String goodsName = pdi.getSelectedCartDisplayBean().getList()
					.get(0).getGoodsName();
			int cartListSize = pdi.getSelectedCartDisplayBean().getList()
					.size();
			if (cartListSize > 1) {
				goodsName = goodsName + "외 " + (cartListSize - 1)  + "종";
			}

			System.out.println(goodsName);

			Connection con = null;

			OrderDao orderDao = OrderDaoProvider.getInstance().getOrderDao();
			CartDao cartDao = CartDaoProvider.getInstance().getCartDao();

			// orderList에 정보 넣기
			OrderListBean bean = new OrderListBean();

			// 주문한 상품의 전체 이름 넣기
			bean.setGoodsName(goodsName);

			// 주문자 이름 넣기
			String orderName = request.getParameter("sn");
			bean.setOrderName(orderName);
			System.out.println("orderName: " + orderName);

			// 주문자 주소 넣기
			String orderAddress = request.getParameter("sad1") + " "
					+ request.getParameter("sad2");
			bean.setOrderAddress(orderAddress);
			System.out.println("orderAddress: " + orderAddress);

			// 주문자 우편번호 넣기
			String orderZipcode = request.getParameter("sadno1") + "-"
					+ request.getParameter("sadno2");
			bean.setOrderZipcode(orderZipcode);
			System.out.println("orderZipcode: " + orderZipcode);

			// 주문자 연락처 넣기
			String orderTel = request.getParameter("st1") + "-"
					+ request.getParameter("st2") + "-"
					+ request.getParameter("st3");
			bean.setOrderTel(orderTel);
			System.out.println("orderTel: " + orderTel);

			// 수령인 이름 넣기
			String addresseeName = request.getParameter("rn");
			bean.setAddresseeName(addresseeName);
			System.out.println("addresseeName: " + addresseeName);

			// 수령인 주소 넣기
			String addresseeAddress = request.getParameter("rad1") + " "
					+ request.getParameter("rad2");
			bean.setAddresseeAddress(addresseeAddress);
			System.out.println("addresseeAddress: " + addresseeAddress);

			// 수령인 우편번호 넣기
			String addresseeZipcode = request.getParameter("radno1") + "-"
					+ request.getParameter("radno2");
			bean.setAddresseeZipcode(addresseeZipcode);
			System.out.println("addresseeZipcode: " + addresseeZipcode);

			// 수령인 연락처 넣기
			String addresseeTel = request.getParameter("rt1") + "-"
					+ request.getParameter("rt2") + "-"
					+ request.getParameter("rt3");
			bean.setAddresseeTel(addresseeTel);
			System.out.println("addresseeTel: " + addresseeTel);

			// 결제 정보 넣기
			int paystate = Integer.parseInt(request.getParameter("payment"));
			bean.setPno(paystate);
			System.out.println("paystate: " + paystate);

			// 총 금액 넣기
			int totalPrice = pdi.getSelectedCartDisplayBean().getTotalPrice();
			bean.setTotalPrice(totalPrice);
			System.out.println("totalPrice: " + totalPrice);

			try {
				con = JdbcUtil.getConnection();				
				// 이미지 가져오기
				// 주문 리스트 만들기
				int orderNo = orderDao.insertOrderList(con, id, bean);

				// 주문량 만큼 goods 에서 빼기
				orderDao.minusGoodsCount(con, orderList);
				
				// 주문 상세 리스트 만들기
				orderDao.insertOrderDetail(con, id, orderNo, orderList);	
				
				// 카트 리스트 삭제
				cartDao.deleteSelecetedCartList(con, id, cartList);

				url = "/cosmetic/order/customerList.do";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			url = "../error/error.do";
		}

		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /order/OrderCustomerList.do
		forwardInfo.setForwardStr(url);
		return forwardInfo;
	}

}