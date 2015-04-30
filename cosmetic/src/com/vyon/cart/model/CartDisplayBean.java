package com.vyon.cart.model;

import java.util.List;

public class CartDisplayBean {
	private List<CartBean> list; // 장바구니 리스트
	private int shipping; // 배송비
	private int totalPrice; // 총합계 금액

	public CartDisplayBean(List<CartBean> list) {
		// 정보 삽입
		setList(list);
		// 총 금액 구하기
		int totalPrice = 0;

		if (list != null) {
			for (CartBean bean : list) {
				int sumPrice = bean.getSumPrice();
				totalPrice += sumPrice;
			}
		}
		
		// 배송료 결정
		int shipping = 0;
		if (totalPrice <= 50000) {
			shipping = 2500;
		} else {
			shipping = 0;
		}

		setShipping(shipping);

		// 마지막으로 배송료 더하기
		totalPrice += shipping;

		setTotalPrice(totalPrice);
	}

	public List<CartBean> getList() {
		return list;
	}

	public void setList(List<CartBean> list) {
		this.list = list;
	}

	public int getShipping() {
		return shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
