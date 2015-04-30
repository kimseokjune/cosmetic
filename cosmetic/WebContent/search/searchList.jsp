<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.2.1.min.js"></script>
<style>
/* 	div,ul,li,a,img,select,option,h3,p{  */
/* 		margin:0;   */
/* 		padding:0;  */
/*  	}  */
ul {
	list-style: none;
}

#cwrap {
	width: 1200px;
	margin: 0 auto;
}

#conleft {
	float: left;
	width: 200px;
	/* 		border:1px solid #f00; */
}

#conright {
	float: left;
	width: 990px;
	/* 		border:1px solid #f00; */
}

#search {
	clear: both;
	width: 990px;
	float: right;
}

#shop_title {
	margin-top: 15px;
}

#detail_goods {
	width: 990px;
}

#navi_menu {
	width: 200px;
	height: 250px;
	/* 		border:1px solid #f00; */
}

#navi_menu ul {
	width: 130px;
	height: 210px;
	/* 		border:1px solid #f00; */
}

#navi_menu ul li {
	width: 130px;
	height: 30px;
}

#navi_align {
	width: 990px;
}

#align_left {
	float: left;
	margin-top: 0px;
}

#align_right {
	float: right;
}

#align_right li {
	float: left;
}

.banner1 {
	margin-top: 30px;
}

#cat_title {
	margin-top: 25px;
	font-size: 20px;
}

#detail {
	float: left;
	width: 180px;
	height: 230px;
	margin: 10px 30px;
	/* 		border:1px solid #f00; */
}

#detail img {
	width: 140px;
	margin: 0px 20px;
	/* 		border:1px solid #f00; */
}

#detail p {
	font-size: 12px;
	text-align: center;
	line-height: 1.5em;
}

#bold {
	font-weight: bold;
	font-size: 14px;
}

#bold_line {
	font-weight: bold;
	text-decoration: line-through;
}

#red {
	color: red;
	font-weight: bold;
	font-size: 14px;
}

#con_page {
	width: 990px;
	clear: both;
}

#conpage_align {
	width: 990px;
	text-align: center;
}
</style>
<script>
	
</script>
</head>
<body>
	
		<div id="cwrap">
			<div id="conleft"></div>
			<div id="conright">

				<p id="cat_title">
					<a href="#">검색결과</a>
				</p>
				<hr />
				<div id="navi_align">
				
					<p id="align_left">전체 ${data.totalRow} 개의 상품이 있습니다.</p>
