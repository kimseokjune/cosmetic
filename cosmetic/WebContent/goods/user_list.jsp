<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.2.1.min.js"></script>
<style>
	div,ul,li,a,img,select,option,h3,p{
		margin:0; 
		padding:0;
	}
	ul{
		list-style:none;
	}
	#user_list_wrap {
		width:1200px;
	}
	#user_list_conleft{
		float:left;
		width:200px;
	}
	#user_list_conright{
		float:left;
		width:990px;
	}
	#search{
		clear:both;
		width:990px;
		float:right;
	}
	#shop_title{
		margin-top:15px;
	}
	#navi_menu{
		width:200px;
		height:250px;
	}
	#navi_menu ul{
		width:130px;
		height:210px;
	}
	#navi_menu ul li{
		width:130px;
		height:30px;
	}
	#navi_align{
		width:990px;
	}
	#align_left{
		float:left;		
	}
	#align_right{
		float:right;
	}
	#align_right li{
		float:left;
	}
	.banner1{
		margin-top:30px;
	}
	#cate_title{
		margin-top:25px;
		font-size:15px;
	}
	#detail{
		float:left;
		width:180px;
		height:230px;
		margin:10px 30px;
	}
	#detail img{
		width:140px;
		margin:0px 20px;
	}
	#detail p{
		font-size:12px;
		text-align:center;
		line-height:1.5em;
	}
	#bold{
		font-weight:bold;
		font-size:14px;
	}
	#bold_line{
		font-weight:bold;
		text-decoration:line-through;
	}		
	#red{
		color:red;
		font-weight:bold;
		font-size:14px;
	}
	#con_page{
		width:990px;
		clear:both;
	}
	#conpage_align{
		width:990px;
		text-align:center;
	}
	#user_list_page_wrap{
		clear:both;
		margin: 0 auto;
		text-align: center;
	}
	#user_list_page{
		align: center;
	}
	
</style>
 <script>

 </script>
</head>
<body>
<div id="user_list_wrap">
	<div id="user_list_conleft">
		<div id="shop_title">
			<img src="image/shop_title.gif"/>
		</div>
		<div id="navi_menu">
			<ul>
