<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int grade = (Integer) session.getAttribute("grade");
%>

<style>
#board_list {
	width: 90%;
	margin: 0 auto;
}

#board_name {
	font-size: 20pt;
	height: 100px;
}

#board_search {
	text-align: right
}

#board_field {
	background-color: #86BCE1;
	color: #fff;
	font-weight: bold;
	font-size: 13px;
	text-align: center;
	height: 30px;
}

.board_no {
	width: 6%;
	text-align: center
}

.board_title {
	width: 67%
}

.board_writer {
	width: 10%;
	text-align: center
}

.board_date {
	width: 11%;
	text-align: center
}

.board_target {
	width: 6%;
	text-align: center
}

#board_write {
	text-align: right;
}

#board_content {
	background-color: #eeeeee
}

#board_content td {
	height: 30px;
}

#paging {
	text-align: center;
}
</style>
<title>Insert title here</title>
</head>
<body>


	<table id="board_list">
		<tr id="board_name">
			<td colspan="5">${boardsetting.board_name}</td>
		</tr>

		<tr id="board_search">
			<td colspan="5">



				<form action="list.do?id=${param.id}&page=${data.page}">
					<select name="searchKey">
						<option value="title"
							<c:if test="${data.searchKey =='title' }">
							selected ="selected"
							</c:if>>제목</option>
						<option value="content"
							<c:if test="${data.searchKey =='content' }">
					selected ="selected"
				</c:if>>내용</option>
						<option value="id"
							<c:if test="${data.searchKey =='writer' }">
					selected ="selected"
				</c:if>>작성자</option>
						<option value="title/content"
							<c:if test="${data.searchKey =='title/content' }">
					selected ="selected"
				</c:if>>제목+내용</option>
						<option value="title/content/id"
							<c:if test="${data.searchKey =='title/content/writer' }">
					selected ="selected"
				</c:if>>제목+내용+작성자</option>
					</select> <input type="hidden" name="id" value="${boardsetting.board_id }">
					<input type="text" name="searchWord" value="${data.searchWord }" />
					<button type="submit">검색</button>
				</form>


			</td>
		</tr>

		<tr id="board_field">
			<td class="board_no">글번호</td>
			<td class="board_title">제목</td>
			<td class="board_writer">작성자</td>
			<td class="board_date">작성일</td>
			<td class="board_target">조회수</td>
		</tr>

		<c:forEach var="bean" items="${data.list}">
			<tr id="board_content">
				<td class="board_no">${ bean.no }</td>
				<td><c:forEach begin="0" end="${bean.lv*5}"><%="&nbsp"%></c:forEach>
					<c:if test="${bean.lv>0}">
						<img src="img/reply.gif">
					</c:if> <a
					href="./view.do?id=${param.id}&no=${bean.no}&page=${data.page}&searchKey=${data.searchKey}&searchWord=${data.searchWord}">${ bean.title }</a></td>
				<td class="board_writer">${ bean.id }</td>
				<td class="board_date">${ bean.wdate }</td>
				<td class="board_target">${ bean.target }</td>
			</tr>

		</c:forEach>

		<tr>
			<td id="board_write" colspan=5><c:if
					test="${boardsetting.writer_lv<= grade}">
					<a href="./write.do?id=${boardsetting.board_id}"> [글쓰기]</a>
				</c:if>
		</tr>

		<tr id="paging">
			<td colspan="5">&nbsp; <a
				href="list.do?id=${boardsetting.board_id}&page=1&searchKey=${data.searchKey}&searchWord=${data.searchWord}">[처음]</a>
				<c:if test="${data.startPage > 1 }">
					<a
						href="list.do?id=${boardsetting.board_id}&page=${data.page-data.pagePerGroup }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&lt;&lt;</a>&nbsp;
			</c:if> <!-- 			이전페이지 이동 --> <c:if test="${data.page != 1 }">
					<a
						href="list.do?id=${boardsetting.board_id}&page=${data.page-1 }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&lt;</a>&nbsp;
			</c:if> <!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 --> <c:forEach var="page"
					begin="${data.startPage}" end="${data.endPage }">
					<c:choose>
						<c:when test="${data.page == page }">
							<b>[${page }]</b>
						</c:when>
						<c:otherwise>
							<a
								href="list.do?id=${boardsetting.board_id}&page=${page }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">[${page }]</a>&nbsp;
				</c:otherwise>
					</c:choose>
				</c:forEach> <!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝--> <!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
				<c:if test="${data.page != data.totalPage }">
					<a
						href="list.do?id=${boardsetting.board_id}&page=${data.page + 1 }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&gt;</a>&nbsp;
			</c:if> <c:if test="${data.endPage<data.totalPage}">
					<a
						href="list.do?id=${boardsetting.board_id}&page=${data.page+data.pagePerGroup }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&gt;&gt;</a>&nbsp;
			</c:if> <a
				href="list.do?id=${boardsetting.board_id}&page=${data.totalPage }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">[끝]</a>&nbsp;
			</td>
		</tr>
	</table>