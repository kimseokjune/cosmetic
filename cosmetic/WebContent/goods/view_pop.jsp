<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>goods view</title>
<style>
html,body,div,ul,ol,li,h1,h2,h3,h4,h5,h6,form,input,textarea,p,th,td,img,a { margin:0; padding:0; }
img { border:none; vertical-align:top; }
li { list-style:none; }
a { text-decoration:none; color:#000; }
a:hover { text-decoration:underline; }
a:active { text-decoration:none; }
body{
	margin:0 auto;
	width:1000px;
}
section{
	width:1000px;
}
#left{ 
	float:left; 
	width:40%;
	height:400px;
}
#right{ 
	float:left;
	width:60%;
}

</style>
<script>

</script>
</head>
<body>
<h3><a href="#">cleansing</a></h3>
<hr />
<section>
<div id="left">
<img src="../image/cleansing_01.jpg"/>
<img src="../image/btn_zoom.gif"/>
</div>
<div id="right">
	<div>[비욘] 클렌징 밀크_200mg</div>
	<div>판매가격 : 66.000원</div>
	<div>적립금 : 600원</div>
	<div>
		구매수량 :
		<input type="number" id="purchCount" min="1" value="1" length="20"/> 개
	</div>
	<div><img src="../image/shoppingguide.gif"/></div>
	<div>
		<a href="#"><img src="../image/btn_buy.gif" /></a>
		<a href="#"><img src="../image/btn_cart.gif" /></a>
		<a href="#"><img src="../image/btn_wish.gif" /></a>
	</div>
</div>
<div id="detail">
	<ul>
		<li><img src="../image/title_detail_01.gif"/></li>
		<li><img src="../image/title_detail_02.gif"/></li>
		<li><img src="../image/title_detail_03.gif"/></li>
		<li><img src="../image/title_detail_04.gif"/></li>
	</ul>
	<img src="../image/title_detail.gif"/>
</div>
<div>
	<img src="../image/detail_01.jpg"/>
	<img src="../image/cleansing_detail_02.jpg"/>
	<img src="../image/cleansing_detail_03.jpg"/>
	<img src="../image/detail_04.jpg"/>
</div>
<div>
상품후기 정보
</div>
<div>
상품 Q&A
</div>
<div>
배송/반품/교환 정보
</div>
</section>
</body>
</html>