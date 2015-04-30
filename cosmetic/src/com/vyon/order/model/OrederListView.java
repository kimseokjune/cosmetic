package com.vyon.order.model;

public class OrederListView {
	private int totalRow, totalPage, startRow, endRow, startPage, endPage,
			page, rowPerPage, pagePerGroup;
	private String searchKey;
	private String searchWord;
	private String startDate;
	private String endDate;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public OrederListView() {
	}

	public OrederListView(int totalRow, int totalPage, int startRow,
			int endRow, int startPage, int endPage, int page, int rowPerPage,
			int pagePerGroup, String searchKey, String searchWord,
			String startDate, String endDate) {
		// 생성하면선 값을 셋팅 : 미리 값을 구해 놓는다.
		this.totalRow = totalRow;
		this.totalPage = totalPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.startPage = startPage;
		this.endPage = endPage;
		this.page = page;
		this.rowPerPage = rowPerPage;
		this.pagePerGroup = pagePerGroup;
		this.searchKey = searchKey;
		this.searchWord = searchWord;
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		
		System.out.println("========================");
		System.out.println("totalRow: " + totalRow);
		System.out.println("totalPage: " + totalPage);
		System.out.println("startRow: " + startRow);
		System.out.println("endRow: " + endRow);
		System.out.println("startPage: " + startPage);
		System.out.println("endPage: " + endPage);
		System.out.println("page: " + page);
		System.out.println("rowPerPage: " + rowPerPage);
		System.out.println("pagePerGroup: " + pagePerGroup);
		System.out.println("searchKey: " + searchKey);
		System.out.println("searchWord: " + searchWord);
		System.out.println("startDate: " + startDate);
		System.out.println("endDate: " + endDate);
		System.out.println("========================");
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public int getPagePerGroup() {
		return pagePerGroup;
	}

	public void setPagePerGroup(int pagePerGroup) {
		this.pagePerGroup = pagePerGroup;
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
}
