package com.vyon.goods.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.goods.model.AttachedFile;
import com.vyon.goods.model.Goods;
import com.vyon.jdbc.JdbcUtil;

public abstract class GoodsDao {
	// 공통이 아닌 메소드를 추상 메소드로 선언
	public abstract int admin_write(Connection con, Goods goods)
			throws SQLException;

	public abstract int admin_writeFile(Connection con, int no, String gcode,
			List<AttachedFile> fileList) throws SQLException;

	public abstract List<Goods> user_list(Connection con, int startRow,
			int endRow, String cate, String sort, String method)
			throws SQLException;

	public abstract List<Goods> admin_list(Connection con, int startRow,
			int endRow, String cate, String sort, String method)
			throws SQLException;

	// *** 공통으로 사용되는(동일한 코드) 메소드를 구현한다.

	// 상품 상세보기 - 선택한 gcode의 하나의 글 보기
	public Goods admin_view(Connection con, String gcode) throws SQLException {
		System.out.println("GoodsDao.admin_view()");
		// 처리
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = null;
		try {
			// 상품 정보만 저정하는 sql 작성
			String sql = " select g.gcode,g.name,g.product,g.productDate,g.count,p.price,p.disrate, "
					+ " to_char(p.startdate, 'yyyy-mm-dd') startdate,to_char(p.enddate, 'yyyy-mm-dd') enddate, "
					+ " round(p.price*(100-p.disrate)/100,-1) sale "
					+ " from goods g, price p "
					+ " where g.gcode=p.gcode "
					+ " and g.gcode= ? "
					+ " and sysdate>=p.startDate "
					+ " and to_char(sysdate,'yyyy-mm-dd')<=to_char(p.enddate,'yyyy-mm-dd') ";

			System.out.println(sql);
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcode);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next()) {
					goods = new Goods();
					goods.setGcode(rs.getString("gcode"));
					goods.setName(rs.getString("name"));
					goods.setProduct(rs.getString("product"));
					goods.setProductDate(rs.getString("productDate"));
					goods.setCount(rs.getInt("count"));
					goods.setPrice(rs.getInt("price"));
					goods.setDisRate(rs.getInt("disrate"));
					goods.setStartDate(rs.getString("startDate"));
					goods.setEndDate(rs.getString("endDate"));
					goods.setSalePrice(rs.getInt("sale"));
					return goods;
				}
			// return makeBoardFromViewResultSet(rs);

		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return null;
	}

	// 상품 상세보기 - 선택한 gcode의 하나의 글 보기
	public Goods user_view(Connection con, String gcode) throws SQLException {
		System.out.println("GoodsDao.user_view()");
		// 처리
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = null;
		try {
			// sql 작성
			String sql = null;
			sql = " select g.gcode, g.name, p.price, p.disrate, g.count, "
					+ " round(p.price*(100-p.disrate)/100,-1) sale "
					+ " from goods g, price p "
					+ " where  g.gcode=p.gcode "
					+ "	and g.gcode= ? "
					+ " and sysdate>=p.startDate "
					+ " and to_char(sysdate,'yyyy-mm-dd')<=to_char(p.enddate,'yyyy-mm-dd') ";

			System.out.println(sql);
			// 상태
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, "imag_size");
			pstmt.setString(1, gcode);

			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			
			
			if (rs != null)
			{
				if (rs.next()){		
					goods = new Goods();
					goods.setGcode(rs.getString("gcode"));
					goods.setName(rs.getString("name"));
					// goods.setGimage2(rs.getString("s_file"));
					// goods.setGimage4(rs.getString("s_file"));
					goods.setPrice(rs.getInt("price"));
					goods.setDisRate(rs.getInt("disrate"));
					goods.setSalePrice(rs.getInt("sale"));
					goods.setCount(rs.getInt("count"));
					System.out.println("count: " + rs.getInt("count"));					
				}
			}		
			
			return goods;
			
			// return makeBoardFromViewResultSet(rs);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}

	// 데이터의 갯수(전체 데이터)를 세는 메소드
	public int selectCount(Connection con, String cate) throws SQLException {
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		// 처리
		try {
			// sql 작성
			if(cate.equals("all")){
				 sql= " select count(*) from goods g ";
			}else{
				 sql = " select count(*) from goods g " + " where g.cname= '"	+ cate + "' ";	
			}
			// 상태
			pstmt = con.prepareStatement(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next())
					return rs.getInt(1);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return 0;
	}
	
	// 데이터의 갯수(전체 데이터)를 sysdate기준으로 세는 메소드
	public int selectCountSysdate(Connection con, String cate) throws SQLException {
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		// 처리
		try {
			// sql 작성
			if(cate.equals("all")){
				 sql= " select count(*) from goods g, price p "
				 +" where g.gcode = p.gcode "
				 +" and sysdate >= p.startDate "
				 +" and to_char(sysdate,'yyyy-mm-dd') <= to_char(p.enddate,'yyyy-mm-dd') ";
			}else{
				sql= " select count(*) from goods g, price p "
				 +" where g.gcode = p.gcode "
				 +" and sysdate>=p.startDate "
				 +" and to_char(sysdate,'yyyy-mm-dd') <= to_char(p.enddate,'yyyy-mm-dd') "
				 +" and g.cname= '" + cate + "' ";	
			}
			// 상태
			pstmt = con.prepareStatement(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next())
					return rs.getInt(1);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return 0;
	}	
	
	// 수정 처리 메소드
	@SuppressWarnings("resource")
	public void admin_update(Connection con, Goods goods, String gcode)
			throws SQLException {
		System.out.println("GoodsDao.admin_update()");

		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		try {
			// sql문 작성

			// goods 테이블 수정 쿼리
			String sql = " update goods "
					+ " set cname=?,name=?,product=?,count=?,productdate=? "
					+ " where gcode = '" + gcode + "' ";
			// 실행상태 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);

			String cname = goods.getCname();
			String name = goods.getName();
			String product = goods.getProduct();
			int count = goods.getCount();
			String productDate = goods.getProductDate();

			pstmt.setString(1, cname);
			pstmt.setString(2, name);
			pstmt.setString(3, product);
			pstmt.setInt(4, count);
			pstmt.setString(5, productDate);
			// pstmt.setString(6, gcode);
			// 실행
			pstmt.executeUpdate();

			// price 테이블 수정 쿼리
			String sql1 = " update price "
					+ " set price=?,disrate=?,startdate=?,enddate=? "
					+ " where gcode = ? ";
			// 실행상태 & 데이터 셋팅
			pstmt = con.prepareStatement(sql1);
			int price = goods.getPrice();
			int disRate = goods.getDisRate();
			String startDate = goods.getStartDate();
			String endDate = goods.getEndDate();

			pstmt.setInt(1, price);
			pstmt.setInt(2, disRate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, gcode);
			// 실행
			pstmt.executeUpdate();

			// 결과표시
			System.out.println("성공적으로 상품수정이 되었습니다.");
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		return;
	}

	// 상품삭제 처리 메소드
	public int admin_delete(Connection con, String gcode) throws SQLException {
		System.out.println("GoodsDao.admin_delete()");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			// sql 작성
			String sql = " delete from goods where gcode = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcode);			
			// 실행
			// - DB이상, 쿼리 이상 : SQLException 생성 throw
			// - 실행 결과가 1 인 경우 : 해당 글이 있어서 delete 성공
			// - 실행 결과가 0 인 경우 : 해당 글이 없다.
			result = pstmt.executeUpdate();			
			// 표시
			if (result == 0) {
				throw new SQLException("해당 상품이 없습니다.");
			} else {
				System.out.println("해당상품이 성공적으로 삭제되었습니다.");
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return 0;
	}

	// 선택상품 삭제 메소드
	public void admin_delete(Connection con, String[] list) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		//처리
		try {
			//상태
			for (String gcode : list) {
				String sql = " delete from goods where gcode = ? ";

				pstmt = con.prepareStatement(sql);
	
				pstmt.setString(1, gcode);
			//실행
				pstmt.executeUpdate();
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
		return;
	}	
	
	// 입력된 상품번호를 가져오는 메소드
	public int currentNo(Connection con) throws SQLException {
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try {
			// sql 작성
			String sql = "select max(no) from goods";
			// 상태
			pstmt = con.prepareStatement(sql);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				if (rs.next())
					return rs.getInt(1);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return 0;
	}

	// 상품에 사진(gimage1~gimage4) 을 가져오는 메소드
	public List<AttachedFile> fileList(Connection con, String gcode)
			throws SQLException {
		// TODO Auto-generated method stub
		// System.out.println("BoardDao.fileList()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttachedFile> list = null;
		boolean createList = false;
		// 처리
		try {
			// sql 작성
			String sql = "select s_file " + " from goods_img "
					+ " where gcode=? order by s_file asc ";
			// System.out.println("BoardDao.fileList().sql:"+sql);
			// System.out.println("BoardDao.fileList().no:"+no);
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcode);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				while (rs.next()) {
					if (!createList) {
						list = new ArrayList<AttachedFile>();
						createList = true;
					}
					AttachedFile file1 = new AttachedFile();
					file1.setServerFile(rs.getString("s_File"));
					list.add(file1);
				}
			// return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		// System.out.println("GoodsDao.fileList().list.size:"+list.size());
		return list;
	}

	// 상품에 사진(gimage2)중 하나 선택 가져오는 메소드
	public String fileListSort(Connection con, String gcode, String img_size)
			throws SQLException {
		// TODO Auto-generated method stub
		// System.out.println("BoardDao.fileList()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try {
			// sql 작성
			String sql = " select s_file " + " from goods_img "
					+ " where gcode=? " + " and img_size=? ";
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcode);
			pstmt.setString(2, img_size);
			// 실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if (rs != null)
				while (rs.next()) {
					return rs.getString("s_file");
				}

		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		// System.out.println("GoodsDao.fileList().list.size:"+list.size());
		return null;
	}

	// 서버에 저장되어있는 파일명 가져오는 메소드

	public List<AttachedFile> getFileNameByNo(Connection con, String gcode)
			throws SQLException {

		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttachedFile> list = null;
		// 처리
		try {
			// sql 작성
			String sql = " select o_file,s_file,img_size " 
			        + " from goods_img "
					+ " where gcode = ? ";
			// 상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcode);
			// System.out.println(sql);
			// 실행 select: executeQuery()

			rs = pstmt.executeQuery();
			if (rs != null) {
				list = new ArrayList<AttachedFile>();
				while (rs.next()) {
					AttachedFile sfn = new AttachedFile();
					sfn.setOriginalFile(rs.getString("o_file"));
					sfn.setServerFile(rs.getString("s_file"));
					list.add(sfn);
					// System.out.println("getFileNameById :" +
					// rs.getString("originalFile"));
				
					return list;
				}
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return null;
	}

	// 파일 업데이트 메소드
	public int admin_updateFile(Connection con, int no, String gcode,
			List<AttachedFile> fileList) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		System.out.println("확인");
		// 처리
		try {
			// sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql = " update goods_img " 
			+ " set o_file=?,s_file=? "
			+ " where gcode = ? and img_size = ?";

			// 상태
			pstmt = con.prepareStatement(sql);
			System.out.println("수정파일 갯수 :" + fileList.size());
			for (AttachedFile af : fileList) {
				// ?에 대한 데이터 셋팅
				pstmt.setString(1, af.getOriginalFile());
				pstmt.setString(2, af.getServerFile());
				pstmt.setString(3, gcode);
				pstmt.setString(4, af.getImgSize());
				// 실행
				pstmt.executeUpdate();
			}
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

}
