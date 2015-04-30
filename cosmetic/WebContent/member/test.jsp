<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    request.setCharacterEncoding("euc-kr");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

 function onclick_a(){

  alert('onclick 을 실행하셨습니다.')

 }

</script>

 






</head>
<body>
<input type="button" value="확인" onclick="onclick_a()">
</body>
</html>