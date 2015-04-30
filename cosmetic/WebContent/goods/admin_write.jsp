<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.2.1.min.js"></script>
<title>goods admin_write</title>

<style>
	#admin_write_wrap { 
		width:900px;
		margin:0 auto;
	}
	#admin_write_table{
		width:900px;
		margin:0 auto;
 		border-collapse:collapse;
		border-top:2px solid #999;
		border-right:0px solid #999;
		border-bottom:2px solid #999;
		border-left:0px solid #999;
		background:#fafbf5;
		
	}
	#admin_write_caption{
		font-size:20px;
		font-weight:bold;
		margin:5px;
	}
	#admin_write_table tr{
		border-top:1px solid #ccc;
		border-right:0px solid #ccc;
		border-bottom:1px solid #ccc;
		border-left:0px solid #ccc;		
	}
	#admin_write_table tr th{
		text-align:left;
		padding:5px 20px;
	}
	#admin_write_table tr td{
		padding:5px 20px;
		border-top:1px solid #ccc;
		border-right:0px solid #ccc;
		border-bottom:1px solid #ccc;
		border-left:1px solid #ccc;				
	}
	#admin_write_btn{
		width:900px;
		text-align:right;
		margin-top:5px;
	}
</style>
</head>
<body>
<div id="admin_write_wrap">
<form action="admin_writeProcess.do" method="post" enctype="multipart/form-data">
	<table id="admin_write_table">
		<caption id="admin_write_caption">관리자 상품 등록</caption>
		<tr>
			<th>카테고리</th>
			<td>
			<select name="cname">
				<option value="cle">cleansing</option>
				<option value="pee">peel &amp; white</option>
				<option value="red">redox</option>
				<option value="cel">cell guard</option>
				<option value="ref">refinning</option>
				<option value="sen">sensitive</option>
				<option value="dis">discount</option>
			</select>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="name" id="name"></td>	
		</tr>
		<tr>
			<th>상품코드</th>
			<td><input type="text" name="gcode" id="gcode"></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="price" id="price"> 원</td>
		</tr>
		<tr>
			<th>수량</th>
			<td><input type="text" name="count" id="count"> 개</td>
		</tr>
		<tr>
			<th>할인율</th>
			<td><input type="text" name="disRate" id="disRate"> %</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td><input type="text" name="product" id="product"></td>
		</tr>
		<tr>
			<th>제조일</th>
			<td><input type="text" name="productDate" id="productDate"></td>
		</tr>
		<tr>
			<th>판매시작일</th>
			<td><input type="text" name="startDate" id="startDate"></td>
		</tr>
		<tr>
			<th>판매종료일</th>
			<td><input type="text" name="endDate" id="endDate"></td>
		</tr>
		<tr>
			<th>gimage1</th>
			<td>
				<input type="file" name="gimage1" id="gimage1" />
			</td>
		</tr>
		<tr>
			<th>gimage2</th>
			<td>
				<input type="file" name="gimage2" id="gimage2" />
			</td>
		</tr>
		<tr>
			<th>gimage3</th>
			<td>
				<input type="file" name="gimage3" id="gimage3" />
			</td>
		</tr>
		<tr>
			<th>gimage4</th>
			<td>
				<input type="file" name="gimage4" id="giamge4" value="" />
			</td>	
		</tr>
	</table>
	<div id="admin_write_btn">
		<input type="submit" value="전송" />
		<input type="reset" value="재입력" />
		<a href="../goods/admin_list.do?cate=all&sort=sale&method=asc"><input type="button" value="상품목록" /></a>
	</div>
	</form>
</div><br/><br/>
</body>
</html>