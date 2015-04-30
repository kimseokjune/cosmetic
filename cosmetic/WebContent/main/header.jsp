<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 			세션에서 아이디와 비밀번호와 등급을 불러온다
	String id = (String) session.getAttribute("id");
	int grade = (Integer) session.getAttribute("grade");
%>
<!DOCTYPE html>

<link rel="stylesheet" href="../main/header_css/style.css">
<script src="../main/header_js/modernizr.js"></script>
<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<script src="../main/header_js/index.js"></script>
<link rel="stylesheet" href="../main/header_css/button.css">
<link rel="stylesheet" href="../main/header_css/login.css">
<link rel="stylesheet" href="../main/header_css/header_default.css">
<link rel="stylesheet" href="../main/header_css/layer_box_effect.css">
<link rel="stylesheet" href="../main/header_css/layer_design.css">


<script>

	$(document).ready(function() {
		$("#search_form_button").click(function() {
			$('#search_form').submit();
		});
	});

	$(document).ready(function() {
		$('#login-trigger').click(function() {
			$(this).next('#login-content').slideToggle();
			$(this).toggleClass('active');

			if ($(this).hasClass('active'))
				$(this).find('span').html('&#x25B2;')
			else
				$(this).find('span').html('&#x25BC;')
		})
	});
</script>


<table id="header">
	<tr>
		<td rowspan="3" id="toplogo"><a href="../main/main.do"><span id ="logo">VYON</span><br><span id ="logo_bottom">visibly yonger</span></a></td>
		<td class="topmenu"><c:choose>

				<c:when test="${sessionScope.id == null }">
	
					<!--  로그인 메뉴 시작 -->

					<nav class="header_login">
						<ul>
							<li id="login"><a id="login-trigger" href="#"> 로그인 
							</a>
								<div id="login-content">
									<form action="../member/loginProcess.do">
										<fieldset id="inputs">
											<input id="username" type="text" name="id"
												placeholder="아이디" required> 
												<input
												id="password" type="password" name="pw"
												placeholder="비밀번호" required>
										</fieldset>
										<fieldset id="actions">
										<layer id="idpasswordlayer"> <a href="../member/serid.do">아이디찾기</a>  | <a href="../member/serpw.do">비밀번호찾기</a></layer>
										<layer> <input type="submit" id="submit" value="로그인"></layer>			
											 
										</fieldset>

									</form>
								</div>
							</li>
							<li id="signup"><a href="../member/member.do">회원가입</a></li>
						</ul>
					</nav>

					<!--  로그인 메뉴 끝-->


				</c:when>
				<c:otherwise>

					<b>${sessionScope.id}</b>님<br>
					<a href="../member/logout.do">로그아웃</a>
					<a href="../mypage/mypage.do">마이페이지</a>
					<a href="../cart/list.do">장바구니</a>

					<c:choose>
						<c:when test="${sessionScope.id.equals('admin') }">
							<a href="../admin/admin.do">관리자메뉴</a>
						</c:when>
					</c:choose>
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
	
	<!-- 검색 시작 -->
	
	
		<td class="topmenu">

			<form action="../search/searchProcess.do" method="post"
				name="search_form" id="search_form">

				<input type="text" name="search" class="input_text_padding">&nbsp;<input
					type="submit" value="검색" class="myButton" >

			</form>

		</td>
		
		<!-- 검색끝 -->

	</tr>

	<tr>
		<td>
			<!--  드롭다운 메뉴 시작 -->


			<nav class="dropdown-menu">
				<ul class="dropdown">
					<li class="drop"><a href="#">about VYON</a>
						<ul class="sub_menu">
							<li><a href="../menu/menu.do?url=aboutVyon/intro.jsp">회사소개</a></li>
							<li><a href="../menu/menu.do?url=aboutVyon/philosophy.jsp">브랜드철학</a></li>
							<li><a href="../menu/menu.do?url=aboutVyon/way.jsp">찾아오시는길</a></li>



						</ul></li>
					<li class="drop"><a href="#">Product</a>
						<ul class="sub_menu">
							<li><a href="../menu/menu.do?url=product/cleansing.jsp">cleansing</a></li>
							<li><a href="../menu/menu.do?url=product/peelNWhite.jsp">peelnwhite</a></li>
							<li><a href="../menu/menu.do?url=product/redox.jsp">redox</a></li>
							<li><a href="../menu/menu.do?url=product/cellguard.jsp">cell
									guard </a></li>
							<li><a href="../menu/menu.do?url=product/refining.jsp">refining</a></li>
							<li><a href="../menu/menu.do?url=product/sensitive.jsp">sensitive</a></li>


						</ul></li>
					<li><a
						href="../goods/user_list.do?cate=cle&sort=sale&method=asc">shop</a>
					</li>

					<li class="drop"><a href="#">Community</a>
						<ul class="sub_menu">

							<li><a href="../board/list.do?id=board_notice">공지사항</a></li>
							<li><a href="../board/list.do?id=board_faq">자주하는 질문</a></li>
							<li><a href="../board/list.do?id=board_qna">질문게시판</a></li>
							<li><a href="../board/list.do?id=board_review">리뷰게시판 </a></li>
						</ul></li>

				</ul>
			</nav> <!--  드롭다운 메뉴 끝 -->









		</td>
	</tr>

</table>




<hr class="style-one">

