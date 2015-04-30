<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Goods admin_view</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.2.1.min.js"></script>
<script>
// $(document).ready(
// 		function() {
// 			// 상품수정 이동 
// 			$("#btn_goods_modify").click(function() {
// 				$("admin_view_form").attr("action", "../goods/admin_update.do?gcode" +gcode);
// 			});
// 			// 상품삭제 실행
// 			$("#btn_delete_goods").click(function() {
// 				$("admin_view_form").attr("action", "../goods/admin_delete.do?gcode" +gcode);
// 			});		
// 			// 상품목록 이동
// 			$("#btn_goods_register").click(
// 				function() {
// 					console.log("btn_goods_register");
// 					// 		alert($("##admin_list_form").attr("action"));
// 					$("#admin_view_form").attr("action", "../goods/admin_list.do?cate=all&sort=sale&method=asc");
// 					// 		alert($("##admin_list_form").attr("action"));
// 				});
// 		});
</script>

<style>
	#admin_view_wrap { 
		width:900px;
		margin:0 auto;
	}
	#admin_list_form {
	width: 900px;
	margin: 0 auto;
	}
	#admin_view_table{
		width:900px;
		margin:0 auto;
 		border-collapse:collapse;
		border-top:2px solid #999;
		border-right:0px solid #999;
		border-bottom:2px solid #999;
		border-left:0px solid #999;
		background:#fafbf5;
		
	}
	#admin_view_caption{
		font-size:20px;
		font-weight:bold;
		margin:5px;
	}
	#admin_view_table tr{
		border-top:1px solid #ccc;
		border-right:0px solid #ccc;
		border-bottom:1px solid #ccc;
		border-left:0px solid #ccc;		
	}
	#admin_view_table tr th{
		text-align:left;
		padding:5px 20px;
	}
	#admin_view_table tr td{
		padding:5px 20px;
		border-top:1px solid #ccc;
		border-right:0px solid #ccc;
		border-bottom:1px solid #ccc;
		border-left:1px solid #ccc;				
	}
	#admin_view_btn{
		width:900px;
		text-align:right;
		margin-top:5px;
	}	
	.somenail{
		width:100px;
		height:100px;
	}
	.readyonly{
		readyonly
	}
</style>
</head>
<body>
<div id="admin_view_wrap">
	<div id="">
	<form id="admin_view_form" action="../goods/admin_delete.do?gcode=${goods.gcode}" method="post" enctype="multipart/form-data">
		<table id="admin_view_table">
			<caption id="admin_view_caption">관리자 상품 상세보기</caption>
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
				<td>${goods.name}</td>	
			</tr>
			<tr>
				<th>상품코드</th>
				<td>${goods.gcode}</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>${goods.price}원</td>
			</tr>
			<tr>
				<th>수량</th>
				<td>${goods.count}개</td>
			</tr>
			<tr>
				<th>제조사</th>
				<td>${goods.product}</td>
			</tr>
			<tr>
				<th>제조일</th>
				<td>${goods.productDate}</td>
			</tr>
			<tr>
				<th>할인율</th>
				<td>${goods.disRate}%</td>
			</tr>
			<tr>
				<th class="readonly">할인가</th>
				<td>${goods.salePrice}원</td>
			</tr>
			<tr>
				<th>판매시작일</th>
				<td>${goods.startDate}</td>
			</tr>
			<tr>
				<th>판매종료일</th>
				<td>${goods.endDate}</td>
			</tr>
			
			<c:forEach var="bean" items="${goods.fileList}" varStatus="status">
			<tr>
				<th>gimage${status.count}</th>
				<td><img src="../goods/gimage/${bean.serverFile}" class="somenail"/></td>
			</tr>
			</c:forEach>
				
		</table>
		<div id="admin_view_btn">
<!-- 			<button id="btn_goods_modify" type="submit">상품수정</button> -->
<!-- 			<button id="btn_delete_goods" type="submit">상품삭제</button> -->
<!-- 			<button id="btn_goods_register" type="submit">상품목록</button> -->
			<a href="../goods/admin_update.do?gcode=${goods.gcode}"><input type="button" value="상품수정" /></a>
			<a href="../goods/admin_delete.do?gcode=${goods.gcode}"><input type="submit" value="상품삭제" /></a>
			<a href="../goods/admin_list.do?cate=all&sort=sale&method=asc"><input type="button" value="상품목록" /></a><br/><br/>
		</div>
		</form>
	</div>
</div>
</body>
</html>