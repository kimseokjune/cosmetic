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
		<div class="layer_title">
			<img src="../member/img/sign.png"> 회원정보수정
		</div>
		<div class="layer_content">
			<form action="../member/member3Process.do" method="post" name="form"
			onSubmit="return check()">
			<br><Br>
				<table id="search_id">
					<tr>
						<td>비밀번호</td>
						<td><input type="password"  name="pw" tabindex="1" /></td>
						<td ><input type="submit" value="확인"
							class="myButton" tabindex="2" /></td>
					</tr>
				</table>

			</form>



		</div>
	</div>

<br>
<Br>


</body>
</html>
	