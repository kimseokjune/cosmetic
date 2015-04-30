<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin list</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<style>
#admin_list_wrap {
	width: 900px;
	margin: 0 auto;
}

#admin_list_navi {
	width: 900px;
	margin: 0 auto;
}

#admin_list_navi ul {
	list-style: none;
	float: right;
}

#admin_list_navi ul li {
	float: left;
	align: right;
	line-height: 20px;
	margin-right: 5px;
}

#admin_list_navi_align {
	height: 20px;
}

#align_search {
	clear:both;
	float:right;	
}

#admin_list_form {
	width: 900px;
	margin: 0 auto;
}

#admin_list_table {
	width: 900px;
	margin: 0 auto;
	border-collapse: collapse;
	border-top: 2px solid #999;
	border-right: 0px solid #999;
	border-bottom: 2px solid #999;
	border-left: 0px solid #999;
	background: #fafbf5;
}

#admin_list_caption {
	font-size: 20px;
	font-weight: bold;
	margin: 5px;
}

#admin_list_table tr {
	border-top: 1px solid #ccc;
	border-right: 0px solid #ccc;
	border-bottom: 1px solid #ccc;
	border-left: 0px solid #ccc;
}

#admin_list_table tr th {
	text-align: left;
	padding: 5px;
	border-top: 1px solid #ccc;
	border-right: 0px solid #ccc;
	border-bottom: 1px solid #ccc;
	border-left: 1px solid #ccc;
}

#admin_list_table tr td {
	padding: 5px;
	text-align: left;
	border-top: 1px solid #ccc;
	border-right: 0px solid #ccc;
	border-bottom: 1px solid #ccc;
	border-left: 1px solid #ccc;
}

#admin_list_table tr td p{
	line-height:120%;
}
.somenail {
	width: 100px;
}

#admin_list_btn {
	width: 900px;
	margin: 0 auto;
	margin: 5px 0px;
}

#admin_list_text_red {
	color: #f00;
}

#admin_list_page_wrap {
	margin: 0 auto;
	text-align: center;
}

#admin_list_page {
	align: center;
}
#bold_line{

}		
#red{
	color:red;
}
#admin_list_choice{
	width:28px;
}



</style>

<script type="text/javascript">
	function goUrl(url) {
		window.location.href = url;
	}

	$(document).ready(
		function() {
			// 전체 선택 
			$("#btn_check_all").click(function() {
				$("input[type=checkbox]").attr("checked", "checked");
			});
			
			// 전체선택 해제
			$("#btn_uncheck_all").click(function() {
				$("input[type=checkbox]").removeAttr("checked");
			});
			
			// 선택된 상품 삭제
			$("#btn_delete_goods").click(function() {
					console.log("btn_delete_goods");
					var gcodeList = new Array();
					
					$("input[name=check]:checkbox").each(function() {
						if ($(this).is(':checked')) {
							gcodeList.push($(this).val());
						}
					});					
					if (gcodeList.length == 0) {
						console.log("삭제할 상품을 선택하세요.");
					} else {
						//콘솔 창에서 리스트 값 확인
						$("#admin_list_form").attr("action", "../goods/admin_deleteProcess.do");
					}
				});
			
			// 상품등록 이동
			$("#btn_goods_register").click(function() {
					console.log("btn_goods_register");
					$("#admin_list_form").attr("action", "../goods/admin_write.do");
				});

		});
</script>

</head>
<body>
	<div id="admin_list_wrap">
		<div id="admin_list_navi">
			<ul id="admin_list_navi_align">
				<li><a
					href="../goods/admin_list.do?cate=${cate}&sort=sale&method=asc">낮은가격</a><span>
						|</span></li>
				<li><a
					href="../goods/admin_list.do?cate=${cate}&sort=sale&method=desc">높은가격</a><span>
						|</span></li>
				<li><a
					href="../goods/admin_list.do?cate=${cate}&sort=name&method=asc">상품명</a><span>
						|</span></li>
				<li><a
					href="../goods/admin_list.do?cate=${cate}&sort=product&method=asc">제조사</a></li>
<!-- 				<li><select name="" id=""> -->
<!-- 						<option value="">8개씩 정렬</option> -->
<!-- 						<option value="">12개씩 정렬</option> -->
<!-- 						<option value="">24개씩 정렬</option> -->
<!-- 						<option value="">36개씩 정렬</option> -->
<!--  					</select> -->
<!-- 				</li>  -->
			</ul>
