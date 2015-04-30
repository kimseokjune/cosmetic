<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.board_setting {
	margin: auto;
	text-align: center;
}

.board_title {
	background: #86bce1;
	color: white;
	font-weight: bold;
	height: 40px;
	text-align: center;
	width: 600px
}

.board_title_width {
	width: 290px;
	background: #86bce1;
	color: white;
	font-weight: bold;
}

input[type="text"] {
	width: 110px
}

#board_name {
	font-size: 15pt;
	height: 100px
}

.board_id {
	width: 700px
}
</style>


</head>
<body>
<c:if test="${resultUpdate==1}">
<script>
alert("수정되었습니다.");
</script>
</c:if>
	<span id="board_name">게시판 옵션 설정</span>

	<table class="board_setting">
		<tr>
			<td class="board_title board_id">게시판 아이디</td>
			<td class="board_title">게시판 이름</td>
			<td class="board_title">글쓰기 권한</td>
			<td class="board_title">덧글 쓰기 권한</td>
			<td class="board_title">답변 권한</td>
			<td class="board_title">파일업로드 권한</td>
			<td class="board_title">페이지당 게시물</td>
			<td class="board_title_width">수정</td>
		</tr>



		<c:forEach var="bean" items="${boardsetting}">
			<tr>
				<td colspan="8">

					<form action="../admin/board_setting_update.do">
						<table class="board_setting">
							<tr>
								<td><input type="text" value="${bean.board_id}" name="board_id" readonly></td>
								<td><input type="text" value="${bean.board_name}"
									name="board_name"></td>
								<td><input type="text" value="${bean.writer_lv}"
									name="writer_lv"></td>
								<td><input type="text" value="${bean.comment_lv}"
									name="comment_lv"></td>
								<td><input type="text" value="${bean.reply_lv}"
									name="reply_lv"></td>
								<td><input type="text" value="${bean.isfileupload}"
									name="isfileupload"></td>
								<td><input type="text" value="${bean.pagepercount}"
									name="pagepercount"></td>
								<td><input type="submit" value="수정" 
									class="board_setting_submit"></td>
							</tr>

						</table>
					</form>

				</td>
			</tr>
		</c:forEach>


	</table>




	<ul>

	</ul>
</body>
</html>