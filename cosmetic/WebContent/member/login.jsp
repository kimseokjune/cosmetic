<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../member/css/style.css" media="screen" type="text/css" />
<script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
</head>
<body id="login">
	
  <div class="login-card">
    <h1>로그인</h1><br>
  <form action="../member/loginProcess.do">
    <input type="text" name="id" placeholder="ID">
    <input type="password" name="pw" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="로그인">
  </form>

  <div class="login-help">
    <a href="../member/member.do">아직 회원이 아니세요?</a><br><a href="../member/serid.do">회원아이디를 잊으셨나요?</a><br><a href="../member/serpw.do">비밀번호가 생각 안나세요?</a>
  </div>
</div>
	

</body>
</html>