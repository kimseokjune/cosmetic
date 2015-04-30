<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#member_id{
color:#4d90fe;
font-size: 17px;
font-weight: bold;
}
</style>
</head>
<body>








	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
		<img src="../member/img/sign.png"> ID찾기
		</div>
		<div class="layer_content">
		  ${member.name}&nbsp;회원님의 아이디는&nbsp;<span id="member_id">${member.id}</span>&nbsp;입니다
		  </div>
			<div class="layer_submit">
			<input type="button" value="로그인하기" class="myButton" onclick="location.href='../member/login.do';"/>
			<input type="button" value="비밀번호찾기" class="myButton" onclick="location.href='../member/serpw.do';"/>
	
		</div>
	</div>




</body>
</html>