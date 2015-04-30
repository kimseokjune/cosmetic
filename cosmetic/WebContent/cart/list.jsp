<%@page import="com.vyon.cart.model.CartDisplayBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String) session.getAttribute("id");
	CartDisplayBean cartDisplayBean = (CartDisplayBean) request
			.getAttribute("cartDisplayBean");
	request.setAttribute("cartDisplayBean", cartDisplayBean);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 전체 선택	
		$("#btn_check_all").click(function() {
			$("input[name=check]:checkbox").each(function() {
				console.log("btn_check_all");
				$(this).attr("checked", true);
			});
		});
		
		// 전체 선택 해제
		$("#btn_uncheck_all").click(function() {
			$("input[name=check]:checkbox").each(function() {
				console.log("btn_uncheck_all");
				$(this).attr("checked", false);
			});
		});
		
		// 쇼핑계속하기
		$("#btn_continue_shop").click(function() {
			console.log("btn_continue_shop");
			$("#cartListForm").attr("action","../goods/user_list.do?cate=cle&sort=sale&method=asc");
		});
		
		// 전체 삭제 버튼 눌렀을 떄
		$("#btn_allDelete_goods").click(function() {
			console.log("btn_allDelete_goods");
			$("#cartListForm").attr("action","../cart/allDelete.do");
		});
		
		// 수량 수정 시 이벤트 처리
		$("button[name=btn_update_count]").click(function() {
			console.log("btn_update_count");
			// 안좋은 하드 코딩.ㅠㅠ
			// cartNo값과 같은 tr의 id 값 가져오기
			var cartNo = $(this).parent().parent().attr("id");
			// cartNo값과 같은 input number의 id의 value 값 가져오기 
			var count = $("#purchCount" + cartNo).val();
			console.log(cartNo);
			console.log(count);
			var link = "../cart/update.do?cartNo="+ cartNo+ "&count="+ count;
			$("#cartListForm").attr("action",link);
		});
		
		// 상품 주문
		$("#btn_payment").click(function() {
			console.log("btn_payment");
			var cartNoList = new Array();
			$("input[name=check]:checkbox").each(function() {
				if ($(this).is(':checked')) {
					cartNoList.push($(this).val());
				}
			});
			
			if (cartNoList.length == 0) {
				alert("주문할 상품을 선택하세요.");
				} else {
					$("#cartListForm").attr("action","../order/payment.do?prevPage=cart");
				}
			});
		
		// 선택 상품 삭제
		$("#btn_delete_goods").click(function() {
			console.log("btn_delete_goods");
			var cartNoList = new Array();
			
			$("input[name=check]:checkbox").each(function() {
				if ($(this).is(':checked')) {
					cartNoList.push($(this).val());
				}
			});

			if (cartNoList.length == 0) {
				alert("삭제할 상품을 선택하세요.");
			} else {
				//콘솔 창에서 리스트 값 확인
				$("#cartListForm").attr("action","../cart/delete.do");
			}
		});
		
		// number 값 변경 했을 때
		$(".number").bind("click keyup", function(e){
			
			var cartNo = $(this).parent().parent().attr("id");
			var gcount = parseInt($("#purchCount"+ cartNo).val());
			var maxCount = parseInt($("#purchCount"+ cartNo).attr("max"));
			
			// 최대값을 넘겼을 경우
			if(gcount>maxCount){
				alert("최대 " + maxCount + "개수 까지만 구매 가능 합니다.");
				$("#purchCount"+ cartNo).attr("value", maxCount);
				gcount = maxCount;
			}
		});
	});
</script>
<style type="text/css">
.layer_content table {
	width: 900px;
	margin: 0 auto;
}

#tr_td_align {
	text-align: right;
	padding-bottom: 6px;
}

.layer_content th {
	background-color: #ccc;
	border-top: 1px solid #999;
	border-bottom: 1px solid #999;
	height: 40px;
}

.border-top {
	border-top: 1px solid #999;
	height: 30px;
}

.border-bottom {
	border-bottom: 1px solid #999;
	height: 30px;
}
</style>
</head>

<body>


	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/sign.png"> 장바구니
		</div>


		<c:choose>

			<c:when test="${cartDisplayBean.list.size() == 0}">
				<div class="layer_content">장바구니가 비워있습니다.</div>
			</c:when>
			<c:otherwise>

				<div class="layer_content">

					<form id="cartListForm" action="#" method="post">
						<table>
							<tr>
								<td colspan="6" id="tr_td_align">
									<button id="btn_check_all" type="button">전체선택</button>
									<button id="btn_uncheck_all" type="button">전체해체</button>
								</td>

							</tr>
							<tr>
								<th>선택</th>
								<th>상품이미지</th>
								<th>상품정보</th>
								<th>판매가</th>
								<th>수량</th>
								<th>합계</th>
							</tr>
							<c:forEach var="bean" items="${cartDisplayBean.list}">
								<tr id="${bean.cartNo}">
									<td><input type="checkbox" name="check"
										value="${bean.cartNo}"></td>
									<td><a href="../goods/user_view.do?gcode=${bean.gcode}">
											<img src="../goods/gimage/${bean.imgName}" width="100"
											height="100">
									</a></td>
									<td><a href="../goods/user_view.do?gcode=${bean.gcode}">${bean.goodsName}</a></td>
									<td>${bean.price}원</td>
									<c:choose>
										<c:when test="${bean.count == 0}">
											<td>품절</td>
										</c:when>
										<c:otherwise>
											<td>
												<input class = "number" type="number" id="purchCount${bean.cartNo}" width="5px" min="1" max="${bean.count}" value="${bean.gcount}">개 <br>
												<button name="btn_update_count">수정</button>
											</td>
										</c:otherwise>
									</c:choose>
									<td>${bean.sumPrice}원</td>
								</tr>
							</c:forEach>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="border-top "><b>배송비</b></td>
								<td class="border-top "><b>${cartDisplayBean.shipping}원<b></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="border-bottom"><b>상품합계금액</b></td>
								<td class="border-bottom"><span
									style="color: red; font-weight: bold">${cartDisplayBean.totalPrice}원</span></td>
							</tr>
							<tr>
								<td colspan="9" height="40">
									<button id="btn_delete_goods">선택상품삭제</button>
									<button id="btn_payment">선택상품주문</button>
									<button id="btn_allDelete_goods">비우기</button>
									<button id="btn_continue_shop">쇼핑계속하기</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<br>
	<Br>
</body>
</html>