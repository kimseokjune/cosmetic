<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#search_id{
width:400px;
margin: 0 auto;
}
</style>
</head>
<body>


	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title"><img src="../member/img/magnifier.png">&nbsp;ID찾기</div>
		<div class="layer_content">
			<form action="../member/seridProcess.do" method="post">
				<table id ="search_id">
					<tr>
						<td >이름</td>
						<td><input type="text" name="name" tabindex="1"/></td>
						<td rowspan="2"> <input type="submit" value="찾기" class="myButton"  tabindex="3"/></td>
					</tr>

					<tr>
						<td>가입 메일주소</td>
						<td><input type="text" name="email" tabindex="2"/></td>
					</tr>
				
				</table>

		


			<div class="layer_submit">
				<input type="button" value="비밀번호찾기" id="mem" class="myButton"
					onclick="location.href='../member/serpw.do';" /> 
			</div>
				</form>
		</div>
	</div>
	
	
</body>
</html>