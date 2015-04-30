package com.vyon.goods.model;

import java.util.List;


public class Goods {
	private int rnum,no,price,count,salePrice,disRate;
	private String cname,gcode,name,gimage1,gimage2,gimage3,gimage4,product,productDate,startDate,endDate;
	private List<AttachedFile> fileList;
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getDisRate() {
		return disRate;
	}
	public void setDisRate(int disRate) {
		this.disRate = disRate;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGimage1() {
		return gimage1;
	}
	public void setGimage1(String gimage1) {
		this.gimage1 = gimage1;
	}
	public String getGimage2() {
		return gimage2;
	}
	public void setGimage2(String gimage2) {
		this.gimage2 = gimage2;
	}
	public String getGimage3() {
		return gimage3;
	}
	public void setGimage3(String gimage3) {
		this.gimage3 = gimage3;
	}
	public String getGimage4() {
		return gimage4;
	}
	public void setGimage4(String gimage4) {
		this.gimage4 = gimage4;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<AttachedFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<AttachedFile> fileList) {
		this.fileList = fileList;
	}
	

}
