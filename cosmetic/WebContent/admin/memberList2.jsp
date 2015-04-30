<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style type="text/css">
.layer_content th {
	background-color: #ccc;
	border-top: 1px solid #999;
	border-bottom: 1px solid #999;
	height: 30px;
}


</style>
</head>
<body>

	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/magnifier.png">&nbsp;&lt;회원리스트&gt;

		</div>
		<div class="layer_content">
			<div id="cate" style="text-align: right;">
				<form action="../admin/adminSearchProcess.do" method="post"
					name="form">
					<select id="class" name="select">
						<option value="search2" selected>통합검색</option>
						<option value="name">이름</option>
						<option value="email">이메일</option>
						<option value="id">아이디</option>

					</select> <input type="text" name="search" /><input type="submit"
						value="검색" />


				</form>
			</div>
			<hr />
			<%-- 		<c:forEach var="bean" items="${data.list}"> --%>
			<%-- 		</c:forEach> --%>
			<div id="detail_goods">
				<table>
					<tr>
						<th width="15">아이디</th>
						<th width="65">비밀번호</th>
						<th width="55">이름</th>
						<th>이메일</th>
						<th width="65">우편번호</th>
						<th>주소</th>
						<th>상세주소</th>
						<th width="85">핸드폰번호</th>
						<th>전화번호</th>
						<th width="30">등급</th>
						<th width="30">상태</th>
					</tr>
					<c:forEach var="bean" items="${data.list}">
						<tr>
							<td class="list">${ bean.id }</td>
							<td class="list">${ bean.pw }</td>
							<td class="list">${ bean.name }</td>
							<td class="list">${ bean.email }</td>
							<td class="list">${ bean.zipcode }</td>
							<td class="list">${ bean.address }</td>
							<td class="list">${ bean.dAddress }</td>
							<td class="list">${ bean.hp }</td>
							<td class="list">${ bean.tel }</td>
							<td class="list">${ bean.grade }</td>
							<td class="list">${ bean.stateNo }</td>
						</tr>

					</c:forEach>
				</table>
			</div>






			<div id="conpage_align">
				<c:choose>
					<c:when test="${check == check2 }">
				&nbsp; <a href="admin4Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
							<a href="admin4Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
						<!-- 			이전페이지 이동 -->
						<c:if test="${data.page != 1 }">
							<a href="admin4Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
						<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
						<c:forEach var="page" begin="${data.startPage}"
							end="${data.endPage }">
							<c:choose>
								<c:when test="${data.page == page }">
									<b>[${page }]</b>
								</c:when>
								<c:otherwise>
									<a href="admin4Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${data.page != data.totalPage }">
							<a href="admin4Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
						<c:if test="${data.endPage<data.totalPage}">
							<a href="admin4Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
						<a href="admin4Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
</c:when>
					<c:otherwise>
&nbsp; <a href="admin6Process.do?page=1">[처음]</a>&nbsp;
				<c:if test="${data.startPage > 1 }">
							<a href="admin6Process.do?page=${data.page-data.pagePerGroup }">&lt;&lt;[이전페이지그룹]</a>&nbsp;
				</c:if>
						<!-- 			이전페이지 이동 -->
						<c:if test="${data.page != 1 }">
							<a href="admin6Process.do?page=${data.page-1 }">&lt;</a>&nbsp;
	 			</c:if>
						<!--			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
						<c:forEach var="page" begin="${data.startPage}"
							end="${data.endPage }">
							<c:choose>
								<c:when test="${data.page == page }">
									<b>[${page }]</b>
								</c:when>
								<c:otherwise>
									<a href="admin6Process.do?page=${page }">[${page }]</a>&nbsp;
					</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${data.page != data.totalPage }">
							<a href="admin6Process.do?page=${data.page + 1 }">&gt;</a>&nbsp; 
	 			</c:if>
						<c:if test="${data.endPage<data.totalPage}">
							<a href="admin6Process.do?page=${data.page+data.pagePerGroup }">&gt;&gt;[다음페이지그룹]</a>&nbsp;
				</c:if>
						<a href="admin6Process.do?page=${data.totalPage }">[끝]</a>&nbsp;
</c:otherwise>
				</c:choose>
						</div>
			</div>
		</div>



</body>
</html>