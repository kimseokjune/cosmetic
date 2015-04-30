/*
 * Dao를 선택하는 프로그램 작성
 * 싱글톤 패턴을 적용한다.
 */
package com.vyon.board.dao;

public class BoardDaoProvider {
	//2. 싱클톤을 적용할 클래스를 생성해서 인스턴스로 저장한다.
	private static BoardDaoProvider instance
	= new BoardDaoProvider();
	//3. 생성된 인스턴를 받아가기 위한 getter를 만든다.
	public static BoardDaoProvider getInstance(){
//		System.out.println
//		("MessageDaoProvider.getInstance()");
		return instance;
	}
	//1. 생성자를 private 으로 선언해 다른 클래스의 생성금지
	private BoardDaoProvider(){}
	
	// 사용하고자 하는 모든 dbms의 dao 프로그램을 생성해서 저장해 놓는다.
	private MySQLBoardDao mysqlDao 
	= new MySQLBoardDao();
	private OracleBoardDao oracleDao 
	= new OracleBoardDao();
	private MSSQLBoardDao mssqlDao 
	= new MSSQLBoardDao();
	
	// 어떤 dbms를 사용하지를 저장해 놓는 객체 변수 선언
	private String dbms;//oracle 저장된다. :web.xml참조
	// setter 작성 : DaoProviderInit Servlet의 init() 자동 호출
	public void setDbms(String dbms){
		this.dbms = dbms;
	}
	
	// 객체 변수(인스턴스) dbms에 있는 값으로 사용할 Dao를 넘기기
	public BoardDao getBoardDao(){
//		System.out.println
//		("MessageDaoProvider.getMessageDao()");
		if(dbms.equals("oracle")){
			System.out.println("return OracleDao");
			return oracleDao;
		} else if(dbms.equals("mysql")){
			return mysqlDao;
		} else if(dbms.equals("mssql")){
			return mssqlDao;
		}
		return null;
		
	}
}
