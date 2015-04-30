package com.vyon.board.model;

import java.util.List;


/*
 *  게시판의 데이터를 저장해서 전달해 주는 Model이다.
 */
public class Board {
private int rnum,no,grp,grporder,lv,target,parentsno;


public int getParentsno() {
	return parentsno;
}
public void setParentsno(int parentsno) {
	this.parentsno = parentsno;
}
private String title,id,content,wdate,serverfile,originalfile;
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
public int getGrp() {
	return grp;
}
public int getGrporder() {
	return grporder;
}
public int getLv() {
	return lv;
}
public String getTitle() {
	return title;
}
public String getId() {
	return id;
}
public String getContent() {
	return content;
}
public String getWdate() {
	return wdate;
}
public void setNo(int no) {
	this.no = no;
}
public void setGrp(int grp) {
	this.grp = grp;
}
public void setGrporder(int grporder) {
	this.grporder = grporder;
}
public void setLv(int lv) {
	this.lv = lv;
}
public void setTitle(String title) {
	this.title = title;
}
public void setId(String id) {
	this.id = id;
}
public void setContent(String content) {
	this.content = content;
}
public void setWdate(String wdate) {
	this.wdate = wdate;
}

public int getTarget() {
	return target;
}
public void setTarget(int target) {
	this.target = target;
}

public List<AttachedFile> getFileList() {
	return fileList;
}
public void setFileList(List<AttachedFile> fileList) {
	this.fileList = fileList;
}

public String getOriginalfile() {
	return originalfile;
}
public void setOriginalfile(String originalfile) {
	this.originalfile = originalfile;
}
public String getServerfile() {
	return serverfile;
}
public void setServerfile(String serverfile) {
	this.serverfile = serverfile;
}


	}


