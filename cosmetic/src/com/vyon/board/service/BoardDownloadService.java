package com.vyon.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vyon.board.dao.BoardDao;
import com.vyon.board.dao.BoardDaoProvider;

import com.vyon.board.model.ServerFileName;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.jdbc.JdbcUtil;




public class BoardDownloadService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
	    
	    String board_id = request.getParameter("id");
	    String no = request.getParameter("no");
	    
	    // 파일 업로드된 경로
	    String savePath = request.getSession().getServletContext().getRealPath("/board/" + board_id + "_upload");
	
	    BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
	    
		try {
			con=JdbcUtil.getConnection();
			//전체 글의 갯수 구하기
		    // 게시판 이름, 글번호로 originalfile,serverfilename 가져오기
		    ServerFileName sfn = dao.getFileNameByNo(con,board_id,no);

		    // 서버에 실제 저장된 파일명		    
		    String filename = sfn.getServerfilename() ;
		     
		    // 실제 내보낼 파일명
		    String orgfilename = sfn.getOriginalfilename() ;

		    InputStream in = null;
		    OutputStream os = null;
		    File file = null;
		    boolean skip = false;
		    String client = "";
	 
		    try{
		    	 // 파일을 읽어 스트림에 담기
		        try{
		            file = new File(savePath, filename);
		            in = new FileInputStream(file);
		        }catch(FileNotFoundException fe){
		            skip = true;
		        }
		 
		 
		 
		         
		        client = request.getHeader("User-Agent");
		 
		        // 파일 다운로드 헤더 지정
		        response.reset() ;
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-Description", "JSP Generated Data");
		 
		 
		        if(!skip){
		 
		             
		            // IE
		            if(client.indexOf("MSIE") != -1){
		                response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));
		 
		            }else{
		                // 한글 파일명 처리
		                orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");
		 
		                response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
		                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
		            }  
		             
		            response.setHeader ("Content-Length", ""+file.length() );
		 
		 
		       
		            os = response.getOutputStream();
		            byte b[] = new byte[(int)file.length()];
		            int leng = 0;
		             
		            while( (leng = in.read(b)) > 0 ){
		                os.write(b,0,leng);
		            }
		 
		        }else{
		            response.setContentType("text/html;charset=UTF-8");
		            //out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
		 
		        }
		         
		        in.close();
		        os.close();
		        
		    }catch(Exception e){
		      e.printStackTrace();
		    }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
	
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../board/list.jsp");
		return forwardInfo;
	}

}
