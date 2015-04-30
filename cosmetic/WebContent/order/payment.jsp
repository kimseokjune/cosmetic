<%@page import="java.util.List"%>
<%@page import="com.vyon.cart.model.CartDisplayBean"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.vyon.order.model.PaymentDisplayInfo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {

				// 결제 버튼 선택시
				$("form[id=payForm]").submit(function() {
							var condition = false;
							
							if ($("#senderName").val() == "") {
								alert("주문자 명을 입력하세요.");								
							} else if ($("#senderZipcode1").val() == ""
									|| $("#senderZipcode2").val() == "") {
								alert("주문자의 우편번호를 입력하세요.");
							} else if ($("#senderAddr").val() == ""
									|| $("#senderdAddr").val() == "") {
								alert("주문자의 주소를 입력하세요.");
							} else if ($("#senderTel1").val() == ""
									|| $("#senderTel2").val() == ""
									|| $("#senderTel3").val() == "") {
								alert("주문자의 전화번호를 입력하세요.");
							} else if ($("#senderEmail").val() == "") {
								alert("주문자의 이메일을 입력하세요.");
							} else if ($("#recieverName").val() == "") {
								alert("수령자 명을 입력하세요.");
							} else if ($("#recieverZipcode1").val() == ""
									|| $("#recieverZipcode2").val() == "") {
								alert("수령자의 우편번호를 입력하세요.");
							} else if ($("#recieverAddr").val() == ""
									|| $("#recieverdAddr").val() == "") {
								alert("수령자의 주소를 입력하세요.");
							} else if ($("#recieverTel1").val() == ""
									|| $("#recieverTel2").val() == ""
									|| $("#recieverTel3").val() == "") {
								alert("수령자의 전화번호를 입력하세요.");
							} else {
								condition = true;
							}
							
							if(condition){
								$("form[id=payForm]").attr("action","../order/paymentProcess.do");
							}else{
								return false;
							}
						});

				// 취소 버튼 선택시
				$("#btn_payment_cancel").click(function() {
					$("#payForm").attr("action", "javascript:history.back()")
				});

				// 주문 고객 정보와 동일을 눌렀을 때
				$("#check").click(
						function() {
							if ($("#check").attr("checked") == "checked") {
								$("#recieverName").attr("value",
										$("#senderName").val());
								$("#recieverZipcode1").attr("value",
										$("#senderZipcode1").val());
								$("#recieverZipcode2").attr("value",
										$("#senderZipcode2").val());
								$("#recieverAddr").attr("value",
										$("#senderAddr").val());
								$("#recieverdAddr").attr("value",
										$("#senderdAddr").val());
								$("#recieverTel1").attr("value",
										$("#senderTel1").val());
								$("#recieverTel2").attr("value",
										$("#senderTel2").val());
								$("#recieverTel3").attr("value",
										$("#senderTel3").val());
							} else {
								$("#recieverName").attr("value", "");
								$("#recieverZipcode1").attr("value", "");
								$("#recieverZipcode2").attr("value", "");
								$("#recieverAddr").attr("value", "");
								$("#recieverdAddr").attr("value", "");
								$("#recieverTel1").attr("value", "");
								$("#recieverTel2").attr("value", "");
								$("#recieverTel3").attr("value", "");
							}
						});
			});
	function zipfind(people) {
		var prevPage = "";
		if (people == "sender") {
			prevPage = "sender";
		} else if (people = "reciever") {
			prevPage = "reciever";
		}

		var url = "zip.jsp?prevPage=" + prevPage;
		console.log(url);
		window
				.open(
						url,
						"우편번호",
						"scrollbars=yes, width=450, height=300, resizable=yes, menubar=no, top=150, left=260");
		return true;
	};
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
	font-weight: bold;
	text-align: left;
	padding-bottom: 5px;
}

#order_table th {
	height: 25px;
}

.text_align_left {
	text-align: left;
}

.border-bottom {
	border-bottom: 1px solid #999
}

.border-top {
	border-top: 1px solid #999
}

