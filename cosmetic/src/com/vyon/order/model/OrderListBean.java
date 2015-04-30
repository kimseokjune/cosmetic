package com.vyon.order.model;


public class OrderListBean {

	private int orderNo; // 주문번호
	
	private String id; // 주문자 아이디
	private String goodsName; // 상품 총 이름
	private String orderDay; // 주문날짜
	private String paymentDay; // 결제날짜
	private String orderName; // 주문자
	private String orderAddress; // 주문자 주소
	private String orderZipcode; // 주문자 우편번호
	private String orderTel; // 주문자 연락처
	private String addresseeName; // 수령인 이름
	private String addresseeAddress; // 수령인 주소
	private String addresseeZipcode; // 수령인 우편번호
	private String addresseeTel; // 수령인 연락처
	private String deliveryDay; // 배송일

	private int pno; // 결제 상태 번호
	private int sno; // 주문 상태 번호

	private String paystate; // 결제 정보
	private String orderstate; // 주문 상태
	private int totalPrice; // 총 결제 금액

	public String getOrderDay() {
		return orderDay;
	}

	public void setOrderDay(String orderDay) {
		this.orderDay = orderDay;
	}

	public String getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(String paymentDay) {
		this.paymentDay = paymentDay;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderZipcode() {
		return orderZipcode;
	}

	public void setOrderZipcode(String orderZipcode) {
		this.orderZipcode = orderZipcode;
	}

	public String getOrderTel() {
		return orderTel;
	}

	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}

	public String getAddresseeName() {
		return addresseeName;
	}

	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}

	public String getAddresseeAddress() {
		return addresseeAddress;
	}

	public void setAddresseeAddress(String addresseeAddress) {
		this.addresseeAddress = addresseeAddress;
	}

	public String getAddresseeTel() {
		return addresseeTel;
	}

	public void setAddresseeTel(String addresseeTel) {
		this.addresseeTel = addresseeTel;
	}

	public String getDeliveryDay() {
		return deliveryDay;
	}

	public void setDeliveryDay(String deliveryDay) {
		this.deliveryDay = deliveryDay;
	}

	public String getPaystate() {
		return paystate;
	}

	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}

	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAddresseeZipcode() {
		return addresseeZipcode;
	}

	public void setAddresseeZipcode(String addresseeZipcode) {
		this.addresseeZipcode = addresseeZipcode;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
