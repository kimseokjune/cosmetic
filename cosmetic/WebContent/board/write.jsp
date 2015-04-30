<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String id  = (String) session.getAttribute("id");
String name = (String) session.getAttribute("name");
int grade = (Integer) session.getAttribute("grade");%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#board_write{
width:100%;
}

.board_write_th{
width:10%
}

#title{
width:100%;

}

#textarea{
width:100%;
height:100%;
}

#textarea_td{
height:500px;
}

#board_write_last_td{
text-align:right;
}

#board_file{
text-align:left;
}

#board_name {
	font-size: 20pt;
}

</style>
<title>Insert title here</title>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#write_submit").click(function(){
       $('#write').submit();
    });
});
</script>
<body>


	<form action="writeProcess.do?id=${param.id}" method="post"
		enctype="multipart/form-data" id ="write">
		<table id ="board_write">
				<tr>
			<td colspan=2><p id=board_name>${boardsetting.board_name}</p></td>
		</tr>
			<tr>
				<th class="board_write_th">제목:</th>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<th class="board_write_th">내용:</th>
				<td id="textarea_td"><textarea name="content" id="textarea" ></textarea></td>
			</tr>
			<tr>
				<th class="board_write_th">작성자:</th>
				<td>${name}(${id})<input type="hidden" name="writer_id" value="${id}"></td>
			</tr>
			<tr>
			<c:if test="${boardsetting.isfileupload <= grade }">
				<th class="board_write_th">첨부파일:</th>
				<td id="board_file"><input type="file" name="file"></td>
				</c:if>
			</tr>
			<tr>
				<td colspan=2 id="board_write_last_td">
				<a href='#' onclick='history.back(-1); return false;'>[이전페이지]</a>
				<span id="write_submit">[글쓰기]</span>
				<a href="../board/list.do?id=${param.id }&page=${param.page}&searchKey=${param.searchKey}&searchWord=${param.searchWord}">[목록]</a>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>