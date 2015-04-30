<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#board_write{width:90%;margin:0 auto;}

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

.board_name {font-size: 20pt; height:100px;}


</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#reply_submit").click(function(){
       $('#update').submit();
    });
});
</script>
<title>Insert title here</title>
</head>
<body>

	<form action="updateProcess.do?id=${param.id}&no=${data.no}" method="post"
		enctype="multipart/form-data" id="update">
		<table id ="board_write">
			<tr  class=board_name >
			<td colspan=3 >${boardsetting.board_name}</td>
		</tr>
	 		<tr>
				<th class="board_write_th">제목:</th>
				<td><input type="text" name="title" id="title"  value=${ data.title }></td>
			</tr>
			<tr>
				<th class="board_write_th">내용:</th>
				<td id="textarea_td"><textarea name="content" id="textarea" >${ data.content }</textarea></td>
			</tr>
			<tr>
				<th class="board_write_th">작성자:</th>
				<td>${name}(${id})<input type="hidden" name="writer_id" value="${id}"></td>
			</tr>
			<tr>
				<th class="board_write_th">첨부파일:</th>
				<td id="board_file">${ data.originalfile }<br><input type="file" name="file" ></td>
			</tr>
			<tr>
				<td colspan=2 id="board_write_last_td">
				<a href='#' onclick='history.back(-1); return false;'>[이전페이지]</a>
				<span id="reply_submit">[글수정]</span>
				<a href="../board/list.do?id=${param.id }&page=${param.page}&searchKey=${param.searchKey}&searchWord=${param.searchWord}">[목록]</a>
				</td>
				
			</tr>

		</table>
	</form>
	
</body>
</html>