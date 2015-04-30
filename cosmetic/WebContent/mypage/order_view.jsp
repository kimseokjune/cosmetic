<%@page import="com.vyon.order.model.OrderDetailBean"%>
<%@page import="java.util.List"%>
<%@page import="com.vyon.order.model.OrderListBean"%>
<%@ page language="java" contentType="text/html; charset=uft-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// ���� ��ư�� ������ ��
						$("#btn_back").click(function() {
							var url = "../order/customerList.do";
							$("#orderForm").attr("action", url);
						});

						//��ü ����	
						$("#btn_check_all").click(function() {
							$("input[name=check]:checkbox").each(function() {
								console.log("btn_check_all");
								$(this).attr("checked", true);
							});
						});

						// ��ü ���� ����
						$("#btn_uncheck_all").click(function() {
							$("input[name=check]:checkbox").each(function() {
								console.log("btn_uncheck_all");
								$(this).attr("checked", false);
							});
						});

						// ���� ��ư�� ������ ��
						$("#btn_update")
								.click(
										function() {
											var orderNoList = new Array();

											$("input[name=check]:checkbox")
													.each(
															function() {
																if ($(this)
																		.is(
																				':checked')) {
																	orderNoList
																			.push($(
																					this)
																					.val());
																}
															});

											if (orderNoList.length == 0) {
												alert("������ �ֹ��� �����ϼ���.");
											} else {
												// �䷱������ �ѱ�°� �����ϱ� �մϴ�...
												var url = "";
												var orderNo = "${orderListBean.orderNo}";
												if ($("select[name=select]")
														.val() == "completeDelivery") {
													// ��ۿϷ�
													url = "../order/deliveryComplete.do?orderNo="
															+ orderNo;
												} else if ($(
														"select[name=select]")
														.val() == "exchangeRequest") {
													// ��ȯ ��û 
													url = "../order/exchangeRequest.do?orderNo="
															+ orderNo;
												} else if ($(
														"select[name=select]")
														.val() == "exchangeComplete") {
													// ��ȯ �Ϸ�
													url = "../order/exchangeComplete.do?orderNo="
															+ orderNo;
												} else if ($(
														"select[name=select]")
														.val() == "refundRequest") {
													// ��ǰ ��û
													url = "../order/refundRequest.do?orderNo="
															+ orderNo;
												} else if ($(
														"select[name=select]")
														.val() == "refundComplete") {
													// ��ǰ �Ϸ�
													url = "../order/refundComplete.do?orderNo="
															+ orderNo;
												} else if ($(
														"select[name=select]")
														.val() == "orderCancel") {
													// �ֹ����
													url = "../order/cancel.do?orderNo="
															+ orderNo;
												}

												$("#orderForm").attr("action",
														url);
											}
											;
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


#tr_height{height: 40px;}
#tr_td_align{text-align: right;}
</style>
</head>
<body>

	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/magnifier.png">&nbsp;&lt;�ֹ������󼼺���&gt;

		</div>
		<div class="layer_content">
			<form id="orderForm" action="#" method="post">
				<div>

					<table id="table" width="900">
						<caption>&lt;�ֹ��󼼳���&gt;</caption>
						<Tr id="tr_height">
							<td colspan="2"></td>
							<td colspan="6">�Ʒ����� ������ �ֹ����� <select name="select">
									<option value="orderCancel">�ֹ� ��� ó��</option>
									<option value="completeDelivery">��� �Ϸ� ó��</option>
									<option value="exchangeRequest">��ȯ ��û ó��</option>
									<option value="exchangeComplete">��ȯ �Ϸ� ó��</option>
									<option value="refundRequest">��ǰ ��û ó��</option>
									<option value="refundComplete">��ǰ �Ϸ� ó��</option>
							</select> �մϴ�. (���� �� �ϴ� ���� ��ư �� Ŭ��)
							</td>
							<td colspan="2" id="tr_td_align">
								<button id="btn_check_all" type="button">��ü ����</button>
								<button id="btn_uncheck_all" type="button">��ü ���</button>
							</td>
						</Tr>
						<tr>

							<th width="35" height="30">����</th>
							<th width="50">�̹���</th>
							<th>��ǰ����</th>
							<th width="65">�ǸŰ�</th>
							<th width="35">����</th>
							<th width="65">�Ѱ���</th>
							<th width="80">�ֹ�����</th>
							<th width="85">������¥</th>
							<th width="85">��۳�¥</th>
							<th width="85">��ۿϷᳯ¥</th>
						</tr>
						<c:forEach var="bean" items="${orderDetailList}">
							<tr id="${bean.no}" class="border-bottom">
								<td><input type="checkbox" name="check" value="${bean.no}"></td>
								<td><img src="../goods/gimage/${bean.imgName}" width="50"
									height="50"></td>
								<td>${bean.goodsName}</td>
								<td>${bean.price}��</td>
								<td>${bean.gcount}</td>
								<td>${bean.sumPrice}��</td>
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
						<caption>&lt;�ֹ��� �ۼ�&gt;</caption>
						<tr >
							<th rowspan="4">1.�ֹ��� ����</th>
							<td class="border-top">�ֹ��ڸ�</td>
							<td class="border-top" >${orderListBean.orderName}</td>
						</tr>
						<tr>
							<td>�ֹ��� �����ȣ</td>
							<td>${orderListBean.orderZipcode}</td>
						</tr>
						<tr>
							<td>�ֹ��� �ּ�</td>
							<td>${orderListBean.orderAddress}</td>
						</tr>
						<tr>
							<td class="border-bottom">�ֹ��� ����ó</td>
							<td class="border-bottom">${orderListBean.orderTel}</td>
						</tr>
						<tr>
							<th rowspan="4">2.��� ����</th>
							<td>������</td>
							<td>${orderListBean.addresseeName}</td>
						</tr>
						<tr>
							<td>������ �����ȣ</td>
							<td>${orderListBean.addresseeZipcode}</td>
						</tr>
						<tr>
							<td>������ �ּ�</td>
							<td>${orderListBean.addresseeAddress}</td>
						</tr>
						<tr>
							<td class="border-bottom">������ ����ó</td>
							<td class="border-bottom">${orderListBean.addresseeTel}</td>
						</tr>
						<tr>
							<th>3.�����ݾ�</th>
							<td colspan="2" class="border-bottom">${orderListBean.totalPrice}��</td>
						</tr>
						<tr>
							<th>4.��������</th>
							<td colspan="2" class="border-bottom">${orderListBean.paystate}</td>
						</tr>

					</table>
				</div>
				<br />



				<div class="layer_submit">
					<button id="btn_back" class="myButton">��������</button>
					<button id="btn_update" class="myButton">����</button>
				</div>
			</form>
		</div>

	</div>
<br><Br>
</body>
</html>