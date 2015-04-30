<%@page import="com.vyon.order.model.OrderDetailBean"%>
<%@page import="java.util.List"%>
<%@page import="com.vyon.order.model.OrderListBean"%>
<%@ page language="java" contentType="text/html; charset=uft-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	OrderListBean orderListBean = (OrderListBean) request.getAttribute("orderListBean");
	List<OrderDetailBean> orderDetailList = (List<OrderDetailBean>) request.getAttribute("orderDetailList");
	
	request.setAttribute("orderListBean", orderListBean);
	request.setAttribute("orderDetailList", orderDetailList);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 이전 버튼을 눌렀을 때
		$("#btn_back").click(function() {
			var url = "../order/adminList.do";
			$("#orderDetailForm").attr("action", url);
		});
		
		//전체 선택	
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
		
		// 수정 버튼을 눌렀을 때
		$("#btn_update").click(function() {
			var orderNoList = new Array();
			$("input[name=check]:checkbox").each(function() {
				if ($(this).is(':checked')) {
					orderNoList.push($(this).val());
				}
			});
			
			if (orderNoList.length == 0) {
				alert("변경할 주문을 선택하세요.");
			} else {
				// 요런식으로 넘기는거 위험하긴 합니다...
				var url = "";
				var orderNo = "${orderListBean.orderNo}";
				if ($("select[name=select]").val() == "checkDeposit") {
					// 입금 확인 처리 					
					url = "../order/checkDeposit.do?orderNo=" + orderNo;
				} else if ($("select[name=select]").val() == "readyDelivery") {
					// 배송 준비 처리 
					url = "../order/readyDelivery.do?orderNo=" + orderNo;
				} else if ($("select[name=select]").val() == "shipping") {
					// 배송 중 처리
					url = "../order/shipping.do?orderNo=" + orderNo;
				} else if ($("select[name=select]").val() == "exchangeProcess") {
					// 교환 접수 처리
					url = "../order/exchangeProcess.do?orderNo=" + orderNo;
				} else if ($("select[name=select]").val() == "exchanging") {
					// 교환 중 처리
					url = "../order/exchanging.do?orderNo=" + orderNo;
				} else if ($("select[name=select]").val() == "refundProcess") {
					// 반품 중 처리
					url = "../order/refundProcess.do?orderNo=" + orderNo;
				} else if ($("select[name=select]").val() == "refunding") {
					// 반품 중 처리
					url = "../order/refunding.do?orderNo=" + orderNo;
				}
				
				$("#orderDetailForm").attr("action", url);
			}
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
}

.layer_content caption {
	text-align: left;
	padding-bottom: 5px;
}

.border-bottom {
	border-bottom: 1px solid #999
}

.border-top {
	border-top: 1px solid #999
}

#order_table th {
	height: 100px
}

#tr_height {
	height: 40px;
}

#tr_td_align {
	text-align: right;
}
</style>
</head>


<body>

	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/magnifier.png">&nbsp;&lt;주문내역상세보기&gt;

		</div>
		<div class="layer_content">

			<form id="orderDetailForm" action="#" method="post">
				<div>
					<table id="table" width="900">
						<caption>&lt;주문상세내역&gt;</caption>
						<Tr id="tr_height">
							<td colspan="2"></td>
							<td colspan="6">아래에서 선택한 주문건을 <select name="select">
									<option value="checkDeposit">결제 확인 처리</option>
									<option value="readyDelivery">배송 준비 중 처리</option>
									<option value="shipping">배송 중 처리</option>
									<option value="exchangeProcess">교환 접수 처리</option>
									<option value="exchanging">교환 중 처리</option>
									<option value="refundProcess">반품 접수 처리</option>
									<option value="refunding">반품 중 처리</option>
							</select> 합니다. (변경 후 하단 수정 버튼 꼭 클릭)
							</td>
							<td colspan="2" id="tr_td_align">
								<button id="btn_check_all" type="button">전체 선택</button>
								<button id="btn_uncheck_all" type="button">전체 취소</button>
							</td>
						</Tr>
						<tr>

							<th width="35" height="30">선택</th>
							<th width="50">이미지</th>
							<th>상품정보</th>
							<th width="65">판매가</th>
							<th width="35">수량</th>
							<th width="65">총가격</th>
							<th width="80">주문상태</th>
							<th width="85">결제날짜</th>
							<th width="85">배송날짜</th>
							<th width="85">배송완료날짜</th>
						</tr>

						<c:forEach var="bean" items="${orderDetailList}">
							<tr>
								<td><input type="checkbox" name="check" value="${bean.no}"></td>
								<td><img src="../order/gimage/${bean.imgName}" width="100"
									height="100"></td>
								<td>${bean.goodsName}</td>
								<td>${bean.price}원</td>
								<td>${bean.gcount}</td>
								<td>${bean.sumPrice}원</td>
								<td>${bean.orderState}</td>
								<td>${bean.paymentday}</td>
								<td>${bean.deliveryday}</td>
								<td>${bean.receiptday}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />


				<div>
					<table width="900" id="order_table">
						<caption>&lt;주문서 작성&gt;</caption>
						<tr>
							<th rowspan="4">1.주문자 정보</th>
							<td class="border-top">주문자명</td>
							<td class="border-top">${orderListBean.orderName}</td>
						</tr>
						<tr>
							<td>주문자 우편번호</td>
							<td>${orderListBean.orderZipcode}</td>
						</tr>
						<tr>
							<td>주문자 주소</td>
							<td>${orderListBean.orderAddress}</td>
						</tr>
						<tr>
							<td class="border-bottom">주문자 연락처</td>
							<td class="border-bottom">${orderListBean.orderTel}</td>
						</tr>
						<tr>
							<th rowspan="4">2.배송 정보</th>
							<td>수령인</td>
							<td>${orderListBean.addresseeName}</td>
						</tr>
						<tr>
							<td>수령인 우편번호</td>
							<td>${orderListBean.addresseeZipcode}</td>
						</tr>
						<tr>
							<td>수령인 주소</td>
							<td>${orderListBean.addresseeAddress}</td>
						</tr>
						<tr>
							<td class="border-bottom">수령인 연락처</td>
							<td class="border-bottom">${orderListBean.addresseeTel}</td>
						</tr>
						<tr>
							<th>3.결제금액</th>
							<td colspan="2" class="border-bottom">${orderListBean.totalPrice}원</td>
						</tr>
						<tr>
							<th>4.결제수단</th>
							<td colspan="2" class="border-bottom">${orderListBean.paystate}</td>
						</tr>

					</table>
				</div>
				<br />



				<div class="layer_submit">
					<button id="btn_back" class="myButton">이전으로</button>
					<button id="btn_update" class="myButton">수정</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>