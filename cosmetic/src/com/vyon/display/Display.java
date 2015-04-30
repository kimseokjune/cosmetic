package com.vyon.display;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Display {

	public static List<Integer> tokenizer(String str) {
		// TODO Auto-generated method stub

		
		// 가져온 문자 ',' 단위로 나누기
		StringTokenizer st = new StringTokenizer(str, ",");
		// 나눈 token들을 다시 Integer객체 List로 만들기
		List<Integer> list = new ArrayList<Integer>();

		while (st.hasMoreTokens()) {
			Integer integer = Integer.parseInt(st.nextToken());
			list.add(integer);
		}
		
		return list; 
	}
}