<!-- 			<br /> -->
<!-- 			<div id="space"></div> -->
<!-- 			<br /> -->
<!-- 			<ul id="align-search"> -->
<!-- 				<li><input type="search" value="검색" />상품검색</li> -->
<!-- 			</ul> -->
		</div>
		<form id="admin_list_form" action="#" method="post" >
			<table id="admin_list_table">
				<caption id="admin_list_caption">관리자 상품목록</caption>
				<tr>

					<td colspan="2">카테고리</td>
					<td colspan="8"><select name="cname"
						onchange="goUrl(this.options[this.selectedIndex].value)">	
							<option
								value="../goods/admin_list.do?cate=all&sort=sale&method=asc"
								<c:if test="${cate =='all' }">
							selected ="selected"
							</c:if>>all
								category</option>
							<option
								value="../goods/admin_list.do?cate=cle&sort=sale&method=asc"
								<c:if test="${cate =='cle' }">
							selected ="selected"
							</c:if>>cleansing</option>
							<option
								value="../goods/admin_list.do?cate=pee&sort=sale&method=asc"
								<c:if test="${cate =='pee' }">
							selected ="selected"
							</c:if>>peel
								&amp; white</option>
							<option
								value="../goods/admin_list.do?cate=red&sort=sale&method=asc"
								<c:if test="${cate =='red' }">
							selected ="selected"
							</c:if>>redox</option>
							<option
								value="../goods/admin_list.do?cate=cel&sort=sale&method=asc"
								<c:if test="${cate =='cel' }">
							selected ="selected"
							</c:if>>cell
								guard</option>
							<option
								value="../goods/admin_list.do?cate=ref&sort=sale&method=asc"
								<c:if test="${cate =='ref' }">
							selected ="selected"
							</c:if>>refining</option>
							<option
								value="../goods/admin_list.do?cate=sen&sort=sale&method=asc"
								<c:if test="${cate =='sen' }">
							selected ="selected"
							</c:if>>sensitive</option>
							<option
								value="../goods/admin_list.do?cate=dis&sort=sale&method=asc"
								<c:if test="${cate =='dis' }">
							selected ="selected"
							</c:if>>discount</option>
					</select></td>
				</tr>
				<tr>
					<th id="admin_list_choice">선택</th>
					<th>상품이미지</th>
					<th>상품명</th>
					<th>상품코드</th>
					<th>가격(할인가)</th>
					<th>할인율</th>
					<th>제조사</th>
					<th>제조일</th>
					<th>판매여부</th>
					<th>수정</th>
				</tr>

				<c:forEach var="bean" items="${data.list}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="check" value="${bean.gcode}" class="checkbox" /></td>
						<td><a href="../goods/admin_view.do?gcode=${bean.gcode}"><img
								src="../goods/gimage/${bean.gimage1}" class="somenail" /></a></td>
						<td>${bean.name}</td>
						<td>${bean.gcode}</td>
						<td>
							<p id="bold_line">${bean.price}원</p>
							<p id="red">(${bean.salePrice}원)</p>
						</td>
						<td>${bean.disRate}%</td>
						<td>${bean.product}</td>
						<td>${bean.productDate}</td>
						<td id="admin_list_sale_desition"><c:choose>
								<c:when
									test="${today >= bean.startDate && today <= bean.endDate}">판매중</c:when>
								<c:otherwise>
									<span id="admin_list_text_red">판매종료</span>
								</c:otherwise>
							</c:choose></td>
						<td><a href="../goods/admin_update.do?gcode=${bean.gcode}"><input
								type="button" value="수정"></a></td>
					</tr>
				</c:forEach>

			</table>
			<div id="admin_list_btn">
				<button id="btn_check_all" type="button">전체선택</button>
				<button id="btn_uncheck_all" type="button">전체해제</button>
				<button id="btn_delete_goods" type="submit">선택상품삭제</button>
				<button id="btn_goods_register" type="submit">상품등록</button>
			</div>
		</form>
		
		<!-- 페이징처리시작 -->
		<div id="admin_list_page_wrap">
			<div id="admin_list_page">
				<a href="admin_list.do?cate=${cate}&page=1&sort=${sort}&method=${method}">[처음]</a>
				<c:if test="${data.startPage > 1 }">
					<a
						href="admin_list.do?cate=${cate}&page=${data.page-data.pagePerGroup}&sort=${sort}&method=${method}">&lt;&lt;</a>
				</c:if>
				<!-- 			이전페이지 이동 -->
				<c:if test="${data.page != 1 }">
					<a
						href="admin_list.do?cate=${cate}&page=${data.page-1 }&sort=${sort}&method=${method}">&lt;</a>
				</c:if>

				<!-- 	페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 시작 -->
				<c:forEach var="page" begin="${data.startPage}"
					end="${data.endPage }">
					<c:choose>
						<c:when test="${data.page == page}">
							<b>[${page}]</b>
						</c:when>
						<c:otherwise>
							<a
								href="admin_list.do?cate=${cate}&page=${page}&sort=${sort}&method=${method}">[${page}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

				<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
				<c:if test="${data.page != data.totalPage }">
					<a
						href="admin_list.do?cate=${cate}&page=${data.page + 1}&sort=${sort}&method=${method}">&gt;</a>
				</c:if>
				<c:if test="${data.endPage<data.totalPage}">
					<a
						href="admin_list.do?cate=${cate}&page=${data.page+data.pagePerGroup }&sort=${sort}&method=${method}">&gt;&gt;</a>
				</c:if>
				<a
					href="admin_list.do?cate=${cate}&page=${data.totalPage}&sort=${sort}&method=${method}">[끝]</a>
			</div>
		</div>
		<!-- 페이징처리끝 -->
	</div><br/><br/>

</body>
</html>