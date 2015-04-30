<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String) session.getAttribute("id");
%>
<%
	int grade = (Integer) session.getAttribute("grade");
%>

<style>
#board_view{width:90%;margin:0 auto;} 
.board_name {font-size: 20pt; height:100px;}
.board_content{text-align: center;}
.board_writer{width:200px;text-align: center;}
.board_date{text-align:center;}
.board_reply{background: #eeeeee;width:90%;margin:0 auto;}
.board_reply_edit{width:100px;text-align: right;}
#reply_rdate{font-size: 11px}
hr.style-three {border: 0; border-bottom: 1px dashed #ccc;   background: #999;}
#update_reply{width:100%}
#reply_submit{width:7%;text-align: right;}
.board_menu{text-align: right;}
.board_field{background-color: #86BCE1; color: #fff; font-weight: bold; font-size: 13px; text-align: center; height:30px;}
</style>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$(".rcontent").show();
		$(".rcontent_edit").hide();
		$(".edit_text").click(function() {
			//	alert("test");
			var $obj = $(this).parent().parent().next().find('.rcontent_div');
			//	alert($obj.attr("class"));
			$obj.toggle();
		});
	});
</script>
</head>
<body>



	<table id="board_view">
	
		<tr  class=board_name >
			<td colspan=3 >${boardsetting.board_name}</td>
		</tr>
		
		<tr>
			<td class="board_no board_field">${ data.no }</td>
			<td class="board_title board_field" >${ data.title }</td>
			<td class="board_writer board_field" >${ data.id }</td>
		</tr>
		<tr>
			<td></td>

			<td id="file">
			<c:choose>
					<c:when test="${ data.originalfile != '0' }"> 
			파일첨부 : <a href="../board/download.do?id=${param.id}&no=${data.no}">${ data.originalfile }</a>
					</c:when>
					<c:otherwise>
			파일첨부 : 파일 없음
			</c:otherwise>
				</c:choose>
			</td>
			
			<td class="board_date">${ data.wdate }</td>
		</tr>
		<tr>
			<td class="board_content" colspan="3" >
				<c:if test="${ data.originalfile != '0' }">
				<hr>
				<img src="../board/download.do?id=${param.id}&no=${data.no}" >
			</c:if>
			<br>${ data.content }
			
			</td>
		</tr>
		<tr>
		
		
			<!--  덧글 게시판 출력 -->
			<td colspan="3" >
			
			
			
			<c:forEach var="reply" items="${reply}">
					<form
						action="../board/replyUpdateProcess.do?id=${param.id }&no=${reply.no}&pno=${reply.pno}"
						method="post">
						<table class="board_reply">
							<tr>
								<td><input type="hidden" name="reply_writer"
									value="${reply.id}">${reply.id} <span id="reply_rdate">(${reply.rdate})</span></td>
								<td class="board_reply_edit"><span class="edit_text">수정</span> , 
								<a href="../board/replyDeleteProcess.do?id=${param.id }&no=${reply.no}&pno=${reply.pno}">삭제</a>
								
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="rcontent rcontent_div">${reply.rcontent}</div>
									<hr class="style-three">
									<div class="rcontent_edit rcontent_div">
										<textarea name="replyUpdate" id="update_reply">${reply.rcontent}</textarea>
										<input type="submit" value="확인">
									</div>

								</td>
							</tr>

						</table>
					</form>
				</c:forEach> <c:if test="${boardsetting.comment_lv <= grade }">
					<form
						action="../board/replyWriteProcess.do?id=${param.id }&no=${data.no}&reply_writer=${id}"
						method="post">
						<table class="board_reply">
							<tr>
								<td><textarea name="replycontent" id="update_reply"></textarea></td>
								<td id ="reply_submit"><input type="submit" value="확인"></td>

							</tr>
						</table>
					</form>
				</c:if>
				

				
				
				</td>
								<!--  덧글 게시판 출력 완료 -->
		</tr>
		<tr>
			<td colspan=3 class="board_menu">
			<hr>
			<br> 
			<c:if test="${boardsetting.reply_lv <= grade }">
			   <a href="../board/reply.do?id=${param.id}&no=${data.no}">[답변]</a>
		    </c:if> 
		    
		    <c:if test="${data.id == id or data.id=='admin'}">
				<a href="../board/update.do?id=${param.id}&no=${data.no}">[수정]</a>
				<a href="../board/delete.do?id=${param.id}&no=${data.no}">[삭제]</a>
			</c:if> 
			
			<a href="../board/list.do?id=${param.id }&page=${param.page}&searchKey=${param.searchKey}&searchWord=${param.searchWord}">[목록]</a>
			</td>
		</tr>


	</table>
