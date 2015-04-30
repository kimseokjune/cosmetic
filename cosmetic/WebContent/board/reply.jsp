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



</style>
<title>Insert title here</title>
</head>
<body>

	<form action="replyProcess.do?id=${param.id}&no=${data.no}" method="post"
		enctype="multipart/form-data">
		<table id ="board_write">
			<tr>
				<th class="board_write_th">제목:</th>
				<td><input type="text" name="title" id="title" value="Re:${ data.title }"></td>
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
				<td id="board_file"><input type="file" name="file"></td>
			</tr>
			<tr>
				<td colspan=2 id="board_write_last_td"><input type="submit" value="답변"></td>
			</tr>

		</table>
	</form>
</body>
</html>