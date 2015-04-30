package com.vyon.order.model;

public class OrderDetailBean {
	
	private int no; // 주문상세번호
	private String gcode; // 상품코드
	private String goodsName; // 상품 이름
	private int price; //구매 가격
	private int gcount; // 상품 개수
	private String imgName; //이미지 이름
	private String orderState; // 현재 배송 상태
	private int sno; // 배송 상태 
	private int sumPrice; // 총 가격	
	private String deliveryday; // 배송날짜
	private String receiptday; // 배송완료날짜
	private String paymentday; // 결제 날짜
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getGcount() {
		return gcount;
	}
	public void setGcount(int gcount) {
		this.gcount = gcount;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getReceiptday() {
		return receiptday;
	}
	public void setReceiptday(String receiptday) {
		this.receiptday = receiptday;
	}
	public String getDeliveryday() {
		return deliveryday;
	}
	public void setDeliveryday(String deliveryday) {
		this.deliveryday = deliveryday;
	}
	public String getPaymentday() {
		return paymentday;
	}
	public void setPaymentday(String paymentday) {
		this.paymentday = paymentday;
	}
}
