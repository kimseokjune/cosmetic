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

public class OracleGoodsDao extends GoodsDao {

	@Override
	public List<Goods> admin_list(Connection con, int startRow, int endRow, String cate, String sort, String method)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("OracleGoodsDao.list()");
		//System.out.println(cate);
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Goods> list = null;
		// 처리
		try{
			//sql 작성
			String sql =null;
				//admin_list의 해당 상품을 category별 정렬
			if(cate.equals("all")){
				sql=" select g.cname,g.gcode,g.name,g.product,g.productdate,gi.s_file, "
				+" p.price,p.disrate,p.startdate,p.enddate, " 
				+" round(p.price*(100-p.disrate)/100,-1) sale "
				+" from goods g, price p, goods_img gi "
				+" where gi.img_size='s' "
				+" and g.gcode=gi.gcode "
				+" and g.gcode=p.gcode "
				+" order by " + sort +" "+ method;
				
			} else {
				sql=" select g.cname,g.gcode,g.name,g.product,g.productdate,gi.s_file, "
				+" p.price,p.disrate,p.startdate,p.enddate, " 
				+" round(p.price*(100-p.disrate)/100,-1) sale "
				+" from goods g, price p, goods_img gi "
				+" where g.cname= ? "
				+" and gi.img_size='s' "
				+" and g.gcode=gi.gcode "
				+" and g.gcode=p.gcode " 
				+" order by " + sort +" "+ method;	
			}
				// rownum을 붙인다.
				sql=" select rownum rnum,cname,gcode,name,product,productdate,s_file, "
				+" price,disrate,startdate,enddate, " 
				+" round(price*(100-disrate)/100,-1) sale "
				+" from ("+sql+") ";
				
				// rnum중에서 startRow,endRow를 적용시킨다.:where
				sql=" select * from ("+sql+") "
				+" where rnum between ? and ? ";
			
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			if(!cate.equals("all")){
				pstmt.setString(1, cate);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
				
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null){
				list=new ArrayList<Goods>();
				while(rs.next()){
					Goods goods = new Goods();
					goods.setCname(rs.getString("cname"));
					goods.setGcode(rs.getString("gcode"));
					goods.setName(rs.getString("name"));
					goods.setProduct(rs.getString("product"));
					goods.setProductDate(rs.getString("productDate"));
					goods.setGimage1(rs.getString("s_file"));
					goods.setPrice(rs.getInt("price"));
					goods.setDisRate(rs.getInt("disrate"));
					goods.setStartDate(rs.getString("startDate"));
					goods.setEndDate(rs.getString("endDate"));
					goods.setSalePrice(rs.getInt("sale"));
					list.add(goods);
//					list.add(makeGoodsFromListResultSet(rs));
				}
			}
			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
	}	
	
	@Override
	public List<Goods> user_list(Connection con, int startRow, int endRow, String cate, String sort, String method)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("OracleGoodsDao.user_list()");
		//System.out.println(cate);
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Goods> list = null;
		// 처리
		try{
			// sql 작성
			// category all 과 cate구별 처리
			String sql =null;
			if(cate.equals("all")){
			sql= " select g.cname,g.gcode,g.name,gi.s_file,p.price,p.disrate, " 
				+" round(p.price*(100-p.disrate)/100,-1) sale "
				+" from goods g, price p, goods_img gi "
				+" where gi.img_size='s' "
				+" and g.gcode=gi.gcode "
				+" and g.gcode=p.gcode " 
				+" and sysdate>=p.startDate "
				+" and to_char(sysdate,'yyyy-mm-dd')<=to_char(p.enddate,'yyyy-mm-dd') "
				+" order by " + sort +" "+ method;	
			} else {
			sql= " select g.cname,g.gcode,g.name,gi.s_file,p.price,p.disrate, " 
				+" round(p.price*(100-p.disrate)/100,-1) sale "
				+" from goods g, price p, goods_img gi "
				+" where g.cname= ? "
				+" and gi.img_size='s' "
				+" and g.gcode=gi.gcode "
				+" and g.gcode=p.gcode " 
				+" and sysdate>=p.startDate "
				+" and to_char(sysdate,'yyyy-mm-dd')<=to_char(p.enddate,'yyyy-mm-dd') "
				+" order by " + sort +" "+ method;					
			}
			
			// rownum을 붙인다.
			sql=" select rownum rnum,cname,gcode,name,s_file,price,disrate, " 
			+" round(price*(100-disrate)/100,-1) sale "
			+" from ("+sql+") ";
			
			// rnum중에서 startRow,endRow를 적용시킨다.:where
			sql=" select * from ("+sql+") "
			+" where rnum between ? and ? ";
		
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			if(!cate.equals("all")){
				pstmt.setString(1, cate);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}			
			
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null){
				list=new ArrayList<Goods>();
				while(rs.next()){
					Goods goods = new Goods();
					goods.setCname(rs.getString("cname"));
					goods.setGcode(rs.getString("gcode"));
					goods.setName(rs.getString("name"));
					goods.setGimage1(rs.getString("s_file"));
					goods.setPrice(rs.getInt("price"));
					goods.setDisRate(rs.getInt("disrate"));
					goods.setSalePrice(rs.getInt("sale"));
					list.add(goods);
//					list.add(makeGoodsFromListResultSet(rs));
				}
			}
			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public int admin_write(Connection con, Goods goods) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("OracleGoodsDao.admin_write()");
		
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		
		// 처리
		try{
			//sql 작성
			String sql=" insert into goods(cname,gcode,name,product,count,productdate,no) "
			+" values(?,?,?,?,?,?,goods_seq.nextVal) ";
			//상태
			pstmt = con.prepareStatement(sql);

			// ?에 대한 데이터 셋팅
			pstmt.setString(1, goods.getCname());
			pstmt.setString(2, goods.getGcode());
			pstmt.setString(3, goods.getName());
			pstmt.setString(4, goods.getProduct());
			pstmt.setInt(5, goods.getCount());
			pstmt.setString(6, goods.getProductDate());
			// 실행
			pstmt.executeUpdate();
					
			String sql2=" insert into price(pno,gcode,price,disrate,startdate,enddate) "
					+" values(price_seq.nextVal,?,?,?,?,?) ";
			//상태
			pstmt = con.prepareStatement(sql2);

			// ?에 대한 데이터 셋팅
			pstmt.setString(1, goods.getGcode());
			pstmt.setInt(2, goods.getPrice());
			pstmt.setInt(3, goods.getDisRate());
			pstmt.setString(4, goods.getStartDate());
			pstmt.setString(5, goods.getEndDate());
			
			// 실행
			pstmt.executeUpdate();
			
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}
	
	@Override
//	파일을 첨부시키는 메소드
	public int admin_writeFile(Connection con, int no, String gcode, List<AttachedFile> fileList)
		throws SQLException {
		System.out.println("OracleGoodsDao.admin_writeFile()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;

		// 처리
		try{
			//sql 작성
			// goods의 내용의 리스트 해당항목을 상품번호 desc
			String sql=" insert into goods_img(no,gcode,"
			+" o_file,s_file,img_size) "
			+" values(goods_img_seq.nextval,?,?,?,?) ";
			
			//상태
			pstmt = con.prepareStatement(sql);
			for(AttachedFile af : fileList){
				// ?에 대한 데이터 셋팅
				pstmt.setString(1, gcode);
				pstmt.setString(2, af.getOriginalFile());
				pstmt.setString(3, af.getServerFile());
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