.button_padding {
	padding-top: 6px;
	text-align: right;
}
</style>
</head>
<body>

	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/magnifier.png">&nbsp;&lt;주문하기&gt;

		</div>

		<c:choose>
			<c:when test="${pdi.selectedCartDisplayBean.list.size() == 0}">
				<div class="layer_content">구매할 수 있는 상품이 없습니다. 수량을 확인하세요.</div>
			</c:when>
			<c:otherwise>
				<div class="layer_content">
					<br>
					<table id="order_table">
						<caption>주문상세내역</caption>
						<tr>
							<th>상품이미지</th>
							<th>상품정보</th>
							<th>판매가</th>
							<th>수량</th>
							<th>합계</th>
						</tr>
						<c:forEach var="bean" items="${pdi.selectedCartDisplayBean.list}">
							<tr id="${bean.cartNo}">
								<td><a href="../goods/user_view.do?gcode=${bean.gcode}">
										<img src="../goods/gimage/${bean.imgName}" width="100"
										height="100">
								</a></td>
								<td><a href="../goods/user_view.do?gcode=${bean.gcode}">${bean.goodsName}</a></td>
								<td>${bean.price}원</td>
								<td>${bean.gcount}개<br>
								<td>${bean.sumPrice}원</td>
							</tr>
						</c:forEach>
					</table>
					<c:choose>
						<c:when test="${pdi.insufficientListBean.list.size() == 0}">
							<span style="font-weight: bold">장바구니에 담긴 모든 상품을 구매할 수
								있습니다.</span>
						</c:when>
						<c:otherwise>
							<span style="color: red; font-weight: bold"> <c:forEach
									var="bean" items="${pdi.insufficientListBean.list}">
							${bean.goodsName},
						</c:forEach> 는 수량이 부족해서 구매할 수 없습니다.
							</span>
						</c:otherwise>
					</c:choose>



					<form id="payForm" name="payForm" action="#" method="post">

						<table>
							<caption>주문서작성</caption>
							<tr>
								<th rowspan="5" height="150px">1.주문자 정보</th>
								<td class="border-top">주문하시는 분</td>
								<td class="text_align_left border-top"><input
									id="senderName" name="sn" type="hidden"
									value="${pdi.memberBean.name}" />${pdi.memberBean.name}</td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td class="text_align_left"><input type="hidden" size="4"
									id="senderZipcode1" name="sadno1" value="${zip1}" />${zip1}-<input
									type="hidden" size="4" id="senderZipcode2" name="sadno2"
									value="${zip2}" />${zip2}&nbsp;</td>
							</tr>
							<tr>
								<td>주소</td>
								<td class="text_align_left"><input type="hidden"
									id="senderAddr" name="sad1" value="${pdi.memberBean.address}" />${pdi.memberBean.address}&nbsp;<input
									type="hidden" id="senderdAddr" name="sad2"
									value="${pdi.memberBean.dAddress}" />${pdi.memberBean.dAddress}
								</td>
							</tr>
							<tr>
								<td>연락처</td>
								<td class="text_align_left"><input id="senderTel1"
									name="st1" type="hidden" style="width: 50px;" value="${tel[0]}" />${tel[0]}-<input
									id="senderTel2" name="st2" type="hidden" style="width: 50px;"
									value="${tel[1]}" />${tel[1]}-<input id="senderTel3"
									name="st3" type="hidden" style="width: 50px;" value="${tel[2]}" />${tel[2]}</td>
							</tr>
							<tr>
								<td>이메일</td>
								<td class="text_align_left"><input id="senderEmail"
									name="se" type="hidden" value="${pdi.memberBean.email}" />${pdi.memberBean.email}</td>
							</tr>
							<tr>
								<th rowspan="7" height="190px">2. 배송정보 정보</th>
								<td class="border-top">배송 확인</td>
								<td class="text_align_left border-top"><input id="check"
									type="checkbox" /> 주문고객 정보와 동일합니다</td>
							</tr>
							<tr>
								<td>받으실 분</td>
								<td class="text_align_left"><input id="recieverName"
									name="rn" type="text" /></td>
							</tr>
							<tr>
								<td colspan="2">받으실 곳</td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td class="text_align_left"><input type="text" size="4"
									id="recieverZipcode1" name="radno1" />-<input type="text"
									size="4" id="recieverZipcode2" name="radno2" />&nbsp;<input
									type="button" value="우편번호검색" onclick="zipfind('reciever')" /></td>
							</tr>
							<tr>
								<td>주소</td>
								<td class="text_align_left"><input type="text"
									id="recieverAddr" name="rad1" />&nbsp;<input type="text"
									id="recieverdAddr" name="rad2" /> <br /></td>
							</tr>
							<tr>
								<td>연락처</td>
								<td class="text_align_left"><input id="recieverTel1"
									name="rt1" type="text" style="width: 50px;">-<input
									id="recieverTel2" name="rt2" type="text" style="width: 50px;">-<input
									id="recieverTel3" name="rt3" type="text" style="width: 50px;"></td>
							</tr>
							<tr>
								<td>남기실 말씀</td>
								<td class="text_align_left"><input type="text" /></td>
							</tr>
							<tr>
								<th rowspan="2" height="70px">3.결제금액</th>
								<td class="border-top">배송비</td>
								<td class="text_align_left border-top">${pdi.selectedCartDisplayBean.shipping} 원 <br/> 5만원 이상 구매시 무료 </td>
							</tr>
							<tr>
								<td>총 결제금액</td>
								<td class="text_align_left">${pdi.selectedCartDisplayBean.totalPrice}원</td>
							</tr>
							<tr>
								<th height="70px" class="border-top">4.결제수단</th>
								<td colspan="2" class="text_align_left border-top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="radio" name="payment" checked="checked" value="1" />무통장입금
									<input type="radio" name="payment" value="2" />신용카드 <input
									type="radio" name="payment" value="3" />계좌이체 <input
									type="radio" name="payment" />가상계좌
								</td>
							</tr>
							<Tr>
								<td colspan="3" class="button_padding"><input
									id="btn_payment_complete" type="submit" value="결제하기"
									class="myButton"> <input id="btn_payment_cancel"
									type="submit" value="취소하기" class="myButton"></td>
							</Tr>
						</table>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>