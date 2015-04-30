package com.vyon.order.model;

import com.vyon.cart.model.CartDisplayBean;
import com.vyon.member.model.Member;

/**
 * @author 302-55
 * 
 */
public class PaymentDisplayInfo {
	private Member memberBean;
	private CartDisplayBean selectedCartDisplayBean;
	private CartDisplayBean insufficientListBean;

	public Member getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(Member memberBean) {
		this.memberBean = memberBean;
	}

	public CartDisplayBean getSelectedCartDisplayBean() {
		return selectedCartDisplayBean;
	}

	public void setSelectedCartDisplayBean(CartDisplayBean cartDisplayBean) {
		this.selectedCartDisplayBean = cartDisplayBean;
	}

	public CartDisplayBean getInsufficientListBean() {
		return insufficientListBean;
	}

	public void setInsufficientListBean(CartDisplayBean insufficientListBean) {
		this.insufficientListBean = insufficientListBean;
	}

}
