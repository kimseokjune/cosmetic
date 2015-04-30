package com.vyon.goods.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vyon.controller.ForwardInfo;
import com.vyon.controller.Service;
import com.vyon.goods.dao.GoodsDao;
import com.vyon.goods.dao.GoodsDaoProvider;
import com.vyon.goods.model.AttachedFile;
import com.vyon.goods.model.Goods;
import com.vyon.jdbc.JdbcUtil;

public class GoodsAdminWriteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GoodsAdminWriteProcessService.process()");

		String uploadPath
		=request.getServletContext().getRealPath("goods/gimage");
		System.out.println(uploadPath);
		//첨부파일 용량 제한 : 100M
		int size = 100*1024*1024;
		// 파일 업로드와 함께하는 데이터 받기
		// ** Multipart 사용
		MultipartRequest multi
	      = new MultipartRequest(request, uploadPath,
	      size, "utf-8",new DefaultFileRenamePolicy());
		
		// 입력된 데이터를 꺼내오기
		String cname = multi.getParameter("cname");
//		System.out.println("카네고리명:"+cname);
		String name = multi.getParameter("name");
		String gcode = multi.getParameter("gcode");
		int price = Integer.parseInt(multi.getParameter("price"));
		int count = Integer.parseInt(multi.getParameter("count"));
		int disRate = Integer.parseInt(multi.getParameter("disRate"));
		String product = multi.getParameter("product");
		String productDate = multi.getParameter("productDate");
		String startDate = multi.getParameter("startDate");
		String endDate = multi.getParameter("endDate");

		
		// 첨부파일 처리 : 다중 파일 처리
		@SuppressWarnings("unchecked")
		Enumeration<String> fileNames= multi.getFileNames();
			
		List<AttachedFile> fileList = null;
		boolean attachFile = false;
		// input file 입력란이 없는 경우
		while(fileNames.hasMoreElements()){
			String fileName = fileNames.nextElement();
//			System.out.println("BoardWriteProcessService.service().fileName:"+fileName);
			String originalFile
			= multi.getOriginalFileName(fileName);
//			System.out.println("내컴파일:"+originalFile);
			if(originalFile!=null){
				if(!attachFile){
					fileList = new ArrayList<AttachedFile>();
					attachFile = true;
				}
				String serverFile 
				= multi.getFilesystemName(fileName);
//				System.out.println("서버파일:"+serverFile);
				AttachedFile attachedFile = new AttachedFile();
				attachedFile.setOriginalFile(originalFile);
				attachedFile.setServerFile(serverFile);
				
				
				String of = originalFile;
				String ofss = of.substring(8, 9);
				
				switch (ofss) {
				case "1":
					attachedFile.setImgSize("s");
					break;
				case "2":
					attachedFile.setImgSize("m");
					break;
				case "3":
					attachedFile.setImgSize("b");
					break;
				case "4":
					attachedFile.setImgSize("d");
					break;
				}
				
				fileList.add(attachedFile);
			}
		}
		System.out.println("FileList의 크기"+fileList.size());
		// 처리 후 입력된 파일의 내용 확인용 프로그램
		if(fileList !=null && fileList.size()>0){
			for(AttachedFile af:fileList){
				System.out.println("원본파일:"+af.getOriginalFile());
				System.out.println("서버파일:"+af.getServerFile());
			}
		}
		// 파일을 제외한 데이터 셋팅
		Goods goods = new Goods();
		goods.setCname(cname);
		goods.setName(name);
		goods.setGcode(gcode);
		goods.setPrice(price);
		goods.setCount(count);
		goods.setDisRate(disRate);
		goods.setProduct(product);
		goods.setProductDate(productDate);
		goods.setStartDate(startDate);
		goods.setEndDate(endDate);
		// 첨부된 파일 데이터 셋팅
		goods.setFileList(fileList);
		
		// DB 처리
		GoodsDao dao 
		= GoodsDaoProvider.getInstance().getGoodsDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			// 상품등록에 등록글을 쓴다.
			dao.admin_write(con, goods);
			
			// 파일이 있는 경우 처리
			if(fileList!=null && fileList.size()>0){
				// 방금 작성된 상품번호를 가져온다.
				int no = dao.currentNo(con);
				// 작성된 상품번호로 파일 정보를 입력한다.
				dao.admin_writeFile(con, no, gcode, goods.getFileList());
			}
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtil.close(con);
		}		
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("admin_list.do?cate=cle&sort=sale&method=asc");
		return forwardInfo;
	}

}
