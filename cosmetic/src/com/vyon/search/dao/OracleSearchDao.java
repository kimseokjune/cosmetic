package com.vyon.search.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vyon.jdbc.JdbcUtil;
import com.vyon.search.model.Search;

public class OracleSearchDao extends SearchDao {

	

	@Override
	public List<Search> search(Connection con, Search search,int startRow, int endRow,String name2) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Search> list = null;
		// 처리
		try{
			System.out.println("goods.Name()은"+search.getName());
			System.out.println("name2는"+name2);
			System.out.println(startRow);
			System.out.println(endRow);
			//sql 작성
			if(search.getName()!=null){
			String sql=" select g.cname,g.gcode,g.name,g.product, "
					+" gi.o_file,gi.s_file,p.price,p.disrate, "
					+" round(p.price*(100-p.disrate)/100,-1) sale "
					+" from goods g,goods_img gi,price p where "
					+" g.gcode=gi.gcode and gi.gcode=p.gcode "
					+" and gi.img_size ='s' ";
					sql =" select rownum rnum,cname,gcode,name, "
					+ " o_file,s_file,price,disrate,product,sale "
					+" from ("+sql+")where 1=2 or name like ? or "
					+" gcode like ? or product like ? ";
					sql= " select * from ("+sql+") "
						+" where rnum between ? and ? ";
//					통합검색에 1에서 8까지 검색한다 순서는 무작위
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			pstmt.setString(1, "%" + search.getName() + "%");
			pstmt.setString(2, "%" + search.getName() + "%");
			pstmt.setString(3, "%" + search.getName() + "%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			}else{
				String sql=" select g.cname,g.gcode,g.name,g.product, "
						+" gi.o_file,gi.s_file,p.price,p.disrate, "
						+" round(p.price*(100-p.disrate)/100,-1) sale "
						+" from goods g,goods_img gi,price p where "
						+" g.gcode=gi.gcode and gi.gcode=p.gcode "
						+" and gi.img_size ='s' ";
						sql =" select rownum rnum,cname,gcode,name, "
						+ " o_file,s_file,price,disrate,product,sale "
						+" from ("+sql+")where 1=2 or name like ? or "
						+" gcode like ? or product like ? ";
						sql= " select * from ("+sql+") "
							+" where rnum between ? and ? ";
					pstmt = con.prepareStatement(sql);
					// ?에 대한 데이터 셋팅
					pstmt.setString(1, "%" + name2 + "%");
					pstmt.setString(2, "%" + name2 + "%");
					pstmt.setString(3, "%" + name2 + "%");
					pstmt.setInt(4, startRow);
					pstmt.setInt(5, endRow);
					//실행 select: executeQuery()
					rs=pstmt.executeQuery();
			}
			if(rs!=null){
				list=new ArrayList<Search>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("rnum"));
				list.add(makeSearchFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
		
		
	}

	@Override
	public List<Search> search(Connection con, Search search, int startRow,
			int endRow, String name2, String select) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Search> list = null;
		// 처리
		try{
			System.out.println("goods.Name()은"+search.getName());
			System.out.println("name2는"+name2);
			System.out.println("startRow는"+startRow);
			System.out.println("endRow는"+endRow);
			System.out.println("select는"+select);
			//sql 작성
			if(search.getName()!=null){
				if(select != null){
			String sql=" select g.cname,g.gcode,g.name,g.product,gi.o_file,gi.s_file,p.price,p.disrate, "
					  +" round(p.price*(100-p.disrate)/100,-1) sale "
					  +" from goods g,goods_img gi,price p "
					  +" where g.gcode=gi.gcode and gi.gcode=p.gcode and gi.img_size ='s' " ;
			//상태
				   sql=" select rownum rnum,cname,gcode,name,o_file,s_file,price,disrate,sale,product "
					 + " from ("+sql+") where 1=2 or " + select 
				     + " like ? ";
					// rnum중에서 startRow,endRow를 적용시킨다.:where
					sql=" select * from ("+sql+") "
					+ " where rnum between ? and ? ";
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, select);
			pstmt.setString(1, "%" + search.getName() + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			}else{
				String sql=" select g.cname,g.gcode,g.name,g.product, "
						+" gi.o_file,gi.s_file,p.price,p.disrate, "
						+" round(p.price*(100-p.disrate)/100,-1) sale "
						+" from goods g,goods_img gi,price p where "
						+" g.gcode=gi.gcode and gi.gcode=p.gcode "
						+" and gi.img_size ='s' ";
						sql =" select rownum rnum,cname,gcode,name, "
						+ " o_file,s_file,price,disrate,product,sale "
						+" from ("+sql+")where 1=2 or name like ? or "
						+" gcode like ? or product like ? ";
						sql= " select * from ("+sql+") "
							+" where rnum between ? and ? ";
					pstmt = con.prepareStatement(sql);
					// ?에 대한 데이터 셋팅
					pstmt.setString(1, "%" + search.getName() + "%");
					pstmt.setString(2, "%" + search.getName() + "%");
					pstmt.setString(3, "%" + search.getName() + "%");
					pstmt.setInt(4, startRow);
					pstmt.setInt(5, endRow);
					//실행 select: executeQuery()
					rs=pstmt.executeQuery();
				
			}
			}else{
				String sql=" select * from goods g,goods_img gi,price p  "
						+ " where g.gcode=gi.gcode and gi.gcode=p.gcode and gi.img_size ='s' "
						+ " where rnum between ? and ? " ;
					
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
					//실행 select: executeQuery()
					rs=pstmt.executeQuery();
			}
			if(rs!=null){
				list=new ArrayList<Search>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));

				list.add(makeSearchFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2, String select) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Search> list = null;
		// 처리
		try{
			System.out.println("name2는"+name2);
			System.out.println("startRow는"+startRow);
			System.out.println("endRow는"+endRow);
			System.out.println("select는"+select);
			//sql 작성
			if(select != null){
			String sql=" select g.cname,g.gcode,g.name,g.product,gi.o_file,gi.s_file,p.price,p.disrate, "
					+ " round(p.price*(100-p.disrate)/100,-1) sale "
					+ "from goods g,goods_img gi,price p where g.gcode=gi.gcode "
					+ "and gi.gcode=p.gcode  and gi.img_size ='s' " ;
			//상태
			sql=" select rownum rnum,cname,gcode,name,o_file,s_file,price,disrate,sale,product "
					+ " from ("+sql+") where 1=2 or " + select 
				+ " like ? ";
					// rnum중에서 startRow,endRow를 적용시킨다.:where
					sql=" select * from ("+sql+") "
					+ " where rnum between ? and ? ";
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, select);
			pstmt.setString(1, "%" + name2 + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			}else{
				System.out.println("여기까지옴");
				String sql=" select g.cname,g.gcode,g.name,g.product, "
						+" gi.o_file,gi.s_file,p.price,p.disrate, "
						+" round(p.price*(100-p.disrate)/100,-1) sale "
						+" from goods g,goods_img gi,price p where "
						+" g.gcode=gi.gcode and gi.gcode=p.gcode "
						+" and gi.img_size ='s' ";
						sql =" select rownum rnum,cname,gcode,name, "
						+ " o_file,s_file,price,disrate,product,sale "
						+" from ("+sql+")where 1=2 or name like ? or "
						+" gcode like ? or product like ? ";
						sql= " select * from ("+sql+") "
							+" where rnum between ? and ? ";
					pstmt = con.prepareStatement(sql);
					// ?에 대한 데이터 셋팅
					pstmt.setString(1, "%" + name2 + "%");
					pstmt.setString(2, "%" + name2 + "%");
					pstmt.setString(3, "%" + name2 + "%");
					pstmt.setInt(4, startRow);
					pstmt.setInt(5, endRow);
					//실행 select: executeQuery()
					
					rs=pstmt.executeQuery();
			}
			if(rs!=null){
				list=new ArrayList<Search>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));

				list.add(makeSearchFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2, String select, String sort, String method)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Search> list = null;
		// 처리
		try{
			System.out.println("name2는"+name2);
			System.out.println("startRow는"+startRow);
			System.out.println("endRow는"+endRow);
			System.out.println("select는"+select);
			System.out.println("sort는"+sort);
			System.out.println("method는"+method);
			//sql 작성
			if(select != null){
			String sql=" select g.cname,g.gcode,g.name,g.product, "
					+ " gi.o_file,gi.s_file,p.price,p.disrate, "
					+ " round(p.price*(100-p.disrate)/100,-1) sale "
					+ " from goods g,goods_img gi,price p where g.gcode=gi.gcode "
					+ " and gi.gcode=p.gcode and gi.img_size ='s' "
					+ " order by " + sort +" "+ method ;
			//상태
			sql=" select rownum rnum,cname,gcode,product,name,o_file,s_file,price,disrate,sale "
					+ " from ("+sql+")where 1=2 or " + select 
				    + " like ? ";
					// rnum중에서 startRow,endRow를 적용시킨다.:where
					sql=" select * from ("+sql+") "
					+ " where rnum between ? and ? ";
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, select);
			pstmt.setString(1, "%" + name2 + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			}else{
				String sql=" select g.cname,g.gcode,g.name,g.product,"
						+ " gi.o_file,gi.s_file,p.price,p.disrate, "
						+ " round(p.price*(100-p.disrate)/100,-1) sale " 
						+ " from goods g,goods_img gi,price p "
						+ " where g.gcode=gi.gcode "
						+ " and gi.gcode=p.gcode "
						+ " and gi.img_size ='s' order by " + sort +" "+ method ;
					//상태
					sql=" select rownum rnum,cname,gcode,product,name,o_file,s_file,price,disrate,sale "
							+ " from ("+sql+")  where 1=2 or name "
						    + " like ? or gcode like ? or product like ? ";
							// rnum중에서 startRow,endRow를 적용시킨다.:where
							sql=" select * from ("+sql+") "
							+ " where rnum between ? and ? ";
					pstmt = con.prepareStatement(sql);
					// ?에 대한 데이터 셋팅
					pstmt.setString(1, "%" + name2 + "%");
					pstmt.setString(2, "%" + name2 + "%");
					pstmt.setString(3, "%" + name2 + "%");
					pstmt.setInt(4, startRow);
					pstmt.setInt(5, endRow);
					//실행 select: executeQuery()
					rs=pstmt.executeQuery();
			}
			if(rs!=null){
				list=new ArrayList<Search>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));

				list.add(makeSearchFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Search> list = null;
		// 처리
		try{
			System.out.println("name2는"+name2);
			System.out.println("startRow는"+startRow);
			System.out.println("endRow는"+endRow);
			//sql 작성
			String sql=" select g.cname,g.gcode,g.name,g.product,gi.o_file,gi.s_file,p.price,p.disrate, "
					+ " round(p.price*(100-p.disrate)/100,-1) sale "
					+ "from goods g,goods_img gi,price p where g.gcode=gi.gcode "
					+ "and gi.gcode=p.gcode  and gi.img_size ='s' " ;
			//상태
			sql=" select rownum rnum,cname,gcode,name,o_file,s_file,price,disrate,sale,product "
					+ " from ("+sql+") where 1=2 or name like ? " 
				+ " or product like ? or gcode like ? ";
					// rnum중에서 startRow,endRow를 적용시킨다.:where
					sql=" select * from ("+sql+") "
					+ " where rnum between ? and ? ";
//					통합검색에 1에서 8까지 받아온다
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, select);
			pstmt.setString(1, "%" + name2 + "%");
			pstmt.setString(2, "%" + name2 + "%");
			pstmt.setString(3, "%" + name2 + "%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			
			
			if(rs!=null){
				list=new ArrayList<Search>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));

				list.add(makeSearchFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
	}

	@Override
	public List<Search> search(Connection con, int startRow, int endRow,
			String name2, String sort, String method) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Search> list = null;
		// 처리
		try{
			System.out.println("name2는"+name2);
			System.out.println("startRow는"+startRow);
			System.out.println("endRow는"+endRow);
			System.out.println("sort는"+sort);
			System.out.println("method는"+method);
			//sql 작성
			String sql=" select g.cname,g.gcode,g.name,g.product,gi.o_file,gi.s_file,p.price, "
					+ " p.disrate,round(p.price*(100-p.disrate)/100,-1) sale "
					+" from goods g,goods_img gi,price p where g.gcode=gi.gcode and "
				+ " gi.gcode=p.gcode and gi.img_size ='s' order by " + sort +" "+ method ;
			//상태
			sql=" select rownum rnum,cname,gcode,product,name,o_file,s_file,price,disrate,sale "
					+ " from ("+sql+") where 1=2 or " 
				+ " name like ? or product like ? or gcode like ? ";
					// rnum중에서 startRow,endRow를 적용시킨다.:where
					sql=" select * from ("+sql+") "
					+ " where rnum between ? and ? ";
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
//			pstmt.setString(1, select);
			pstmt.setString(1, "%" + name2 + "%");
			pstmt.setString(2, "%" + name2 + "%");
			pstmt.setString(3, "%" + name2 + "%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			
			if(rs!=null){
				list=new ArrayList<Search>();
			// 6.결과표시
			while(rs.next()){
				// List에 BoardBean 데이터 채우기
				
				System.out.println(rs.getString("name"));

				list.add(makeSearchFromListResultSet(rs));
				
				//
			}
			
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return list;
	}

	
	
	
}
