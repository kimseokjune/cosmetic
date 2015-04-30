<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vyon.co.kr</title>
<style>
#mypage_wrapper {
	width: 1100px;
	margin: 0 auto;
}

#mypage_left_div {
	width: 200px;
	float: left;

}

#mypage_right_div {

	width: 900px;
	float: left;

}
</style>


</head>
<body>
	<div id="mypage_wrapper">
		<div id="mypage_left_div">
				<jsp:include page="../mypage/mypage_left.jsp"></jsp:include>
		</div>

		<div id="mypage_right_div">
				<jsp:include page="${mypage_right}"></jsp:include>
		</div>

	</div>
</body>
</html>