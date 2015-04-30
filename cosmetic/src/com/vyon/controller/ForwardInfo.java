package com.vyon.controller;

public class ForwardInfo {
	// forward가 true : forward, false : redirect한다.
	// if(obj.isForward()){
	//	forward 처리
	// } else {
	//  redirect 처리
	// }
	private boolean forward;
	private String forwardStr;
	
	public boolean isForward() {
		return forward;
	}
	public void setForward(boolean forward) {
		this.forward = forward;
	}
	public String getForwardStr() {
		return forwardStr;
	}
	public void setForwardStr(String forwardStr) {
		this.forwardStr = forwardStr;
	}
}
