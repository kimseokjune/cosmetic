package com.vyon.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
// @WebServlet(name = "FrontController", urlPatterns = { "/FrontController" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// properties 에 있는 정보를 읽어서 저장해 놓는 객체
	private Map<String, Service> serviceMap	= new HashMap<String, Service>();

	/* 서버가 실행이 되면서 바로 자동으로 실행되는 메소드 
	 * web.xml 초기값으로 되어 있는 properties 파일의 
	 * 값을 이용하여 객체를 생성해서 Map에 넣어 둔다.
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// web.xml에서 선언한 초기값을 가져오는 처리
		// 가져오는 properties 파일의 이름 configFile에 매칭이
		// 되는 /WEB-INF/commandURI.properties 을 가져온다.
		System.out.println("com.vyon.common.FrontController.init() 시작");
		String configFile = getInitParameter("configFile");
		System.out.println("properties위치 : "+configFile);
		// /WEB-INF/commandURI.properties 파일에서
		// properties를 가져와 저장하기 위한 객체 선언.
		Properties prop = new Properties();
		// configFile을 가지고 파일 객체에 연결하기 위한 선언
		FileInputStream fis = null;
		try {
			// configFile 파일의 위치한 절대 위치를 가져온다.
			String configFilePath 
			= getServletContext().getRealPath(configFile);
			// 가져온 파일위 절대위치로 입력하는 객체 생성
			fis = new FileInputStream(configFilePath);
			// WEB-INF/commandURI.properties 파일로 부터
			// K=V 형식으로 되어 있는
			// 프로퍼티 셋팅해 주는 메소드(load())
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			// file 닫기
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // file 닫기 끝
		} // end of try-catch-finally

		// prop에 데이터를 Map에 넣어야 한다.
		// 문제점:prop은 순차적으로 데이터를 가져 올수 없다.
		// prop을 Iterator로 전환 후 Map에 넣는다.\
		// Map의 데이터 - URI(k) : String,
		// 객체(v:Instance) : Controller
		Iterator<Object> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			// key = URI
			String command = (String) keyIter.next();
			// value = Class(controller)
			String serviceClassName = prop.getProperty(command);
			// 가져온 value(serviceClassName)를 객체로 생성한다.
			try {
				// 가져온 value=Class name 이 존재 여부\
				Class<?> serviceClass = Class.forName(serviceClassName);
				// Class로 인스턴스화 시킨다. newInstance()
				Service serviceInstance 
				= (Service) serviceClass.newInstance();
//				System.out.println("생성된 instance 주소:"
//				+serviceInstance);
				// Map에 데이터를 넣는다.
				// URI(k) : String - command,
				// 객체(v) : Service - serviceInstance
				serviceMap.put(command, serviceInstance);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException e) {
				throw new ServletException(e);
			}// end of try-catch-finally
		}// end of while
//		System.out.println(serviceMap.size());
	} //end of init()

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get이나 post 둘다 잡아서 자동으로 처리되는 메소드
		System.out.println("FrontController.service() 시작");
		
		// 한글처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// command = URI를 찾아 낸다.
		String command = request.getServletPath();
//		/board/list.do
		System.out.println("접속주소 :"+ command);
		// 실행되는 서비스를 찾기 : commandURI.properties 참조
//		com.webjjang.board.controller.BoardController
		Service service = serviceMap.get(command);
//		Service 객체를 실행하고 진행방식과 진행되는 곳을 돌려 받는다.
		ForwardInfo forwardInfo	= service.process(request, response);
		
		System.out.println("FrontController.service():처리끝");
		// 진행 방식 처리
		// 진행되는 방식 : forwardInfo.isForward()
		System.out.println("진행방식:forward?"+forwardInfo.isForward());
		// 진행되는 곳 : forwardInfo.getForwardStr()
		System.out.println("진행장소:"	+forwardInfo.getForwardStr());

		//forward하는것을 request에 담아서 보낸다.
		request.setAttribute("main",forwardInfo.getForwardStr());
		
		if(forwardInfo.isForward()){
			// forward 방식의 진행
//			jsp에서는 <jsp:forward page="" 와 같다.
			
//          forwardStr을 request에 담아서 보내기떄문에 전체 레이아웃인 main.jsp만 forward시킨다.
			RequestDispatcher di= request.getRequestDispatcher("../main/main.jsp");
			di.forward(request, response);
		} else {
			// redirect 방식의 진행
			response.sendRedirect
			(forwardInfo.getForwardStr());
		}
	}

}