<!-- 			<a href="링크걸주소"><img src="기본이미지주소" onmouseover="this.src='마우스올렸을때 바뀔이미지주소'" onmouseout="this.src='기본이미지주소'" border="0"></a> -->
				<li><a href="../goods/user_list.do?cate=cle&sort=sale&method=asc"><img src="image/002.gif" onmouseover="this.src='image/002_over.gif'" onmouseout="this.src='image/002.gif'" /></a></li>
				<li><a href="../goods/user_list.do?cate=pee&sort=sale&method=asc"><img src="image/003.gif" onmouseover="this.src='image/003_over.gif'" onmouseout="this.src='image/003.gif'" /></a></li>
				<li><a href="../goods/user_list.do?cate=red&sort=sale&method=asc"><img src="image/004.gif" onmouseover="this.src='image/004_over.gif'" onmouseout="this.src='image/004.gif'" /></a></li>
				<li><a href="../goods/user_list.do?cate=cel&sort=sale&method=asc"><img src="image/005.gif" onmouseover="this.src='image/005_over.gif'" onmouseout="this.src='image/005.gif'" /></a></li>
				<li><a href="../goods/user_list.do?cate=ref&sort=sale&method=asc"><img src="image/006.gif" onmouseover="this.src='image/006_over.gif'" onmouseout="this.src='image/006.gif'" /></a></li>
				<li><a href="../goods/user_list.do?cate=sen&sort=sale&method=asc"><img src="image/007.gif" onmouseover="this.src='image/007_over.gif'" onmouseout="this.src='image/007.gif'" /></a></li>
				<li><a href="../goods/user_list.do?cate=dis&sort=sale&method=asc"><img src="image/008.gif" onmouseover="this.src='image/008_over.gif'" onmouseout="this.src='image/008.gif'" /></a></li>
			</ul>
		</div>
		<div id="ban_center" class="banner1">
			<img src="image/ban_center.gif"/>
		</div>
		<div id="ban_bank" class="banner1">
			<img src="image/ban_bank.gif"/>
		</div>
		<div>
			<select name="" id="" onchange="if(this.value) window.open(this.value);" style="width:170px;margin:10px 0px 30px 0px">
				<option selected value="" ><span id="bank_bold">은행 바로가기</span></option>
		        <option value="http://www.wooribank.com" >우리은행</option>
		        <option value="http://www.ibk.co.kr" >기업은행</option>
		        <option value="http://www.kbstar.com" >국민은행</option>  
		        <option value="http://www.hanabank.com" >하나은행</option> 
		        <option value="http://www.shinhan.com" >신한은행</option>
		        <option value="http://www.nonghyup.com">농협	&nbsp;</option>
		        <option value="http://www.keb.co.kr" >외환은행</option> 
		        <option value="http://www.citibank.co.kr">시티은행</option> 
		        <option value="http://www.epostbank.go.kr">우체국</option>
    		</select>
		</div>
	</div>
	<div id="user_list_conright">
		<p id="cate_title"><a href="../goods/user_list.do?cate=${cate}">
		<c:choose>
			<c:when test="${cate == 'cle'}">CLEANSING</c:when>
			<c:when test="${cate == 'pee'}">PEEL &amp; WHITE</c:when>
			<c:when test="${cate == 'red'}">REDOX</c:when>
			<c:when test="${cate == 'cel'}">CELL GUARD</c:when>
			<c:when test="${cate == 'ref'}">REFINING</c:when>
			<c:when test="${cate == 'sen'}">SENSITIVE</c:when>
			<c:when test="${cate == 'dis'}">DISCOUNT</c:when>
		</c:choose>
		</a></p>
		<hr />
		<div id="navi_align">
			<p id="align_left">전체 ${data.totalRow}개의 상품이 있습니다.</p>
			<ul id="align_right">
				<li><a href="../goods/user_list.do?cate=${cate}&sort=sale&method=asc">낮은가격 |</a></li>
				<li><a href="../goods/user_list.do?cate=${cate}&sort=sale&method=desc"> 높은가격 |</a></li>
				<li><a href="../goods/user_list.do?cate=${cate}&sort=name&method=asc"> 상품명 |</a></li>
				<li><a href="../goods/user_list.do?cate=${cate}&sort=product&method=asc"> 제조사 </a></li>
				<li>
					<select name="" id="">
				        <option value="" >8개씩 정렬</option>
				        <option value="" >12개씩 정렬</option>
				        <option value="" >24개씩 정렬</option>
				        <option value="" >36개씩 정렬</option> 
		    		</select>
				</li>
			</ul><br/>
		</div><br />
		<hr />
		<div id="detail_goods">
			<c:forEach var="bean" items="${data.list}">
				<div id="detail">
					<a href="../goods/user_view.do?gcode=${bean.gcode}"><img src="../goods/gimage/${bean.gimage1}" /></a><br/>
					<p>${bean.name}</p>
					<c:choose>
						<c:when test="${ bean.price == bean.salePrice}">
							<p id="bold">${bean.price}원</p>
						</c:when>
						<c:otherwise>
							<p id="bold_line">${bean.price}원</p>
							<p id="red">${bean.salePrice}원(${bean.disRate}% 할인)</p>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
		
		<!-- 페이징처리시작 -->
		<div id="user_list_page_wrap">
			<div id="user_list_page">
				<a href="user_list.do?cate=${cate}&page=1&sort=${sort}&method=${method}">[처음]</a>
				<c:if test="${data.startPage > 1 }">
					<a
						href="user_list.do?cate=${cate}&page=${data.page-data.pagePerGroup}&sort=${sort}&method=${method}">&lt;&lt;</a>
				</c:if>
				<!-- 			이전페이지 이동 -->
				<c:if test="${data.page != 1 }">
					<a
						href="user_list.do?cate=${cate}&page=${data.page-1 }&sort=${sort}&method=${method}">&lt;</a>
				</c:if>

				<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 시작 -->
				<c:forEach var="page" begin="${data.startPage}"
					end="${data.endPage }">
					<c:choose>
						<c:when test="${data.page == page }">
							<b>[${page}]</b>
						</c:when>
						<c:otherwise>
							<a
								href="user_list.do?cate=${cate}&page=${page}&sort=${sort}&method=${method}">[${page}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

				<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
				<c:if test="${data.page != data.totalPage }">
					<a
						href="user_list.do?cate=${cate}&page=${data.page + 1}&sort=${sort}&method=${method}">&gt;</a>
				</c:if>
				<c:if test="${data.endPage<data.totalPage}">
					<a
						href="user_list.do?cate=${cate}&page=${data.page+data.pagePerGroup }&sort=${sort}&method=${method}">&gt;&gt;</a>
				</c:if>
				<a
					href="user_list.do?cate=${cate}&page=${data.totalPage}&sort=${sort}&method=${method}">[끝]</a>
			</div>
		<!-- 페이징처리끝 -->		
		</div>
	</div>
</div>
</body>
</html>