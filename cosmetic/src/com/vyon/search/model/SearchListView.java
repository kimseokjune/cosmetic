package com.vyon.search.model;

import java.util.List;


public class SearchListView {

	public SearchListView(){}
	
	public SearchListView(int totalRow, int totalPage, int startRow, int endRow, 
			int startPage, int endPage, int page, int rowPerPage, int pagePerGroup, List<Search> list){
		//생성하면서 값을 셋팅 : 미리 값을 구해 놓는다.
		this.totalRow = totalRow;
		this.totalPage = totalPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.startPage = startPage;
		this.endPage = endPage;
		this.page = page;
		this.rowPerPage = rowPerPage;
		this.pagePerGroup = pagePerGroup;
		this.list = list;
	}
		
	private int totalRow, totalPage, startRow, endRow, startPage, endPage, 
		page, rowPerPage, pagePerGroup;
	
	private List<Search> list;
	
	public int getTotalRow() {
		return totalRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getPage() {
		return page;
	}
	public List<Search> getList() {
		return list;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}

	public int getPagePerGroup() {
		return pagePerGroup;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public void setPagePerGroup(int pagePerGroup) {
		this.pagePerGroup = pagePerGroup;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setList(List<Search> list) {
		this.list = list;
	}	
}
