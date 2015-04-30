<%@page import="com.vyon.order.model.OrderListBean"%>
<%@page import="java.util.List"%>
<%@page import="com.vyon.order.model.OrederListView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="../js/jquery-ui-1.11.4.custom/jquery-ui.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript"
	src="../js/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {

				// 시작 날짜를 선택 했을 때
				$("input[name=startDate]").datepicker(
						{
							dateFormat : 'yy/mm/dd',
							maxDate : 'today',
							onSelect : function(dateText) {
								$("input[name=endDate]").datepicker('option',
										'minDate', dateText);
							}
						});

				// 끝 날짜를 선택 했을 때
				$("input[name=endDate]").datepicker(
						{
							dateFormat : 'yy/mm/dd',
							maxDate : 'today',
							onSelect : function(dateText) {
								$("input[name=startDate]").datepicker('option',
										'maxDate', dateText);
							}
						});

				// 검색 버튼을 눌렀을 때
				$("#btn_search").click(function() {
					var url = "../order/customerList.do";
					$("#searchForm").attr("action", url);
				});
			});
</script>
<style type="text/css">
.layer_content table {
	width: 850px;
	margin: 0 auto;

	
}

.layer_content th {
	background-color: #ccc;
	border-top: 1px solid #999;
	border-bottom: 1px solid #999;
	height: 30px;
}

.layer_content td{ padding: 5px;}

</style>
</head>
<body>
	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/magnifier.png">&nbsp;주문리스트
		</div>
		<c:choose>
			<c:when test="${orderList.size() == 0}">
				<div class="layer_content">주문 리스트가 없습니다.</div>
			</c:when>
			<c:otherwise>
				<div class="layer_content">
					<form id="searchForm" action="#" method="post">
					<br>
						<table >
							<tr>
								<td>검색 기간 선택</td>
								<td><input name="startDate" value="${pageView.startDate}">-<input
									name="endDate" value="${pageView.endDate}"></td>
							</tr>
							<tr>
								<td>키워드 선택</td>

								<td><select name="searchKey">
										<option value="OLNO"
											<c:if test="${pageView.searchKey =='OLNO'}"> selected ="selected" </c:if>>주문번호</option>
										<option value="GOODSNAME"
											<c:if test="${pageView.searchKey =='GOODSNAME'}"> selected ="selected" </c:if>>상품명</option>
								</select> <input name="searchWord" type="text"
									value="${pageView.searchWord}">
									<button id="btn_search">검색</button></td>
							</tr>
						</table>
					</form>

					<br>
					<table>

						<tr>
							<th>주문일시</th>
							<th>주문번호</th>
							<th>주문자</th>
							<th>받는분</th>
							<th>결제방법</th>
							<th>주문금액</th>
							<th>상세보기</th>
						</tr>
						<c:forEach var="bean" items="${orderList}">
							<tr>
								<td>${bean.orderDay}</td>
								<td id="${bean.orderNo}"><a
									href="/cosmetic/order/customerView.do?orderNo=${bean.orderNo}">[${bean.orderNo}]
										${bean.goodsName}</a></td>
								<td>${bean.orderName}</td>
								<td>${bean.addresseeName}</td>
								<td>${bean.paystate}</td>
								<td>${bean.totalPrice} 원 </td>
								<td><button
										onclick="location.href='/cosmetic/order/customerView.do?orderNo=${bean.orderNo}'">보기</button></td>
							</tr>
						</c:forEach>
						<tr id="paging">
							<td colspan="7">&nbsp; <a
								href="customerList.do?page=1&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">[처음]</a>
								<c:if test="${pageView.startPage - pageView.pagePerGroup > 0}">
									<a
										href="customerList.do?page=${pageView.startPage - pageView.pagePerGroup}&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">&lt;&lt;</a>&nbsp;
							</c:if> <!-- 이전페이지 이동 --> <c:if test="${pageView.page - 1 > 0}">
									<a
										href="customerList.do?page=${pageView.page-1}&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">&lt;</a>&nbsp;
							</c:if> <!-- 페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 --> <c:forEach var="page"
									begin="${pageView.startPage}" end="${pageView.endPage }">
									<c:choose>
										<c:when test="${pageView.page == page}">
											<b>[${page}]</b>
										</c:when>
										<c:otherwise>
											<a
												href="customerList.do?page=${page}&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">[${page}]</a>&nbsp;
									</c:otherwise>
									</c:choose>
								</c:forEach> <!-- 페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝--> <!-- 다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
								<c:if test="${pageView.page + 1 <= pageView.totalPage}">
									<a
										href="customerList.do?page=${pageView.page + 1}&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">&gt;</a>&nbsp;
							</c:if> <c:if
									test="${pageView.startPage + pageView.pagePerGroup <= pageView.totalPage}">
									<a
										href="customerList.do?page=${pageView.startPage + pageView.pagePerGroup}&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">&gt;&gt;</a>&nbsp;
							</c:if> <a
								href="customerList.do?page=${pageView.totalPage}&searchKey=${pageView.searchKey}&searchWord=${pageView.searchWord}&startDate=${pageView.startDate}&endDate=${pageView.endDate}">[끝]</a>&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<br>
	<br>
</body>
</html>