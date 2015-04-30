<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.2.1.min.js"></script>
<title>goods admin_update</title>
<style>
	#admin_update_wrap { 
		width:900px;
		margin:0 auto;
	}
	#admin_update_table{
		width:900px;
		margin:0 auto;
 		border-collapse:collapse;
		border-top:2px solid #999;
		border-right:0px solid #999;
		border-bottom:2px solid #999;
		border-left:0px solid #999;
		background:#fafbf5;
		
	}
	#admin_update_caption{
		font-size:20px;
		font-weight:bold;
		margin:5px;
	}
	#admin_update_table tr{
		border-top:1px solid #ccc;
		border-right:0px solid #ccc;
		border-bottom:1px solid #ccc;
		border-left:0px solid #ccc;		
	}
	#admin_update_table tr th{
		text-align:left;
		padding:5px 20px;
	}
	#admin_update_table tr td{
		padding:5px 20px;
		border-top:1px solid #ccc;
		border-right:0px solid #ccc;
		border-bottom:1px solid #ccc;
		border-left:1px solid #ccc;				
	}
	#admin_update_btn{
		width:900px;
		text-align:right;
		margin-top:5px;
	}	
</style>
</head>
<div id="admin_update_wrap">
<form action="../goods/admin_updateProcess.do" method="post" enctype="multipart/form-data">
<table id ="admin_update_table">
	<caption id="admin_update_caption">관리자 상품 수정</caption>
		<tr>
			<th>카테고리</th>
			<td>
			<select name="cname">
				<option value="cle">cleansing</option>
				<option value="pee">peel &amp; white</option>
				<option value="red">redox</option>
				<option value="cel">cell guard</option>
				<option value="ref">refining</option>
				<option value="sen">sensitive</option>
				<option value="dis">discount</option>
			</select>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="name" id="name" value="${goods.name}" /></td>
		</tr>
		<tr>
			<th>상품코드</th>
			<td><input type="text" name="gcode" id="gcode" value="${goods.gcode}" readonly/></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="price" id="price" value="${goods.price}" /> 원</td>
		</tr>
		<tr>
			<th>수량</th>
			<td><input type="text" name="count" id="count" value="${goods.count}" /> 개</td>
		</tr>
		<tr>
			<th>할인율</th>
			<td><input type="text" name="disRate" id="disRate" value="${goods.disRate}" /> %</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td><input type="text" name="product" id="product" value="${goods.product}" /></td>
		</tr>
		<tr>
			<th>제조일</th>
			<td><input type="text" name="productDate" id="productDate" value="${goods.productDate}" /></td>
		</tr>
		<tr>
			<th>판매시작일</th>
			<td><input type="text" name="startDate" id="startDate" value="${goods.startDate}" /></td>
		</tr>
		<tr>
			<th>판매종료일</th>
			<td><input type="text" name="endDate" id="endDate" value="${goods.endDate}" /></td>
		</tr>
		
		<c:forEach var="bean" items="${goods.fileList}" varStatus="status">
			<tr>
				<th>gimage${status.count}</th>
				<td>
					${bean.serverFile}
					<input type="file" name="gimage${status.count}" id="gimage" value=""/>
				</td>
			</tr>
		</c:forEach>
	</table>	
		<div id="admin_update_btn">
				<input type="submit" value="전송" />
				<input type="reset" value="원래대로"/>
				<input type="button" value="상품목록" onclick="location='admin_list.do?cate=all&sort=sale&method=asc'" />
		</div>
</form>
</div>
</html>