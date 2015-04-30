<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#search{
	float:left;
	margin-left:400px;
	margin-top:4px;
}
#btn{
clear:both;
	list-style:none;
	float:left;
	margin-left:300px;
}

#search_pw{
width:400px;
margin: 0 auto;
}

</style>
</head>
<body>





	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title"><img src="../member/img/magnifier.png">&nbsp;비밀번호 찾기</div>
		<div class="layer_content">
			<form action="../member/serpwProcess.do" method="post">
				<table id ="search_pw">
				    <tr>
						<td>아이디</td>
						<td><input type="text" name="id" tabindex="1"/></td>
						<td rowspan="3"> <input type="submit" value="찾기" class="myButton"  tabindex="4"/></td>
					</tr>
				
					<tr>
						<td >이름</td>
						<td><input type="text" name="name" tabindex="2"/></td>
						
					</tr>

					<tr>
						<td>가입 메일주소</td>
						<td><input type="text" name="email" tabindex="3"/></td>
					</tr>
				
				</table>

			</form>


			<div class="layer_submit">
				<input type="button" value="아이디찾기" id="mem" class="myButton"
					onclick="location.href='../member/serid.do';" /> 
			</div>
		</div>
	</div>

















	
</body>
</html>