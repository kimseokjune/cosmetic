package com.vyon.board.model;

public class BoardSetting {
	private String board_id,board_name;
	private int writer_lv,comment_lv,reply_lv,pagepercount,isfileupload;
	
	public String getBoard_id() {
		return board_id;
	}
	public String getBoard_name() {
		return board_name;
	}
	public int getWriter_lv() {
		return writer_lv;
	}
	public int getComment_lv() {
		return comment_lv;
	}
	public int getReply_lv() {
		return reply_lv;
	}
	public int getPagepercount() {
		return pagepercount;
	}
	public int getIsfileupload() {
		return isfileupload;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public void setWriter_lv(int writer_lv) {
		this.writer_lv = writer_lv;
	}
	public void setComment_lv(int comment_lv) {
		this.comment_lv = comment_lv;
	}
	public void setReply_lv(int reply_lv) {
		this.reply_lv = reply_lv;
	}
	public void setPagepercount(int pagepercount) {
		this.pagepercount = pagepercount;
	}
	public void setIsfileupload(int isfileupload) {
		this.isfileupload = isfileupload;
	}

}
