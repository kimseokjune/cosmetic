<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vyon.co.kr</title>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,200' rel='stylesheet' type='text/css'>
<style>
#main_wrapper {
	width: 1200px;
	margin: 0 auto;
	font-size: 14px;
	color : #505050;
}

#main_wrapper header{
margin-bottom: 30px;

}

#main_wrapper footer{
margin-top: 40px;
}

a { text-decoration:none } 
a:link {text-decoration: none; color: #505050;}
a:visited {text-decoration: none; color: #505050;}
a:active {text-decoration: none; color: #505050;}
a:hover{text-decoration: none;color:#3498db;}




</style>

</head>
<body>
	<div id="main_wrapper">
		<header>

			<jsp:include page="../main/header.jsp"></jsp:include>

		</header>

		<section>
			<jsp:include page="${main}"></jsp:include>
		</section>

		<footer>
			<jsp:include page="../main/footer.jsp"></jsp:include>
		</footer>
	</div>
</body>
</html>