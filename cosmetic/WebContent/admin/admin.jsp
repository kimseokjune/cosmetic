<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vyon.co.kr</title>
<style>
#admin_wrapper {
	width: 1100px;
	margin: 0 auto;
}

#admin_left_div {
	width: 200px;
	float: left;
}

#admin_right_div {

	width: 900px;
	float: left;

}
</style>


</head>
<body>
	<div id="admin_wrapper">
		<div id="admin_left_div">
	
				<jsp:include page="../admin/admin_left.jsp"></jsp:include>
		</div>

		<div id="admin_right_div">
	
				<jsp:include page="${admin_right}"></jsp:include>
	
		</div>

	</div>
</body>
</html>