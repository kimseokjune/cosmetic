<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String) session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
<title>goods view</title>
<style>
	#user_view_wrap{
		width:1200px;
		margin:0 auto;
	}
	#user_view_top_wrap{
		width:1200px;
		height:400px;
	}
	#user_view_left{ 
		float:left; 
		width:400px;
	}
	#user_view_image{
		width:400px;
	}
	#image_btn_zoom{
		margin-left:150px;
	}
	#user_view_right{ 
		float:left;
		width:680px;
		margin:20px 50px;
	}
	#user_view_text{
		width:400px;
		margin:15px 50px;
	}
	#user_view_name{
		fontsize:16px;
		font-weight:bold;
	}
	#purchCount{
		width:50px;
	}
	#user_view_guide{
		margin-left:50px;
		margin-top:20px;
		margin-bottom:20px;
	}
	#user_view_btn{
		margin:20px 50px;
	}
	#user_view_detail{
		clear:both;
	}
	#user_view_detail ul{
		list-style:none;
	}
	#user_view_detail ul li{
		float:left;
	}
	#user_view_detail_image{
		clear:both;
		width:1200px;
	}
	#span_color_weight{
		color:red;
		font-weight: bold;
	}

</style>
<script type="text/javascript">
	$(document).ready(function(){
		// 장바구니 링크 초기화
		var gcount = $("#purchCount").val();
		var gcode = window.location.search.split('=');
		var url = "";
		$("#btn_cart").attr("href","#");
		$("#btn_buy").attr("href","#");
		
		// number 값 변경할 때마다 링크 주소 변경
		$("#purchCount").bind("click keyup", function(e){
			gcount = parseInt($("#purchCount").val());
			var maxCount = parseInt($("#purchCount").attr("max"));
			
			// 최대값을 넘겼을 경우
			if(gcount>maxCount){
				alert("최대 " + maxCount + "개수 까지만 구매 가능 합니다.");
				$("#purchCount").attr("value", maxCount);
				gcount = maxCount;
			}
			
			console.log(gcount);			
			console.log(gcode[1]);
			$("#btn_cart").attr("href", "../cart/insert.do?gcode="+gcode[1]+"&gcount="+gcount);
			$("#btn_buy").attr("href", "../order/payment.do?gcode="+gcode[1]+"&gcount="+gcount+"&prevPage=goodsView");
		});
		
		// 품절된 상품을 카드에 담으려고 할 때
		$("#btn_cart").click(function(){
			var count = ${goods.count};
			if(count == 0){
				alert("품절된 상품 입니다.");
				$("#btn_cart").attr("href", "#");
			}else{
				var id = $("#id").val();
				if(id != ""){
					$("#btn_cart").attr("href", "../cart/insert.do?gcode="+gcode[1]+"&gcount="+gcount);	
				}else{
					alert("로그인을 해주세요.");
					$("#btn_cart").attr("href", "#");
				}
				
			}
		});
		
		// 품절된 상품을 구매하려고 할떄
		$("#btn_buy").click(function(){
			var count = ${goods.count};
			if(count == 0){
				alert("품절된 상품 입니다.");
				$("#btn_cart").attr("href", "#");	
			}else{
				var id = $("#id").val();
				if(id != ""){
					$("#btn_buy").attr("href", "../order/payment.do?gcode="+gcode[1]+"&gcount="+gcount+"&prevPage=goodsView");
				}else{
					alert("로그인을 해주세요.");	
					$("#btn_cart").attr("href", "#");
				}
			}
		});
	});
	
// 	팝업이미지
	function popimage(imagesrc,winwidth,winheight){
		 var look='width='+winwidth+',height='+winheight+','
		  popwin=window.open("","",look)
		  popwin.document.open()
		  popwin.document.write('<title>Image Window</title><body topmargin=100 leftmargin=100><img style=cursor:hand; onclick="self.close()" src="'+imagesrc+'"></body>')
		  popwin.document.close()
	}	
	
</script>
</head>
<body>	
	<div id="user_view_wrap">
		<h3><a href="#">cleansing</a></h3>
		<hr />
		<div id="user_view_top_wrap">
			<div id="user_view_left">
				<img src="../goods/gimage/${isM}" id="user_view_image"/>
				<a href="#" onclick="popimage('../goods/gimage/${isB}',700,700);return false"><img src="image/btn_zoom.gif" id="image_btn_zoom"/></a>
			</div>
			<div id="user_view_right">
	<!-- 		gcode값과 gcount 값을 2개를 넘겨야해서 form tag를 사용합니다. -->
			<form action="#" method="post">
					<div id="user_view_text">
						<input id="id" type="text" hidden="hideen" value="${id}">
						<p id="user_view_name">${goods.name}</p>
						<p>판매가격 : ${goods.price}원</p>
						<p>할인가격 : ${goods.salePrice}원(${goods.disRate}% 할인)</p>
						<p>구매수량 :
						<c:choose>
							<c:when test = "${goods.count > 0}">
								<input type="number" id="purchCount" min="1" max="${goods.count}" value="1"/> 개
							</c:when>
							<c:otherwise>
								<span id="span_color_weight">품절</span>
							</c:otherwise>
						</c:choose>
						</p>
					</div>
					<div id="user_view_guide">
						<img src="image/shoppingguide.gif"/>
					</div>
					<div id="user_view_btn">
					<!--input type number에 있는 값과 gcode 값을 한꺼번에 넘겨줘야해서 수정했습니다. -->
						<a id="btn_buy"><img src="image/btn_buy.gif" /></a>
						<a id="btn_cart"><img src="image/btn_cart.gif" /></a>
						<a href="#"><img src="image/btn_wish.gif" /></a>
					</div>
			</form>
			</div>
		</div><br/><br>
		<hr />
		<div id="user_view_detail">
			<ul>
				<li><img src="image/title_detail_01.gif"/></li>
				<li><img src="image/title_detail_02.gif"/></li>
				<li><img src="image/title_detail_03.gif"/></li>
				<li><img src="image/title_detail_04.gif"/></li>
			</ul>
		</div>
		<div id="user_view_detail_image">
			<img src="../goods/gimage/${isD}" />	
		</div>
	</div>
</body>
</html>