<c:choose>
<c:when test="${check2 == check3}">

					<ul id="align_right">
						<li><a
							href="../search/search6Process.do?sort=sale&method=asc">낮은가격
								|</a></li>
						<li><a
							href="../search/search6Process.do?sort=sale&method=desc">높은가격
								|</a></li>
						<li><a
							href="../search/search6Process.do?sort=name&method=asc">상품명 |</a></li>
						<li><a
							href="../search/search6Process.do?sort=product&method=asc">제조사
						</a></li>
						<li>
						<c:choose>
						<c:when test="${row2 == row3 }">
						<form method="get" name="form2">
						<select name="row"
							onchange="location.href='../search/search10Process.do?row='+document.form2.row.value">
								<option value="8">8개씩 정렬</option>
								<option value="12">12개씩 정렬</option>
								<option value="24">24개씩 정렬</option>
								<option value="36">36개씩 정렬</option>
						</select>
						</form>
						</c:when>
						
						<c:otherwise>
						<form method="get" name="form2">
						<select name="row"
							onchange="location.href='../search/search9Process.do?row='+document.form2.row.value">
								<option value="8">8개씩 정렬</option>
								<option value="12">12개씩 정렬</option>
								<option value="24">24개씩 정렬</option>
								<option value="36">36개씩 정렬</option>
						</select>
						</form>
						</c:otherwise>
						</c:choose>
						
						</li>
						
					</ul>
					</c:when>
					<c:otherwise>
					<ul id="align_right">
						<li><a
							href="../search/search4Process.do?sort=sale&method=asc">낮은가격
								|</a></li>
						<li><a
							href="../search/search4Process.do?sort=sale&method=desc">높은가격
								|</a></li>
						<li><a
							href="../search/search4Process.do?sort=name&method=asc">상품명 |</a></li>
						<li><a
							href="../search/search4Process.do?sort=product&method=asc">제조사
						</a></li>
						<li>
						<c:choose>
						<c:when test="${row2 == row4 }">
						<form method="get" name="form3">
						<select name="row"
							onchange="location.href='../search/search11Process.do?row='+document.form3.row.value">
								<option value="8">8개씩 정렬</option>
								<option value="12">12개씩 정렬</option>
								<option value="24">24개씩 정렬</option>
								<option value="36">36개씩 정렬</option>
						</select>
						</form>
						</c:when>
						<c:otherwise>
						<form method="get" name="form4">
						<select name="row"
							onchange="location.href='../search/search12Process.do?row='+document.form4.row.value">
								<option value="8">8개씩 정렬</option>
								<option value="12">12개씩 정렬</option>
								<option value="24">24개씩 정렬</option>
								<option value="36">36개씩 정렬</option>
						</select>
						</form>
						</c:otherwise>
						</c:choose>
						</li>
						
					</ul>
					</c:otherwise>
					</c:choose>
					<br />
					<div id="space">
						<div></div>
					</div>
					<form action="../search/search2Process.do" method="post" name="form">
					<ul id="search">

						<li><select id="class" name="select">
								<option value="search2" selected>통합검색</option>
								<option value="name">제품명</option>
								<option value="gcode">상품코드</option>
								<option value="product">제조사</option>

						</select> <input type="text" name="search" /><input type="submit"
							value="검색" /></li>
							
					</ul>
					</form>
				</div>
				<br />
				<hr />
				<%-- 		<c:forEach var="bean" items="${data.list}"> --%>
				<%-- 		</c:forEach> --%>
				<div id="detail_goods">

					<c:choose>
						<c:when test="${data.totalRow == 0}">
							<p>해당 단어로 검색이 되지 않습니다.</p>
						</c:when>

					</c:choose>
					<c:forEach var="bean" items="${data.list}">
						<div id="detail">

							<a href="../goods/user_view.do?gcode=${bean.gcode}"><img
								src="../goods/gimage/${bean.gimage1}" /></a><br />
							<p>${bean.name}</p>
							<c:choose>
								<c:when test="${ bean.price == bean.salePrice}">
									<p id="bold">${bean.price}원</p>
								</c:when>
								<c:otherwise>
									<p id="bold_line">${bean.price}원</p>
									<p id="red">${bean.salePrice}원(${bean.disRate}%할인)</p>
								</c:otherwise>
							</c:choose>
						</div>


					</c:forEach>
				</div>

				<div id="con_page">
					<div id="conpage_align">
						&nbsp;
						<c:choose>
							<c:when test="${check == check5}">
								<a href="search3Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search3Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<!-- 			이전페이지 이동 -->
								<c:if test="${data.page != 1 }">
									<a href="search3Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search3Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${data.page != data.totalPage }">
									<a href="search3Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search3Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search3Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
				
				</c:when>
							<c:when test="${check4 == check5 }">
								<a href="search5Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search5Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<c:if test="${data.page != 1 }">
									<a href="search5Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search5Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${data.page != data.totalPage }">
									<a href="search5Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search5Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search5Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			
				</c:when>
					<c:when test="${check7 == check5 }">
								<a href="search7Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search7Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<c:if test="${data.page != 1 }">
									<a href="search7Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search7Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${data.page != data.totalPage }">
									<a href="search7Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search7Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search7Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			
				</c:when>
			<c:when test="${check8 == check5 }">
				<a href="search13Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search13Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<!-- 			이전페이지 이동 -->
								<c:if test="${data.page != 1 }">
									<a href="search13Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search13Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

								<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
								<c:if test="${data.page != data.totalPage }">
									<a href="search13Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search13Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search13Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			</c:when>
			<c:when test="${check9 == check5 }">
				<a href="search14Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search14Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<!-- 			이전페이지 이동 -->
								<c:if test="${data.page != 1 }">
									<a href="search14Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search14Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

								<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
								<c:if test="${data.page != data.totalPage }">
									<a href="search14Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search14Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search14Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			</c:when>
			<c:when test="${check10 == check5 }">
				<a href="search15Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search15Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<!-- 			이전페이지 이동 -->
								<c:if test="${data.page != 1 }">
									<a href="search15Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search15Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

								<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
								<c:if test="${data.page != data.totalPage }">
									<a href="search15Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search15Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search15Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			</c:when>
			<c:when test="${check11 == check5 }">
				<a href="search16Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search16Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<!-- 			이전페이지 이동 -->
								<c:if test="${data.page != 1 }">
									<a href="search16Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search16Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

								<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
								<c:if test="${data.page != data.totalPage }">
									<a href="search16Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search16Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search16Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			</c:when>
				<c:otherwise>
				<a href="search8Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
									<a
										href="search8Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
								<!-- 			이전페이지 이동 -->
								<c:if test="${data.page != 1 }">
									<a href="search8Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
								<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
								<c:forEach var="page" begin="${data.startPage}"
									end="${data.endPage }">
									<c:choose>
										<c:when test="${data.page == page }">
											<b>[${page }]</b>
										</c:when>
										<c:otherwise>
											<a href="search8Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

								<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
								<c:if test="${data.page != data.totalPage }">
									<a href="search8Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
								<c:if test="${data.endPage<data.totalPage}">
									<a
										href="search8Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
								<a href="search8Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
			
				
				</c:otherwise>

						</c:choose>
					</div>
				</div>
			</div>
		</div>
	
</body>
</html>